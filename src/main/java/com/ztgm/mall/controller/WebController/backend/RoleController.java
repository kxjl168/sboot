package com.ztgm.mall.controller.WebController.backend;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.ztgm.mall.controller.BaseController;
import com.ztgm.mall.pojo.Role;
import com.ztgm.mall.service.PermissionService;
import com.ztgm.mall.service.RoleService;
import com.ztgm.mall.service.ManagerService;
import com.ztgm.mall.util.Message;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/privilege/role")
public class RoleController extends BaseController {
	@Autowired
	private ManagerService userService;

	@Autowired
	private PermissionService permissonService;

	@Autowired
	private RoleService roleService;

	/**
	 * 权限菜单管理
	 * 
	 * @param map
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	@RequestMapping("/index")
	public String index(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {

		Map principal = (Map) request.getAttribute("principal");

		return "/backend/privilege/role/index";
	}

	/**
	 * 构造用户树数据 zTree
	 * 
	 * @param orgname
	 *            用户组或者用户名称
	 * @return
	 * @date 2016-3-3
	 * @author zj
	 */
	@RequestMapping(value = { "/getRoleTree" }, method = RequestMethod.POST)
	public @ResponseBody List<String> getRoleTree(@RequestParam("user_id") String user_id) {
		// 获取所有部门信息
		return roleService.getRoleTree(user_id);
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

		Role q = new Role();
		q.setName(name);
		List<Role> rst = roleService.selectRoleList(q);

		Gson gs = new Gson();
		JSONObject data = new JSONObject();

		try {
			JSONArray rows = new JSONArray(gs.toJson(rst));
			data.put("total", ((Page<Role>) rst).getTotal());
			data.put("rows", rows);
		} catch (Exception e) {
			// TODO: handle exception
		}

		String datastr = data.toString();
		return datastr;// responseData(request, response, datastr);

	}

	@RequestMapping("/load")
	@ResponseBody
	public Role load(HttpServletRequest request) {
		String id = request.getParameter("id");

		Role p = roleService.selectByPrimaryKey(id);

		return p;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Message updateNoticeRead(HttpServletRequest request) {
		String id = request.getParameter("id");
		Message msg = new Message();
		int result = roleService.deleteByPrimaryKey(id);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(Role role, HttpServletRequest request) {
		JSONObject jsonObject = null;

		String menuids = request.getParameter("menuids");

		jsonObject = roleService.insertSelective(role, menuids);

		return jsonObject.toString();

	}


	@RequestMapping("/update")
	@ResponseBody
	public String update(Role role, HttpServletRequest request) {
		JSONObject jsonObject = null;

		String menuids = request.getParameter("menuids");
		jsonObject = roleService.updateByPrimaryKeySelective(role, menuids);

		return jsonObject.toString();

	}

}
