package com.ztgm.mall.dao;

import com.ztgm.mall.pojo.UserOnlineLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserOnlineLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserOnlineLog record);

    int insertSelective(UserOnlineLog record);

    UserOnlineLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserOnlineLog record);

    int updateByPrimaryKey(UserOnlineLog record);

    List getOnlineCustomeStaticsList(@Param("ipStr") String ipStr, @Param("userId") String userId);
}