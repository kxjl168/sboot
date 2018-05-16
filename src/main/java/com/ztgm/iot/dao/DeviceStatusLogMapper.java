package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.DeviceStatusLog;

public interface DeviceStatusLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(DeviceStatusLog record);

    int insertSelective(DeviceStatusLog record);

    DeviceStatusLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DeviceStatusLog record);

    int updateByPrimaryKey(DeviceStatusLog record);
}