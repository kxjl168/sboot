package com.ztgm.mall.service.impl;

import com.github.pagehelper.Page;
import com.ztgm.mall.dao.OrderDetailMapper;
import com.ztgm.mall.dao.OrderMapper;
import com.ztgm.mall.pojo.Brand;
import com.ztgm.mall.pojo.Order;
import com.ztgm.mall.pojo.OrderDetail;
import com.ztgm.mall.pojo.PageCondition;
import com.ztgm.mall.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.ztgm.mall.util.PageUtil.getPage;
import static com.ztgm.mall.util.PageUtil.packageTableData;

/**
 * @author handsomeXiao
 * @date 2018/6/11 14:45
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderDetailMapper orderDetailMapper;

	public Order selectByPrimaryKey(String id) {
		return orderMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public String showOrders(Order order, PageCondition pageCondition) {
		String resultString = "";
		String offset = pageCondition.getOffset();
		String pageSize = pageCondition.getPageSize();
		Page page = getPage(offset, pageSize);
		List<Order> orderList = orderMapper.showOrders(order);
		try {
			resultString = packageTableData(page, orderList);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return resultString;
	}

	@Override
	public List<Order> showOrderList(Order order, PageCondition pageCondition) {
		String resultString = "";
		String offset = pageCondition.getOffset();
		String pageSize = pageCondition.getPageSize();
		Page page = getPage(offset, pageSize);
		return orderMapper.showOrders(order);

	}
	
	
	public OrderDetail selectDetailByPrimaryKey(String id) {
		return orderDetailMapper.selectByPrimaryKey(id);
	}
	
	
	
	public JSONObject updateByPrimaryKeySelective(Order record) {

		JSONObject rtn = new JSONObject();

		try {
			orderMapper.updateByPrimaryKeySelective(record);
			
			rtn.put("result", true);

			return rtn;
		} catch (Exception e) {

			// logger.error("更新出错", e);
			try {
				rtn.put("result", false);
				rtn.put("message", "更新出错");
			} catch (Exception e2) {
				// TODO: handle exception
			}
			return rtn;

		}

	}


    @Override
    public List<Map> selectOrderStaticsList(String startTime, String endTime, int orderStatus, String customName) {
        return orderMapper.selectOrderStaticsList(startTime,endTime,orderStatus,customName);
    }
}
