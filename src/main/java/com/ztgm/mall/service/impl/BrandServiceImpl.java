package com.ztgm.mall.service.impl;

import com.ztgm.mall.dao.BrandMapper;

import com.ztgm.mall.dao.ManagerMapper;
import com.ztgm.mall.pojo.Brand;
import com.ztgm.mall.service.BrandService;
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
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandMapper brandMapper;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int deleteByPrimaryKey(String id) {
		return brandMapper.deleteByPrimaryKey(id);
	}

	public int insert(Brand record) {
		return brandMapper.insert(record);
	}

	@Transactional
	public JSONObject insertSelective(Brand record) {

		JSONObject rtn = new JSONObject();

		try {
			record.setId(UUIDUtil.getUUID());
			int rst = brandMapper.insertSelective(record);

			rtn.put("result", true);

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

	public Brand selectByPrimaryKey(String id) {
		return brandMapper.selectByPrimaryKey(id);
	}

	@Transactional
	public JSONObject updateByPrimaryKeySelective(Brand record) {

		JSONObject rtn = new JSONObject();

		try {

			
			brandMapper.updateByPrimaryKeySelective(record);
			
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

	public int updateByPrimaryKey(Brand record) {
		return brandMapper.updateByPrimaryKey(record);
	}

	/**
	 * 条件查询
	 * 
	 * @param optionDef
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	public List<Brand> selectBrandList(Brand optionDef) {
		return brandMapper.selectBrandList(optionDef);
	}

	@Override
	public List<Brand> getClassifyBrands(Integer level,String classifyId) {
		return brandMapper.getClassifyBrands(level,classifyId);
	}

}
