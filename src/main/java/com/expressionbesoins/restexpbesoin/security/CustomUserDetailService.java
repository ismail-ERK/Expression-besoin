package com.expressionbesoins.restexpbesoin.security;


import com.expressionbesoins.restexpbesoin.model.UserEntity;
import com.expressionbesoins.restexpbesoin.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.expressionbesoins.restexpbesoin.model.User userEntity = accountService.findUserByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException("User not found !!!");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        userEntity.getRoles().forEach(role -> {
            //Il y a un probleme
            authorities.add(new SimpleGrantedAuthority(String.valueOf(role.getName())));
        });
//        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}
