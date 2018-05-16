package com.ztgm.iot.pojo;

import java.util.List;

public class SceneDevice {

    private String sceneName;//场景名称

    private Combination combination;//场景

    private List<Device> deviceList;//场景有一些设备

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public Combination getCombination() {
        return combination;
    }

    public void setCombination(Combination combination) {
        this.combination = combination;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }
}
