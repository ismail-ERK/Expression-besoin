package com.expressionbesoins.restexpbesoin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class Securityconfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;
    @Qualifier("getEncoder")
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http

               .authorizeRequests()
               .antMatchers("/login/**")
               .permitAll()
               .antMatchers(HttpMethod.POST, "/users/**")
               .permitAll()
               .antMatchers(HttpMethod.POST, "/user/registration/**")
               .permitAll()

               .antMatchers(HttpMethod.POST, "/user/signin/**")
               .permitAll()

               .antMatchers(HttpMethod.POST, "/tasks/**")
               .hasAuthority("ADMIN")
               .antMatchers(HttpMethod.PUT).hasAnyAuthority("USER")
               .anyRequest()
               .permitAll()
               .and()
               .csrf().disable()
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               // Filters
               // 1 - Filter Authentication
               .addFilter(new JWTAuthenticationFilter(authenticationManager()))
               // 2 - Filter Authorization
               .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService)
               .passwordEncoder(passwordEncoder);
    }
}
