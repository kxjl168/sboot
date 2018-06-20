package com.ztgm.mall.pojo;

public class AttributeDef {
    private String id;

    private String name;

    private Integer isSpecification;

    private Integer mandatory; //'是否为必选属性（0为非必选，1为必选）',

    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getIsSpecification() {
        return isSpecification;
    }

    public void setIsSpecification(Integer isSpecification) {
        this.isSpecification = isSpecification;
    }

    public Integer getMandatory() {
        return mandatory;
    }

    public void setMandatory(Integer mandatory) {
        this.mandatory = mandatory;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}