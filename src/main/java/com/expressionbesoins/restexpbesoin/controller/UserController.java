package com.expressionbesoins.restexpbesoin.controller;

/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.annotations.HasAuthority;
import com.expressionbesoins.restexpbesoin.dto.UserDto;
import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.model.UserEntity;
import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import com.expressionbesoins.restexpbesoin.service.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @HasAuthority(authority = PrivilegeEnum.ROLE_USER)
    @PostMapping
    public ResponseEntity<User> createOneUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createOneUser(userDto),new HttpHeaders(), HttpStatus.CREATED);
    }




}
