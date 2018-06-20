package com.ztgm.mall.controller.WebController.front;

import com.ztgm.mall.pojo.Brand;
import com.ztgm.mall.pojo.Commodity;
import com.ztgm.mall.pojo.CommodityAttribute;
import com.ztgm.mall.pojo.CommodityInstance;
import com.ztgm.mall.pojo.CommondityInstanceOptions;
import com.ztgm.mall.pojo.Estimate;
import com.ztgm.mall.pojo.MUser;
import com.ztgm.mall.pojo.Order;
import com.ztgm.mall.pojo.OrderDetail;
import com.ztgm.mall.pojo.PageCondition;
import com.ztgm.mall.service.CommodityService;
import com.ztgm.mall.service.EstimateService;
import com.ztgm.mall.service.OrderService;
import com.ztgm.mall.util.DateUtil;

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
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 用户评论管理
 * 
 * @author zj
 * @date 2018年6月13日
 *
 */
@Controller
@PropertySource("classpath:project.properties")
@RequestMapping("/userCenter/commet")
public class UserCommetController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CommodityService goodsService;

	@Value("${HTTP_PATH}")
	private String FILE_SVR_HTTP_OUTER_PATH;

	@Autowired
	private EstimateService estimateService;

	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		map.put("httppath", FILE_SVR_HTTP_OUTER_PATH);
		return "/frontend/user/commetCenter";
	}

	@RequestMapping("/detail")
	public String detail(Order item, Map<String, Object> maps) {

		Order order = orderService.selectByPrimaryKey(item.getId());

		maps.put("order", order);
		maps.put("httppath", FILE_SVR_HTTP_OUTER_PATH);
		
		
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.getPrincipal();

		MUser muser = new MUser();
		// TODO
		muser.setId("1");
		maps.put("cuserId", muser.getId());
		
		
		
		if (order.getDetails().size() > 0)
			maps.put("goods", order.getDetails().get(0).getCommodityInstance());
		else
			maps.put("goods", "");

		return "/frontend/user/goodsCommet";
	}

	@RequestMapping("/update")
	@ResponseBody
	public String update(Order item, HttpServletRequest request) {
		JSONObject jsonObject = null;

		// String menuids = request.getParameter("menuids");
		jsonObject = orderService.updateByPrimaryKeySelective(item);

		return jsonObject.toString();

	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request) {
		JSONObject jsonObject = null;

		String data = request.getParameter("data");
		try {
			
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.getPrincipal();

			MUser muser = new MUser();
			// TODO
			muser.setId("1");
			

			JSONArray jarray = new JSONArray(data);
			List<Estimate> commets = new ArrayList<>();

			for (int i = 0; i < jarray.length(); i++) {
				Estimate cattr = new Estimate();
				cattr.setCommodityId(jarray.optJSONObject(i).optString("gid"));
				cattr.setCommodityInstanceId(jarray.optJSONObject(i).optString("iid"));
				cattr.setOrderId(jarray.optJSONObject(i).optString("oid"));
				cattr.setOrderDetailId(jarray.optJSONObject(i).optString("odid"));
				cattr.setValue(jarray.optJSONObject(i).optString("value"));
				cattr.setStars(jarray.optJSONObject(i).optInt("star"));
				cattr.setStatus(1);
				cattr.setUserId(muser.getId());
				cattr.setIsanony(jarray.optJSONObject(i).optInt("isanony"));

				commets.add(cattr);
			}

			/// String menuids = request.getParameter("menuids");

			jsonObject = estimateService.insertSelective(commets);
			
			
			//更新订单状态为已评价
			Order o=new Order();
			o.setId(commets.get(0).getOrderId());
			o.setState(8);
			orderService.updateByPrimaryKeySelective(o);
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return jsonObject.toString();

	}

	@RequestMapping("/showOrders")
	@ResponseBody
	public String showOrders(Order order, PageCondition pageCondition) {

		Subject currentUser = SecurityUtils.getSubject();
		currentUser.getPrincipal();

		MUser muser = new MUser();
		// TODO
		muser.setId("1");

		// 获取前台用户

		// order.setUserId(muser.getId());

		String resultString = orderService.showOrders(order, pageCondition);
		return resultString;
	}
}
