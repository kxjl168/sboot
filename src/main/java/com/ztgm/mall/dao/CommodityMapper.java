package com.ztgm.mall.dao;

import java.util.List;

import com.ztgm.mall.pojo.Commodity;
import com.ztgm.mall.pojo.Commodity;

public interface CommodityMapper {
    int deleteByPrimaryKey(String id);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKeyWithBLOBs(Commodity record);

    int updateByPrimaryKey(Commodity record);
    
    
   	/**
   	 * 条件查询
   	 * 
   	 * @param role
   	 * @return
   	 * @author zj
   	 * @date 2018年5月9日
   	 */
   	List<Commodity> selectCommodityList(Commodity role);
}