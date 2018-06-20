package com.ztgm.mall.service;

import java.util.List;

import org.json.JSONObject;

import com.ztgm.mall.pojo.Commodity;
import com.ztgm.mall.pojo.CommodityInstance;


public interface CommodityDetailService {

	//instance
	int deleteByPrimaryKey(String id);


	JSONObject insertSelective(CommodityInstance record);

	CommodityInstance selectByPrimaryKey(String id);

	JSONObject updateByPrimaryKeySelective(CommodityInstance record) ;



	/**
	 * 条件查询
	 * 
	 * @param role
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	List<CommodityInstance> selectCommodityList(CommodityInstance role);
	

}
