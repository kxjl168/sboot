package com.ztgm.mall.dao;

import com.ztgm.mall.pojo.CommodityThirdClassify;

public interface CommodityThirdClassifyMapper {
    int deleteByPrimaryKey(String id);

    int insert(CommodityThirdClassify record);

    int insertSelective(CommodityThirdClassify record);

    CommodityThirdClassify selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CommodityThirdClassify record);

    int updateByPrimaryKey(CommodityThirdClassify record);
}