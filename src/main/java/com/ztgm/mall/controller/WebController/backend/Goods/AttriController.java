package com.ztgm.mall.controller.WebController.backend.Goods;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.ztgm.mall.pojo.AttributeDef;
import com.ztgm.mall.pojo.Brand;
import com.ztgm.mall.pojo.PageCondition;
import com.ztgm.mall.service.AttributeService;
import com.ztgm.mall.util.Message;
import com.ztgm.mall.util.PageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ztgm.mall.util.PageUtil.packageTableData;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 * 
 * @author zj
 * @date 2018年5月25日
 *
 */
@Controller
@RequestMapping("/manager/attri")
public class AttriController {

	@Autowired
	private AttributeService attriService;

	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
	
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.getPrincipal();

		return "/backend/goods/attri/index";
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
	public String list(AttributeDef q,PageCondition pageCondition, HttpServletRequest request,
			HttpServletResponse response) {

		
		
		String resultString = "";

		Page page = PageUtil.getPage(pageCondition);
		List<AttributeDef> rst = attriService.selectAttributeDefList(q);
		try {
			resultString = packageTableData(page, rst);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return resultString;// responseData(request, response, datastr);

		
		
		

	}

	@RequestMapping("/load")
	@ResponseBody
	public AttributeDef load(HttpServletRequest request) {
		String id = request.getParameter("id");

		AttributeDef p = attriService.selectByPrimaryKey(id);

		return p;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Message updateNoticeRead(HttpServletRequest request) {
		String id = request.getParameter("id");
		Message msg = new Message();
		try {
			int result = attriService.deleteByPrimaryKey(id);
			if (result == 1) {
				msg.setBol(true);
			}	
		} catch (Exception e) {
			if(e.getMessage().contains("a foreign key constraint fails"))
			{
				msg.setMessage("商品属性已被商品引用，不允许删除！");
			}
			msg.setBol(false);
		}
		
		return msg;
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(AttributeDef role, HttpServletRequest request) {
		JSONObject jsonObject = null;

		/// String menuids = request.getParameter("menuids");

		jsonObject = attriService.insertSelective(role);

		return jsonObject.toString();

	}

	/**
	 * 
	 *
	 * @param user
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(AttributeDef attri, HttpServletRequest request) {
		JSONObject jsonObject = null;
		try {

			if (null == attri.getId() || "".equals(attri.getId())) {
				jsonObject = attriService.insertSelective(attri);
			} else {
				jsonObject = attriService.updateByPrimaryKeySelective(attri);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject.toString();
	}

	@RequestMapping("/update")
	@ResponseBody
	public String update(AttributeDef role, HttpServletRequest request) {
		JSONObject jsonObject = null;

		// String menuids = request.getParameter("menuids");
		jsonObject = attriService.updateByPrimaryKeySelective(role);

		return jsonObject.toString();

	}

}
