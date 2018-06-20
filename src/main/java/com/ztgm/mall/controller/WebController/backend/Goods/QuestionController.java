package com.ztgm.mall.controller.WebController.backend.Goods;

import com.ztgm.mall.pojo.PageCondition;
import com.ztgm.mall.util.PageUtil;
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
import com.ztgm.mall.pojo.UserQuestion;
import com.ztgm.mall.service.UserQuestionService;
import com.ztgm.mall.service.UserQuestionService;
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
@RequestMapping("/manager/question")
public class QuestionController {


    @Autowired
    private UserQuestionService userQuestionService;


    /**
     * 显示页面
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "/backend/goods/userQuestion/index";
    }

    /**
     * 显示表格数据
     *
     * @param userQuestion
     * @param pageCondition
     * @return
     */
    @RequestMapping("/showQuestion")
    @ResponseBody
    public String showQuestion(UserQuestion userQuestion, PageCondition pageCondition) {
        String result = userQuestionService.showQuestion(userQuestion, pageCondition);
        return result;
    }

    /**
     * 删除问题
     *
     * @param request
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Integer deleteQuestion(HttpServletRequest request) {
        String id = request.getParameter("id");
        int result = userQuestionService.deleteQuestion(id);
        return result;
    }

    @RequestMapping("/replyQuestion")
    @ResponseBody
    public Integer replyQuestion(UserQuestion question) {
        Integer result = userQuestionService.replyQuestion(question);
        return result;
    }
}
