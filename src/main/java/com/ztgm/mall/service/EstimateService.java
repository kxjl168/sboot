package com.ztgm.mall.service;

import java.util.List;

import com.ztgm.mall.pojo.PageCondition;
import org.json.JSONObject;

import com.ztgm.mall.pojo.Estimate;


public interface EstimateService {

    /**
     * 按条件查询评价
     * @param estimate
     * @param pageCondition
     * @return
     */
    String showEstimate(Estimate estimate, PageCondition pageCondition);

    /**
     * 根据id删除评价
     * @param estimate
     * @return
     */
    int deleteEstimate(Estimate estimate);
    
    int deleteByPrimaryKey(String id);


	JSONObject insertSelective(Estimate record);

	Estimate selectByPrimaryKey(String id);

	JSONObject updateByPrimaryKeySelective(Estimate record);

	int updateByPrimaryKey(Estimate record);

	/**
	 * 批量订单评论
	 * @param records
	 * @return
	 * @author zj
	 * @date 2018年6月15日
	 */
	public JSONObject insertSelective(List<Estimate> records) ;
}
