package com.ztgm.mall.service;

import com.ztgm.mall.pojo.BroseRecord;
import com.ztgm.mall.pojo.UserSearchLog;

import java.util.List;


public interface UserSearchLogService {

    int deleteByPrimaryKey(String id);

    int insert(UserSearchLog record);

    int insertSelective(UserSearchLog record);

    UserSearchLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserSearchLog record);

    int updateByPrimaryKey(UserSearchLog record);

    List<UserSearchLog> selectUserSearchLogList(String startTime,String endTime,String customName,String keyWord,String ip);

}
