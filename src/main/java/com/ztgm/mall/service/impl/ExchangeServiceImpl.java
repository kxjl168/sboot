package com.ztgm.mall.service.impl;

import com.ztgm.mall.dao.OrderExchangeMapper;
import com.ztgm.mall.dao.ManagerMapper;
import com.ztgm.mall.dao.OrderDetailMapper;
import com.ztgm.mall.dao.OrderExchangeDetailMapper;
import com.ztgm.mall.pojo.OrderExchange;
import com.ztgm.mall.pojo.OrderExchangeDetail;
import com.ztgm.mall.service.ExchangeService;
import com.ztgm.mall.service.OptionService;
import com.ztgm.mall.util.UUIDUtil;
import com.ztgm.mall.pojo.Manager;
import com.ztgm.mall.pojo.OrderDetail;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeServiceImpl implements ExchangeService {

	@Autowired
	private OrderExchangeMapper exchangeMapper;

	@Autowired
	private OrderExchangeDetailMapper exchangeDetailMapper;
	
	@Autowired
	private OrderDetailMapper orderDetailMapper;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int deleteByPrimaryKey(String id) {
		return exchangeMapper.deleteByPrimaryKey(id);
	}

	@Transactional
	public JSONObject insertSelective(OrderExchange record) {

		JSONObject rtn = new JSONObject();

		try {
			String id = UUIDUtil.getUUID();
			record.setId(id);
			int rst = exchangeMapper.insertSelective(record);

			//退还详情
			OrderExchangeDetail detail = new OrderExchangeDetail();
			detail.setId(UUIDUtil.getUUID());
			detail.setOrderExchangelId(id);
			detail.setRemark(record.getRemark());
			detail.setState(0);
			detail.setUserId(record.getUserId());
			exchangeDetailMapper.insertSelective(detail);
			
			//订单详情表识
			OrderDetail odetail=new OrderDetail();
			odetail.setId(record.getOrderDetailId());
			odetail.setState(1);//有退换货记录
			orderDetailMapper.updateByPrimaryKeySelective(odetail);

			if (rst > 0) {
				rtn.put("result", true);

			} else {
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

	public OrderExchange selectByPrimaryKey(String id) {
		return exchangeMapper.selectByPrimaryKey(id);
	}

	@Transactional
	public JSONObject updateByPrimaryKeySelective(OrderExchange record) {

		JSONObject rtn = new JSONObject();

		try {

			exchangeMapper.updateByPrimaryKeySelective(record);

			//退还详情
			OrderExchangeDetail detail = new OrderExchangeDetail();
			detail.setId(UUIDUtil.getUUID());
			detail.setOrderExchangelId(record.getId());
			detail.setRemark(record.getRemark());
			detail.setState(record.getState());
			detail.setUserId(record.getUserId());
			exchangeDetailMapper.insertSelective(detail);
			
			
			//订单详情表识
			OrderDetail odetail=new OrderDetail();
			odetail.setId(record.getOrderDetailId());
			odetail.setState(1);//有退换货记录
			orderDetailMapper.updateByPrimaryKeySelective(odetail);
			
			
			
			
			

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

	@Override
	public List<OrderExchange> selectOrderExchangeList(OrderExchange role) {
		return exchangeMapper.selectOrderExchangeList(role);
	}

}
