package com.expressionbesoins.restexpbesoin.mappers.users;

import com.expressionbesoins.restexpbesoin.dto.UserCreationDTO;
import com.expressionbesoins.restexpbesoin.dto.UserDto;
import com.expressionbesoins.restexpbesoin.dto.UserLoginDTO;
import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import com.expressionbesoins.restexpbesoin.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class IUserMapperImpl implements IUserMapper{
    @Autowired
    RoleRepo roleRepo;
    @Qualifier("getEncoder")
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User map(UserCreationDTO userCreationDTO) {
        User user = new User();
        user.setUsername(userCreationDTO.getUsername());
        user.setFirstName(userCreationDTO.getFirstName());
        user.setLastName(userCreationDTO.getLastName());
        user.setEmail(userCreationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userCreationDTO.getPassword()));
        Set<Role> roles = new HashSet<Role>();
        Role role = roleRepo.findRoleByName(PrivilegeEnum.ROLE_USER);
        roles.add(role);
        user.setRoles(roles);
        return user;
    }

    @Override
    public User map(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getFirstName()+"."+user.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return user;
    }


    @Override
    public UserCreationDTO map(User user) {
        UserCreationDTO userCreationDTO = new UserCreationDTO();
        userCreationDTO.setUsername(user.getUsername());
        userCreationDTO.setFirstName(user.getFirstName());
        userCreationDTO.setLastName(user.getLastName());
        userCreationDTO.setEmail(user.getEmail());
        userCreationDTO.setPassword(user.getPassword());
        Set<Role> roles = user.getRoles();
        userCreationDTO.setRoles(roles);
        return userCreationDTO;
    }

    @Override
    public UserDto mapDto(User user) {
        UserDto  userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
