package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.GroupDevice;

import java.util.List;

public interface GroupDeviceMapper {
    int deleteByPrimaryKey(String id);

    int insert(GroupDevice record);

    int insertSelective(GroupDevice record);

    GroupDevice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GroupDevice record);

    int updateByPrimaryKey(GroupDevice record);

    List<GroupDevice> selectDeviceGroup(String devId);


    int deleteByGroupId(String groupId);
}