package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.UserDeviceLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public interface UserDeviceLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserDeviceLog record);

    int insertSelective(UserDeviceLog record);

    UserDeviceLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserDeviceLog record);

    int updateByPrimaryKey(UserDeviceLog record);
    List<UserDeviceLog> selectLatestUserOper(@Param("fromDate")Date fromDate,@Param("userId")String userId,@Param("data") String data);
}