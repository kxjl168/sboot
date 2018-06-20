package com.ztgm.mall.service;

import java.util.List;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;

import com.ztgm.mall.pojo.Commodity;
import com.ztgm.mall.pojo.CommodityInstance;


public interface CommodityService {

	//instance
	int deleteByPrimaryKey(String id);

	int insert(Commodity record);

	JSONObject insertSelective(Commodity record);

	Commodity selectByPrimaryKey(String id);

	JSONObject updateByPrimaryKeySelective(Commodity record) ;

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

	/**
	 * 条件查询
	 *
	 * @param level 分类级别
	 * @param classifyId 分类id
	 * @return
	 * @author 李飞
	 * @date 2018年6月6日
	 */
	Page<CommodityInstance> selectCommodityListByClassify(Integer level,Integer sort,Integer page,String brand ,String option ,String classifyId, Integer limit);
	
	
	
	
	//instance
	/**
	 * 逻辑删除商品选项
	 * @param id
	 * @return
	 * @author zj
	 * @date 2018年6月8日
	 */
	int deleteOptionLogicalByPrimaryKey(String id);


	/**
	 * 根据商品分类查找商品选项
	 * @param level
	 * @param id
	 * @return
	 */
    List getOptionsByClassify(Integer level, String id);
}
