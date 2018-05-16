package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.Device;
import com.ztgm.iot.pojo.Region;

import java.util.List;

public interface RegionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Region record);

    int insertSelective(Region record);

    Region selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKeyWithBLOBs(Region record);

    int updateByPrimaryKey(Region record);

    List<Region> selectRegionByGroupId(String groupId);

    Region selectBygetWayIdAndRegionId(String getwayid, String RegionId);
}