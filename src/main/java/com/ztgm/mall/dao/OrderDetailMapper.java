package com.ztgm.mall.dao;

import com.ztgm.mall.pojo.OrderDetail;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
    
    
    /**
     * 查询一个订单下的订单详情
     * @param id
     * @return
     * @author zj
     * @date 2018年6月14日
     */
    OrderDetail selectOrderDetailList(String id);
}