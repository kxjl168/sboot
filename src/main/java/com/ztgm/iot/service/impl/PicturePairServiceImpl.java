package com.ztgm.iot.service.impl;

import com.ztgm.iot.dao.PicturePairMapper;
import com.ztgm.iot.pojo.Device;
import com.ztgm.iot.service.PicturePairService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicturePairServiceImpl implements PicturePairService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PicturePairMapper picturePairMapper;

    @Override
    public List<Device> findAllDevice() {
        return picturePairMapper.findAllDevice();
    }

    @Override
    public Integer submitPictureChange(String deviceId, String pictureId) {
        Integer result;
        try {
            result = picturePairMapper.submitPictureChange (deviceId, pictureId);
        } catch (Exception e) {
            e.printStackTrace();
            result = -1;
            logger.error("图片更新失败！");
        }
        return result;
    }
}
