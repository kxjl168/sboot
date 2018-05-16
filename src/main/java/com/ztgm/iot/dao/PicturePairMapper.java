package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PicturePairMapper {

    /**
     * 查询所有设备及图片
     * @return
     */
    List<Device> findAllDevice();

    /**
     * 提交设备图片修改
     * @param deviceId
     * @param pictureId
     * @return
     */
    Integer submitPictureChange(@Param("deviceId") String deviceId, @Param("pictureId") String pictureId);
}
