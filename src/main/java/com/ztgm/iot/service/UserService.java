package com.ztgm.iot.service;

import com.ztgm.iot.pojo.User;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface UserService {

	
	
	 int deleteByPrimaryKey(String id);

	    int insert(User record);

	    int insertSelective(User record);

	    User selectByPrimaryKey(String id);

	    int updateByPrimaryKeySelective(User record);

	    int updateByPrimaryKeyWithBLOBs(User record);

	    int updateByPrimaryKey(User record);


	
    /**
     * 通过user bean对象查询user 列表
     * @param user
     * @return
     */
    List<User> selectUserByUser(User user);

    /**
     * 通过user bean对象查询user 列表
     * @param user
     * @return
     */
    Map initSuperAdmin(User user);


    /**
     *获取系统中现有的管理员用户列表
     * @return 现有管理员用户列表
     */
    List<User> getAdminUsers();

    /**
     * 新增用户
     */
    JSONObject saveUser(User user);

    /**
     * 新增用户
     */
    JSONObject saveUser(User user,String roleids);

    /**
     * 更新用户信息
     */
    JSONObject updateUser(User user,String roleids);


    /**
     * 通过user bean对象查询user 列表
     * @param conditions
     * @return
     */
    List<Map> selectUserByUser(Map conditions);


    List<Map> selectUserList();

    int deleteUser(String uid);

}
