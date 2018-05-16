package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.Role;
import com.ztgm.iot.pojo.RolePermission;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);
    
    /***
     * 删除角色的所有权限
     * @param role
     * @author zj
     * @date 2018年5月10日
     */
   int  deleteRolePerssion(Role role);
}