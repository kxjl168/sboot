package com.ztgm.mall.controller.WebController.backend.statistics;


import com.github.pagehelper.PageHelper;
import com.ztgm.mall.pojo.UserOnlineLog;
import com.ztgm.mall.service.UserOnlineService;
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
 * 客户在线统计
 * @author wx
 * @date 2018年6月15日
 *
 */
@Controller
@RequestMapping("/statics/userOnline")

public class UserOnlineStatisticsController {

    @Autowired
    private UserOnlineService onlineCustomerService;

    /**
     *保存记录
     */
    @RequestMapping("/save")
    @ResponseBody
    public AppResult onlineCustomeSave(@RequestParam(value = "userId") String userId,  @RequestParam(value = "ipStr") String ipStr,@RequestParam(value = "state") String state, HttpServletRequest request) {

        UserOnlineLog userOnlineLog = new UserOnlineLog();
        userOnlineLog.setUserId(userId);
        userOnlineLog.setIp(ipStr);
        userOnlineLog.setState(state);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        userOnlineLog.setCreateTime(date);
        onlineCustomerService.insert(userOnlineLog);
        return AppResultUtil.success();
    }

    /**
     *g更新记录
     */
    @RequestMapping("/update")
    @ResponseBody
    public AppResult UserOnlineUpdate(@RequestParam(value = "userId") String userId,  @RequestParam(value = "ipStr") String ipStr,@RequestParam(value = "state") String state, HttpServletRequest request) {

        UserOnlineLog userOnlineLog = new UserOnlineLog();
        userOnlineLog.setUserId(userId);
        userOnlineLog.setIp(ipStr);
        userOnlineLog.setState(state);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        userOnlineLog.setCreateTime(date);
        onlineCustomerService.update(userOnlineLog);
        return AppResultUtil.success();
    }

    /**
     *查找记录list
     */
    @RequestMapping("/getList")
    @ResponseBody
    public AppResult getOnlineCustomeStaticsList(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "userId") String userId,@RequestParam(value = "ipStr") String ipStr, HttpServletRequest request) {
        PageHelper.startPage(pageNo, pageSize);
        List<UserOnlineLog> userSearchLogList = onlineCustomerService.getOnlineCustomeStaticsList(ipStr,userId);
        return AppResultUtil.success(userSearchLogList);
    }

}
