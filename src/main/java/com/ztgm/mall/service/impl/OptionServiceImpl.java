package com.ztgm.mall.service.impl;

import com.ztgm.mall.dao.OptionDefMapper;

import com.ztgm.mall.dao.ManagerMapper;
import com.ztgm.mall.pojo.OptionDef;
import com.ztgm.mall.service.OptionService;
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
public class OptionServiceImpl implements OptionService {

	@Autowired
	private OptionDefMapper optionDefMapper;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int deleteByPrimaryKey(String id) {
		return optionDefMapper.deleteByPrimaryKey(id);
	}

	public int insert(OptionDef record) {
		return optionDefMapper.insert(record);
	}

	@Transactional
	public JSONObject insertSelective(OptionDef record) {

		JSONObject rtn = new JSONObject();

		try {
			String id = UUIDUtil.getUUID();
			record.setId(id);
			int rst = optionDefMapper.insertSelective(record);

			if (rst > 0) {
				rtn.put("result", true);

				rtn.put("id", id);
				rtn.put("name", record.getName());
			}else
			{
				rtn.put("result", false);
			}

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

	public OptionDef selectByPrimaryKey(String id) {
		return optionDefMapper.selectByPrimaryKey(id);
	}

	@Transactional
	public JSONObject updateByPrimaryKeySelective(OptionDef record) {

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

	public int updateByPrimaryKey(OptionDef record) {
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
	public List<OptionDef> selectOptionDefList(OptionDef optionDef) {
		return optionDefMapper.selectOptionDefList(optionDef);
	}

}
