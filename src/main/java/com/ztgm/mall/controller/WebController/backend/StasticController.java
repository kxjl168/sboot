package com.ztgm.mall.controller.WebController.backend;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Map;
import java.util.Random;

/**
 * 统计
 * @author zj
 * @date 2018年5月25日
 *
 */
@Controller
@RequestMapping("/manager/stastic")
public class StasticController {


    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
        map.put("name", "admin" );
        map.put("admin", "admin2" );

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getPrincipal();

        return "/backend/normal/index";
    }

    @RequestMapping("/index2")
    public String index2(Map<String, Object> map) {
        map.put("name", "admin" );
        map.put("admin", "admin2" );

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getPrincipal();

        return "/backend/normal/index";
    }

    @RequestMapping("/index3")
    public String index3(Map<String, Object> map) {
        map.put("name", "admin" );
        map.put("admin", "admin2" );

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getPrincipal();

        return "/backend/normal/index";
    }

    
    @RequestMapping("/index4")
    public String index4(Map<String, Object> map) {
        map.put("name", "admin" );
        map.put("admin", "admin2" );

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getPrincipal();

        return "/backend/normal/index";
    }

    
    @RequestMapping("/index5")
    public String index5(Map<String, Object> map) {
        map.put("name", "admin" );
        map.put("admin", "admin2" );

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getPrincipal();

        return "/backend/normal/index";
    }

    
    @RequestMapping("/index6")
    public String index6(Map<String, Object> map) {
        map.put("name", "admin" );
        map.put("admin", "admin2" );

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getPrincipal();

        return "/backend/normal/index";
    }

    
    @RequestMapping("/index7")
    public String index7(Map<String, Object> map) {
        map.put("name", "admin" );
        map.put("admin", "admin2" );

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getPrincipal();

        return "/backend/normal/index";
    }

    
    @RequestMapping("/index8")
    public String index8(Map<String, Object> map) {
        map.put("name", "admin" );
        map.put("admin", "admin2" );

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getPrincipal();

        return "/backend/normal/index";
    }



}
