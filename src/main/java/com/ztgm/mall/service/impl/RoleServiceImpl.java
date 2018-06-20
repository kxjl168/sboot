package com.ztgm.mall.service.impl;

import com.ztgm.mall.dao.RoleMapper;
import com.ztgm.mall.dao.RolePermissionMapper;
import com.ztgm.mall.dao.ManagerMapper;
import com.ztgm.mall.pojo.Role;
import com.ztgm.mall.pojo.RolePermission;
import com.ztgm.mall.pojo.Manager;
import com.ztgm.mall.service.RoleService;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private ManagerMapper ManagerMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int deleteByPrimaryKey(String id) {
		return roleMapper.deleteByPrimaryKey(id);
	}

	public int insert(Role record) {
		return roleMapper.insert(record);
	}

	@Transactional
	public JSONObject insertSelective(Role record, String ids) {

		JSONObject rtn = new JSONObject();

		try {

			Role tp = roleMapper.selectByPrimaryKey(record.getId());
			if (tp != null) {
				rtn.put("result", false);
				rtn.put("message", "已经此存在角色ID");
				return rtn;
			}

			int rst = roleMapper.insertSelective(record);

			updateRolePermissionList(record.getId(), ids);

			rtn.put("result", true);

			return rtn;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("insert出错", e);
			try {
				rtn.put("result", false);
				rtn.put("message", "insert出错");
			} catch (Exception e2) {
				// TODO: handle exception
			}

			return rtn;
		}

	}

	/*
	 * public JSONObject saveOrUpdate(Role record) { if (null == record.getId() ||
	 * "".equals(record.getId())) return insertSelective(record); else return
	 * updateByPrimaryKeySelective(record); }
	 */
	public Role selectByPrimaryKey(String id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	public int updateRolePermissionList(String role_id, String permissionids) {

		int rst = -1;
		try {
			Role query = new Role();
			query.setId(role_id);

			rolePermissionMapper.deleteRolePerssion(query);

			// 添加
			String[] menus = permissionids.split(",");
			for (int i = 0; i < menus.length; i++) {
				if (menus[i].trim().equals(""))
					continue;

				RolePermission item = new RolePermission();
				item.setSysRoleId(role_id);
				item.setSysPermissionId(menus[i]);

				rolePermissionMapper.insertSelective(item);
			}

			rst = 1;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return rst;
	}

	/**
	 * 构造权限树数据
	 * 
	 * @param role_id
	 * @return
	 * @author zj
	 * @date 2018年5月10日
	 */
	public List<String> getRoleTree(String Manager_id) {
		List<String> lstTree = new ArrayList<String>();

		try {

			Manager u = new Manager();
			u.setId(Manager_id);
			List<Role> Managerroles = selectRoleByManager(u);

			// 一级菜单
			Role q = new Role();
			q.setAvailable("1");
			List<Role> allroles = selectRoleList(q);

			String rootid = "0";
			for (Role role : allroles) {
				StringBuffer sBuffer = new StringBuffer();
				sBuffer.append("{");
				sBuffer.append("id:\"" + role.getId() + "\",");
				sBuffer.append("pId:\"" + rootid + "\",");
				sBuffer.append("open:true,");// 根节点打开

				if (Managerroles != null)
					for (int i = 0; i < Managerroles.size(); i++) {
						if (Managerroles.get(i).getId().equals(role.getId())) {
							sBuffer.append("checked:true,");// 选中
							break;
						}
					}

				sBuffer.append("name:\"" + role.getName() + "\",");

				sBuffer.append("remark:\"" + "" + "\"");
				// sBuffer.append("iconSkin:\"" + "diygroup" + "\"");
				// sBuffer.append("icon:\"" + "images/group2.png" + "\",");

				sBuffer.append("}");
				lstTree.add(sBuffer.toString());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lstTree;
	}

	@Transactional
	public JSONObject updateByPrimaryKeySelective(Role record, String ids) {

		JSONObject rtn = new JSONObject();

		try {

			if (null == record || null == record.getId()) {
				rtn.put("result", false);
				rtn.put("message", "id为空");
				return rtn;
			}
			roleMapper.updateByPrimaryKeySelective(record);
			
			/*
			 * if(true) throw new RuntimeException();
			 */
			updateRolePermissionList(record.getId(), ids);

			rtn.put("result", true);

			return rtn;
		} catch (Exception e) {
			// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		
			// logger.error("更新出错", e);
			try {
				rtn.put("result", false);
				rtn.put("message", "更新出错");
			} catch (Exception e2) {
				// TODO: handle exception
			}
			return rtn;

		

		}
		
	}

	public int updateByPrimaryKey(Role record) {
		return roleMapper.updateByPrimaryKey(record);
	}

	/**
	 * 条件查询
	 * 
	 * @param permission
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	public List<Role> selectRoleByManager(Manager Manager) {
		return roleMapper.selectRoleByManager(Manager);
	}

	/**
	 * 条件查询
	 * 
	 * @param role
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	public List<Role> selectRoleList(Role role) {
		return roleMapper.selectRoleList(role);
	}

	@Override
	public List<Map> selectRoleByManagerId(String ManagerId) {
		return roleMapper.selectRoleByManagerId(ManagerId);
	}

	@Override
	public List<Map> selectRoles() {
		return roleMapper.selectRoleByConditions(new HashMap());
	}

}
