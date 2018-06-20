package com.ztgm.mall.pojo;

import java.util.Date;
import java.util.List;

import com.ztgm.mall.util.DateUtil;

public class OrderExchange {
    private String id;

    private String orderDetailId;

    private Integer state;

    private Integer type;

    private String userId;

    private Date createTime;
    
    
    //关联
    private OrderDetail orderDetail;
    private List<OrderExchangeDetail> details;
    

    //query
    private String remark;
	private String createTimeStr;
    private String name;
    private String startTime;

    private String endTime;
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId == null ? null : orderDetailId.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        this.createTimeStr=DateUtil.getDateStr(createTime, "yyyy-MM-dd HH:mm:ss");
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public List<OrderExchangeDetail> getDetails() {
		return details;
	}

	public void setDetails(List<OrderExchangeDetail> details) {
		this.details = details;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
}