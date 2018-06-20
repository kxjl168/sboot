package com.ztgm.mall.controller.WebController.backend.statistics;

import com.github.pagehelper.PageHelper;
import com.ztgm.mall.pojo.BroseRecord;
import com.ztgm.mall.service.BrowerRecordService;
import com.ztgm.mall.util.AppResult;
import com.ztgm.mall.util.AppResultUtil;
import com.ztgm.mall.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 浏览记录统计接口
 *
 * @author zhangyong
 * @date 2018年6月11日
 */
@Controller
@RequestMapping("/statics/browerrecord")
public class BrowseController {

    @Autowired
    private BrowerRecordService browerRecordService;

    @RequestMapping("/save")
    @ResponseBody
    public AppResult browerSave(@RequestParam(value = "userId") String userId, @RequestParam(value = "commondityId") String commondityId, @RequestParam(value = "commondityInstanceId") String commondityInstanceId, HttpServletRequest request) {
        BroseRecord broseRecord = new BroseRecord();
        broseRecord.setId(UUIDUtil.getUUID());
        broseRecord.setUserId(userId);
        broseRecord.setCommondityId(commondityId);
        broseRecord.setCommondityInstanceId(commondityInstanceId);
        browerRecordService.insert(broseRecord);
        return AppResultUtil.success();
    }

    /**
     *
     * @param commondityInstanceId
     * @param type       0天   1周  2月  3年
     * @param request
     * @return
     */
    @RequestMapping("/selectcount")
    @ResponseBody
    public AppResult browerSelectCount(@RequestParam(value = "commondityInstanceId") String commondityInstanceId,@RequestParam(value = "type") int type, HttpServletRequest request) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        switch (type){
            case 0:
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
            break;
            case 1:
                int day_of_week = calendar.get(Calendar. DAY_OF_WEEK) - 1;
                if (day_of_week == 0 ) {
                    day_of_week = 7 ;
                }
                calendar.add(Calendar.DATE , -day_of_week + 1 );
                break;
            case 2:
                calendar.add(Calendar.MONTH, 0);
                calendar.set(Calendar.DAY_OF_MONTH,1);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                break;
            case 3:
                calendar.set(Calendar.MONTH, Calendar.JANUARY);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                break;
        }
        String date = simpleDateFormat.format(calendar.getTime());
        int count = browerRecordService.selectOrderVisitCount(date);
        Map<String,Integer> map = new HashMap<>();
        map.put("count",count);
        return AppResultUtil.success(map);
    }

    @RequestMapping("/selectrecentlist")
    @ResponseBody
    public AppResult browerSelectRecentList(@RequestParam(value = "userId") String userId,@RequestParam(value = "pageNo") int pageNo,@RequestParam(value = "pageSize") int pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNo,pageSize);
        List broseRecordList = browerRecordService.selectRecentVisitList(userId);
        return AppResultUtil.success(broseRecordList);
    }

}
