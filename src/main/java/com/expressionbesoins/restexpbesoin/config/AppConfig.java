package com.expressionbesoins.restexpbesoin.config;

/**
 * @autor abdelhadi mouzafir
 */

import org.modelmapper.ModelMapper;
import org.springframework.aop.scope.DefaultScopedObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// ! PLEASE PAY ATTENTION ON THESE ANNOTATIONS NEXT TIME

@Configuration
@ComponentScan({ "com.expressionbesoins.restexpbesoin" })
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

}
