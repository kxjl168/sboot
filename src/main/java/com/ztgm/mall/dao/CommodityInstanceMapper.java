package com.ztgm.mall.dao;

import java.util.List;

import com.ztgm.mall.pojo.Commodity;
import com.ztgm.mall.pojo.CommodityInstance;
import org.apache.ibatis.annotations.Param;

public interface CommodityInstanceMapper {
    int deleteByPrimaryKey(String id);

    int insert(CommodityInstance record);

    int insertSelective(CommodityInstance record);

    CommodityInstance selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CommodityInstance record);

    int updateByPrimaryKey(CommodityInstance record);

    /**
     * 条件查询
     *
     * @param role
     * @return
     * @author zj
     * @date 2018年5月9日
     */
    List<CommodityInstance> selectCommodityInstanceList(CommodityInstance role);


    /**
     * 条件查询
     *
     * @param firstClassify 一级分类id
     * @return
     * @author 李飞
     * @date 2018年6月4日
     */
    List<CommodityInstance> getTopCommodityInstanceListByFirstClassify(@Param("firstClassify") String firstClassify);

    /**
     * 条件查询
     *
     * @param level      分类级别
     * @param classifyId 分类Id
     * @param level      分类级别
     * @return
     * @author 李飞
     * @date 2018年6月4日
     */
    List<CommodityInstance> selectCommodityListByClassify(@Param("level") Integer level,
                                                          @Param("sort") Integer sort,
                                                          @Param("brand") String brand,
                                                          @Param("option") String option,
                                                          @Param("classifyId") String classifyId);
}