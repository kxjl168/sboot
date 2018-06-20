package com.ztgm.mall.dao;

import java.util.List;

import com.ztgm.mall.pojo.Estimate;
import com.ztgm.mall.pojo.UserQuestion;

public interface EstimateMapper {
	/**
	 * 按条件查询评价
	 * @param estimate
	 * @return
	 */
   	List<Estimate> showEstimate (Estimate estimate);


    /**
     * 根据id删除评价
     * @param estimate
     * @return
     */
    int deleteEstimate(Estimate estimate);
    
    int deleteByPrimaryKey(String id);

    int insert(Estimate record);

    int insertSelective(Estimate record);

    Estimate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Estimate record);

    int updateByPrimaryKey(Estimate record);
  
}