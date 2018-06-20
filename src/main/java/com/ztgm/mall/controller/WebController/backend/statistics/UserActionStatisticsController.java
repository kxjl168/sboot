package com.ztgm.mall.controller.WebController.backend.statistics;


import com.github.pagehelper.PageHelper;
import com.ztgm.mall.pojo.UserActionLog;
import com.ztgm.mall.service.UserActionService;
import com.ztgm.mall.util.AppResult;
import com.ztgm.mall.util.AppResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 客户活动统计
 * @author wx
 * @date 2018年6月15日
 *
 */
@Controller
@RequestMapping("/statics/userAction")
public class UserActionStatisticsController {

    @Autowired
    private UserActionService userActionService;

    /**
     *保存记录
     */
    @RequestMapping("/save")
    @ResponseBody
    public AppResult onlineCustomeSave(@RequestParam(value = "userId") String userId, @RequestParam(value = "ipStr") String ipStr, @RequestParam(value = "action") String action, HttpServletRequest request) {

        UserActionLog userActionLog = new UserActionLog();
        userActionLog.setUserId(userId);
        userActionLog.setIp(ipStr);
        userActionLog.setAction(action);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        userActionLog.setActionTime(date);
        userActionService.insert(userActionLog);
        return AppResultUtil.success();
    }



    /**
     *查找记录list
     */
    @RequestMapping("/getList")
    @ResponseBody
    public AppResult getOnlineCustomeStaticsList(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "userId") String userId,@RequestParam(value = "startTime") String startTime,@RequestParam(value = "endTime") String endTime,@RequestParam(value = "ipStr") String ipStr, HttpServletRequest request) {
        PageHelper.startPage(pageNo, pageSize);
        List<UserActionLog> userSearchLogList = userActionService.getOnlineCustomeStaticsList(ipStr,userId,startTime,endTime);
        return AppResultUtil.success(userSearchLogList);
    }
}
