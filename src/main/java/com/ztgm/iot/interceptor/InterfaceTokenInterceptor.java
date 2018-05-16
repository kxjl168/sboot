package com.ztgm.iot.interceptor;

import com.ztgm.iot.pojo.User;
import com.ztgm.iot.service.impl.GroupUserServiceImpl;
import com.ztgm.iot.service.impl.UserServiceImpl;
import com.ztgm.iot.util.TokenUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterfaceTokenInterceptor implements HandlerInterceptor {

    private static Logger logger = LogManager.getLogger(InterfaceTokenInterceptor.class);
    /* redis  实现
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext( request.getServletContext());
        StringRedisTemplate rt=(StringRedisTemplate)ctx.getBean("stringRedisTemplate");
        rt.opsForValue().set("hello",String.valueOf(System.currentTimeMillis()));

        RedisTemplate rtp=(RedisTemplate)ctx.getBean("redisTemplate");
//        UserTest u=new UserTest("zhang",100);
//        rtp.opsForValue().set("u_zhang",u);

        Object obj=rtp.opsForValue().get("u_zhang");
        UserTest u=(UserTest)rtp.opsForValue().get("u_zhang");
        System.out.println(u.getName()+" __ "+u.getAge());

        String token=request.getParameter("Token");
        if(token==null){
            response.getWriter().append("No login Token");
            return false;
        }
        String userinfo=rt.opsForValue().get(token);
        if(userinfo==null) {
            response.getWriter().append("Login expired");
            return false;
        }
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MINUTE,1); //30分钟后 失效

        System.out.println(cal.get(Calendar.MINUTE));
        rt.opsForValue().set(token,userinfo,1*60, TimeUnit.SECONDS);
        //rt.expireAt(token,cal.getTime());
        return true;
    }
    */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext( request.getServletContext());
        UserServiceImpl userService=(UserServiceImpl)ctx.getBean("userService");
        GroupUserServiceImpl groupUserService=(GroupUserServiceImpl)ctx.getBean("groupUserServiceImpl");

        String token=request.getParameter("Token");
        String token2=request.getHeader("Token");
        token= token!=null?token:token2;

        if(token==null){
            //对获取图片放行
            if(request.getRequestURI().lastIndexOf("filehold")!=-1){
                return true;
            }
            response.getWriter().append("{\"code\":\"00\",\"message\":\"no login\",\"data\":{}}");
            return false;
        }


        logger.info("Token："+token);
        User user=userService.getUserByToken(token);
        if(user==null){
            response.getWriter().append("{\"code\":\"01\",\"message\":\"no this user\",\"data\":{}}");
            return false;
        }
        TokenUtil.setCurrentUser(user);
        TokenUtil.setCurGroupUser(groupUserService.findGroupByUser(user.getId()));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
