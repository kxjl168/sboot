package com.ztgm.mall.controller.WebController.front;

import com.github.pagehelper.Page;
import com.ztgm.mall.pojo.Brand;
import com.ztgm.mall.pojo.Commodity;
import com.ztgm.mall.pojo.CommodityAttribute;
import com.ztgm.mall.pojo.CommodityInstance;
import com.ztgm.mall.pojo.CommondityInstanceOptions;
import com.ztgm.mall.pojo.Estimate;
import com.ztgm.mall.pojo.MUser;
import com.ztgm.mall.pojo.Order;
import com.ztgm.mall.pojo.OrderDetail;
import com.ztgm.mall.pojo.OrderExchange;
import com.ztgm.mall.pojo.PageCondition;
import com.ztgm.mall.service.CommodityService;
import com.ztgm.mall.service.EstimateService;
import com.ztgm.mall.service.ExchangeService;
import com.ztgm.mall.service.OrderService;
import com.ztgm.mall.util.DateUtil;
import com.ztgm.mall.util.PageUtil;
import com.ztgm.mall.util.UserIDUtil;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ztgm.mall.util.PageUtil.packageTableData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 售后，退换货/退款
 * 
 * @author zj
 * @date 2018年6月19日
 *
 */
@Controller
@PropertySource("classpath:project.properties")
@RequestMapping("/userCenter/shou")
public class UserExChangeController {

	@Autowired
	private OrderService orderService;

	@Value("${HTTP_PATH}")
	private String FILE_SVR_HTTP_OUTER_PATH;

	@Autowired
	private EstimateService estimateService;

	@Autowired
	private ExchangeService exchangeService;

	@RequestMapping("/list")
	@ResponseBody
	public String list(OrderExchange q, PageCondition pageCondition, HttpServletRequest request,
			HttpServletResponse response) {

		String resultString = "";

		Page page = PageUtil.getPage(pageCondition);
		List<OrderExchange> rst = exchangeService.selectOrderExchangeList(q);

		try {
			resultString = packageTableData(page, rst);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return resultString;// responseData(request, response, datastr);

	}

	/**
	 * 选择售后类型
	 * 
	 * @param item
	 * @param maps
	 * @return
	 * @author zj
	 * @date 2018年6月19日
	 */
	@RequestMapping("/select")
	public String select(OrderDetail item, Map<String, Object> maps) {
		OrderDetail orderDetail = orderService.selectDetailByPrimaryKey(item.getId());

		maps.put("order", orderDetail);
		maps.put("httppath", FILE_SVR_HTTP_OUTER_PATH);

		Subject currentUser = SecurityUtils.getSubject();
		currentUser.getPrincipal();

		MUser muser = new MUser();
		// TODO
		muser.setId("1");
		maps.put("cuserId", muser.getId());

		if (orderDetail != null)
			maps.put("goods", orderDetail.getCommodityInstance());
		else
			maps.put("goods", "");

		return "/frontend/user/goodsshou";
	}

	@RequestMapping("/exchange")
	public String exchange(OrderExchange item, Map<String, Object> maps, HttpServletRequest request) {
		OrderDetail orderDetail = orderService.selectDetailByPrimaryKey(item.getOrderDetailId());

		maps.put("order", orderDetail);
		maps.put("httppath", FILE_SVR_HTTP_OUTER_PATH);

		Subject currentUser = SecurityUtils.getSubject();
		currentUser.getPrincipal();

		MUser muser = new MUser();
		// TODO
		muser.setId("1");
		maps.put("cuserId", muser.getId());

		maps.put("type", request.getParameter("type"));

		if (orderDetail != null)
			maps.put("goods", orderDetail.getCommodityInstance());
		else
			maps.put("goods", "");

		return "/frontend/user/goodsexchange";
	}

	/**
	 * 退换货管理列表
	 * @param item
	 * @param maps
	 * @return
	 * @author zj
	 * @date 2018年6月19日
	 */
	@RequestMapping("/index")
	public String index(Order item, Map<String, Object> maps) {

		return "/frontend/user/exchangeCenter";
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(OrderExchange item, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = null;

		try {

			String userId = UserIDUtil.getUserID(SecurityUtils.getSubject(), response);

			item.setUserId(userId);

			jsonObject = exchangeService.insertSelective(item);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return jsonObject.toString();

	}
	

	@RequestMapping("/update")
	@ResponseBody
	public String update(OrderExchange item, HttpServletRequest request) {
		JSONObject jsonObject = null;

		// String menuids = request.getParameter("menuids");
		jsonObject = exchangeService.updateByPrimaryKeySelective(item);

		return jsonObject.toString();

	}


}
