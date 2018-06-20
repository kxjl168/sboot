package com.ztgm.mall.service.impl;

import com.ztgm.mall.dao.UserSearchLogMapper;
import com.ztgm.mall.pojo.BroseRecord;
import com.ztgm.mall.pojo.UserSearchLog;
import com.ztgm.mall.service.BrowerRecordService;
import com.ztgm.mall.service.UserSearchLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserSearchLogServiceImpl implements UserSearchLogService {

    @Autowired
    private UserSearchLogMapper userSearchLogMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public int deleteByPrimaryKey(String id) {
        return userSearchLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserSearchLog record) {
        return userSearchLogMapper.insert(record);
    }

    @Override
    public int insertSelective(UserSearchLog record) {
        return userSearchLogMapper.insertSelective(record);
    }

    @Override
    public UserSearchLog selectByPrimaryKey(String id) {
        return userSearchLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserSearchLog record) {
        return userSearchLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserSearchLog record) {
        return userSearchLogMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<UserSearchLog> selectUserSearchLogList(String startTime,String endTime,String customName,String keyWord,String ip) {
        return userSearchLogMapper.selectUserSearchLogList(startTime,endTime,customName,keyWord,ip);
    }
}
