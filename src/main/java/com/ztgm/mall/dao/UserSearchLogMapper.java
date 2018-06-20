package com.ztgm.mall.dao;

import com.ztgm.mall.pojo.UserSearchLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserSearchLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserSearchLog record);

    int insertSelective(UserSearchLog record);

    UserSearchLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserSearchLog record);

    int updateByPrimaryKey(UserSearchLog record);

    List<UserSearchLog> selectUserSearchLogList(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("customName")  String customName,@Param("keyWord")  String keyWord,@Param("ip")  String ip);
}