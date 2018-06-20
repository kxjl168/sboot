package com.ztgm.mall.dao;

import java.util.Map;

import com.ztgm.mall.pojo.Manager;
import com.ztgm.mall.pojo.ManagerRole;

public interface ManagerRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(ManagerRole record);

    int insertSelective(ManagerRole record);

    ManagerRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ManagerRole record);

    int updateByPrimaryKey(ManagerRole record);
    
 
    int update(Map ManagerRole);
    
    
    /**
     * 删除用户的角色
     * @param Manager
     * @return
     * @author zj
     * @date 2018年5月10日
     */
    int deleteManagerRole(Manager Manager);
}