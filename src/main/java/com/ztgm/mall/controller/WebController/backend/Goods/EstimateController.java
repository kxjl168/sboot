package com.ztgm.mall.controller.WebController.backend.Goods;

import com.ztgm.mall.pojo.PageCondition;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.ztgm.mall.pojo.Estimate;
import com.ztgm.mall.service.EstimateService;
import com.ztgm.mall.service.EstimateService;
import com.ztgm.mall.util.Message;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author handsome Xiao
 */
@Controller
@RequestMapping("/manager/estimate")
public class EstimateController {


    @Autowired
    private EstimateService estimateService;


    @RequestMapping("/index")
    public String index() {
        return "/backend/goods/estimate/index";
    }


    /**
     * 按条件查询评价
     * @param estimate
     * @param pageCondition
     * @return
     */
    @RequestMapping("/showEstimate")
    @ResponseBody
    public String showEstimate(Estimate estimate, PageCondition pageCondition) {
        String resultString = estimateService.showEstimate(estimate, pageCondition);
        return resultString;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Integer deleteEstimate(Estimate estimate) {
        Integer result = estimateService.deleteEstimate(estimate);
        return result;
    }
}
