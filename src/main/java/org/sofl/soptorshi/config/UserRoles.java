package org.sofl.soptorshi.config;

public class UserRoles {
    public static final String HR_ADMIN_EXECUTIVE = "ROLE_HR_ADMIN_EXECUTIVE";
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String JR_EXECUTIVE ="ROLE_JR_EXECUTIVE";
    public static final String IT_OPERATOR ="ROLE_IT_OPERATOR";
    public static final String PRODUCTION_MANAGER ="ROLE_PRODUCTION_MANAGER";
    public static final String SHIFT_IN_CHARGE ="ROLE_SHIFT_IN_CHARGE";
    public static final String SUPERVISOR ="ROLE_SUPERVISOR";
    public static final String PRODUCTION_WORKER ="PRODUCTION_WORKER";
    public static final String PRODUCTION_HELPER ="PRODUCTION_HELPER";
    public static final String QA_MANAGER ="ROLE_QA_MANAGER";
    public static final String MICROBIOLOGIES ="ROLE_MICROBIOLOGIST";
    public static final String QA_EXECUTIVE ="ROLE_QA_EXECUTIVE";
    public static final String QA_HELPER ="ROLE_QA_HELPER";
    public static final String QA_ROLE_PLANT_ENGINEER ="ROLE_QA_ROLE_PLANT_ENGINEER";
    public static final String FOREMAN ="ROLE_FOREMAN";
    public static final String ELECTRICIAN ="ROLE_ELECTRICIAN";
    public static final String ROLE_OPERATOR ="ROLE_M_M_HELPER";
    public static final String HR_IT_OPERATOR ="ROLE_HR_IT_OPERATOR";



    private UserRoles(){

    }

    public static String[] getAllRoles(){
        return new String[]{HR_ADMIN_EXECUTIVE,ADMIN, JR_EXECUTIVE, IT_OPERATOR, PRODUCTION_MANAGER, SHIFT_IN_CHARGE, SUPERVISOR, PRODUCTION_WORKER, PRODUCTION_HELPER, QA_MANAGER,
        MICROBIOLOGIES, QA_EXECUTIVE, QA_HELPER, QA_ROLE_PLANT_ENGINEER, FOREMAN, ELECTRICIAN, ROLE_OPERATOR, HR_IT_OPERATOR};
    }
}
