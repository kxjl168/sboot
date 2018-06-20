package com.ztgm.mall.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UsernamePasswordUsertypeToken extends UsernamePasswordToken {
    private static final long serialVersionUID = 11162593121979791L;

    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUsertype(String userType) {
        this.userType = userType;
    }

    public UsernamePasswordUsertypeToken() {
        super();
    }

    public UsernamePasswordUsertypeToken(String loginName, String password, String userType) {
        super(loginName,password);
        this.userType = userType;
    }

    public UsernamePasswordUsertypeToken(String loginName, String password,boolean rememberMe,String host, String userType) {

        super(loginName, password,rememberMe,host);

        this.userType = userType;

    }

}