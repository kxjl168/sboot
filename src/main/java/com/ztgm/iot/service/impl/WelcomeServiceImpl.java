package com.ztgm.iot.service.impl;

import com.ztgm.iot.dao.WelcomeMapper;
import com.ztgm.iot.pojo.Notice;
import com.ztgm.iot.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WelcomeServiceImpl implements WelcomeService {

    @Autowired
    private WelcomeMapper welcomeMapper;

    @Override
    public List<String> getGroupIdByUserId(String userId) {
        return welcomeMapper.getGroupIdByUserId(userId);
    }

    @Override
    public Map getHrefCount(List<String> groupIdList) {
        Map hrefCountMap = new HashMap(16);
        try {
            if (groupIdList == null || groupIdList.isEmpty()) {
                hrefCountMap.put("deviceManager", 0);
                hrefCountMap.put("sceneManager", 0);
                hrefCountMap.put("regionManager", 0);
                hrefCountMap.put("deviceWarning", 0);
            } else {
                hrefCountMap = welcomeMapper.getHrefCount (groupIdList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hrefCountMap;
    }

    @Override
    public List<Notice> getUserNotice(String userId) {
        List<Notice> noticeList = welcomeMapper.getUserNotice (userId);
        return noticeList;
    }

    @Override
    public List<Map> getRegionLogCountList(List<String> groupIdList, String year) {
        List<Map> resultMapList = new ArrayList<>();
        for (int i = 1; i <= 12 ; i++) {
            Map monthCountMap = new HashMap(16);
            monthCountMap.put("month", i);
            monthCountMap.put("count", 0);
            resultMapList.add(monthCountMap);
        }

        if (groupIdList == null || groupIdList.isEmpty()) {
            return resultMapList;
        }

        List<Map> monthCountMapList = welcomeMapper.getRegionLogCountList (groupIdList, year);
        if (monthCountMapList == null || monthCountMapList.isEmpty()) {
            return resultMapList;
        }
        for (Map map : monthCountMapList) {
            Integer month = Integer.valueOf(String.valueOf(map.get("month")));
            Long count = (Long) map.get("count");
            resultMapList.get(month - 1).put("count", count);
        }
        return resultMapList;
    }

    @Override
    public List<Map> getSceneLogCountList(List<String> groupIdList, String year) {
        List<Map> resultMapList = new ArrayList<>();
        for (int i = 1; i <= 12 ; i++) {
            Map monthCountMap = new HashMap(16);
            monthCountMap.put("month", i);
            monthCountMap.put("count", 0);
            resultMapList.add(monthCountMap);
        }

        if (groupIdList == null || groupIdList.isEmpty()) {
            return resultMapList;
        }

        List<Map> monthCountMapList = welcomeMapper.getSceneLogCountList (groupIdList, year);
        if (monthCountMapList == null || monthCountMapList.isEmpty()) {
            return resultMapList;
        }
        for (Map map : monthCountMapList) {
            Integer month = Integer.valueOf(String.valueOf(map.get("month")));
            Long count = (Long) map.get("count");
            resultMapList.get(month - 1).put(month, count);
        }
        return resultMapList;
    }

    @Override
    public Map getSelectYearList(List<String> groupIdList) {
        Map resultMap = new HashMap(16);

        resultMap.put("regionYearList", new ArrayList<>());
        resultMap.put("sceneYearList", new ArrayList<>());

        if (groupIdList == null || groupIdList.isEmpty()) {
            return resultMap;
        }

        try {
            List<String> regionYearList = welcomeMapper.getRegionYearList (groupIdList);
            List<String> sceneYearList = welcomeMapper.getSceneYearList (groupIdList);
            resultMap.put("regionYearList", regionYearList);
            resultMap.put("sceneYearList", sceneYearList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map getDeviceStatusCountList(List<String> groupIdList) {

        Map<String, Integer> resultMap = new HashMap(16);
        resultMap.put("switchgearOpenCount", 0);
        resultMap.put("switchgearCloseCount", 0);
        resultMap.put("adjustOpenCount", 0);
        resultMap.put("adjustCloseCount", 0);

        if (groupIdList == null || groupIdList.isEmpty()) {
            return resultMap;
        }

        try {
            List<Map> deviceStatusCountList = welcomeMapper.getDeviceStatusCountList (groupIdList);
            if (deviceStatusCountList == null || deviceStatusCountList.isEmpty()) {
                return resultMap;
            }

            for (Map map : deviceStatusCountList) {
                String paramType = (map.get("paramType") == null) ? "" : map.get("paramType").toString();
                String data = (map.get("data") == null) ? "" : map.get("data").toString();
                int count = Integer.valueOf((map.get("count") == null) ? "0" : map.get("count").toString());
                if ("".equals(paramType) || "".equals(data) || count == 0) {
                    continue;
                }
                if ("1".equals(paramType)) {
                    if ("0".equals(data)) {
                        resultMap.put("switchgearCloseCount", resultMap.get("switchgearCloseCount") + count);
                    } else {
                        resultMap.put("switchgearOpenCount", resultMap.get("switchgearOpenCount") + count);
                    }
                } else {
                    if ("0".equals(data)) {
                        resultMap.put("adjustCloseCount", resultMap.get("adjustCloseCount") + count);
                    } else {
                        resultMap.put("adjustOpenCount", resultMap.get("adjustOpenCount") + count);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public void updateNoticeRead(String noticeId) {
        welcomeMapper.updateNoticeRead (noticeId);
    }
}
