package com.ztgm.mall.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.ztgm.mall.pojo.OptionDef;
import com.ztgm.mall.pojo.Order;
import com.ztgm.mall.pojo.OrderDetail;
import com.ztgm.mall.pojo.PageCondition;

/**
 * @author handsomeXiao
 * @date 2018/6/11 14:44
 */
public interface OrderService {

	
	OrderDetail selectDetailByPrimaryKey(String id);
	

	Order selectByPrimaryKey(String id);

	/**
	 * 列表返回
	 * @param order
	 * @param pageCondition
	 * @return
	 * @author zj
	 * @date 2018年6月14日
	 */
	public List<Order> showOrderList(Order order, PageCondition pageCondition);

    /**
     * 查询所有符合条件的订单
     * @param order
     * @param pageCondition
     * @return
     */
    String showOrders (Order order, PageCondition pageCondition);

    public JSONObject updateByPrimaryKeySelective(Order record);

    /**
     * 订单统计
     * @param startTime
     * @param endTime
     * @param orderStatus
     * @param customName
     * @author zhangyong
     * @return
     */
    List<Map> selectOrderStaticsList (String startTime, String endTime, int orderStatus, String customName);
}
