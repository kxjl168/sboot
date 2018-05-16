package com.ztgm.iot.util;

import com.ztgm.iot.pojo.GroupUser;
import com.ztgm.iot.pojo.User;

public class TokenUtil {

    private static ThreadLocal<User> currentUser=new ThreadLocal<>();
    private static ThreadLocal<GroupUser> curGroupUser=new ThreadLocal<>();
    private String token;

    public static User getCurrentUser() {
        return currentUser.get();
    }

    public static void setCurrentUser(User curUser) {
        currentUser.set(curUser);
    }


    public static GroupUser getCurGroupUser() {
        return curGroupUser.get();
    }

    public static void setCurGroupUser(GroupUser groupUser) {
        curGroupUser.set(groupUser);
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }





}
