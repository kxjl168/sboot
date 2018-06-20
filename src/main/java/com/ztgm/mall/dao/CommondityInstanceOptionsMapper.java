package com.ztgm.mall.dao;

import java.util.List;
import java.util.Map;

import com.ztgm.mall.pojo.CommodityInstance;
import com.ztgm.mall.pojo.CommondityInstanceOptions;
import org.apache.ibatis.annotations.Param;

public interface CommondityInstanceOptionsMapper {
    int deleteByPrimaryKey(String id);

    int insert(CommondityInstanceOptions record);

    int insertSelective(CommondityInstanceOptions record);

    CommondityInstanceOptions selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CommondityInstanceOptions record);

    int updateByPrimaryKey(CommondityInstanceOptions record);
    
    
    
   	/**
      	 * 条件查询
      	 * 
      	 * @param role
      	 * @return
      	 * @author zj
      	 * @date 2018年5月9日
      	 */
      	List<CommondityInstanceOptions> selectCommodityOptionListByInstanceId(String id);

	/**
	 *
	 * @param map 查询条件
	 * @return
	 */
	List getOptionsByClassify(Map map);

	/**
	 * 查询选项值
	 * @param map 查询条件
	 * @return
	 */
	List getOptionValues(Map map);
}