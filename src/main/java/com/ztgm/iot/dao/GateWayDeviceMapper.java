package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.GateWayDevice;

public interface GateWayDeviceMapper {
    int deleteByPrimaryKey(String id);

    int insert(GateWayDevice record);

    int insertSelective(GateWayDevice record);

    GateWayDevice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GateWayDevice record);

    int updateByPrimaryKey(GateWayDevice record);
}