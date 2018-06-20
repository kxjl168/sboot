package com.ztgm.mall.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.ztgm.mall.pojo.Role;

public interface RoleService {

	int deleteByPrimaryKey(String id);

	int insert(Role record);

	
	JSONObject insertSelective(Role record, String permissonids);

	Role selectByPrimaryKey(String id);


	JSONObject updateByPrimaryKeySelective(Role record, String permissionids);

	int updateByPrimaryKey(Role record);

	/**
	 * 更新角色下的权限菜单
	 * 
	 * @param role_id
	 * @param permissionids
	 * @return
	 * @author zj
	 * @date 2018年5月10日
	 */
	public int updateRolePermissionList(String role_id, String permissionids);

	/**
	 * 构造权限树数据
	 * 
	 * @param role_id
	 * @return
	 * @author zj
	 * @date 2018年5月10日
	 */
	public List<String> getRoleTree(String Manager_id);

	/**
	 * update / save
	 * 
	 * @param record
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	// public JSONObject saveOrUpdate(Role record);
	/**
	 * 条件查询
	 * 
	 * @param role
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	List<Role> selectRoleList(Role role);

	/**
	 * 通过Manager bean对象查询Manager 列表
	 * 
	 * @param ManagerId
	 *            用户id
	 * @return 用户所属角色
	 */
	List<Map> selectRoleByManagerId(String ManagerId);

	/**
	 * 获取所有角色列表
	 * 
	 * @return
	 */
	List<Map> selectRoles();

}
