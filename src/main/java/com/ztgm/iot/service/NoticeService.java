package com.ztgm.iot.service;

import com.ztgm.iot.pojo.Device;
import com.ztgm.iot.pojo.Notice;

import java.util.List;

public interface NoticeService {

    /**
     * 查询消息列表
     * @param userId
     * @return
     * @author zj
     * @date 2018年4月17日
     */
    List<Notice> noticeList(String userId);

  
    int deleteByPrimaryKey(String id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);
    
    

}
