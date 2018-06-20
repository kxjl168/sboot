package com.ztgm.mall.util;

import com.ztgm.mall.pojo.MUser;
//import com.ztgm.mall.pojo.GroupManager;
import com.ztgm.mall.pojo.Manager;

public class TokenUtil {

    private static ThreadLocal<MUser> currentUser=new ThreadLocal<>();
 //   private static ThreadLocal<GroupManager> curGroupManager=new ThreadLocal<>();
    private String token;

    public static MUser getCurrentUser() {
        return currentUser.get();
    }

    public static void setCurrentUser(MUser curUser) {
    	currentUser.set(curUser);
    }

/*
    public static GroupManager getCurGroupManager() {
        return curGroupManager.get();
    }

    public static void setCurGroupManager(GroupManager groupManager) {
        curGroupManager.set(groupManager);
    }*/


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }





}
