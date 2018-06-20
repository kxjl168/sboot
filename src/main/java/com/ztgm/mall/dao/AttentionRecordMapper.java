package com.ztgm.mall.dao;

import com.ztgm.mall.pojo.AttentionRecord;

public interface AttentionRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(AttentionRecord record);

    int insertSelective(AttentionRecord record);

    AttentionRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AttentionRecord record);

    int updateByPrimaryKey(AttentionRecord record);
}