package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.SmsConfig;

public interface SmsConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(SmsConfig record);

    int insertSelective(SmsConfig record);

    SmsConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SmsConfig record);

    int updateByPrimaryKey(SmsConfig record);
}