package com.ztgm.mall.dao;

import java.util.List;
import java.util.Map;

import com.ztgm.mall.pojo.CommodityOption;
import com.ztgm.mall.pojo.CommodityOption;

public interface CommodityOptionMapper {
    int deleteByPrimaryKey(String id);

    int insert(CommodityOption record);

    int insertSelective(CommodityOption record);

    CommodityOption selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CommodityOption record);

    int updateByPrimaryKey(CommodityOption record);
    
    
    
    /**
     * 删除商品下的关系，更新，先删除再添加
     * @param id
     * @return
     * @author zj
     * @date 2018年6月4日
     */
    int deleteByGoodsId(String id);
   
    
    
/*    <select id="selectSameCommodityOptionList" parameterType="java.util.Map" resultMap="BaseResultMap">
   select * 
     from mall_commodity_option instance 
     where commondity_id=#{commondity_id} and option_def_id=#{option_def_id}   order by sort
   </select>*/
     /**
    	 * 查询关联-查询同属一个商品及一个选项的所有选项值
    	 * 
    	 * @param role
    	 * @return
    	 * @author zj
    	 * @date 2018年5月9日
    	 */
    	List<CommodityOption> selectSameCommodityOptionList(Map map);
     
     
     
    
    /**
   	 * 查询关联-distinct 选项
   	 * 
   	 * @param role
   	 * @return
   	 * @author zj
   	 * @date 2018年5月9日
   	 */
   	List<CommodityOption> selectDistinctCommodityOptionListByCommodityId(String id);
    
    
    
    /**
   	 * 查询关联
   	 * 
   	 * @param role
   	 * @return
   	 * @author zj
   	 * @date 2018年5月9日
   	 */
   	List<CommodityOption> selectCommodityOptionListByCommodityId(String id);
   	
    /**
   	 * 条件查询
   	 * 
   	 * @param role
   	 * @return
   	 * @author zj
   	 * @date 2018年5月9日
   	 */
   	List<CommodityOption> selectCommodityOptionList(CommodityOption role);
}