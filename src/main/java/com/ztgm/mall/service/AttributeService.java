package com.ztgm.mall.service;

import java.util.List;

import org.json.JSONObject;

import com.ztgm.mall.pojo.AttributeDef;


public interface AttributeService {

	int deleteByPrimaryKey(String id);

	int insert(AttributeDef record);

	JSONObject insertSelective(AttributeDef record);

	AttributeDef selectByPrimaryKey(String id);

	JSONObject updateByPrimaryKeySelective(AttributeDef record);

	int updateByPrimaryKey(AttributeDef record);

	/**
	 * 条件查询
	 * 
	 * @param role
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	List<AttributeDef> selectAttributeDefList(AttributeDef role);


}
