package com.ztgm.mall.controller.AppController;

import com.github.pagehelper.Page;
import com.ztgm.mall.pojo.Estimate;
import com.ztgm.mall.pojo.MUser;
import com.ztgm.mall.pojo.Order;
import com.ztgm.mall.pojo.PageCondition;
import com.ztgm.mall.service.EstimateService;
import com.ztgm.mall.service.OrderService;
import com.ztgm.mall.util.AppResult;
import com.ztgm.mall.util.AppResultUtil;
import com.ztgm.mall.util.PageUtil;
import com.ztgm.mall.util.TokenUtil;
import com.ztgm.mall.util.UserIDUtil;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ztgm.mall.util.PageUtil.getPage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping("/interface/order")
public class MobileOrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private EstimateService estimateService;

	private Logger logger = Logger.getLogger(MobileOrderController.class);

	/**
	 * 查询订单接口
	 * 
	 * @param goodName
	 * @param startTime
	 * @param endTime
	 * @param offset
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 * @author zj
	 * @date 2018年6月20日
	 */
	@ResponseBody
	@RequestMapping(value = "/showOrders")

	public AppResult showOrders(String goodName, String startTime, String endTime, Integer state, String offset,
			String pageSize, HttpServletRequest request, HttpServletResponse response) {
		try {

			MUser user = TokenUtil.getCurrentUser();

			Order order = new Order();
			order.setGoodName(goodName);
			order.setStartTime(startTime);
			order.setEndTime(endTime);
			order.setUserId(user.getId());
			order.setState(state);

			PageCondition pageCondition = new PageCondition();
			pageCondition.setOffset(offset);
			pageCondition.setPageSize(pageSize);

			String jstr= orderService.showOrders(order, pageCondition);
			
			return AppResultUtil.success(jstr);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return AppResultUtil.fail();
		}
	}

	/**
	 * 更新订单状态/ 删除订单/ 确认收货
	 * 
	 * @param item
	 * @param request
	 * @return
	 * @author zj
	 * @date 2018年6月20日
	 */
	@RequestMapping("/update")
	@ResponseBody
	public AppResult update(String id, Integer state, HttpServletRequest request) {
		JSONObject jsonObject = null;

		Order item = new Order();
		item.setId(id);
		item.setState(state);
		// String menuids = request.getParameter("menuids");
		jsonObject = orderService.updateByPrimaryKeySelective(item);

		return AppResultUtil.success(jsonObject.toString());
	}

	/**
	 * 评价订单
	 * 
	 * @param request
	 * @return
	 * @author zj
	 * @date 2018年6月20日
	 */
	@RequestMapping("/commet")
	@ResponseBody
	public AppResult commet(HttpServletRequest request) {
		JSONObject jsonObject = null;

		String data = request.getParameter("data");
		try {

			MUser muser = TokenUtil.getCurrentUser();

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
			jsonObject = estimateService.insertSelective(commets);

			// 更新订单状态为已评价
			Order o = new Order();
			o.setId(commets.get(0).getOrderId());
			o.setState(8);
			orderService.updateByPrimaryKeySelective(o);

		} catch (Exception e) {
			e.printStackTrace();
			return AppResultUtil.fail();
		}

		return AppResultUtil.success(jsonObject.toString());

	}

}
