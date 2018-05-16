package com.ztgm.iot.pojo;

import java.util.Date;
import java.util.List;

public class Warning {
    private Integer id;

    private String deviceId;

    private String warningValue;

    private Integer autoReport;

    private String telephone;

    private Integer status;

    private String msg;

    private Date createDate;

    private String creater;

    private boolean triggerNotice;

    private boolean triggerMessage;

    private boolean triggerOtherDevice;

    private Device device;

    private List<WarningTriggerDevice> triggerDeviceList;

    private String groupId;



    public boolean isTriggerNotice() {
        return triggerNotice;
    }

    public void setTriggerNotice(boolean triggerNotice) {
        this.triggerNotice = triggerNotice;
    }

    public boolean isTriggerMessage() {
        return triggerMessage;
    }

    public void setTriggerMessage(boolean triggerMessage) {
        this.triggerMessage = triggerMessage;
    }

    public boolean isTriggerOtherDevice() {
        return triggerOtherDevice;
    }

    public void setTriggerOtherDevice(boolean triggerOtherDevice) {
        this.triggerOtherDevice = triggerOtherDevice;
    }




    public List<WarningTriggerDevice> getTriggerDeviceList() {
        return triggerDeviceList;
    }

    public void setTriggerDeviceList(List<WarningTriggerDevice> triggerDeviceList) {
        this.triggerDeviceList = triggerDeviceList;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getWarningValue() {
        return warningValue;
    }

    public void setWarningValue(String warningValue) {
        this.warningValue = warningValue == null ? null : warningValue.trim();
    }

    public Integer getAutoReport() {
        return autoReport;
    }

    public void setAutoReport(Integer autoReport) {
        this.autoReport = autoReport;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}