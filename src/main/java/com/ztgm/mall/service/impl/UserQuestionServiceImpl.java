package com.ztgm.mall.service.impl;

import com.github.pagehelper.Page;
import com.google.gson.Gson;
import com.ztgm.mall.dao.UserQuestionMapper;

import com.ztgm.mall.pojo.PageCondition;
import com.ztgm.mall.pojo.UserQuestion;
import com.ztgm.mall.service.UserQuestionService;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ztgm.mall.util.PageUtil.*;

/**
 * @author handsome Xiao
 */
@Service
public class UserQuestionServiceImpl implements UserQuestionService {

    @Autowired
    private UserQuestionMapper questionMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String showQuestion(UserQuestion question, PageCondition pageCondition) {

        String resultString = "";
        String offset = pageCondition.getOffset();
        String pageSize = pageCondition.getPageSize();
        Page page = getPage(offset, pageSize);
        List<UserQuestion> questionList = questionMapper.showQuestion(question);
        try {
            resultString = packageTableData(page, questionList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    @Override
    public Integer deleteQuestion(String id) {
        return questionMapper.deleteQuestion (id);
    }

    @Override
    public Integer replyQuestion(UserQuestion question) {
        Integer result = questionMapper.replyQuestion(question);
        return result;
    }
}
