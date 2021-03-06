package com.ztgm.mall.controller.WebController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.ztgm.mall.controller.BaseController;
import com.ztgm.mall.pojo.Permission;
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
@RequestMapping("/privilege/permission")
public class PermissionController extends BaseController {
	@Autowired
	private ManagerService userService;
	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService permissionService;

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

		return "/backend/privilege/permission/index";
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
		String pid = request.getParameter("pid");
		String type = request.getParameter("type");
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

		Permission q = new Permission();
		q.setName(name);
		q.setParentid(pid);
		q.setType(type);
		List<Permission> rst = permissionService.selectPermissionList(q);

		Gson gs = new Gson();
		JSONObject data = new JSONObject();

		try {
			JSONArray rows = new JSONArray(gs.toJson(rst));
			data.put("total", ((Page<Permission>) rst).getTotal());
			data.put("rows", rows);
		} catch (Exception e) {
			// TODO: handle exception
		}

		String datastr = data.toString();
		return datastr;// responseData(request, response, datastr);

	}

	@RequestMapping("/load")
	@ResponseBody
	public Permission load(HttpServletRequest request) {
		String id = request.getParameter("id");

		Permission p = permissionService.selectByPrimaryKey(id);

		return p;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Message updateNoticeRead(HttpServletRequest request) {
		String id = request.getParameter("id");
		Message msg = new Message();
		int result = permissionService.deleteByPrimaryKey(id);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(Permission permission, HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		try {

			Permission ptmp = permissionService.selectByPrimaryKey(permission.getId());
			if (ptmp != null) {
				
				jsonObject.put("result", false);
				jsonObject.put("message", "已存在相同ID的菜单项");
			} else
				jsonObject = permissionService.insertSelective(permission);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject.toString();

	}

	@RequestMapping("/update")
	@ResponseBody
	public String update(Permission permission, HttpServletRequest request) {
		JSONObject jsonObject   = new JSONObject();
		try {
			jsonObject = permissionService.updateByPrimaryKeySelective(permission);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject.toString();

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
	@RequestMapping(value = { "/getMenuTree" }, method = RequestMethod.POST)
	public @ResponseBody List<String> getMenuTree(@RequestParam("role_id") String role_id) {
		// 获取所有部门信息
		return permissionService.getPermissionTreeThree(role_id);
	}
	
	/**
	 * 构造用户树数据 zTree -二级查询
	 * 
	 * @param orgname
	 *            用户组或者用户名称
	 * @return
	 * @date 2016-3-3
	 * @author zj
	 */
	@RequestMapping(value = { "/getMenuTreeSecond" }, method = RequestMethod.POST)
	public @ResponseBody List<String> getMenuTreeSecond(@RequestParam("role_id") String role_id) {
		// 获取所有部门信息
		return permissionService.getPermissionTreeSecond(role_id);
	}

}
