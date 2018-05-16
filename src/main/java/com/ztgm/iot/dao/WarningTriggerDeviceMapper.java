package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.WarningTriggerDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WarningTriggerDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WarningTriggerDevice record);

    int insertSelective(WarningTriggerDevice record);

    WarningTriggerDevice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WarningTriggerDevice record);

    int updateByPrimaryKey(WarningTriggerDevice record);

    List<WarningTriggerDevice> selectTriggerDevices(@Param("warningId")int warningId);
    int deleteByWarningId(int warningId);
 }