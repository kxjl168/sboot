package com.ztgm.iot.service;

import com.ztgm.iot.pojo.Warning;

import java.util.List;

public interface WarningService {
    public List<Warning> warningList(String userId);
    public Warning findWarningById(int id);
    public Warning findWarningByDeviceId(String deviceId);
    public int createWarning(Warning w);
    public int updateWarning(Warning w);
    public int deleteWarning(int id);

    public void triggerWarning(String deviceId,int paramType,int currentData);
}
