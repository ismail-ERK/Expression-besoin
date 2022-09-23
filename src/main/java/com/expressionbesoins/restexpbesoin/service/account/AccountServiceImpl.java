package com.expressionbesoins.restexpbesoin.service.account;


import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import com.expressionbesoins.restexpbesoin.repository.RoleRepo;
import com.expressionbesoins.restexpbesoin.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    UserRepo userRepository;
    @Autowired
    RoleRepo roleRepository;
    @Autowired
    PasswordEncoder bCryptPasswordEncoder;


    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }


    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public User findUserByIUserName(String username) {
        User user = userRepository.findUserByUsername(username);
//        if(userEntity==null) throw new UserNotFoundException("User not found !!");
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
//        if(userEntity==null) throw new UserNotFoundException("User not found !!");
        return user;
    }

    @Override
    public User addRoleToUser(String username, PrivilegeEnum role) {
        Role roleEntity = roleRepository.findRoleByName(role);
        if (roleEntity == null) {
            Role roleEntity1 = new Role();
            roleEntity1.setName(role);
            roleRepository.save(roleEntity1);
        }
        ;
        User userEntity = this.findUserByIUserName(username);
        Set<Role> roles = userEntity.getRoles();
        roles.add(roleEntity);

        userEntity.setRoles(roles);
        return userRepository.save(userEntity);

    }


}
