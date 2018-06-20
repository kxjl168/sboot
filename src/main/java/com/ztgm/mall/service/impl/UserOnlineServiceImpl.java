package com.ztgm.mall.service.impl;

import com.ztgm.mall.dao.UserOnlineLogMapper;
import com.ztgm.mall.pojo.UserOnlineLog;
import com.ztgm.mall.service.UserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOnlineServiceImpl implements UserOnlineService {


    @Autowired
    private UserOnlineLogMapper userOnlineLogMapper;
    /**
     * 插入
     *
     */
    public int insert(UserOnlineLog record){


        return userOnlineLogMapper.insert(record);
    }

    /**
     * 跟新
     *
     */
    public int update(UserOnlineLog record){

        return userOnlineLogMapper.updateByPrimaryKey(record);
    }

    /**
     * 查询
     *
     */
    public List<UserOnlineLog> getOnlineCustomeStaticsList(String ipStr, String userId){

        return userOnlineLogMapper.getOnlineCustomeStaticsList("ipStr","userId");
    }
}
