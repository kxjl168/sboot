package com.ztgm.mall.controller.WebController.backend.Goods;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.ztgm.mall.pojo.AttributeDef;
import com.ztgm.mall.pojo.Brand;
import com.ztgm.mall.pojo.Commodity;
import com.ztgm.mall.pojo.CommodityAttribute;
import com.ztgm.mall.pojo.CommodityFirstClassify;
import com.ztgm.mall.pojo.CommodityOption;
import com.ztgm.mall.pojo.CommondityAttach;
import com.ztgm.mall.pojo.CommondityImage;
import com.ztgm.mall.pojo.Order;
import com.ztgm.mall.pojo.PageCondition;
import com.ztgm.mall.service.AttributeService;
import com.ztgm.mall.service.BrandService;
import com.ztgm.mall.service.CommodityClassifyService;
import com.ztgm.mall.service.CommodityService;
import com.ztgm.mall.service.OptionService;
import com.ztgm.mall.util.Message;
import com.ztgm.mall.util.PageUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ztgm.mall.util.PageUtil.getPage;
import static com.ztgm.mall.util.PageUtil.packageTableData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 商品
 * 
 * @author zj
 * @date 2018年5月25日
 *
 */
@Controller
@RequestMapping("/manager/goods")
public class GoodsController {

	private Logger logger = Logger.getLogger(GoodsController.class);

	// 文件目录外网访问地址
	@Value("${HTTP_PATH}")
	private String FILE_SVR_HTTP_OUTER_PATH;

	@Autowired
	private CommodityService goodsService;

	@Autowired
	private BrandService barndService;

	@Autowired
	private AttributeService attributeService;

	@Autowired
	private OptionService optionService;

	@Autowired
	private CommodityClassifyService commodityClassifyService;

	@RequestMapping("/index")
	public String index(Map<String, Object> map) {

		map.put("httppath", FILE_SVR_HTTP_OUTER_PATH);

		// 品牌列表
		Brand bq = new Brand();
		List<Brand> brandlist = barndService.selectBrandList(bq);
		map.put("brandlist", brandlist);

		// 分类
		List<CommodityFirstClassify> typelist1 = commodityClassifyService.showTopUserfullClassify();
		map.put("typelist1", typelist1);

		Subject currentUser = SecurityUtils.getSubject();
		currentUser.getPrincipal();

		return "/backend/goods/gd/index";
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
	public String list(Commodity q, PageCondition pageCondition, HttpServletRequest request,
			HttpServletResponse response) {

		String resultString = "";

		Page page = PageUtil.getPage(pageCondition);
		List<Commodity> rst = goodsService.selectCommodityList(q);

		try {
			resultString = packageTableData(page, rst);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return resultString;// responseData(request, response, datastr);

	}

	@RequestMapping("/load")
	@ResponseBody
	public Commodity load(HttpServletRequest request) {
		String id = request.getParameter("id");

		Commodity p = goodsService.selectByPrimaryKey(id);

		return p;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Message delete(HttpServletRequest request) {
		String id = request.getParameter("id");
		// 删除实例，设置状态
		Message msg = new Message();

		try {
			int result = goodsService.deleteByPrimaryKey(id);
			if (result == 1) {
				msg.setBol(true);
			}
		} catch (Exception e) {
			if (e.getMessage().contains("a foreign key constraint fails")) {
				msg.setMessage("商品已被商品详情引用，不允许删除！");
			}
			msg.setBol(false);
		}

		return msg;
	}

	/**
	 * \ 编辑商品过程删除商品选项
	 * 
	 * @param request
	 * @return
	 * @author zj
	 * @date 2018年6月8日
	 */
	@RequestMapping("/deleteoption")
	@ResponseBody
	public Message deleteoption(HttpServletRequest request) {
		String id = request.getParameter("id");
		// 删除实例，设置状态
		Message msg = new Message();

		try {
			int result = goodsService.deleteOptionLogicalByPrimaryKey(id);
			if (result == 1) {
				msg.setBol(true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			msg.setBol(false);
		}

		return msg;
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(Commodity role, HttpServletRequest request) {
		JSONObject jsonObject = null;

		/// String menuids = request.getParameter("menuids");

		jsonObject = goodsService.insertSelective(role);

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
	// @Transactional
	public String saveOrUpdate(Commodity item, HttpServletRequest request) {
		JSONObject jsonObject = null;
		try {

			String intro = request.getParameter("introduction");
			item.setIntroduction(intro);

			// 图片
			List<CommondityImage> cimgs = new ArrayList<>();
			String imgs = request.getParameter("imgs");
			JSONArray jimgs = new JSONArray(imgs);
			for (int i = 0; i < jimgs.length(); i++) {
				CommondityImage cimage = new CommondityImage();
				cimage.setFileId(jimgs.optJSONObject(i).optString("id"));
				cimgs.add(cimage);
			}
			item.setCommondityImages(cimgs);

			int option_sort = 1;
			// 选项
			String options = request.getParameter("options");
			List<CommodityOption> coptions = new ArrayList<>();

			JSONArray joptions = new JSONArray(options);
			for (int i = 0; i < joptions.length(); i++) {
				CommodityOption cattr = new CommodityOption();
				cattr.setOptionDefId(joptions.optJSONObject(i).optString("id"));
				cattr.setValue(joptions.optJSONObject(i).optString("val"));
				cattr.setId(joptions.optJSONObject(i).optString("pid"));
				cattr.setSort(option_sort);
				coptions.add(cattr);
				option_sort++;
			}
			item.setCommodityOptions(coptions);

			// 属性
			String attrs = request.getParameter("attrs");
			List<CommodityAttribute> cattrs = new ArrayList<>();

			JSONArray jattrs = new JSONArray(attrs);
			for (int i = 0; i < jattrs.length(); i++) {
				CommodityAttribute cattr = new CommodityAttribute();
				cattr.setAttributeDefId(jattrs.optJSONObject(i).optString("id"));

				cattr.setValue(jattrs.optJSONObject(i).optString("val"));
				cattrs.add(cattr);
			}
			item.setCommodityAttributes(cattrs);

			// 附件
			List<CommondityAttach> cattachs = new ArrayList<>();
			String attachs = request.getParameter("attachs");
			JSONArray jattachs = new JSONArray(attachs);
			for (int i = 0; i < jattachs.length(); i++) {
				CommondityAttach cimage = new CommondityAttach();
				cimage.setFileId(jattachs.optJSONObject(i).optString("id"));
				cattachs.add(cimage);
			}
			item.setCommondityAttachs(cattachs);

			if (null == item.getId() || "".equals(item.getId())) {
				jsonObject = goodsService.insertSelective(item);
			} else {
				jsonObject = goodsService.updateByPrimaryKeySelective(item);
			}

		} catch (Exception e) {
			// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}

		return jsonObject.toString();
	}

	@RequestMapping("/update")
	@ResponseBody
	public String update(Commodity role, HttpServletRequest request) {
		JSONObject jsonObject = null;

		// String menuids = request.getParameter("menuids");
		jsonObject = goodsService.updateByPrimaryKeySelective(role);

		return jsonObject.toString();

	}

}
