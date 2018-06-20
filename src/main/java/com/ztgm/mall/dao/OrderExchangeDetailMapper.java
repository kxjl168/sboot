package com.ztgm.mall.dao;

import java.util.List;

import com.ztgm.mall.pojo.OrderExchangeDetail;

public interface OrderExchangeDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderExchangeDetail record);

    int insertSelective(OrderExchangeDetail record);

    OrderExchangeDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderExchangeDetail record);

    int updateByPrimaryKeyWithBLOBs(OrderExchangeDetail record);

    int updateByPrimaryKey(OrderExchangeDetail record);
    
    
    List<OrderExchangeDetail> selectDetailListById(String id);

    
}