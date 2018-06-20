package com.ztgm.mall.dao;

import com.ztgm.mall.pojo.Manager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ManagerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKeyWithBLOBs(Manager record);

    int updateByPrimaryKey(Manager record);

    List<Manager> selectManagerByManager(Manager Manager);

    List<Manager> getAdminManagers();


    List<Manager> selectManagerByNamePwd(@Param("name") String name, @Param("password") String password);
    List<Manager> selectManagerByToken(String token);
    List<Manager> selectManagerByPhone(String phone);

    List<Map> selectManagerByMap(Map conditions);

    List<Map> selectManagerList();
}