package com.ztgm.iot.service.impl;

import com.ztgm.iot.dao.DeviceMapper;
import com.ztgm.iot.dao.GateWayMapper;
import com.ztgm.iot.dao.GroupMapper;
import com.ztgm.iot.dao.NoticeMapper;

import com.ztgm.iot.pojo.Device;
import com.ztgm.iot.pojo.GateWay;
import com.ztgm.iot.pojo.Group;
import com.ztgm.iot.pojo.Notice;
import com.ztgm.iot.pojo.RegionDeviceList;

import com.ztgm.iot.service.NoticeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class NoticeServiceImpl implements NoticeService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private NoticeMapper noticeMapper;

	/**
	 * 查询消息列表
	 * 
	 * @param userId
	 * @return
	 * @author zj
	 * @date 2018年4月17日
	 */
	@Override
	public List<Notice> noticeList(String userId) {
		List<Notice> noticeLists = new ArrayList<>();
		try {
			noticeLists = noticeMapper.noticeList(userId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询消息集合失败");
		}
		return noticeLists;
	}

	public int deleteByPrimaryKey(String id) {
		return noticeMapper.deleteByPrimaryKey(id);
	}

	public int insert(Notice record) {
		return noticeMapper.insert(record);
	}

	public int insertSelective(Notice record) {
		return noticeMapper.insertSelective(record);
	}

	public Notice selectByPrimaryKey(String id) {
		return noticeMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(Notice record) {
		return noticeMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Notice record) {
		return noticeMapper.updateByPrimaryKey(record);
	}
}
