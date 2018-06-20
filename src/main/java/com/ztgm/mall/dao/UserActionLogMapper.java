package com.ztgm.mall.dao;

import com.ztgm.mall.pojo.UserActionLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserActionLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserActionLog record);

    int insertSelective(UserActionLog record);

    UserActionLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserActionLog record);

    int updateByPrimaryKey(UserActionLog record);

    List getUserActionStaticsList(@Param("ipStr") String ipStr, @Param("userId") String userId, @Param("startTime") String startTime, @Param("endTime") String endTime);
}