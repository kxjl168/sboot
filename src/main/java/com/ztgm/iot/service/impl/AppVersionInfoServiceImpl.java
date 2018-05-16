package com.ztgm.iot.service.impl;

import com.ztgm.iot.dao.AppVersionInfoMapper;
import com.ztgm.iot.pojo.AppVersionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class AppVersionInfoServiceImpl {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppVersionInfoMapper appVersionInfoDao ;


    public AppVersionInfo selectLatestVersion(String appType) {
        AppVersionInfo v=null;
        try {
            v = appVersionInfoDao.selectLatestVersion(appType);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询出错");
        }
        return v;
    }

    public int saveAppVersionInfo(AppVersionInfo v) {

        try {
            int result = appVersionInfoDao.insertSelective(v);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加预警失败");
            return 0;
        }

    }


}
