package com.expressionbesoins.restexpbesoin.security;


import com.expressionbesoins.restexpbesoin.Constraints.SecurityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }

    // Cette methode s'execute quand l'utilisateur tempte de s'authentifier
    // A chaque fois le client entre son username et password cette methode va s'executer
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        com.expressionbesoins.restexpbesoin.model.User user = null;
        try{
            // Puisque le données vont arriver sous format JSON
            // C'est pour cela on doit travailler avec ObjectMapper de JACKSON
            // readValue pour deserialier à partir de l'objet request
            // getInput stram pour recuperer le corps de la requete
            // ce contenue je veut le stoquer dans objet de type UserEntity.class
            user = new ObjectMapper().readValue(request.getInputStream(), com.expressionbesoins.restexpbesoin.model.User.class);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        // Tout cela c'est juste pour aller à la requete et recuperer username et password et les donneés à spring
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        return  authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        User springUser = (User) authResult.getPrincipal();
        String jwtToken = Jwts.builder()
                .setSubject(springUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis()+ SecurityConstants.EXPIRAYION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .claim("roles", springUser.getAuthorities())
                .compact();
        response.addHeader(SecurityConstants.HEAD_STRING, SecurityConstants.TOKEN_PREFIX + jwtToken);
    }
}
