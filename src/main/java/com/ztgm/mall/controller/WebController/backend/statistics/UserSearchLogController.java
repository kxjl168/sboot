package com.ztgm.mall.controller.WebController.backend.statistics;

import com.github.pagehelper.PageHelper;
import com.ztgm.mall.pojo.UserSearchLog;
import com.ztgm.mall.service.UserSearchLogService;
import com.ztgm.mall.util.AppResult;
import com.ztgm.mall.util.AppResultUtil;
import com.ztgm.mall.util.DeviceUtil;
import com.ztgm.mall.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户搜索历史统计接口
 *
 * @author zhangyong
 * @date 2018年6月10日
 */
@Controller
@RequestMapping("/statics/usersearchlog")
public class UserSearchLogController {

    @Autowired
    private UserSearchLogService userSearchLogService;

    @RequestMapping("/save")
    @ResponseBody
    public AppResult saveSearchKey(@RequestParam(value = "userId") String userId,@RequestParam(value = "keyWord") String keyWord,HttpServletRequest request) {
        String ipAddress = DeviceUtil.getIpAddress(request);
        UserSearchLog userSearchLog = new UserSearchLog();
        userSearchLog.setId(UUIDUtil.getUUID());
        userSearchLog.setUserId(userId);
        userSearchLog.setKeyword(keyWord);
        userSearchLog.setIp(ipAddress);
        int result = userSearchLogService.insert(userSearchLog);
        return AppResultUtil.success();
    }

    @RequestMapping("/selectusersearchlist")
    @ResponseBody
    public AppResult selectUserSearchLogList(@RequestParam(value = "startTime") String startTime,@RequestParam(value = "endTime") String endTime,@RequestParam(value = "keyWord") String keyWord,@RequestParam(value = "customName") String customName,@RequestParam(value = "ip") String ip,@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize,HttpServletRequest request) {
        PageHelper.startPage(pageNo, pageSize);
        List<UserSearchLog> userSearchLogList = userSearchLogService.selectUserSearchLogList(startTime,endTime,customName,keyWord,ip);
        return AppResultUtil.success(userSearchLogList);
    }


}
