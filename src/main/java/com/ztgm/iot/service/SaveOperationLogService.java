package com.ztgm.iot.service;

public interface SaveOperationLogService {

    //设备操作日志
    void deviceOperationLog(String deviceId,String userId,String information);

    //区域操作日志
    void regionOperationLog(String regionId,String userId);

    //场景操作日志
    void sceneOperationLog(String sceneId,String userId);
}
