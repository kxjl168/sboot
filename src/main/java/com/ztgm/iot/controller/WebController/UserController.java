package com.ztgm.iot.controller.WebController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.ztgm.iot.pojo.GroupUser;
import com.ztgm.iot.pojo.Permission;
import com.ztgm.iot.pojo.User;
import com.ztgm.iot.pojo.UserInfo;
import com.ztgm.iot.service.RoleService;
import com.ztgm.iot.service.impl.GroupUserServiceImpl;
import com.ztgm.iot.service.impl.UserServiceImpl;
import com.ztgm.iot.util.Message;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager/user")
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private GroupUserServiceImpl groupUserService;

	@Autowired
	private RoleService roleService;

	@RequestMapping("/manager")
	public String manager(@RequestParam Map<String, String> params, Model model) {
		// pageNo
		String pn = params.get("pn");
		// pageSize;
		String ps = params.get("ps");

		int pageNo = 0;
		int pageSize = 10;
		try {
			if (null != pn) {
				pageNo = Integer.parseInt(pn);
			}
			if (null != ps) {
				pageSize = Integer.parseInt(ps);
			}
		} catch (NumberFormatException e) {

		}

		PageHelper.startPage(pageNo, pageSize);

		List<Map> users = userService.selectUserByUser(params);
		// 分页信息
		PageInfo<Map> pageInfo = new PageInfo<>(users);
		List<Map> roles = roleService.selectRoles();

		model.addAttribute("users", users);

		// map.put("pager",pageInfo);
		model.addAttribute("pager", pageInfo);
		model.addAttribute("roles", roles);

		return "/backend/privilege/user/manager";
	}

	/**
	 * bootstrap tables jquery查询数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	@RequestMapping("/list")
	@ResponseBody
	public String list(@RequestParam Map<String, String> params, HttpServletRequest request,
			HttpServletResponse response) {

		String name = request.getParameter("name");
		// pageNo
		String offset = request.getParameter("offset");

		// pageSize;
		String ps = request.getParameter("pageSize");

		int pageNo = 0;
		int pageSize = 10;
		int ofst = 0;
		try {
			if (offset != null) {
				ofst = Integer.parseInt(offset);

			}

			if (null != ps) {
				pageSize = Integer.parseInt(ps);
			}

			pageNo = (int) Math.ceil(((double) (ofst + 1)) / ((double) pageSize));

		} catch (NumberFormatException e) {

		}
		PageHelper.startPage(pageNo, pageSize);

		/*
		 * Subject subject = SecurityUtils.getSubject(); String userId =
		 * UserIDUtil.getUserID(subject, response);
		 */

		User q = new User();
		q.setUsername(name);
		List<User> rst = userService.selectUserByUser(q);

		Gson gs = new Gson();
		JSONObject data = new JSONObject();
		String datastr = "";
		try {
			//JSONArray rows = new JSONArray(gs.toJson(rst));
			data.put("total", ((Page<User>) rst).getTotal());
			data.put("rows", rst);
			datastr = data.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return datastr;// responseData(request, response, datastr);

	}

	@RequestMapping("/UserList")
	@ResponseBody
	public List<Map> UserList() {
		return userService.selectUserList();
	}

	@RequestMapping("/deleteUser")
	@ResponseBody
	public Message UserList(HttpServletRequest request) {
		String uid = request.getParameter("id");
		Message msg = new Message();
		int result = userService.deleteUser(uid);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/getUser")
	@ResponseBody
	public String getUser(@RequestParam Map<String, String> params) {
		JSONObject jsonObject = new JSONObject();
		List<Map> users = userService.selectUserByUser(params);
		if (null != users && 1 == users.size()) {
			jsonObject.putAll(users.get(0));
		}
		return jsonObject.toString();
	}

	/**
	 * 新增普通用户请求 demo
	 *
	 * @param user
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(User user, Model model, HttpServletRequest request) {
		JSONObject jsonObject = null;
		try {

			String menuids = request.getParameter("roleids");

			if (null == user.getId() || "".equals(user.getId())) {
				jsonObject = userService.saveUser(user, menuids);
			} else {
				jsonObject = userService.updateUser(user, menuids);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject.toString();
	}

	@RequestMapping("/etrpermissionTransfer")
	public String etrPermissionTransfer() {
		return "/frontend/permission/permissiontransfer";
	}

	/**
	 * 权限转移（设备控制权）
	 */
	@RequestMapping("/permissionTransfer")
	@ResponseBody
	public Map<String, String> controlPermissionTransfer(String phone) {
		Map<String, String> map = new HashMap<>();
		try {
			User u = userService.getUserByPhone(phone);
			if (u == null) {
				map.put("code", "no_user");
				map.put("message", "没有这个用户。");
				return map;
				// return
				// AppResultUtil.fire(AppResultUtil.no_user_code,AppResultUtil.no_user_message,null);
			}
			Subject subject = SecurityUtils.getSubject();
			Map principal = (Map) subject.getPrincipal();
			String userId = (String) principal.get("userId");
			GroupUser groupUser = groupUserService.findGroupByUser(userId);

			// to do 验证是否家庭组成员
			List<User> list = groupUserService.findGroupMemberList(groupUser.getGroupId());
			boolean inGroup = false;
			for (User gu : list) {
				if (u.getId().equals(gu.getId())) {
					inGroup = true;
					break;
				}
			}
			if (!inGroup) {
				map.put("code", "not_in_group");
				map.put("message", "用户不在组内。");
				return map;
				// return AppResultUtil.fire(User_Code_U001,"This user not in group. ",null);
				// return AppResultUtil.fire(appCode.U001,appMess.U001,null);
			}
			userService.controlPermissionTransfer(userId, u.getId());
			map.put("code", "success");
			map.put("message", "操作成功！");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "fail");
			map.put("message", "操作失败！");
			return map;
		}
	}

}