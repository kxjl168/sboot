package com.ztgm.mall.dao;

import com.ztgm.mall.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    /**
     * 查询订单列表
     *
     * @param order
     * @return
     */
    List<Order> showOrders(Order order);

    /**
     * 订单统计
     *
     * @param startTime
     * @param endTime
     * @param orderStatus
     * @param customName
     * @return
     * @author zhangyong
     */
    List<Map> selectOrderStaticsList(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("orderStatus") int orderStatus, @Param("customName") String customName);
}