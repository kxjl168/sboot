package com.ztgm.mall.dao;

import java.util.List;
import java.util.Map;

import com.ztgm.mall.pojo.Role;
import com.ztgm.mall.pojo.Manager;

public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    
    /**
     * 条件查询
     * @param permission
     * @return
     * @author zj
     * @date 2018年5月9日
     */
    List<Role> selectRoleByManager(Manager Manager);
    
    /**
     * 条件查询
     * @param permission
     * @return
     * @author zj
     * @date 2018年5月9日
     */
    List<Role> selectRoleList(Role record);
    
    
    /**
     * 查询用户所属角色
     * @param ManagerId
     * @return
     */
    List<Map> selectRoleByManagerId(String ManagerId);


    /**
     * 根据条件查询角色列表
     * @param conditions 条件
     * @return 角色列表
     */
    List<Map> selectRoleByConditions(Map conditions);
}