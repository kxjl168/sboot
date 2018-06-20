package com.ztgm.mall.dao;

import java.util.List;

import com.ztgm.mall.pojo.Receipt;
import com.ztgm.mall.pojo.Receipt;

public interface ReceiptMapper {
    int deleteByPrimaryKey(String id);

    int insert(Receipt record);

    int insertSelective(Receipt record);

    Receipt selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Receipt record);

    int updateByPrimaryKey(Receipt record);
    
	/**
   	 * 条件查询
   	 * 
   	 * @param role
   	 * @return
   	 * @author zj
   	 * @date 2018年5月9日
   	 */
   	List<Receipt> selectReceiptList(Receipt role);
}