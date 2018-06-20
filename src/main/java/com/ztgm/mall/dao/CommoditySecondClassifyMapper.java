package com.ztgm.mall.dao;

import com.ztgm.mall.pojo.CommoditySecondClassify;

import java.util.List;
import java.util.Map;

public interface CommoditySecondClassifyMapper {
    int deleteByPrimaryKey(String id);

    int insert(CommoditySecondClassify record);

    int insertSelective(CommoditySecondClassify record);

    CommoditySecondClassify selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CommoditySecondClassify record);

    int updateByPrimaryKey(CommoditySecondClassify record);


    List<CommoditySecondClassify> selectByConditions(Map conditions);
}