package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.FaultRecord;

public interface FaultRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(FaultRecord record);

    int insertSelective(FaultRecord record);

    FaultRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FaultRecord record);

    int updateByPrimaryKey(FaultRecord record);
}