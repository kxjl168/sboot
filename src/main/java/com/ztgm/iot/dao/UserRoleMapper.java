package com.ztgm.iot.dao;

import java.util.Map;

import com.ztgm.iot.pojo.User;
import com.ztgm.iot.pojo.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    
 
    int update(Map userRole);
    
    
    /**
     * 删除用户的角色
     * @param user
     * @return
     * @author zj
     * @date 2018年5月10日
     */
    int deleteUserRole(User user);
}