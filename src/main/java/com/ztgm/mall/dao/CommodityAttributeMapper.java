package com.ztgm.mall.dao;

import java.util.List;

import com.ztgm.mall.pojo.CommodityAttribute;
import com.ztgm.mall.pojo.CommodityInstance;

public interface CommodityAttributeMapper {
    int deleteByPrimaryKey(String id);

    int insert(CommodityAttribute record);

    int insertSelective(CommodityAttribute record);

    CommodityAttribute selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CommodityAttribute record);

    int updateByPrimaryKey(CommodityAttribute record);
    
    
    /**
     * 删除商品下的关系，更新，先删除再添加
     * @param id
     * @return
     * @author zj
     * @date 2018年6月4日
     */
    int deleteByGoodsId(String id);
    
    /**
   	 * 查询商品下的条件查询
   	 * 
   	 * @param role
   	 * @return
   	 * @author zj
   	 * @date 2018年5月9日
   	 */
   	List<CommodityAttribute> selectCommodityAttributeListByCommodityId(String id);
   	
    /**
   	 * 条件查询
   	 * 
   	 * @param role
   	 * @return
   	 * @author zj
   	 * @date 2018年5月9日
   	 */
   	List<CommodityAttribute> selectCommodityAttributeList(CommodityAttribute role);
}