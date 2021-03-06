package com.ztgm.iot.config;

import com.ztgm.iot.interceptor.InterfaceTokenInterceptor;
import com.ztgm.iot.interceptor.PrincipalInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    /**
     * 登录session key
     */
    public static final String SESSION_KEY = "user";

    @Bean
    public PrincipalInterceptor getSecurityInterceptor() {
        return new PrincipalInterceptor();
    }

    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        /*addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");*/

        // 拦截配置
       // addInterceptor.addPathPatterns("/**");
        
    //    addInterceptor.addPathPatterns("/permission/**");
        addInterceptor.addPathPatterns("/manager/**");
        addInterceptor.addPathPatterns("/user/**");
        
        //需要使用PrincipalFilter的spring权限管理的url
        addInterceptor.addPathPatterns("/privilege/**");
        
        
//        addInterceptor.excludePathPatterns("/interface/**");
        registry.addInterceptor(new InterfaceTokenInterceptor())
                .addPathPatterns("/interface/**")
                .addPathPatterns("/interface/*/*/*")
                .excludePathPatterns("/interface/downloadapp")
                .excludePathPatterns("/interface/downloadapp2")
                .excludePathPatterns("/interface/downloadappUrl")
                .excludePathPatterns("/interface/latestVersionInfo")
                .excludePathPatterns("/interface/user/register")
                .excludePathPatterns("/interface/user/forgetpw")
                .excludePathPatterns("/interface/user/validateCode")
                .excludePathPatterns("/interface/user/imgValidateCode")
                .excludePathPatterns("/interface/user/chkImgValCode")
                .excludePathPatterns("/interface/user/headerpic")
                .excludePathPatterns("/interface/user/login")
                .excludePathPatterns("/interface/user/wxinlogin");


    }
}