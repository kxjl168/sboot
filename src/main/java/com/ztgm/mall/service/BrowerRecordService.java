package com.ztgm.mall.service;

import com.ztgm.mall.pojo.BroseRecord;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


public interface BrowerRecordService {

    int deleteByPrimaryKey(String id);

    int insert(BroseRecord record);

    int insertSelective(BroseRecord record);

    BroseRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BroseRecord record);

    int updateByPrimaryKey(BroseRecord record);

    int selectOrderVisitCount(String date);

    List selectRecentVisitList(String userId);

}
