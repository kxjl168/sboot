package com.ztgm.mall.dao;

import java.util.List;

import com.ztgm.mall.pojo.OptionDef;

public interface OptionDefMapper {
    int deleteByPrimaryKey(String id);

    int insert(OptionDef record);

    int insertSelective(OptionDef record);

    OptionDef selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OptionDef record);

    int updateByPrimaryKey(OptionDef record);
    
	/**
	 * 条件查询
	 * 
	 * @param role
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	List<OptionDef> selectOptionDefList(OptionDef role);

}