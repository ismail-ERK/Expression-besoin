package com.expressionbesoins.restexpbesoin.service;

/**
 * @author abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import com.expressionbesoins.restexpbesoin.model.Privilege;
import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.service.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

// ? I am explaining
// ? On startup of the application,
// ? we'll use an ApplicationListener on ContextRefreshedEvent to load our initial data on server start
// ? let us setup the roles & privileges
// * I'm so hyped for being able to do this myself ,  with the help of spring Docs

@Component
public class SetRoleAndPrivilege  {

    Logger LOGGER = LoggerFactory.getLogger(SetRoleAndPrivilege.class);
    // ? the ContextRefreshedEvent may be fired multiple times
    // ? depending on how many contexts we have configured in our application
    boolean alreadySetup = false;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;

        // ! here we ll define all the roles for the appropriate users

        LOGGER.info("Application is now ready!");
        List<Privilege> AuthentifiedUserPrivileges = createPrivilegesList(
                Arrays.asList(
                        PrivilegeEnum.VIEW_PRIVILEGE,
                        PrivilegeEnum.EDIT_PRIVILEGE,
                        PrivilegeEnum.REGULAR_USER)
        );

        List<Privilege> AdminPrivileges = createPrivilegesList(
                Arrays.asList(
                        PrivilegeEnum.MANAGE_REQUESTS,
                        PrivilegeEnum.MANAGE_USERS
                        )
        );
        AdminPrivileges.addAll(AuthentifiedUserPrivileges);
        // ! here I am defining the privileges for the administrator (financial service , Mayor)
        createRoleIfNotFound(PrivilegeEnum.ROLE_USER,AuthentifiedUserPrivileges);
        // ! here I ll define the privileges for the consumer (Professors , staff .. )
        createRoleIfNotFound(PrivilegeEnum.ROLE_ADMIN,AdminPrivileges);

        Role adminRole = roleService.findByName(PrivilegeEnum.ROLE_ADMIN);
        User user = new User();
        user.setFirstName("Test");
        user.setUsername("Test");
        user.setLastName("Test");
        user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("test@test.com");
        user.setRoles(new HashSet<Role>(Collections.singletonList(adminRole)));
        user.setEnabled(true);
        userService.saveRegisteredUser(user);

        alreadySetup = true;
        LOGGER.info("STILL WORKING");
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(PrivilegeEnum name) {
        return privilegeService.savePrivilege(new Privilege(name));
    }

    @Transactional
    Role createRoleIfNotFound(PrivilegeEnum name, Collection<Privilege> privileges) {
        return roleService.saveRole(new Role(name),privileges);
    }

    // ? to make concise I created this method ; more understandable

    List<Privilege> createPrivilegesList(List<PrivilegeEnum> privileges) {
        List<Privilege> privileges_ = new ArrayList<>();
        for (PrivilegeEnum pr : privileges) {
            privileges_.add(createPrivilegeIfNotFound(pr));
        }
        return privileges_;
    }

    // ? Maybe for future use
//    List<Role> createRolesList(List<RoleEnum> roles , Collection<Privilege> privileges) {
//        List<Role> roles_ = new ArrayList<>();
//        for (RoleEnum ro : roles) {
//            roles_.add(createRoleIfNotFound(ro,privileges));
//        }
//        return roles_;
//    }
}
