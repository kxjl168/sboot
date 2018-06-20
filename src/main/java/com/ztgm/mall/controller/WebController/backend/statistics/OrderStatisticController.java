package com.ztgm.mall.controller.WebController.backend.statistics;

import com.github.pagehelper.PageHelper;
import com.ztgm.mall.pojo.Order;
import com.ztgm.mall.pojo.PageCondition;
import com.ztgm.mall.pojo.UserSearchLog;
import com.ztgm.mall.service.OrderService;
import com.ztgm.mall.util.AppResult;
import com.ztgm.mall.util.AppResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 订单统计接口
 *
 * @author zhangyong
 * @date 2018年6月13日
 */
@Controller
@RequestMapping("/statics/order")
public class OrderStatisticController {


    @Autowired
    private OrderService orderService;

    @RequestMapping("/selectOrderList")
    @ResponseBody
    public AppResult selectOrderStaticsList(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "orderStatus") int orderStatus, @RequestParam(value = "startTime") String startTime, @RequestParam(value = "endTime") String endTime, @RequestParam(value = "customName") String customName, HttpServletRequest request) {
        PageHelper.startPage(pageNo, pageSize);
        List<Map> userSearchLogList = orderService.selectOrderStaticsList(startTime,endTime,orderStatus,customName);
        return AppResultUtil.success(userSearchLogList);
    }

}
