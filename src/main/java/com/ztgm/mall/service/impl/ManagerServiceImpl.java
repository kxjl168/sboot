package com.ztgm.mall.service.impl;

import com.ztgm.mall.dao.*;
import com.ztgm.mall.pojo.*;
import com.ztgm.mall.service.ManagerService;
import com.ztgm.mall.util.UUIDUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Transactional
@Service("ManagerService")
public class ManagerServiceImpl implements ManagerService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ManagerMapper ManagerMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private ManagerRoleMapper ManagerRoleMapper;
/*	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private GroupManagerMapper groupManagerMapper;
*/
	@Autowired
	private ManagerMapper ManagerDao;
/*	@Autowired
	private ManagerInfoMapper ManagerInfoDao;
	@Autowired
	private CombinationMapper combinationMapper;*/

	public int deleteByPrimaryKey(String id) {
		return ManagerMapper.deleteByPrimaryKey(id);
	}

	public int insert(Manager record) {
		return ManagerMapper.insert(record);
	}

	public int insertSelective(Manager record) {
		return ManagerMapper.insertSelective(record);
	}

	public Manager selectByPrimaryKey(String id) {
		return ManagerMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(Manager record) {
		return ManagerMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(Manager record) {
		return ManagerMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(Manager record) {
		return ManagerMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Manager> selectManagerByManager(Manager Manager) {

		return ManagerMapper.selectManagerByManager(Manager);
	}

	@Override
	@Transactional
	public Map initSuperAdmin(Manager Manager) {
		Map<String, java.io.Serializable> rtn = new HashMap<String, java.io.Serializable>();
		rtn.put("result", false);

		try {
			// 插入用户信息
			Manager.setId(UUIDUtil.getUUID());

			Manager.setCreateDate(new Date());
			Manager.setCreater("0");
			ManagerMapper.insert(Manager);

			// 查询管理员角色信息
			Map<String, String> conditions = new HashMap<>();
			conditions.put("name", "系统管理员");
			List<Map> roles = roleMapper.selectRoleByConditions(conditions);
			if (null == roles) {
				rtn.put("msg", "管理员角色未找到");
				return rtn;
			}

			Map role = roles.get(0);

			// 插入用户角色表
			ManagerRole ManagerRole = new ManagerRole();
			ManagerRole.setId(UUIDUtil.getUUID());
			ManagerRole.setSysManagerId(Manager.getId());
			ManagerRole.setSysRoleId(String.valueOf(role.get("id")));
			ManagerRoleMapper.insert(ManagerRole);

			rtn.put("result", true);
		} catch (Exception e) {
			rtn.put("msg", "未知异常");
			// todo
			// Log
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return rtn;
	}

	@Override
	public List<Manager> getAdminManagers() {
		return ManagerMapper.getAdminManagers();
	}

	public Manager login(String Managername, String password) {
		List<Manager> ulist = ManagerDao.selectManagerByNamePwd(Managername, password);
		if (ulist == null || ulist.size() == 0) {
			return null;
		}
		return ulist.get(0);
	}

	public Manager getManagerByToken(String token) {
		List<Manager> ulist = ManagerDao.selectManagerByToken(token);
		if (ulist == null || ulist.size() == 0) {
			return null;
		}
		return ulist.get(0);
	}

	/**
	 * 更新token
	 *
	 * @param token
	 */
	public void updateManagerToke(String id, String token) {
		Manager u = new Manager();
		u.setId(id);
		u.setToken(token);
		ManagerDao.updateByPrimaryKeySelective(u);
	}

	public Manager getManagerByPhone(String phone) {
		List<Manager> ulist = ManagerDao.selectManagerByPhone(phone);
		if (ulist == null || ulist.size() == 0) {
			return null;
		}
		return ulist.get(0);
	}

	

	/**
	 * @param Manager
	 * @return
	 */
	@Override
	@Transactional
	public JSONObject saveManager(Manager Manager) {
		return saveManager(Manager, "");
	}

	/**
	 * @param Manager
	 * @return
	 */
	@Override
	@Transactional
	public JSONObject saveManager(Manager Manager, String roleids) {
		JSONObject rtn = new JSONObject();

		if (null == Manager || null == Manager.getPassword() || null == Manager.getTelephone()) {
			rtn.put("result", false);
			rtn.put("message", "手机号码或者密码为空");
			return rtn;
		}

		try {
			Map<String, String> conditions = new HashMap<>();
			conditions.put("telephone", Manager.getTelephone());
			List<Map> Managers = ManagerDao.selectManagerByMap(conditions);
			if (null != Managers && 0 < Managers.size()) {
				logger.info("用户输入手机号码重复");
				rtn.put("result", false);
				rtn.put("message", "用户输入手机号码重复");
				return rtn;
			}

			Manager.setId(UUIDUtil.getUUID());
			Manager.stPasswordAndEncrype(Manager.getPassword());
			Manager.setCreateDate(new Date());
			Manager.setCreater("0");
			ManagerDao.insert(Manager);

			// 添加
			String[] menus = roleids.split(",");
			for (int i = 0; i < menus.length; i++) {
				if (menus[i].trim().equals(""))
					continue;

				ManagerRole item = new ManagerRole();
				item.setSysManagerId(Manager.getId());
				item.setSysRoleId(menus[i]);
				item.setId(UUIDUtil.getUUID());

				ManagerRoleMapper.insertSelective(item);
			}

			rtn.put("result", true);

			return rtn;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("新增用户失败", e);
			rtn.put("result", false);
			rtn.put("message", "新增用户失败");
			return rtn;
		}
	}

	@Override
	@Transactional
	public JSONObject updateManager(Manager Manager, String roleids) {
		JSONObject rtn = new JSONObject();

		if (null == Manager || null == Manager.getId()) {
			rtn.put("result", false);
			rtn.put("message", "用户id为空");
			return rtn;
		}

		try {
			if (null != Manager.getPassword() && !"".equals(Manager.getPassword())) {
				Manager.stPasswordAndEncrype(Manager.getPassword());
				ManagerDao.updateByPrimaryKeySelective(Manager);
			}

			// 清空
			ManagerRoleMapper.deleteManagerRole(Manager);

			// 添加
			String[] menus = roleids.split(",");
			for (int i = 0; i < menus.length; i++) {
				if (menus[i].trim().equals(""))
					continue;

				ManagerRole item = new ManagerRole();
				item.setSysManagerId(Manager.getId());
				item.setSysRoleId(menus[i]);
				item.setId(UUIDUtil.getUUID());

				ManagerRoleMapper.insertSelective(item);
			}

			// Map<String, String> ManagerRole = new HashMap<>();
			// ManagerRole.put("id", Manager.getManagerRoleId());
			// ManagerRole.put("sysRoleId", Manager.getRole());
			// ManagerRoleMapper.update(ManagerRole);

			rtn.put("result", true);

			return rtn;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("更新用户出错", e);
			rtn.put("result", false);
			rtn.put("message", "更新用户出错");
			return rtn;
		}
	}

	@Override
	public List<Map> selectManagerByManager(Map conditions) {
		return ManagerMapper.selectManagerByMap(conditions);
	}

	public void directUpdateManager(Manager u) {
		ManagerMapper.updateByPrimaryKey(u);
	}

	@Override
	public List<Map> selectManagerList() {
		List<Map> ManagerList = new ArrayList<>();
		try {
			ManagerList = ManagerMapper.selectManagerList();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询用户列表出错");
		}
		return ManagerList;
	}

	@Override
	@Transactional
	public int deleteManager(String uid) {
		int result = 0;
		try {
			result = ManagerMapper.deleteByPrimaryKey(uid);
			
			Manager q=new Manager();
			q.setId(uid);
			ManagerRoleMapper.deleteManagerRole(q);
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			logger.error("删除用户出错");
		}
		return result;
	}

}
