package com.ztgm.iot.controller.AppController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztgm.iot.pojo.Device;
import com.ztgm.iot.pojo.Notice;

import com.ztgm.iot.service.NoticeService;
import com.ztgm.iot.service.SaveOperationLogService;
import com.ztgm.iot.util.AppResult;
import com.ztgm.iot.util.AppResultUtil;
import com.ztgm.iot.util.TokenUtil;
import com.ztgm.iot.util.sendSMS.HttpClientUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 消息列表
 * 
 * @author zj
 * @date 2018年4月17日
 *
 */
@RestController
@RequestMapping("/interface/message")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	/**
	 * 消息列表
	 * @param userId
	 * @param pn 分页起始页数
	 * @param ps 每页数据
	 * @return
	 * @author zj
	 * @date 2018年4月19日
	 */
	@RequestMapping("/list")
	public AppResult list(String userId, Integer pn, Integer ps) {

		Integer startpage = pn == null ? 1 : pn;
		Integer size = ps == null ? 20 : ps;

		PageHelper.startPage(startpage,size);
		List<Notice> device = noticeService.noticeList(userId);
		PageInfo<Notice> plist=new PageInfo<>(device);

		return AppResultUtil.success(plist.getList());
	}

	/**
	 * 删除消息
	 * 
	 * @param noticeId
	 * @return
	 * @author zj
	 * @date 2018年4月19日
	 */
	@RequestMapping("/del")
	public AppResult del(String noticeId) {
		noticeService.deleteByPrimaryKey(noticeId);
		return AppResultUtil.success(null);
	}

	/**
	 * 设置消息为已读
	 * 
	 * @param noticeId
	 * @return
	 * @author zj
	 * @date 2018年4月19日
	 */
	@RequestMapping("/read")
	public AppResult read(String noticeId) {
		Notice notice = noticeService.selectByPrimaryKey(noticeId);
		notice.setStatus(1);// 已读
		noticeService.updateByPrimaryKeySelective(notice);
		return AppResultUtil.success(null);
	}

}
