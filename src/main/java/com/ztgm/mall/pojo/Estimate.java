package com.ztgm.mall.pojo;

import lombok.Data;


/**
 * @author handsome Xiao
 */
@Data
public class Estimate {
    private String id;

    private String userId;

    private String orderId;

    private String orderDetailId;

    private String commodityId;

    private String commodityInstanceId;

    private String value;

    private Integer stars;
    
    private Integer status;
    
    private Integer isanony;//是否匿名 1匿名，0实名
    

    private String createTime;
    private String userName;
    private String goodName;
    private String startTime;
    private String endTime;
}