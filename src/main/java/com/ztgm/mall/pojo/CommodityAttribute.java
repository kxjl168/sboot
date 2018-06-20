package com.ztgm.mall.pojo;

public class CommodityAttribute {
    private String id;

    private String commodityId;

    private String attributeDefId;

    private String value;
    
    private AttributeDef attri;

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

    public String getAttributeDefId() {
        return attributeDefId;
    }

    public void setAttributeDefId(String attributeDefId) {
        this.attributeDefId = attributeDefId == null ? null : attributeDefId.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

	public AttributeDef getAttri() {
		return attri;
	}

	public void setAttri(AttributeDef attri) {
		this.attri = attri;
	}
}