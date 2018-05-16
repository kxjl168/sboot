package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.AppVersionInfo;

public interface AppVersionInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppVersionInfo record);

    int insertSelective(AppVersionInfo record);

    AppVersionInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppVersionInfo record);

    int updateByPrimaryKey(AppVersionInfo record);


    AppVersionInfo selectLatestVersion(String appType);
}