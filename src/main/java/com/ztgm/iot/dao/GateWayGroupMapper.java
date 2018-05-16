package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.GateWayGroup;

public interface GateWayGroupMapper {
    int deleteByPrimaryKey(String id);

    int insert(GateWayGroup record);

    int insertSelective(GateWayGroup record);

    GateWayGroup selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GateWayGroup record);

    int updateByPrimaryKey(GateWayGroup record);
}