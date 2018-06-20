package com.ztgm.mall.controller.WebController.backend.Goods;

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
 * 投诉管理
 * @author zj
 * @date 2018年5月25日
 *
 */
@Controller
@RequestMapping("/manager/complaint")
public class ComplaintController {


    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
        map.put("name", "admin" );
        map.put("admin", "admin2" );

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getPrincipal();

        return "/backend/normal/index";
    }

    @RequestMapping("/search")
    public String search(Map<String, Object> map) {
        map.put("name", "admin" );
        map.put("admin", "admin2" );

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getPrincipal();

        return "/frontend/public/search";
    }



}
