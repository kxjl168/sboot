package com.ztgm.mall.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author handsomeXiao
 * @date 2018/5/30 16:29
 */
@Data
public class CommodityThirdClassify {

    private String id;

    private String name;

    private String description;

    private Integer enabled;

    private Integer sort;

    private String creator;

    private String createTime;

    private String updater;

    private String updateTime;

    private String secondClassifyId;
}
