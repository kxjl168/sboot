package com.ztgm.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Redirecter {
    @RequestMapping("/")
    public String redirect(){
        //return "redirect:/login.action";
    	  //return "redirect:/manager/admin/index.action";
    	  return "redirect:/public/index.action";
    }
}
