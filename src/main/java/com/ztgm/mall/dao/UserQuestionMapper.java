package com.ztgm.mall.dao;

import java.util.List;

import com.ztgm.mall.pojo.Brand;
import com.ztgm.mall.pojo.UserQuestion;
import org.apache.ibatis.annotations.Param;

/**
 * @author handsome Xiao
 */
public interface UserQuestionMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserQuestion record);

    int insertSelective(UserQuestion record);

    UserQuestion selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserQuestion record);

    int updateByPrimaryKey(UserQuestion record);

    /**
     * 根据条件查询分类
     *
     * @param question
     * @return
     */
    List<UserQuestion> showQuestion(UserQuestion question);

    /**
     * 更新问题状态为已删除
     * @param id
     * @return
     */
    Integer deleteQuestion(String id);

    /**
     * 问题回复
     * @param question
     * @return
     */
    Integer replyQuestion(@Param("question") UserQuestion question);
}