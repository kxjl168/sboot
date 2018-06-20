package com.ztgm.mall.dao;

import com.ztgm.mall.pojo.BroseRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BroseRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(BroseRecord record);

    int insertSelective(BroseRecord record);

    BroseRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BroseRecord record);

    int updateByPrimaryKey(BroseRecord record);

    int selectOrderVisitCount(String date);

    List selectRecentVisitList(@Param("userId") String userId);
}