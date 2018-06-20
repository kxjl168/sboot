package com.ztgm.mall.dao;

import java.util.List;

import com.ztgm.mall.pojo.CommondityAttach;
import com.ztgm.mall.pojo.CommondityImage;

public interface CommondityAttachMapper {
    int deleteByPrimaryKey(String id);

    int insert(CommondityAttach record);

    int insertSelective(CommondityAttach record);

    CommondityAttach selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CommondityAttach record);

    int updateByPrimaryKey(CommondityAttach record);
    
    
    /**
     * 删除商品下的关系，更新，先删除再添加
     * @param id
     * @return
     * @author zj
     * @date 2018年6月4日
     */
    int deleteByGoodsId(String id);
   
    
    /**
   	 * 查询关联
   	 * 
   	 * @param role
   	 * @return
   	 * @author zj
   	 * @date 2018年5月9日
   	 */
   	List<CommondityAttach> selectCommondityAttachListByCommodityId(String id);
   	
}