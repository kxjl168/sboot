package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.DevicePicture;

import java.util.List;

public interface DevicePictureMapper {
    int deleteByPrimaryKey(String id);

    int insert(DevicePicture record);

    int insertSelective(DevicePicture record);

    DevicePicture selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DevicePicture record);

    int updateByPrimaryKey(DevicePicture record);

    List<DevicePicture> findAllPicture();

    DevicePicture selectBydeviceId(String deviceId);
}