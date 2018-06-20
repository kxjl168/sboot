package com.ztgm.mall.service.impl;

import com.github.pagehelper.Page;
 import com.ztgm.mall.dao.EstimateMapper;

import com.ztgm.mall.pojo.Estimate;
import com.ztgm.mall.pojo.PageCondition;
import com.ztgm.mall.service.EstimateService;
import com.ztgm.mall.util.PageUtil;
import com.ztgm.mall.util.UUIDUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static com.ztgm.mall.util.PageUtil.*;

/**
 * @author handsome Xiao
 */
@Service
public class EstimateServiceImpl implements EstimateService {

    @Autowired
    private EstimateMapper estimateMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 按条件查询评价
     * @param estimate
     * @Param page
     * @return
     */
    @Override
    public String showEstimate(Estimate estimate, PageCondition pageCondition) {
        String resultString = "";
        String offset = pageCondition.getOffset();
        String pageSize = pageCondition.getPageSize();
        Page page = getPage(offset, pageSize);
        List<Estimate> estimateList = estimateMapper.showEstimate(estimate);
        try {
            resultString = packageTableData(page, estimateList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    @Override
    public int deleteEstimate(Estimate estimate) {
        return estimateMapper.deleteEstimate (estimate);
    }
    
    @Transactional
	public JSONObject insertSelective(Estimate record) {

		JSONObject rtn = new JSONObject();

		try {
			record.setId(UUIDUtil.getUUID());
			int rst = estimateMapper.insertSelective(record);

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

    
    @Transactional
 	public JSONObject insertSelective(List<Estimate> records) {

 		JSONObject rtn = new JSONObject();

 		try {
 			for (int i = 0; i < records.size(); i++) {
 				Estimate record=records.get(i);
 				record.setId(UUIDUtil.getUUID());
 	 			int rst = estimateMapper.insertSelective(record);

			}
 			
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
    
	public Estimate selectByPrimaryKey(String id) {
		return estimateMapper.selectByPrimaryKey(id);
	}

	@Transactional
	public JSONObject updateByPrimaryKeySelective(Estimate record) {

		JSONObject rtn = new JSONObject();

		try {

			
			estimateMapper.updateByPrimaryKeySelective(record);
			
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

	public int updateByPrimaryKey(Estimate record) {
		return estimateMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}




}
