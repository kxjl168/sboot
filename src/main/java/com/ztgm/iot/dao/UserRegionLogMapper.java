package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.UserRegionLog;

public interface UserRegionLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserRegionLog record);

    int insertSelective(UserRegionLog record);

    UserRegionLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRegionLog record);

    int updateByPrimaryKey(UserRegionLog record);
}