package com.expressionbesoins.restexpbesoin.dto;

import com.expressionbesoins.restexpbesoin.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * @autor abdelhadi mouzafir
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationDTO {
    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String repassword;

    @NotNull
    @Email
    @NotEmpty
    private String email;


    private boolean enabled;

    @NotNull
    private String username;

    private Set<Role> roles = new HashSet<Role>();
}
