package com.ztgm.iot.service;

import com.ztgm.iot.pojo.Notice;

import java.util.List;
import java.util.Map;

public interface WelcomeService {

    /**
     * 根据登录的用户ID查询用户所属的组id
     * @param userId
     * @return
     */
    List<String> getGroupIdByUserId(String userId);

    /**
     * 查询首页链接显示的Count数
     * @param groupIdList
     * @return
     */
    Map getHrefCount(List<String> groupIdList);

    /**
     * 查询首页通知
     * @param userId
     * @return
     */
    List<Notice> getUserNotice(String userId);

    /**
     * 查询所选年份的区域操作数统计
     * @param groupIdList
     * @param year
     * @return
     */
    List<Map> getRegionLogCountList(List<String> groupIdList, String year);

    /**
     * 查询所选年份的场景操作数统计
     * @param groupIdList
     * @param year
     * @return
     */
    List<Map> getSceneLogCountList(List<String> groupIdList, String year);

    /**
     * 查询可选年份
     * @param groupIdList
     * @return
     */
    Map getSelectYearList(List<String> groupIdList);

    /**
     * 查询饼图数据
     * @param groupIdList
     * @return
     */
    Map getDeviceStatusCountList(List<String> groupIdList);

    /**
     * 更新通知为已读
     * @param noticeId
     */
    void updateNoticeRead(String noticeId);
}
