package com.ztgm.mall.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author handsomeXiao
 * @date 2018/5/30 16:26
 */
@Data
public class CommoditySecondClassify {

    private String id;

    private String name;

    private String description;

    private Integer enabled;

    private Integer sort;

    private String creator;

    private String createTime;

    private String updater;

    private String updateTime;

    private String firstClassifyId;

    private List<CommodityThirdClassify> commodityThirdClassifyList;
}
