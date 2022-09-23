package com.expressionbesoins.restexpbesoin.security;

/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import com.expressionbesoins.restexpbesoin.model.Privilege;
import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.repository.RoleRepo;
import com.expressionbesoins.restexpbesoin.repository.UserRepo;
import com.expressionbesoins.restexpbesoin.service.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
@Transactional
public class CustomUserDetails implements UserDetailsService {


    @Autowired
    private UserRepo userRepository;

    @Autowired
    private IUserService service;

    @Autowired
    private MessageSource messages;

    @Autowired
    private RoleRepo roleRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            // ! no need for this here
//            return new org.springframework.security.core.userdetails.User(
//                    " ", " ", true, true, true, true,
//                    getAuthorities(Arrays.asList(
//                            roleRepository.findRoleByName(PrivilegeEnum.ROLE_USER))));
            throw new UsernameNotFoundException("No user found with username: " + email);

        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.isEnabled(), true, true,
                true, getAuthorities(user.getRoles()));


    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<PrivilegeEnum> getPrivileges(Collection<Role> roles) {

        List<PrivilegeEnum> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }

        return privileges;

    }

    private List<GrantedAuthority> getGrantedAuthorities(List<PrivilegeEnum> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (PrivilegeEnum privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege.name()));
        }
        return authorities;
    }
}
