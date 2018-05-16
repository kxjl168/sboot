package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.UserSceneLog;

public interface UserSceneLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserSceneLog record);

    int insertSelective(UserSceneLog record);

    UserSceneLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserSceneLog record);

    int updateByPrimaryKey(UserSceneLog record);
}