package com.ztgm.mall.controller.WebController.front;

import com.alibaba.fastjson.JSONObject;
import com.ztgm.mall.pojo.MUser;
import com.ztgm.mall.service.CustomerService;
import com.ztgm.mall.shiro.UsernamePasswordUsertypeToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/front")
public class FrontController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/isLogin")
    @ResponseBody
    public String index(Map<String, Object> map) {
        String rtn;
        Subject subject = SecurityUtils.getSubject();
        Map principal = (Map)subject.getPrincipal();
        principal = (Map)subject.getPrincipal();
        if(null!=principal){
            principal.put("code",1);
            rtn = JSONObject.toJSONString(principal);
        }else{
            principal = new HashMap();
            principal.put("code",0);
            rtn = JSONObject.toJSONString(principal);
        }

        return rtn;
    }

    @RequestMapping("/loginPage.html")
    public String loginPage(Map<String, Object> map) {


        return "/frontend/login/frontLogin";
    }


    @RequestMapping("/login.action")
    public String login(MUser customer, Map<String, Object> map) {


        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordUsertypeToken token = new UsernamePasswordUsertypeToken(customer.getPhone(), customer.getPass(),"front");
        // 访问安全管理器
        try {
            subject.login(token);


            //设置超时时间
            SecurityUtils.getSubject().getSession().setTimeout(1000 * 1800);
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/public/index.action";
        }
        return "redirect:/public/index.action";
    }


    @RequestMapping("/logout.action")
    public String logout(Map<String, Object> map) {
        Subject subject = SecurityUtils.getSubject();
        if(null!=subject && subject.isAuthenticated()){
            subject.logout();
        }
        return "redirect:/public/index.action";
    }

    @RequestMapping("/registPage.html")
    public String registPage(Map<String, Object> map) {


        return "/frontend/user/account/register";
    }



    @RequestMapping("/regist.action")
    public String regist(MUser customer, Map<String, Object> map) {
        Map result = customerService.registCustomer(customer);
        if(Integer.valueOf(0).equals(result.get("code"))){
            map.put("msg",result.get("msg"));
            return "/frontend/user/account/register";
        }else{
            return "redirect:/front/loginPage.html";
        }
    }

}
