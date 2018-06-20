package com.ztgm.mall.interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class PrincipalInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated()){
            Map principal = (Map) currentUser.getPrincipal();
            request.setAttribute("principal", principal);
        }


        return true;
    }
}