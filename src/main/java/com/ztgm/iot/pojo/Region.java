package com.ztgm.iot.pojo;

import java.util.Date;

public class Region {
    private String id;

    private String groupId;

    private String gatewayId;

    private String gatewayRegionId;

    private String name;

    private String descr;

    private Date createDate;

    private String creater;

    private byte[] img;

    private String imgUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId == null ? null : gatewayId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getGatewayRegionId() {
        return gatewayRegionId;
    }

    public void setGatewayRegionId(String gatewayRegionId) {
        this.gatewayRegionId = gatewayRegionId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}