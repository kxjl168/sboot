package com.ztgm.iot.service;

import com.ztgm.iot.pojo.DevicePicture;

import java.util.List;

public interface DevicePictureService {


    List<DevicePicture> findAllPicture();

    DevicePicture selectBydeviceId(String deviceId);
}
