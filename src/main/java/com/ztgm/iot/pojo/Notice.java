package com.ztgm.iot.pojo;

import java.util.Date;

import org.apache.http.client.utils.DateUtils;

public class Notice {
    private String id;

    private String userId;

    private String msg;

    private String title;

    private Integer status;

    private String createDate;
    
    

    private String creater;

    private Integer rowNo;
    
    
    private Long createDateStr;
    

    public Long getCreateDateStr() {
    	
    
		return createDateStr;
	}

	public void setCreateDateStr(Long createDateStr) {
		this.createDateStr = createDateStr;
	}

	public Integer getRowNo() {
        return rowNo;
    }

    public void setRowNo(Integer rowNo) {
        this.rowNo = rowNo;
    }

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
        Date d=DateUtils.parseDate(createDate,new String[] {"yyyy-MM-dd HH:mm:ss"});
        this.createDateStr=d.getTime();
    	
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }
}