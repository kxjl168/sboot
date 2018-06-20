package com.ztgm.mall.pojo;

import java.util.Date;
import java.util.List;

public class OrderDetail {
    private String id;

    private String orderId;
    
    private Integer number;

    private String commondityInstanceId;
    
    private CommodityInstance commodityInstance;
    
    private Integer sort;
    
    private Integer state;
    
    private OrderExchange orderExchange;

    private Date expectedDate;

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

    public String getCommondityInstanceId() {
        return commondityInstanceId;
    }

    public void setCommondityInstanceId(String commondityInstanceId) {
        this.commondityInstanceId = commondityInstanceId == null ? null : commondityInstanceId.trim();
    }

    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public CommodityInstance getCommodityInstance() {
		return commodityInstance;
	}

	public void setCommodityInstance(CommodityInstance commodityInstance) {
		this.commodityInstance = commodityInstance;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public OrderExchange getOrderExchange() {
		return orderExchange;
	}

	public void setOrderExchange(OrderExchange orderExchange) {
		this.orderExchange = orderExchange;
	}

}