package com.ztgm.mall.controller.WebController.backend;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

import com.ztgm.mall.pojo.Manager;
import com.ztgm.mall.service.RoleService;

import com.ztgm.mall.service.impl.ManagerServiceImpl;

import com.ztgm.mall.util.Message;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
public class ManagerController {
	@Autowired
	private ManagerServiceImpl userService;

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

		List<Map> users = userService.selectManagerByManager(params);
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
		 * ManagerIDUtil.getManagerID(subject, response);
		 */

		Manager q = new Manager();
		q.setUsername(name);
		List<Manager> rst = userService.selectManagerByManager(q);

		Gson gs = new Gson();
		JSONObject data = new JSONObject();
		String datastr = "";
		try {
			// JSONArray rows = new JSONArray(gs.toJson(rst));
			data.put("total", ((Page<Manager>) rst).getTotal());
			data.put("rows", rst);
			datastr = data.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return datastr;// responseData(request, response, datastr);

	}

	@RequestMapping("/ManagerList")
	@ResponseBody
	public List<Map> ManagerList() {
		return userService.selectManagerList();
	}

	@RequestMapping("/deleteManager")
	@ResponseBody
	public Message ManagerList(HttpServletRequest request) {
		String uid = request.getParameter("id");
		Message msg = new Message();
		int result = userService.deleteManager(uid);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/getManager")
	@ResponseBody
	public String getManager(@RequestParam Map<String, String> params) {
		JSONObject jsonObject = new JSONObject();
		List<Map> users = userService.selectManagerByManager(params);
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
	public String saveOrUpdate(Manager user, Model model, HttpServletRequest request) {
		JSONObject jsonObject = null;
		try {

			String menuids = request.getParameter("roleids");

			if (null == user.getId() || "".equals(user.getId())) {
				jsonObject = userService.saveManager(user, menuids);
			} else {
				jsonObject = userService.updateManager(user, menuids);
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

}