package com.ztgm.iot.pojo;

public class DevicePicture {
    private String id;

    private String pictureName;

    private String pictureCode;

    private String appPictureOpen;

    private String appPictureClose;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName == null ? null : pictureName.trim();
    }

    public String getPictureCode() {
        return pictureCode;
    }

    public void setPictureCode(String pictureCode) {
        this.pictureCode = pictureCode == null ? null : pictureCode.trim();
    }

    public String getAppPictureOpen() {
        return appPictureOpen;
    }

    public void setAppPictureOpen(String appPictureOpen) {
        this.appPictureOpen = appPictureOpen == null ? null : appPictureOpen.trim();
    }

    public String getAppPictureClose() {
        return appPictureClose;
    }

    public void setAppPictureClose(String appPictureClose) {
        this.appPictureClose = appPictureClose == null ? null : appPictureClose.trim();
    }
}