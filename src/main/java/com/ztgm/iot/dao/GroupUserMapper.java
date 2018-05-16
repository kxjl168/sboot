package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.GroupUser;
import com.ztgm.iot.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(GroupUser record);

    int insertSelective(GroupUser record);

    GroupUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GroupUser record);

    int updateByPrimaryKey(GroupUser record);

    List<User> selectMemberInGroup(String groupId);

    int deleteGroupMember(@Param("groupId") String groupId, @Param("userId") String userId);

    int deleteGroupUser(@Param("groupId") String groupId, @Param("userId") String userId);

    List<GroupUser> findGroupByUser(String userId);

    List<String> findUserListByGateWayId(String gatewayId);

    int deleteByGroupId(String groupId);

}