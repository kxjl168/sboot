package com.ztgm.mall.pojo;

import java.util.Date;

public class AttentionRecord {
    private String id;

    private String userId;

    private String commondityInstanceId;

    private Date createTime;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCommondityInstanceId() {
        return commondityInstanceId;
    }

    public void setCommondityInstanceId(String commondityInstanceId) {
        this.commondityInstanceId = commondityInstanceId == null ? null : commondityInstanceId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}