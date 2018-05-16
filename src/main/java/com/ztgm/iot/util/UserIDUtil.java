package com.ztgm.iot.util;

import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UserIDUtil {


    public static String getUserID(Subject subject, HttpServletResponse response) {
        if (!subject.isAuthenticated()) {
            try {
                response.sendRedirect("/login.action");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        Map principal = (Map) subject.getPrincipal();
        String userId = (String) principal.get("userId");
        return userId;
    }

}
