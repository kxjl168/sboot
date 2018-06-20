package com.ztgm.mall.pojo;

import java.util.Date;

public class Receipt {
    private String id;

    private String orderId;

    private String receipdHead;

    private String receiptContent;

    private Date receipdTime;

    private Integer state;

    private Date createTime;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getReceipdHead() {
        return receipdHead;
    }

    public void setReceipdHead(String receipdHead) {
        this.receipdHead = receipdHead == null ? null : receipdHead.trim();
    }

    public String getReceiptContent() {
        return receiptContent;
    }

    public void setReceiptContent(String receiptContent) {
        this.receiptContent = receiptContent == null ? null : receiptContent.trim();
    }

    public Date getReceipdTime() {
        return receipdTime;
    }

    public void setReceipdTime(Date receipdTime) {
        this.receipdTime = receipdTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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