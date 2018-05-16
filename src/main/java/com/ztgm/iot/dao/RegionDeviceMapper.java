package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.RegionDevice;
import com.ztgm.iot.pojo.RegionDeviceList;

import java.util.List;

public interface RegionDeviceMapper {
    int deleteByPrimaryKey(String id);

    int insert(RegionDevice record);

    int insertSelective(RegionDevice record);

    RegionDevice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RegionDevice record);

    int updateByPrimaryKey(RegionDevice record);

    //所有区域及对应的设备及状态
    List<RegionDeviceList> regionList();

    //所有区域及对应的设备及状态（用户ID）
    List<RegionDeviceList> regionListBygroupId(List<String> groupId);

    //主键查询区域及对应的设备及状态
    RegionDeviceList findRegionByRegionId(String regionId);

}