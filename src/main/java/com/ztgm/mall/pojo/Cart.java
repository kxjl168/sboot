package com.ztgm.mall.pojo;

import java.util.Date;

public class Cart {
    private String id;

    private String userId;

    private String commondityId;

    private String commondityInstanceId;

    private Integer count;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

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

    public String getCommondityId() {
        return commondityId;
    }

    public void setCommondityId(String commondityId) {
        this.commondityId = commondityId == null ? null : commondityId.trim();
    }

    public String getCommondityInstanceId() {
        return commondityInstanceId;
    }

    public void setCommondityInstanceId(String commondityInstanceId) {
        this.commondityInstanceId = commondityInstanceId == null ? null : commondityInstanceId.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}