package com.ztgm.mall.service;



import com.ztgm.mall.pojo.UserOnlineLog;

import java.util.List;

public interface UserOnlineService {

    /**
     * 插入
     *
     */
    int insert(UserOnlineLog record);

    /**
     * 跟新
     *
     */
    int update(UserOnlineLog record);

    /**
     * 查询
     *
     */
    List<UserOnlineLog> getOnlineCustomeStaticsList(String ipStr,String userId);

}
