package com.ztgm.mall.service;

import java.util.List;

import org.json.JSONObject;

import com.ztgm.mall.pojo.Brand;


public interface BrandService {

	int deleteByPrimaryKey(String id);

	int insert(Brand record);

	JSONObject insertSelective(Brand record);

	Brand selectByPrimaryKey(String id);

	JSONObject updateByPrimaryKeySelective(Brand record);

	int updateByPrimaryKey(Brand record);

	/**
	 * 条件查询
	 * 
	 * @param role
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	List<Brand> selectBrandList(Brand role);


	/**
	 * 条件查询
	 *
	 * @param level 分类级别
	 * @param classifyId 分类id
	 * @return
	 * @author 李飞
	 * @date 2018年6月7日
	 */
	List<Brand> getClassifyBrands(Integer level,String classifyId);

}
