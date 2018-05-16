package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.UserConfig;
import com.ztgm.iot.pojo.UserConfigWithBLOBs;

public interface UserConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserConfigWithBLOBs record);

    int insertSelective(UserConfigWithBLOBs record);

    UserConfigWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserConfigWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UserConfigWithBLOBs record);

    int updateByPrimaryKey(UserConfig record);
}