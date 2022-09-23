package com.expressionbesoins.restexpbesoin.security;

import com.expressionbesoins.restexpbesoin.Constraints.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            response.addHeader("Access-Control-Allow-Origin", "*");
            // Donner l'accees aux cettes headers -> le serveur va lire ses headers
            response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Request-With, Headers, authorization");
        // Demander aux client de lire cettes headers -> le client va lire ses headers
            response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
            if(request.getMethod().equals("OPTIONS")){
                response.setStatus(HttpServletResponse.SC_OK);
            }
            else {
                String jwtToken = request.getHeader(SecurityConstants.HEAD_STRING);
                if(jwtToken == null || !jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX)){
                    filterChain.doFilter(request, response);
                    return;
                }
                Claims claims = Jwts.parser()
                        .setSigningKey(SecurityConstants.SECRET)
                        .parseClaimsJws(jwtToken.replace(SecurityConstants.TOKEN_PREFIX,""))
                        .getBody();
                String email = claims.getSubject();
                ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                roles.forEach(role->{
                    authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                });
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request,response);
            }
    }
}
