package com.ztgm.mall.controller.WebController.backend.Goods;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.ztgm.mall.pojo.AttributeDef;
import com.ztgm.mall.pojo.Brand;
import com.ztgm.mall.pojo.OptionDef;
import com.ztgm.mall.pojo.PageCondition;
import com.ztgm.mall.pojo.OptionDef;
import com.ztgm.mall.service.OptionService;
import com.ztgm.mall.util.Message;
import com.ztgm.mall.util.PageUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ztgm.mall.util.PageUtil.packageTableData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 商品选项
 * @author zj
 * @date 2018年5月25日
 *
 */
@Controller
@RequestMapping("/manager/option")
public class OptionController {

	@Autowired
	private OptionService optionService;
	

    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
        map.put("name", "admin" );
        map.put("admin", "admin2" );

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getPrincipal();

        return "/backend/goods/option/index";
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
	public String list(OptionDef q, PageCondition pageCondition, HttpServletRequest request,
			HttpServletResponse response) {

		String resultString = "";

		Page page = PageUtil.getPage(pageCondition);
		List<OptionDef> rst = optionService.selectOptionDefList(q);
		try {
			resultString = packageTableData(page, rst);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return resultString;// responseData(request, response, datastr);

	}

	@RequestMapping("/load")
	@ResponseBody
	public OptionDef load(HttpServletRequest request) {
		String id = request.getParameter("id");

		OptionDef p = optionService.selectByPrimaryKey(id);

		return p;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Message updateNoticeRead(HttpServletRequest request) {
		String id = request.getParameter("id");
		Message msg = new Message();
		int result = optionService.deleteByPrimaryKey(id);
		if (result == 1) {
			msg.setBol(true);
		}
		return msg;
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(OptionDef role, HttpServletRequest request) {
		JSONObject jsonObject = null;

		///String menuids = request.getParameter("menuids");

		jsonObject = optionService.insertSelective(role);

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
	public String saveOrUpdate(OptionDef item, HttpServletRequest request) {
		JSONObject jsonObject = null;
		try {

		
			if (null == item.getId() || "".equals(item.getId())) {
				jsonObject = optionService.insertSelective(item);
			} else {
				jsonObject = optionService.updateByPrimaryKeySelective(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject.toString();
	}




	@RequestMapping("/update")
	@ResponseBody
	public String update(OptionDef role, HttpServletRequest request) {
		JSONObject jsonObject = null;

		//String menuids = request.getParameter("menuids");
		jsonObject = optionService.updateByPrimaryKeySelective(role);

		return jsonObject.toString();

	}


}
