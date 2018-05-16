package com.ztgm.iot.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.ztgm.iot.pojo.Permission;
import com.ztgm.iot.pojo.Role;

public interface PermissionService {

	int deleteByPrimaryKey(String id);

	int insert(Permission record);

	JSONObject insertSelective(Permission record);

	Permission selectByPrimaryKey(String id);

	JSONObject updateByPrimaryKeySelective(Permission record);

	int updateByPrimaryKey(Permission record);

	
	/**
	 * update / save
	 * @param record
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	public JSONObject saveOrUpdate(Permission record);
	/**
	 * 条件查询
	 * 
	 * @param permission
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	List<Permission> selectPermissionList(Permission permission);

	
	
	/**
	 * 构造权限树数据 zTree
	 * @param role_id
	 * @return
	 * @author zj
	 * @date 2018年5月10日
	 */
	public List<String> getPermissionTree(String role_id) ;
	/**
	 * 获取角色的所有权限
	 * @param role
	 * @return
	 * @author zj
	 * @date 2018年5月10日
	 */
	List<Permission> getRolePermissionList(Role role);
	
	
	/**
	 * 通过roleId 查询角色对应权限
	 * 
	 * @param roleId
	 *            角色id
	 * @return
	 */
	List<Map> selectPermissionsByRoleId(String roleId);

	/**
	 * 查询所有可用功能权限
	 * 
	 * @return
	 */
	List<Map> selectPermissions();

}
