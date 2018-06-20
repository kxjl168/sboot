package com.ztgm.mall.pojo;

import java.util.ArrayList;
import java.util.List;

public class CommodityInstance {
    private String id;

    private String commodityId;

    private Double price;

    private Integer status; //'商品状态  1:正常  2:下线 3:停用',
    
    private Commodity commodity=new Commodity();
    
    private List<CommondityInstanceOptions> commondityInstanceOptions=new ArrayList<>();
    
    
 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId == null ? null : commodityId.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public List<CommondityInstanceOptions> getCommondityInstanceOptions() {
		return commondityInstanceOptions;
	}

	public void setCommondityInstanceOptions(List<CommondityInstanceOptions> commondityInstanceOptions) {
		this.commondityInstanceOptions = commondityInstanceOptions;
	}


}