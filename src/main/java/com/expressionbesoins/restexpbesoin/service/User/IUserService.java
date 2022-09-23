package com.expressionbesoins.restexpbesoin.service.User;

import com.expressionbesoins.restexpbesoin.dto.UserCreationDTO;
import com.expressionbesoins.restexpbesoin.dto.UserDto;
import com.expressionbesoins.restexpbesoin.dto.UserLoginDTO;
import com.expressionbesoins.restexpbesoin.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    User registerNewUserAccount(UserCreationDTO accountDto);
    String loginserAccount(UserLoginDTO accountDto);

    User getUser(String verificationToken);

    User createOneUser(UserDto userDto);

    User saveRegisteredUser(User user);

    void deleteUser(User user);

    void createVerificationTokenForUser(User user, String token);
}
