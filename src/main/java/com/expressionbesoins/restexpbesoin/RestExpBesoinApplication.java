package com.expressionbesoins.restexpbesoin;

import com.expressionbesoins.restexpbesoin.model.Privilege;
import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import com.expressionbesoins.restexpbesoin.repository.PrivilegeRepo;
import com.expressionbesoins.restexpbesoin.repository.RoleRepo;
import com.expressionbesoins.restexpbesoin.repository.UserRepo;
import com.expressionbesoins.restexpbesoin.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootApplication(scanBasePackages = { "com.expressionbesoins.restexpbesoin"})
public class RestExpBesoinApplication implements CommandLineRunner {
//    @Autowired
//    UserService userService;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//    @Autowired
//    RoleRepo roleRepo;
//    @Autowired
//    UserRepo userRepo;
//    @Autowired
//    PrivilegeRepo privilegeRepo;

    public static void main(String[] args) {
        SpringApplication.run(RestExpBesoinApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        long x = 1;
//        long y = 6;
//        long z = 60;
//        Privilege privilege = privilegeRepo.findById(x);
//
//        List<Privilege> privileges = new ArrayList<>();
//        privileges.add(privilege);
//        Role role = roleRepo.findById(y);
//        Set<Role> roles = new HashSet<>();
//        roles.add(role);

//        User user = new User();
//        user.setId(z);
//        user.setFirstName("hamid1");
//        user.setLastName("habachi1");
//        user.setUsername("hamid.habachi.123");
//        user.setEmail("hamid123@gmail.com");
//        user.setPassword(passwordEncoder.encode("123456"));
//        user.setRoles(roles);
//        userRepo.save(user);
    }

//    @Bean
//    BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
}
