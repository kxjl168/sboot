package com.ztgm.mall.dao;

import java.util.List;


import com.ztgm.mall.pojo.CommondityImage;

public interface CommondityImageMapper {
    int deleteByPrimaryKey(String id);

    int insert(CommondityImage record);

    int insertSelective(CommondityImage record);

    CommondityImage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CommondityImage record);

    int updateByPrimaryKey(CommondityImage record);
    
    
    /**
     * 删除商品下的-图片/文件关系，更新，先删除再添加
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
   	List<CommondityImage> selectCommondityImageListByCommodityId(String id);
   	
   /* *//**
   	 * 条件查询
   	 * 
   	 * @param role
   	 * @return
   	 * @author zj
   	 * @date 2018年5月9日
   	 *//*
   	List<CommondityImage> selectCommondityImageList(CommondityImage role);*/
}