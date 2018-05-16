package com.ztgm.iot.dao;

import java.util.List;


import com.ztgm.iot.pojo.Notice;

public interface NoticeMapper {
    int deleteByPrimaryKey(String id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);
    
    
    List<Notice> noticeList(String userid);
}

