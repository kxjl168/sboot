package com.ztgm.mall.service;

import com.ztgm.mall.pojo.CommodityFirstClassify;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author handsomeXiao
 * @date 2018/5/30 16:24
 */
public interface CommodityClassifyService {

	/**
	 * 根据级别和父id，获取父分类下的小分类
	 * @param level
	 * @param pid
	 * @return
	 * @author zj
	 * @date 2018年6月11日
	 */
	 List<CommodityFirstClassify>	getClassifyByPidAndLevel(String level, String pid);
	 /**
     * 获取启用状态的一级分类
     * @return
     * @author zj
     * @date 2018年6月11日
     */
	 public List<CommodityFirstClassify> showTopUserfullClassify();
	 
    List<CommodityFirstClassify> showAllClassify();

    List getAllTopClassify(String level);

    Map getClassifyById(Integer level, String id);

    List<CommodityFirstClassify> getTopClassify();

    /**
     * 更新或插入分类
     * @param parameter
     * @return
     */
    Integer classifyOperate(Map<String, String> parameter);

    /**
     * 删除分类
     * @param parameter
     * @return
     */
    Integer deleteClassify(Map<String, String> parameter);

    /**
     * 分类图片上传
     * @param id
     * @param type
     * @param file
     * @return
     */
    String classifyPictureUpload(String id, String type, MultipartFile file);

    /**
     * 查询下级分类
     * @param level 分类级别
     * @param id 分类id
     * @return
     */
    List getSubClassifies(Integer level, String id);
}
