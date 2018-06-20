package com.ztgm.mall.pojo;

public class ManagerRole {
    private String id;

    private String sysManagerId;

    private String sysRoleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSysManagerId() {
        return sysManagerId;
    }

    public void setSysManagerId(String sysManagerId) {
        this.sysManagerId = sysManagerId == null ? null : sysManagerId.trim();
    }

    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId == null ? null : sysRoleId.trim();
    }
}