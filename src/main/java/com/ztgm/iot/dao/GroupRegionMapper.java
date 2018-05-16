package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.GroupRegion;

public interface GroupRegionMapper {
    int deleteByPrimaryKey(String id);

    int insert(GroupRegion record);

    int insertSelective(GroupRegion record);

    GroupRegion selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GroupRegion record);

    int updateByPrimaryKey(GroupRegion record);

    int deleteByGroupId(String id);
}