package com.ztgm.mall.pojo;

public class CommondityInstanceOptions {
    private String id;

    private String commondityInstanceId;

    private String commodityOptionId;
    
    private String optionValMd5;
    
   
    
    private CommodityOption commodity_option;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCommondityInstanceId() {
        return commondityInstanceId;
    }

    public void setCommondityInstanceId(String commondityInstanceId) {
        this.commondityInstanceId = commondityInstanceId == null ? null : commondityInstanceId.trim();
    }

    public String getCommodityOptionId() {
        return commodityOptionId;
    }

    public void setCommodityOptionId(String commodityOptionId) {
        this.commodityOptionId = commodityOptionId == null ? null : commodityOptionId.trim();
    }

	public CommodityOption getCommodity_option() {
		return commodity_option;
	}

	public void setCommodity_option(CommodityOption commodity_option) {
		this.commodity_option = commodity_option;
	}

	public String getOptionValMd5() {
		return optionValMd5;
	}

	public void setOptionValMd5(String optionValMd5) {
		this.optionValMd5 = optionValMd5;
	}

	
}