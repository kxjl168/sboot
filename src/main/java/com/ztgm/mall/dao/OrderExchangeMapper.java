package com.ztgm.mall.dao;

import java.util.List;

import com.ztgm.mall.pojo.CommondityInstanceOptions;
import com.ztgm.mall.pojo.Order;
import com.ztgm.mall.pojo.OrderExchange;

public interface OrderExchangeMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderExchange record);

    int insertSelective(OrderExchange record);

    OrderExchange selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderExchange record);

    int updateByPrimaryKey(OrderExchange record);
    
    /**
   	 * 条件查询
   	 * 
   	 * @param role
   	 * @return
   	 * @author zj
   	 * @date 2018年5月9日
   	 */
   	List<OrderExchange> selectOrderExchangeList(OrderExchange role);
   	
   	
   	/**
  	 * 条件查询
  	 * 
  	 * @param role
  	 * @return
  	 * @author zj
  	 * @date 2018年5月9日
  	 */
  	List<OrderExchange> selectOrderExchangeByOrderId(String id);
}