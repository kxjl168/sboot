package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WelcomeMapper {

    /**
     * 根据登录的用户ID查询用户所属的组id
     * @param userId
     * @return
     */
    List<String> getGroupIdByUserId (@Param("userId") String userId);

    /**
     * 查询首页链接显示的Count数
     *
     * @param groupList
     * @return
     */
    Map getHrefCount (List<String> groupList);

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
    List<Map> getRegionLogCountList(@Param("groupIdList") List<String> groupIdList, @Param("year") String year);


    /**
     * 查询所选年份的场景操作数统计
     * @param groupIdList
     * @param year
     * @return
     */
    List<Map> getSceneLogCountList(@Param("groupIdList") List<String> groupIdList, @Param("year") String year);

    /**
     * 查询区域可选年份
     * @param groupIdList
     * @return
     */
    List<String> getRegionYearList(List<String> groupIdList);

    /**
     * 查询场景可选年份
     * @param groupIdList
     * @return
     */
    List<String> getSceneYearList(List<String> groupIdList);

    /**
     * 查询饼图数据
     * @param groupIdList
     * @return
     */
    List<Map> getDeviceStatusCountList(List<String> groupIdList);

    /**
     * 更新通知为已读
     * @param noticeId
     */
    void updateNoticeRead(String noticeId);
}
