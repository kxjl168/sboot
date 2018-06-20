package com.ztgm.mall.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author handsomeXiao
 * @date 2018/5/30 16:14
 */
@Data
public class CommodityFirstClassify {

    private String id;

    private String name;

    private String description;

    private String iconId;

    private SvrFileInfo iconFile;

    private String pictureId;

    private SvrFileInfo pictureFile;

    private String bigPictureId;

    private SvrFileInfo bigPictureFile;

    private Integer status;

    private Integer enabled;

    private Integer sort;

    private String creator;

    private String createTime;

    private String updater;

    private String updateTime;

    private List<CommoditySecondClassify> commoditySecondClassifyList;

    private List<CommodityInstance> commodityInstanceList;
}
