package com.ztgm.mall.service.impl;

import com.ztgm.mall.dao.UserActionLogMapper;
import com.ztgm.mall.pojo.UserActionLog;
import com.ztgm.mall.service.UserActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserActionServiceImpl implements UserActionService{

    @Autowired
    private UserActionLogMapper userActionLogMapper;
    /**
     * 插入
     *
     */
    public int insert(UserActionLog record){


        return userActionLogMapper.insert(record);
    }

    /**
     * 查询
     *
     */
    public List<UserActionLog> getOnlineCustomeStaticsList(String ipStr, String userId,String startTime,String endTime){

        return userActionLogMapper.getUserActionStaticsList("ipStr","userId","startTime","endTime");
    }
}
