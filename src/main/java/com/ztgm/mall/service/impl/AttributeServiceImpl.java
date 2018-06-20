package com.ztgm.mall.service.impl;

import com.ztgm.mall.dao.AttributeDefMapper;

import com.ztgm.mall.dao.ManagerMapper;
import com.ztgm.mall.pojo.AttributeDef;
import com.ztgm.mall.service.AttributeService;
import com.ztgm.mall.util.UUIDUtil;
import com.ztgm.mall.pojo.Manager;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttributeServiceImpl implements AttributeService {

	@Autowired
	private AttributeDefMapper optionDefMapper;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int deleteByPrimaryKey(String id) {
		return optionDefMapper.deleteByPrimaryKey(id);
	}

	public int insert(AttributeDef record) {
		return optionDefMapper.insert(record);
	}

	@Transactional
	public JSONObject insertSelective(AttributeDef record) {

		JSONObject rtn = new JSONObject();

		try {

			String id=UUIDUtil.getUUID();
			record.setId(id);
			
			int rst = optionDefMapper.insertSelective(record);

			rtn.put("result", true);
			rtn.put("id", id);
			rtn.put("name", record.getName());

			return rtn;
		} catch (Exception e) {

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

	public AttributeDef selectByPrimaryKey(String id) {
		return optionDefMapper.selectByPrimaryKey(id);
	}

	@Transactional
	public JSONObject updateByPrimaryKeySelective(AttributeDef record) {

		JSONObject rtn = new JSONObject();

		try {

			
			optionDefMapper.updateByPrimaryKeySelective(record);
			
			rtn.put("result", true);

			return rtn;
		} catch (Exception e) {

			// logger.error("更新出错", e);
			try {
				rtn.put("result", false);
				rtn.put("message", "更新出错");
			} catch (Exception e2) {
				// TODO: handle exception
			}
			return rtn;

		}

	}

	public int updateByPrimaryKey(AttributeDef record) {
		return optionDefMapper.updateByPrimaryKey(record);
	}

	/**
	 * 条件查询
	 * 
	 * @param optionDef
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	public List<AttributeDef> selectAttributeDefList(AttributeDef optionDef) {
		return optionDefMapper.selectAttributeDefList(optionDef);
	}

}
