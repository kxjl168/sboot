package com.ztgm.iot.dao;

import java.util.List;
import java.util.Map;

import com.ztgm.iot.pojo.Permission;
import com.ztgm.iot.pojo.Role;
import com.ztgm.iot.pojo.User;

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
    List<Role> selectRoleByUser(User user);
    
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
     * @param userId
     * @return
     */
    List<Map> selectRoleByUserId(String userId);


    /**
     * 根据条件查询角色列表
     * @param conditions 条件
     * @return 角色列表
     */
    List<Map> selectRoleByConditions(Map conditions);
}