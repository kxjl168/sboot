package com.ztgm.mall.service;

import com.ztgm.mall.pojo.UserActionLog;

import java.util.List;

public interface UserActionService {

    /**
     * 插入
     *
     */
    int insert(UserActionLog record);

    /**
     * 查询
     *
     */
    List<UserActionLog> getOnlineCustomeStaticsList(String ipStr, String userId,String startTime,String endTime);

}
