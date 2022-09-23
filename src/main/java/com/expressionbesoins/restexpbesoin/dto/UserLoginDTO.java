package com.expressionbesoins.restexpbesoin.dto;

/**
 * @autor abdelhadi mouzafir
 */


import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Data
public class UserLoginDTO {

    @NotNull
    @Email
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    public UserLoginDTO() {

    }
// ? better to do it in frontend since this is a restful app
//    @AssertTrue
//    private Boolean terms;

}
