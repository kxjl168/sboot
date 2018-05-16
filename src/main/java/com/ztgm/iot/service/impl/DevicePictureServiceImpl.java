package com.ztgm.iot.service.impl;

import com.ztgm.iot.dao.DevicePictureMapper;
import com.ztgm.iot.pojo.DevicePicture;
import com.ztgm.iot.service.DevicePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevicePictureServiceImpl implements DevicePictureService {

    @Autowired
    private DevicePictureMapper devicePictureMapper;

    @Override
    public List<DevicePicture> findAllPicture() {
        return devicePictureMapper.findAllPicture ();
    }

    @Override
    public DevicePicture selectBydeviceId(String deviceId) {
        return devicePictureMapper.selectBydeviceId(deviceId);
    }
}
