package com.ztgm.mall.dao;

import java.util.List;
import java.util.Map;

import com.ztgm.mall.pojo.Permission;
import com.ztgm.mall.pojo.Role;

public interface PermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    /**
     * 条件查询
     * @param permission
     * @return
     * @author zj
     * @date 2018年5月9日
     */
    List<Permission> selectPermissionList(Permission permission);
    
    public List<Permission> getRolePermissionList(Role role);
    /**
     * 根据角色id查询角色对应权限列表
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(String roleId);
    
    /**
     * 根据角色id查询角色对应权限列表
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByManagerId(String managerId);


    /**
     * 查询系统现有所有功能列表
     * @return
     */
    List<Map> selectPermissions();
}