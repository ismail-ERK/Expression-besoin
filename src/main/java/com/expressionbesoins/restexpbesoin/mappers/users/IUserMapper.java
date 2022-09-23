package com.expressionbesoins.restexpbesoin.mappers.users;

import com.expressionbesoins.restexpbesoin.dto.UserCreationDTO;
import com.expressionbesoins.restexpbesoin.dto.UserDto;
import com.expressionbesoins.restexpbesoin.dto.UserLoginDTO;
import com.expressionbesoins.restexpbesoin.model.User;

public interface IUserMapper {
    public User map(UserCreationDTO userCreationDTO);
    public User map(UserDto userDto);
    public UserCreationDTO map(User user);
    public UserDto mapDto(User user);
}
