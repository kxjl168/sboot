package com.ztgm.mall.service.impl;

import com.ztgm.mall.dao.CommodityAttributeMapper;
import com.ztgm.mall.dao.CommodityInstanceMapper;
import com.ztgm.mall.dao.CommodityMapper;
import com.ztgm.mall.dao.CommodityOptionMapper;
import com.ztgm.mall.dao.CommondityImageMapper;
import com.ztgm.mall.dao.ManagerMapper;
import com.ztgm.mall.pojo.Commodity;
import com.ztgm.mall.pojo.CommodityAttribute;
import com.ztgm.mall.pojo.CommodityInstance;
import com.ztgm.mall.pojo.CommodityOption;
import com.ztgm.mall.pojo.CommondityImage;
import com.ztgm.mall.service.CommodityDetailService;
import com.ztgm.mall.service.CommodityService;
import com.ztgm.mall.service.OptionService;
import com.ztgm.mall.util.UUIDUtil;
import com.ztgm.mall.pojo.Manager;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class CommodityDetailServiceImpl implements CommodityDetailService {

	@Autowired
	private CommodityMapper commodityMapper;
	@Autowired
	private CommodityInstanceMapper commodityInstanceMapper;

	@Autowired
	private CommondityImageMapper commondityImageMapper;

	@Autowired
	private CommodityAttributeMapper commodityAttributeMapper;

	@Autowired
	private CommodityOptionMapper commodityOptionMapper;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int deleteByPrimaryKey(String id) {

		// instance 逻辑删除
		CommodityInstance detail = commodityInstanceMapper.selectByPrimaryKey(id);
		if (detail != null)
			detail.setStatus(3);
		return commodityInstanceMapper.updateByPrimaryKeySelective(detail);
	}

	public int insert(Commodity record) {
		return commodityMapper.insert(record);
	}

	@Transactional(rollbackFor= {Exception.class},propagation=Propagation.REQUIRES_NEW)
	public JSONObject insertSelective(CommodityInstance record) {

		JSONObject rtn = new JSONObject();

		try {
			record.setId(UUIDUtil.getUUID());

			int rst = commodityInstanceMapper.insertSelective(record);

			rtn.put("result", true);

			return rtn;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("insert出错", e);
			try {
				rtn.put("result", false);
				rtn.put("message", "insert出错");
			} catch (Exception e2) {
				// TODO: handle exception
			}

			return rtn;
		}

	}

	public CommodityInstance selectByPrimaryKey(String id) {
		return commodityInstanceMapper.selectByPrimaryKey(id);
	}

	@Transactional(rollbackFor= {Exception.class},propagation=Propagation.REQUIRES_NEW)
	public JSONObject updateByPrimaryKeySelective(CommodityInstance record) {

		JSONObject rtn = new JSONObject();

		try {
			
		
			int rst = commodityInstanceMapper.updateByPrimaryKeySelective(record);

			rtn.put("result", true);

			return rtn;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			 
			logger.error("更新出错", e);
			try {
				rtn.put("result", false);
				rtn.put("message", "更新出错");
			} catch (Exception e2) {
				// TODO: handle exception
			}
			return rtn;

		}

	}

	public int updateByPrimaryKey(Commodity record) {
		return commodityMapper.updateByPrimaryKey(record);
	}

	/**
	 * 条件查询- 过滤state 为3 已经删除的
	 * 
	 * @param optionDef
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	public List<CommodityInstance> selectCommodityList(CommodityInstance optionDef) {
		return commodityInstanceMapper.selectCommodityInstanceList(optionDef);
	}

}
