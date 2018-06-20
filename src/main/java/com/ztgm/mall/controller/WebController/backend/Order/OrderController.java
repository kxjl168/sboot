package com.ztgm.mall.controller.WebController.backend.Order;

import com.ztgm.mall.pojo.Order;
import com.ztgm.mall.pojo.PageCondition;
import com.ztgm.mall.service.OrderService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Map;
import java.util.Random;


/**
 * @author handsome Xiao
 */
@Controller
@RequestMapping("/manager/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @RequestMapping("/index")
    public String index() {
        return "/backend/order/index";
    }

    @RequestMapping("/showOrders")
    @ResponseBody
    public String showOrders(Order order, PageCondition pageCondition) {
        String resultString  = orderService.showOrders(order, pageCondition);
        return resultString;
    }
}
