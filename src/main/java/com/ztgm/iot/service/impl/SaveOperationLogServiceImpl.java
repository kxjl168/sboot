package com.ztgm.iot.service.impl;

import com.ztgm.iot.dao.UserDeviceLogMapper;
import com.ztgm.iot.dao.UserRegionLogMapper;
import com.ztgm.iot.dao.UserSceneLogMapper;
import com.ztgm.iot.pojo.UserDeviceLog;
import com.ztgm.iot.pojo.UserRegionLog;
import com.ztgm.iot.pojo.UserSceneLog;
import com.ztgm.iot.service.SaveOperationLogService;
import com.ztgm.iot.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class SaveOperationLogServiceImpl implements SaveOperationLogService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDeviceLogMapper userDeviceLogMapper;
    @Autowired
    private UserRegionLogMapper userRegionLogMapper;
    @Autowired
    private UserSceneLogMapper userSceneLogMapper;

    @Override
    public void deviceOperationLog(String deviceId, String userId, String information) {
        try {
            UserDeviceLog userDeviceLog = new UserDeviceLog();
            userDeviceLog.setId(UUIDUtil.getUUID());
            userDeviceLog.setUserId(userId);
            userDeviceLog.setDeviceId(deviceId);
            userDeviceLog.setAction(information);
            userDeviceLogMapper.insertSelective(userDeviceLog);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("存储设备操作日志出错");
        }
    }
    //用户最近操作
    public List<UserDeviceLog>  userDeviceLasteOper(String userId,Date fromDate,String data){
        return  userDeviceLogMapper.selectLatestUserOper(fromDate,userId,data);

    }

    @Override
    public void regionOperationLog(String regionId, String userId) {
        try {
            UserRegionLog userRegionLog = new UserRegionLog();
            userRegionLog.setId(UUIDUtil.getUUID());
            userRegionLog.setUserId(userId);
            userRegionLog.setRegionId(regionId);
            userRegionLogMapper.insertSelective(userRegionLog);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("存储区域操作日志出错");
        }
    }

    @Override
    public void sceneOperationLog(String sceneId, String userId) {
        try {
            UserSceneLog userSceneLog = new UserSceneLog();
            userSceneLog.setId(UUIDUtil.getUUID());
            userSceneLog.setUserId(userId);
            userSceneLog.setGroupId(sceneId);
            userSceneLog.setCreateDate(new Date());
            userSceneLogMapper.insert(userSceneLog);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("存储场景操作日志出错");
        }
    }


}
