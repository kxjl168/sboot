package com.ztgm.mall.service;

import com.ztgm.mall.pojo.Manager;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface ManagerService {

	int deleteByPrimaryKey(String id);

	int insert(Manager record);

	int insertSelective(Manager record);

	Manager selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Manager record);

	int updateByPrimaryKeyWithBLOBs(Manager record);

	int updateByPrimaryKey(Manager record);

	/**
	 * 通过Manager bean对象查询Manager 列表
	 * 
	 * @param Manager
	 * @return
	 */
	List<Manager> selectManagerByManager(Manager Manager);

	/**
	 * 通过Manager bean对象查询Manager 列表
	 * 
	 * @param Manager
	 * @return
	 */
	Map initSuperAdmin(Manager Manager);

	/**
	 * 获取系统中现有的管理员用户列表
	 * 
	 * @return 现有管理员用户列表
	 */
	List<Manager> getAdminManagers();

	/**
	 * 新增用户
	 */
	JSONObject saveManager(Manager Manager);

	/**
	 * 新增用户
	 */
	JSONObject saveManager(Manager Manager, String roleids);

	/**
	 * 更新用户信息
	 */
	JSONObject updateManager(Manager Manager, String roleids);

	/**
	 * 通过Manager bean对象查询Manager 列表
	 * 
	 * @param conditions
	 * @return
	 */
	List<Map> selectManagerByManager(Map conditions);

	List<Map> selectManagerList();

	int deleteManager(String uid);

}
