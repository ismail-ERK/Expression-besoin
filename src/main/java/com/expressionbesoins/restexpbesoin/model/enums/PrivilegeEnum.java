package com.expressionbesoins.restexpbesoin.model.enums;

/**
 * @autor abdelhadi mouzafir
 */

public enum PrivilegeEnum {

    // ! roles
    ROLE_USER(PrivilegeNames.ROLE_USER),
    ROLE_ADMIN(PrivilegeNames.ROLE_ADMIN),
    ROLE_SUPER_ADMIN(PrivilegeNames.ROLE_SUPER_ADMIN),

    // ! à modifier
    MANAGE_USERS(PrivilegeNames.MANAGE_USERS),
    MANAGE_REQUESTS(PrivilegeNames.MANAGE_REQUESTS),
    REGULAR_USER(PrivilegeNames.REGULAR_USER),
    VIEW_PRIVILEGE(PrivilegeNames.VIEW_PRIVILEGE),
    EDIT_PRIVILEGE(PrivilegeNames.EDIT_PRIVILEGE),


    ;

    private final String label;

    // ? I am using this class since I am working on a clean code ;
    // ? we can do it the regular way

    public class PrivilegeNames {

        public static final String MANAGE_USERS = "MANAGE_USERS";
        public static final String MANAGE_REQUESTS = "MANAGE_REQUESTS";
        public static final String REGULAR_USER = "REGULAR_USER";
        public static final String VIEW_PRIVILEGE = "REGULAR_USER";
        public static final String EDIT_PRIVILEGE = "REGULAR_USER";

        // ! roles

        public static final String ROLE_USER = "ROLE_USER";
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
        public static final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";

    }


    private PrivilegeEnum(String label) {
        this.label = label;
    }

    public String toString() {
        return this.label;
    }

}
