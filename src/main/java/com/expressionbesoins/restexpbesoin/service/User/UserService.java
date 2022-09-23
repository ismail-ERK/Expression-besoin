package com.expressionbesoins.restexpbesoin.service.User;
/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.dto.UserCreationDTO;
import com.expressionbesoins.restexpbesoin.dto.UserDto;
import com.expressionbesoins.restexpbesoin.dto.UserLoginDTO;
import com.expressionbesoins.restexpbesoin.exception.AlreadyUsedEmail;
import com.expressionbesoins.restexpbesoin.exception.ConfirmPassowrdIncorrect;
import com.expressionbesoins.restexpbesoin.mappers.users.IUserMapper;
import com.expressionbesoins.restexpbesoin.model.Privilege;
import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import com.expressionbesoins.restexpbesoin.repository.RoleRepo;
import com.expressionbesoins.restexpbesoin.repository.UserRepo;
import com.expressionbesoins.restexpbesoin.security.JwtUtil;
import com.expressionbesoins.restexpbesoin.service.SetRoleAndPrivilege;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService implements IUserService {

    Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepo userRepo;
    // ? I use modelMapper to make object mapping easy , by automatically determining how on object model maps to another , based on conventions
    // ? instead of hardcoding the conversion using useless bunch of lines
    ModelMapper modelMapper;

    @Qualifier("getEncoder")
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private IUserMapper mapper;
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

   // @Autowired
    //private SessionRegistry sessionRegistry;

    // ! Depending on the needs we ll define some useful methods here
    // ! aftermaths

    // ! for testing  ************************
    @Override
    public User saveRegisteredUser(User user){
        User user_ = userRepo.findUserByUsername(user.getUsername());
        if(user_ == null) {
            user_ = userRepo.save(user);
        }
        return user_;
    }

    // * new account
    @Override
    public User registerNewUserAccount(final UserCreationDTO accountDto) {
        if(!accountDto.getPassword().equals(accountDto.getRepassword())){
            throw new ConfirmPassowrdIncorrect("Confirm Password is Incorrect...");
        }
        if (emailExists(accountDto.getEmail())) {
            throw new AlreadyUsedEmail("There is an account with that email address: " + accountDto.getEmail());
        }
//        LOGGER.debug(accountDto.toString());
        final User user = mapper.map(accountDto);
        LOGGER.debug(user.toString());
        return userRepo.save(user);
    }

    @Override
    public String loginserAccount(UserLoginDTO accountDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    accountDto.getEmail(), accountDto.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("bad creditiel for username " + accountDto.getEmail());
        }
        UserDetails loadUserByUsername = loadUserByUsername(accountDto.getEmail());
        String token = jwtUtil.generateToken(loadUserByUsername);
        return token;
    }

    public User findUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }
    @Transactional
    public int deleteByuserName(String userName) {
        return userRepo.deleteUserByUsername(userName);
    }

    // ? model to dto conversion
    public UserLoginDTO convertToDTO(User user) {
        return modelMapper.map(user, UserLoginDTO.class);
    }

    // ? dto to model conversion
    public User convertToModel(UserCreationDTO userLoginDTO) {
        return modelMapper.map(userLoginDTO, User.class);
    }


    @Override
    public User getUser(String verificationToken) {
        return null;
    }

    @Override
    public User createOneUser(UserDto userDto) {
        User user = mapper.map(userDto);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepo.findRoleByName(PrivilegeEnum.ROLE_USER));
        user.setRoles(roles);
//        user.setId(id);
        return userRepo.save(user);
    }


    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {

    }
    private boolean emailExists(final String email) {
        return userRepo.findUserByEmail(email) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(email);
        if(user==null || user.getId()==null){
            throw new UsernameNotFoundException("user "+email+" not founded");
        }
        else {

            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    Collection<GrantedAuthority> authorities = new ArrayList<>();
                    user.getRoles().forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority(String.valueOf(role.getName())));
                    });
                    return authorities;
                }

                @Override
                public String getPassword() {
                    return user.getPassword();
                }

                @Override
                public String getUsername() {
                    return user.getUsername();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }
            };
        }
    }
}
