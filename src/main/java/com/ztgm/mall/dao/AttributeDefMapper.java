package com.ztgm.mall.dao;

import java.util.List;

import com.ztgm.mall.pojo.AttributeDef;
import com.ztgm.mall.pojo.OptionDef;

public interface AttributeDefMapper {
    int deleteByPrimaryKey(String id);

    int insert(AttributeDef record);

    int insertSelective(AttributeDef record);

    AttributeDef selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AttributeDef record);

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