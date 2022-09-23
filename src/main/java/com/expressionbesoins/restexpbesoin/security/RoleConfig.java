package com.expressionbesoins.restexpbesoin.security;

/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
public class RoleConfig {

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy =   PrivilegeEnum.ROLE_SUPER_ADMIN+">"+PrivilegeEnum.ROLE_ADMIN +"\n"+PrivilegeEnum.ROLE_ADMIN +">" + PrivilegeEnum.ROLE_USER ;
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    // !Finally, to include this role hierarchy in Spring Web Expressions ,
    // ! we add the roleHierarchy instance to the WebSecurityExpressionHandler

    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }

}
