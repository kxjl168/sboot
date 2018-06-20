package com.ztgm.mall.service;

import com.ztgm.mall.pojo.PageCondition;
import org.json.JSONObject;

import com.ztgm.mall.pojo.UserQuestion;


/**
 * @author handsome Xiao
 */
public interface UserQuestionService {

	/**
	 * 查询用户问题
	 * @param question
	 * @param pageCondition
	 */
    String showQuestion(UserQuestion question, PageCondition pageCondition);

	/**
	 * 更新问题状态为删除
	 * @param id
	 * @return
	 */
	Integer deleteQuestion(String id);

	/**
	 * 
	 * @param question
	 * @return
	 */
    Integer replyQuestion(UserQuestion question);
}
