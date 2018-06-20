package com.ztgm.mall.dao;

import com.ztgm.mall.pojo.DeliveryAddress;

public interface DeliveryAddressMapper {
    int deleteByPrimaryKey(String id);

    int insert(DeliveryAddress record);

    int insertSelective(DeliveryAddress record);

    DeliveryAddress selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DeliveryAddress record);

    int updateByPrimaryKey(DeliveryAddress record);
}