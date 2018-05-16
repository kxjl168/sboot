package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.GateWay;

import java.util.List;
import java.util.Map;

public interface GateWayMapper {
    int deleteByPrimaryKey(String id);

    int insert(GateWay record);

    int insertSelective(GateWay record);

    GateWay selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GateWay record);

    int updateByPrimaryKey(GateWay record);

    GateWay selectByDeviceId(String deviceId);

    GateWay selectByRegionID(String regionId);

    GateWay selectBySceneId(String SceneId);

    GateWay selectByName(String name);

    List<GateWay> selectGateWaysByGroup(List<String> groupId);

    List<GateWay> selectGateWaysByConditions(Map conditions);
}