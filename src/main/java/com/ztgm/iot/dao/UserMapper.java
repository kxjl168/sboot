package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    List<User> selectUserByUser(User user);

    List<User> getAdminUsers();


    List<User> selectUserByNamePwd(@Param("name") String name, @Param("password") String password);
    List<User> selectUserByToken(String token);
    List<User> selectUserByPhone(String phone);

    List<Map> selectUserByMap(Map conditions);

    List<Map> selectUserList();
}