package com.ztgm.mall.dao;

import java.util.List;

import com.ztgm.mall.pojo.Brand;
import com.ztgm.mall.pojo.OptionDef;
import org.apache.ibatis.annotations.Param;

public interface BrandMapper {
    int deleteByPrimaryKey(String id);

    int insert(Brand record);

    int insertSelective(Brand record);

    Brand selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Brand record);

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
	 * 查询分类所有品牌
	 * @param level 分类等级
	 * @param id 分类id
	 * @return
	 * @author 李飞
	 * @date 2018年6月7日
	 */
	List<Brand> getClassifyBrands(@Param("level") Integer level, @Param("classifyId")String classifyId);
}