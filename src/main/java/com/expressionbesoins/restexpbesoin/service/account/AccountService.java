package com.expressionbesoins.restexpbesoin.service.account;

import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.RoleEntity;
import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.model.UserEntity;
import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;

public interface AccountService {
    User saveUser(User user);
    Role saveRole(Role role);
    User findUserByIUserName(String username);
    User findUserByEmail(String email);
    User addRoleToUser(String username, PrivilegeEnum role);

}
