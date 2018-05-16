package com.ztgm.iot.service.impl;


import com.ztgm.iot.dao.PermissionMapper;
import com.ztgm.iot.dao.RoleMapper;
import com.ztgm.iot.pojo.Permission;
import com.ztgm.iot.pojo.Role;
import com.ztgm.iot.service.PermissionService;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private RoleMapper roleDao;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int deleteByPrimaryKey(String id) {
		return permissionMapper.deleteByPrimaryKey(id);
	}

	public int insert(Permission record) {
		return permissionMapper.insert(record);
	}

	public JSONObject insertSelective(Permission record) {

		JSONObject rtn = new JSONObject();

		try {

			permissionMapper.insertSelective(record);

			rtn.put("result", true);

			return rtn;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("更新出错", e);
			try {
				rtn.put("result", false);
				rtn.put("message", "更新出错");
			} catch (Exception e2) {
				// TODO: handle exception
			}

			return rtn;
		}

	}

	public JSONObject saveOrUpdate(Permission record) {
		if (null == record.getId() || "".equals(record.getId()))
			return insertSelective(record);
		else
			return updateByPrimaryKeySelective(record);
	}

	public Permission selectByPrimaryKey(String id) {
		return permissionMapper.selectByPrimaryKey(id);
	}
	
	

	/**
	 * 构造权限树数据
	 * @param role_id
	 * @return
	 * @author zj
	 * @date 2018年5月10日
	 */
	public List<String> getPermissionTree(String role_id) {
		List<String> lstTree = new ArrayList<String>();

		try {

			Role bean = roleDao.selectByPrimaryKey(role_id);

			List<Permission> selectMenus = getRolePermissionList(bean);

			//一级菜单
			Permission query=new Permission();
			query.setType("1");
			query.setAvailable("1");
			List<Permission> menus = selectPermissionList(query);
			
			String rootid = "0";
			for (Permission menu : menus) {
				StringBuffer sBuffer = new StringBuffer();
				sBuffer.append("{");
				sBuffer.append("id:\"" + menu.getId() + "\",");
				sBuffer.append("pId:\"" + rootid + "\",");
				sBuffer.append("open:true,");// 根节点打开

				if (selectMenus != null)
					for (int i = 0; i < selectMenus.size(); i++) {
						if (selectMenus.get(i).getId().equals(menu.getId())) {
							sBuffer.append("checked:true,");// 选中
							break;
						}
					}

				sBuffer.append("name:\"" + menu.getName() + "\",");

				sBuffer.append("remark:\"" + "" + "\"");
				// sBuffer.append("iconSkin:\"" + "diygroup" + "\"");
				// sBuffer.append("icon:\"" + "images/group2.png" + "\",");

				sBuffer.append("}");
				lstTree.add(sBuffer.toString());

			}

			for (int i = 0; i < menus.size(); i++) {
				Permission query2=new Permission();
				query2.setParentid(menus.get(i).getId());
				query2.setAvailable("1");
				List<Permission> all_menus = selectPermissionList(query2);
				

				for (Permission menu : all_menus) {
					StringBuffer sBuffer = new StringBuffer();
					sBuffer.append("{");
					sBuffer.append("id:\"" + menu.getId() + "\",");
					sBuffer.append("pId:\"" + menus.get(i).getId() + "\",");
					sBuffer.append("open:true,");// 根节点打开

					sBuffer.append("name:\"" + menu.getName() + "\",");

					if (selectMenus != null)
						for (int j = 0; j < selectMenus.size(); j++) {
							if (selectMenus.get(j).getId().equals(menu.getId())) {
								sBuffer.append("checked:true,");// 选中
								break;
							}
						}

					sBuffer.append("remark:\"" + "" + "\"");
					// sBuffer.append("iconSkin:\"" + "diygroup" + "\"");
					// sBuffer.append("icon:\"" + "images/group2.png" + "\",");

					sBuffer.append("}");
					lstTree.add(sBuffer.toString());

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lstTree;
	}

	public JSONObject updateByPrimaryKeySelective(Permission record) {

		JSONObject rtn = new JSONObject();

		try {

			if (null == record || null == record.getId()) {
				rtn.put("result", false);
				rtn.put("message", "id为空");
				return rtn;
			}
			permissionMapper.updateByPrimaryKeySelective(record);

			rtn.put("result", true);

			return rtn;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("更新出错", e);
			try {
				rtn.put("result", false);
				rtn.put("message", "更新出错");
			} catch (Exception e2) {
				// TODO: handle exception
			}

			return rtn;
		}

	}

	public int updateByPrimaryKey(Permission record) {
		return permissionMapper.updateByPrimaryKey(record);
	}

	/**
	 * 获取角色的所有权限
	 * 
	 * @param role
	 * @return
	 * @author zj
	 * @date 2018年5月10日
	 */
	public List<Permission> getRolePermissionList(Role role) {
		return permissionMapper.getRolePermissionList(role);
	}

	/**
	 * 条件查询
	 * 
	 * @param permission
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	public List<Permission> selectPermissionList(Permission permission) {
		return permissionMapper.selectPermissionList(permission);
	}

	@Override
	public List<Map> selectPermissionsByRoleId(String roleId) {
		return permissionMapper.selectPermissionsByRoleId(roleId);
	}

	@Override
	public List<Map> selectPermissions() {
		return permissionMapper.selectPermissions();
	}

}
