package org.sofl.soptorshi.config;

public class UserRoles {
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";

    private UserRoles(){

    }

    public static String[] getAllRoles(){
        return new String[]{USER,ADMIN};
    }
}
