package com.ztgm.mall.service;

import java.util.List;

import com.ztgm.mall.pojo.PageCondition;
import org.json.JSONObject;

import com.ztgm.mall.pojo.Commodity;
import com.ztgm.mall.pojo.OrderExchange;;


public interface ExchangeService {


  
    int deleteByPrimaryKey(String id);


	JSONObject insertSelective(OrderExchange record);

	OrderExchange selectByPrimaryKey(String id);

	JSONObject updateByPrimaryKeySelective(OrderExchange record);

	/**
	 * 条件查询
	 * 
	 * @param role
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	List<OrderExchange> selectOrderExchangeList(OrderExchange role);

}
