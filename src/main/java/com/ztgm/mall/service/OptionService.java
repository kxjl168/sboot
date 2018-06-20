package com.ztgm.mall.service;

import java.util.List;

import org.json.JSONObject;

import com.ztgm.mall.pojo.OptionDef;


public interface OptionService {

	int deleteByPrimaryKey(String id);

	int insert(OptionDef record);

	JSONObject insertSelective(OptionDef record);

	OptionDef selectByPrimaryKey(String id);

	JSONObject updateByPrimaryKeySelective(OptionDef record);

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
