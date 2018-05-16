package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.Device;

import java.util.List;

public interface DeviceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKeyWithBLOBs(Device record);

    int updateByPrimaryKey(Device record);

    List<Device> deviceList(List<String> gateWayIds);

    Device selectByAddress(String address,String getwayid);

    Device selectByDeviceId(String deviceid);

    Device selectBygetWayIdAndAddress(String getwayid,String address);

    List<String> selectAllUserByDevId(String devId);
}