package com.ztgm.mall.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author handsome Xiao
 */
@Data
public class Order extends BaseSortPojo {
    private String id;

    private Double payMoney;

    private Integer state;
    
   

    private String remarks;

    private String createTime;

    private String payTime;

    private Integer payType;

    private String userId;

    private String deliveryAddressId;

    private DeliveryAddress deliveryAddress;

    private MUser mUser;

   private List< OrderDetail> details;

    private String startTime;

    private String endTime;

    private String userName;
    
    //query
    private String goodName;
}