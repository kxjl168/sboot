package com.ztgm.iot.service.impl;

import com.ztgm.iot.dao.*;
import com.ztgm.iot.pojo.*;
import com.ztgm.iot.service.UserService;
import com.ztgm.iot.util.UUIDUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private GroupUserMapper groupUserMapper;

	@Autowired
	private UserMapper userDao;
	@Autowired
	private UserInfoMapper userInfoDao;
	@Autowired
	private CombinationMapper combinationMapper;

	public int deleteByPrimaryKey(String id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	public int insert(User record) {
		return userMapper.insert(record);
	}

	public int insertSelective(User record) {
		return userMapper.insertSelective(record);
	}

	public User selectByPrimaryKey(String id) {
		return userMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(User record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(User record) {
		return userMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(User record) {
		return userMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<User> selectUserByUser(User user) {

		return userMapper.selectUserByUser(user);
	}

	@Override
	@Transactional
	public Map initSuperAdmin(User user) {
		Map<String, java.io.Serializable> rtn = new HashMap<String, java.io.Serializable>();
		rtn.put("result", false);

		try {
			// 插入用户信息
			user.setId(UUIDUtil.getUUID());

			user.setCreateDate(new Date());
			user.setCreater("0");
			userMapper.insert(user);

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
			UserRole userRole = new UserRole();
			userRole.setId(UUIDUtil.getUUID());
			userRole.setSysUserId(user.getId());
			userRole.setSysRoleId(String.valueOf(role.get("id")));
			userRoleMapper.insert(userRole);

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
	public List<User> getAdminUsers() {
		return userMapper.getAdminUsers();
	}

	public User login(String username, String password) {
		List<User> ulist = userDao.selectUserByNamePwd(username, password);
		if (ulist == null || ulist.size() == 0) {
			return null;
		}
		return ulist.get(0);
	}

	public User getUserByToken(String token) {
		List<User> ulist = userDao.selectUserByToken(token);
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
	public void updateUserToke(String id, String token) {
		User u = new User();
		u.setId(id);
		u.setToken(token);
		userDao.updateByPrimaryKeySelective(u);
	}

	public User getUserByPhone(String phone) {
		List<User> ulist = userDao.selectUserByPhone(phone);
		if (ulist == null || ulist.size() == 0) {
			return null;
		}
		return ulist.get(0);
	}

	/**
	 * 权限转移（设备控制权）
	 *
	 * @param fromUserId
	 * @param toUserId
	 */
	public void controlPermissionTransfer(String fromUserId, String toUserId) {
		User fromUser = userDao.selectByPrimaryKey(fromUserId);
		User toUser = userDao.selectByPrimaryKey(toUserId);

		toUser.setId(fromUserId);
		fromUser.setId(toUserId);
		userDao.updateByPrimaryKey(toUser);
		userDao.updateByPrimaryKey(fromUser);

		UserInfo fromUserInfo = userInfoDao.selectByPrimaryKey(fromUserId);
		UserInfo toUserInfo = userInfoDao.selectByPrimaryKey(toUserId);
		if (fromUserInfo != null)
			fromUserInfo.setUserId(toUserId);
		if (toUserInfo != null)
			toUserInfo.setUserId(fromUserId);

		userInfoDao.updateByPrimaryKey(fromUserInfo);
		userInfoDao.updateByPrimaryKey(toUserInfo);

	}

	/**
	 * @param user
	 * @return
	 */
	@Override
	@Transactional
	public JSONObject saveUser(User user) {
		return saveUser(user, "");
	}

	/**
	 * @param user
	 * @return
	 */
	@Override
	@Transactional
	public JSONObject saveUser(User user, String roleids) {
		JSONObject rtn = new JSONObject();

		if (null == user || null == user.getPassword() || null == user.getTelephone()) {
			rtn.put("result", false);
			rtn.put("message", "手机号码或者密码为空");
			return rtn;
		}

		try {
			Map<String, String> conditions = new HashMap<>();
			conditions.put("telephone", user.getTelephone());
			List<Map> users = userDao.selectUserByMap(conditions);
			if (null != users && 0 < users.size()) {
				logger.info("用户输入手机号码重复");
				rtn.put("result", false);
				rtn.put("message", "用户输入手机号码重复");
				return rtn;
			}

			user.setId(UUIDUtil.getUUID());
			user.stPasswordAndEncrype(user.getPassword());
			user.setCreateDate(new Date());
			user.setCreater("0");
			userDao.insert(user);

			// 添加
			String[] menus = roleids.split(",");
			for (int i = 0; i < menus.length; i++) {
				if (menus[i].trim().equals(""))
					continue;

				UserRole item = new UserRole();
				item.setSysUserId(user.getId());
				item.setSysRoleId(menus[i]);
				item.setId(UUIDUtil.getUUID());

				userRoleMapper.insertSelective(item);
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
	public JSONObject updateUser(User user, String roleids) {
		JSONObject rtn = new JSONObject();

		if (null == user || null == user.getId()) {
			rtn.put("result", false);
			rtn.put("message", "用户id为空");
			return rtn;
		}

		try {
			if (null != user.getPassword() && !"".equals(user.getPassword())) {
				user.stPasswordAndEncrype(user.getPassword());
				userDao.updateByPrimaryKeySelective(user);
			}

			// 清空
			userRoleMapper.deleteUserRole(user);

			// 添加
			String[] menus = roleids.split(",");
			for (int i = 0; i < menus.length; i++) {
				if (menus[i].trim().equals(""))
					continue;

				UserRole item = new UserRole();
				item.setSysUserId(user.getId());
				item.setSysRoleId(menus[i]);
				item.setId(UUIDUtil.getUUID());

				userRoleMapper.insertSelective(item);
			}

			// Map<String, String> userRole = new HashMap<>();
			// userRole.put("id", user.getUserRoleId());
			// userRole.put("sysRoleId", user.getRole());
			// userRoleMapper.update(userRole);

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
	public List<Map> selectUserByUser(Map conditions) {
		return userMapper.selectUserByMap(conditions);
	}

	public void directUpdateUser(User u) {
		userMapper.updateByPrimaryKey(u);
	}

	@Override
	public List<Map> selectUserList() {
		List<Map> userList = new ArrayList<>();
		try {
			userList = userMapper.selectUserList();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询用户列表出错");
		}
		return userList;
	}

	@Override
	@Transactional
	public int deleteUser(String uid) {
		int result = 0;
		try {
			result = userMapper.deleteByPrimaryKey(uid);
			
			User q=new User();
			q.setId(uid);
			userRoleMapper.deleteUserRole(q);
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			logger.error("删除用户出错");
		}
		return result;
	}

}
