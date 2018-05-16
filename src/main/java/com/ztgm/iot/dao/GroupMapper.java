package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.Device;
import com.ztgm.iot.pojo.Group;
import com.ztgm.iot.pojo.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMapper {
    int deleteByPrimaryKey(String id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

    List<Group> selectGroupIdByGatewayId(String gatewayId);

    List<Group> selectBUserId(String uid);
    List<Group> selectBUserId2(@Param("uid") String uid,@Param("groupName") String groupName);

    List<Region> selectUserAllRegions(String userId);
    List<Device> selectUserAllDevices(String userId);
    List<Region> selectUserAllRegionsExGroup(@Param("userId") String userId, @Param("groupId") String groupId);
    List<Device> selectUserAllDevicesExGroup(@Param("userId") String userId, @Param("groupId") String groupId);

    List<Region> selectGroupRegions(String groupId);
    List<Device> selectGroupDevices(String groupId);
    List selectUserGatewayGroup(String userId);

}