package com.ztgm.iot.service;

import com.ztgm.iot.pojo.Device;

import java.util.List;

public interface PicturePairService {

    List<Device> findAllDevice();

    Integer submitPictureChange(String deviceId, String pictureId);
}
