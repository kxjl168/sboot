package com.ztgm.iot.pojo;

public class UserConfigWithBLOBs extends UserConfig {
    private byte[] currentConfig;

    private byte[] previousConfig;

    public byte[] getCurrentConfig() {
        return currentConfig;
    }

    public void setCurrentConfig(byte[] currentConfig) {
        this.currentConfig = currentConfig;
    }

    public byte[] getPreviousConfig() {
        return previousConfig;
    }

    public void setPreviousConfig(byte[] previousConfig) {
        this.previousConfig = previousConfig;
    }
}