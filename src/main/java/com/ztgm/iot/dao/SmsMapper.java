package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.Sms;

import java.util.List;

public interface SmsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Sms record);

    int insertSelective(Sms record);

    Sms selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Sms record);

    int updateByPrimaryKey(Sms record);

    List<Sms> findValidateCodeSmsByPhone(String phone);
}