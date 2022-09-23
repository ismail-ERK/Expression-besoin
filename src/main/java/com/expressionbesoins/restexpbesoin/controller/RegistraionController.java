package com.expressionbesoins.restexpbesoin.controller;

import com.expressionbesoins.restexpbesoin.dto.UserCreationDTO;
import com.expressionbesoins.restexpbesoin.dto.UserLoginDTO;
import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.service.User.IUserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;;



@RestController
public class RegistraionController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private ModelMapper mapper;
    @Autowired
    private IUserService userService;

    @Autowired
    private MessageSource messages;
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private Environment env;



    // ? Registration
    @PostMapping("/user/registration")
    public ResponseEntity<UserCreationDTO> registerUserAccount(@RequestBody UserCreationDTO accountDto) {
        LOGGER.info("Registering user account with information: {}", accountDto);
        try {
           User user = userService.registerNewUserAccount(accountDto);
           UserCreationDTO userCreationDTO = mapper.map(user, UserCreationDTO.class);

            return new ResponseEntity<>(userCreationDTO,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/user/signin")
    public ResponseEntity<String> loginUserAccount(@RequestBody UserLoginDTO accountDto) {
        LOGGER.info("Registering user account with information: {}", accountDto);
        try {
           String token = userService.loginserAccount(accountDto);

            return new ResponseEntity<>(token,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }  // ? Registration


    @GetMapping("/test")
    public String hello()
    {
        return "<h1>Expression de besoins</h1>";
    }

}
