package com.ztgm.mall.dao;

import com.ztgm.mall.pojo.CommodityFirstClassify;
import com.ztgm.mall.pojo.SvrFileInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author handsomeXiao
 * @date 2018/5/30 16:34
 */
public interface CommodityFirstClassifyMapper {


	/**
	 * 根据级别和父id，获取父分类下的小分类
	 * @param level
	 * @param pid
	 * @return
	 * @author zj
	 * @date 2018年6月11日
	 */
	 List<CommodityFirstClassify>	getClassifyByPidAndLevel(@Param("level")String level, @Param("pid")String pid);
	
    List<CommodityFirstClassify> showAllClassify();

    /**
     * 获取启用状态的一级分类
     * @return
     * @author zj
     * @date 2018年6月11日
     */
    List<CommodityFirstClassify>     showTopUserfullClassify();


    
    List getAllTopClassify(@Param("level")String level);

    List<CommodityFirstClassify> getTopClassify(@Param("limit")Integer limit);

    Map getClassifyById(@Param("level")Integer level, @Param("id")String id);
    /**
     * 根据条件更新分类
     * @param commodityFirstClassify
     * @param level
     * @return
     */
    Integer updateByCondition(@Param("firstClassify") CommodityFirstClassify commodityFirstClassify,@Param("level") String level, @Param("topClassify") String topClassify);

    /**
     * 根据条件插入分类
     * @param commodityFirstClassify
     * @param level
     * @param topClassify
     * @return
     */
    Integer insertByCondition(@Param("firstClassify") CommodityFirstClassify commodityFirstClassify,@Param("level") String level, @Param("topClassify") String topClassify);

    /**
     * 删除分类（其实是更新）
     * @param id
     * @param level
     * @return
     */
    Integer deleteClassify(@Param("id") String id, @Param("level") String level);

    /**
     * 删除分类前查看分类是否已经被关联
     * @param id
     * @param level
     * @return
     */
    Integer getRelationCount (@Param("id") String id, @Param("level") String level);

    /**
     * 更新图片id
     * @param id
     * @param type
     * @param pictureId
     * @return
     */
    Integer updatePictureInfo(@Param("id") String id, @Param("type") String type, @Param("pictureId") String pictureId);

    /**
     * 根据主键查找分类
     * @param id
     * @return
     */
    CommodityFirstClassify selectByPrimaryKey (String id);

    /**
     * 查询下级分类
     * @param level 分类级别
     * @param id 分类id
     * @return
     */
    List<Map> getSubClassifies(@Param("level") Integer level,@Param("id") String id);
}
