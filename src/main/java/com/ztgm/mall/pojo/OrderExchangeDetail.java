package com.ztgm.mall.pojo;

import java.util.Date;

public class OrderExchangeDetail {
    private String id;

    private String orderExchangelId;

    private Integer state;

    private String userId;

    private Date createTime;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderExchangelId() {
        return orderExchangelId;
    }

    public void setOrderExchangelId(String orderExchangelId) {
        this.orderExchangelId = orderExchangelId == null ? null : orderExchangelId.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}