package com.ztgm.iot.service;

import com.ztgm.iot.pojo.RegionDeviceList;

import java.util.List;

public interface RegionService {


    //区域集合
    List<RegionDeviceList> regionList();

    //区域集合（用户ID）
    List<RegionDeviceList> regionListByUserId(String userId);

    //单个区域 根据主键
    RegionDeviceList findRegionByRegionId(String regionId);

    //控制区域
    void controlRegion(String regionId, int data);
}
