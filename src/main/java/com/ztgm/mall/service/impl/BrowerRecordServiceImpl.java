package com.ztgm.mall.service.impl;

import com.ztgm.mall.dao.BroseRecordMapper;
import com.ztgm.mall.pojo.Brand;
import com.ztgm.mall.pojo.BroseRecord;
import com.ztgm.mall.service.BrowerRecordService;
import com.ztgm.mall.util.UUIDUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BrowerRecordServiceImpl implements BrowerRecordService {

    @Autowired
    private BroseRecordMapper broseRecordMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public int deleteByPrimaryKey(String id) {
        return broseRecordMapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insert(BroseRecord record) {
        return broseRecordMapper.insert(record);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertSelective(BroseRecord record) {
        return broseRecordMapper.insertSelective(record);
    }

    @Override
    public BroseRecord selectByPrimaryKey(String id) {
        return broseRecordMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateByPrimaryKeySelective(BroseRecord record) {
        return broseRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateByPrimaryKey(BroseRecord record) {
        return broseRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public int selectOrderVisitCount(String date) {
        return broseRecordMapper.selectOrderVisitCount(date);
    }

    @Override
    public List selectRecentVisitList(String userId) {
        return broseRecordMapper.selectRecentVisitList(userId);
    }
}
