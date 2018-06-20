package com.ztgm.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztgm.mall.dao.SvrFileInfoMapper;
import com.ztgm.mall.pojo.SvrFileInfo;
import com.ztgm.mall.service.SvrFileInfoService;
import com.ztgm.mall.util.DateUtil;
import com.ztgm.mall.util.UUIDUtil;




@Service
public class SvrFileInfoServiceImpl implements SvrFileInfoService{

	@Autowired
	private SvrFileInfoMapper fileDao;
	

	@Override
	public int SaveFileInfo(SvrFileInfo info) {
		
		
		info.setId(UUIDUtil.getUUID());
		info.setSave_date(DateUtil.getNowStr(""));
		
		return fileDao.SaveFileInfo(info);
	}

	@Override
	public int DeleteFileInfo(SvrFileInfo info) {
		return fileDao.DeleteFileInfo(info);
	}

	@Override
	public int addFileDonwLoadNums(SvrFileInfo info) {
		return fileDao.addFileDonwLoadNums(info);
	}

	@Override
	public SvrFileInfo getFileInfo(SvrFileInfo info) {
		return fileDao.getFileInfo(info);
	}
	

}
