package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.Warning;

import java.util.List;

public interface WarningMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Warning record);

    int insertSelective(Warning record);

    Warning selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Warning record);

    int updateByPrimaryKey(Warning record);


    List<Warning> selectWarnings();


    List<Warning> warningListBygroupId(List<String> groupId);


    Warning selectByDeviceId(String deviceId);
}