package com.ztgm.mall.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ztgm.mall.dao.CommodityAttributeMapper;
import com.ztgm.mall.dao.CommodityInstanceMapper;
import com.ztgm.mall.dao.CommodityMapper;
import com.ztgm.mall.dao.CommodityOptionMapper;
import com.ztgm.mall.dao.CommondityAttachMapper;
import com.ztgm.mall.dao.CommondityImageMapper;
import com.ztgm.mall.dao.CommondityInstanceOptionsMapper;
import com.ztgm.mall.dao.ManagerMapper;
import com.ztgm.mall.pojo.Commodity;
import com.ztgm.mall.pojo.CommodityAttribute;
import com.ztgm.mall.pojo.CommodityInstance;
import com.ztgm.mall.pojo.CommodityOption;
import com.ztgm.mall.pojo.CommondityAttach;
import com.ztgm.mall.pojo.CommondityImage;
import com.ztgm.mall.pojo.CommondityInstanceOptions;
import com.ztgm.mall.service.CommodityService;
import com.ztgm.mall.service.OptionService;
import com.ztgm.mall.util.UUIDUtil;
import com.ztgm.mall.util.md5.Md5Encrypt;
import com.ztgm.mall.pojo.Manager;

import org.apache.ibatis.annotations.Param;
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
public class CommodityServiceImpl implements CommodityService {

	@Autowired
	private CommodityMapper commodityMapper;
	@Autowired
	private CommodityInstanceMapper commodityInstanceMapper;

	@Autowired
	private CommondityInstanceOptionsMapper commondityInstanceOptionsMapper;

	@Autowired
	private CommondityImageMapper commondityImageMapper;

	@Autowired
	private CommondityAttachMapper commondityAttachMapper;

	@Autowired
	private CommodityAttributeMapper commodityAttributeMapper;

	@Autowired
	private CommodityOptionMapper commodityOptionMapper;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int deleteByPrimaryKey(String id) {

		/*
		 * // TODO 商品删除/逻辑？历史商品？ zj return commodityMapper.deleteByPrimaryKey(id);
		 */

		// instance 逻辑删除
		Commodity gd = commodityMapper.selectByPrimaryKey(id);
		if (gd != null)
			gd.setStatus(2);
		return commodityMapper.updateByPrimaryKeySelective(gd);
	}

	public int insert(Commodity record) {
		return commodityMapper.insert(record);
	}

	@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRES_NEW)
	public JSONObject insertSelective(Commodity record) {

		JSONObject rtn = new JSONObject();

		try {
			record.setId(UUIDUtil.getUUID());

			String goodid = UUIDUtil.getUUID();

			// 商品
			// record.setFstClassify("1");
			// record.setScdClassify("1");
			// record.setThdClassify("1");
			record.setId(goodid);
			record.setStatus(1);
			int rst1 = commodityMapper.insertSelective(record);

			dealImgOptionAttr(goodid, record);

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

	/**
	 * 处理图片选项属性附件及商品详情数据</br>
	 * <br>
	 * 1.处理图片 </br>
	 * 删除/再添加</br>
	 * </br>
	 * 2./属性/</br>
	 * 删除/再添加</br>
	 * </br>
	 * 3.选项/</br>
	 * 更新</br>
	 * </br>
	 * 
	 * 4.附件/</br>
	 * 删除/添加</br>
	 * </br>
	 * 
	 * 5.实例</br>
	 * 更新
	 * 
	 * @param goodid
	 * @param record
	 * @author zj
	 * @date 2018年6月8日
	 */
	@Transactional
	private void dealImgOptionAttr(String goodid, Commodity record) {
		// 图片
		commondityImageMapper.deleteByGoodsId(goodid);
		int sort_img = 1;
		//
		Iterator<CommondityImage> iterator = record.getCommondityImages().iterator();
		while (iterator.hasNext()) {
			CommondityImage img = iterator.next();
			if (img.getFileId() != null && !img.getFileId().equals("")) {
				img.setCommondityId(goodid);
				img.setId(UUIDUtil.getUUID());
				img.setSort(sort_img);

				sort_img++;
				commondityImageMapper.insertSelective(img);
			}
		}

		// 属性
		commodityAttributeMapper.deleteByGoodsId(goodid);
		Iterator<CommodityAttribute> iterator_attr = record.getCommodityAttributes().iterator();
		//
		while (iterator_attr.hasNext()) {
			CommodityAttribute item = iterator_attr.next();
			if (item.getAttributeDefId() != null && !item.getAttributeDefId().equals("")) {
				item.setCommodityId(goodid);
				item.setId(UUIDUtil.getUUID());
				commodityAttributeMapper.insertSelective(item);
			}
		}

		/*
		 * if(true) throw new RuntimeException("test exception");
		 */
		// 选项
		// int sort = 1;

		/*
		 * List<CommodityOption> exsitOptions =
		 * commodityOptionMapper.selectCommodityOptionListByCommodityId(goodid); sort =
		 * exsitOptions.size() + 1;// 继续排序
		 */ Iterator<CommodityOption> iterator_option = record.getCommodityOptions().iterator();
		//
		while (iterator_option.hasNext()) {
			CommodityOption item = iterator_option.next();

			if (item.getId() != null && item.getId() != "null" && !item.getId().equals("")) {
				commodityOptionMapper.updateByPrimaryKeySelective(item);
			} else if (item.getOptionDefId() != null && !item.getOptionDefId().equals("")) {
				item.setCommondityId(goodid);
				item.setId(UUIDUtil.getUUID());
				// item.setSort(sort);
				item.setStatus(1);
				// sort++;
				commodityOptionMapper.insertSelective(item);
			}
		}

		// 附件
		commondityAttachMapper.deleteByGoodsId(goodid);
		int sort_atach = 1;
		//
		Iterator<CommondityAttach> iterator_attach = record.getCommondityAttachs().iterator();
		while (iterator_attach.hasNext()) {
			CommondityAttach item = iterator_attach.next();
			if (item.getFileId() != null && !item.getFileId().equals("")) {
				item.setCommondityId(goodid);
				item.setId(UUIDUtil.getUUID());
				item.setSort(sort_atach);

				sort_atach++;
				commondityAttachMapper.insertSelective(item);
			}
		}

		// detail instance
		AddGoodsDetail(goodid, record.getCommodityOptions());

	}

	public Commodity selectByPrimaryKey(String id) {
		return commodityMapper.selectByPrimaryKey(id);
	}

	@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRES_NEW)
	public JSONObject updateByPrimaryKeySelective(Commodity record) {

		JSONObject rtn = new JSONObject();

		try {
			// record.setId(UUIDUtil.getUUID());

			String goodid = record.getId();

			// 商品
			// record.setFstClassify("1");
			// record.setScdClassify("1");
			// record.setThdClassify("1");
			record.setId(goodid);
			int rst1 = commodityMapper.updateByPrimaryKeySelective(record);

			dealImgOptionAttr(goodid, record);

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

	// 不同选项的全部组合
	private List<List<CommodityOption>> rst = new ArrayList<>();

	/**
	 * 检查是否已经有该选项组合 -排除倒叙组合
	 * 
	 * @param singelRst
	 * @return
	 * @author zj
	 * @date 2018年6月7日
	 */
	private boolean isHasOption(List<CommodityOption> singelRst, List<List<CommodityOption>> rst) {

		String ids = "";
		for (int j2 = 0; j2 < singelRst.size(); j2++) {
			ids += singelRst.get(j2).getId() + "/";
		}

		for (int i = 0; i < rst.size(); i++) {

			boolean hasAll = true;
			for (int j = 0; j < rst.get(i).size(); j++) {

				if (!ids.contains(rst.get(i).get(j).getId())) {
					hasAll = false;
					break;
				}
			}

			if (hasAll) {
				return hasAll;
			}

		}

		return false;
	}

	/**
	 * 查询目前库中实例详情是否已经包含 选项组合
	 * 
	 * @param singelRst
	 * @param detail
	 * @return
	 * @author zj
	 * @date 2018年6月7日
	 */
	private boolean isHasOptionInInstances(List<CommodityOption> singelRst, List<CommodityInstance> details) {

		// 当前组合
		String ids = "";
		for (int j2 = 0; j2 < singelRst.size(); j2++) {
			ids += singelRst.get(j2).getId() + "/";
		}

		for (int i = 0; i < details.size(); i++) {

			// 选项数量不等，直接不包含
			if (singelRst.size() != details.get(i).getCommondityInstanceOptions().size())
				continue;

			// 检查库中实例是否包含该组合
			boolean hasAll = true;
			for (int j = 0; j < details.get(i).getCommondityInstanceOptions().size(); j++) {

				if (!ids.contains(details.get(i).getCommondityInstanceOptions().get(j).getCommodity_option().getId())) {
					hasAll = false;
					break;
				}
			}

			if (hasAll) {
				return hasAll;
			}

		}

		return false;
	}

	/**
	 * 递归获取所有选项组合
	 * 
	 * @param singelRst
	 * @param opts
	 * @author zj
	 * @date 2018年6月7日
	 */
	private void getCommodityOptionCompat(List<CommodityOption> singelRst, List<CommodityOption> opts) {

		// 所有选项都选择后跳出递归，列入选项组合表
		if (singelRst.size() == opts.size()) {
			if (isHasOption(singelRst, rst))
				return;

			rst.add(singelRst);

			/**
			 * print logger for (int i = 0; i < singelRst.size(); i++) {
			 * System.out.print(singelRst.get(i).getId() + ":" + singelRst.get(i).getValue()
			 * + " / "); } System.out.println();
			 **/
			return;
		}

		for (int i = 0; i < opts.size(); i++) {

			// 判断是否含有第i组选项值
			String optid = opts.get(i).getOptionvals().get(0).getOptionDefId();

			boolean has = false;
			for (int j = 0; j < singelRst.size(); j++) {
				if (singelRst.get(j).getOptionDefId().equals(optid)) {
					has = true;
					break;
				}
			}

			// 没有第i组选项-则添加第i组的选项，并继续递归/计算是否有下一组选项值
			if (!has) {

				for (int j = 0; j < opts.get(i).getOptionvals().size(); j++) {
					List<CommodityOption> tp = new ArrayList<>();
					for (CommodityOption commodityOption2 : singelRst) {
						tp.add(commodityOption2);
					}

					tp.add(opts.get(i).getOptionvals().get(j));

					getCommodityOptionCompat(tp, opts);
				}

			}

		}

	}

	/**
	 * 根据商品选项-增量添加不重复的选项组合 详情及商品详情
	 * 
	 * @param goodid
	 * @param options
	 * @author zj
	 * @date 2018年6月7日
	 */
	@Transactional
	private void AddGoodsDetail(String goodid, List<CommodityOption> options) {

		// 获取传入的distinct optiondef
		List<CommodityOption> distinctOpts = new ArrayList<>();
		List<String> distinctOptids = new ArrayList<>();
		for (int i = 0; i < options.size(); i++) {
			if (!distinctOptids.contains(options.get(i).getOptionDefId())) {
				distinctOptids.add(options.get(i).getOptionDefId());
				distinctOpts.add(options.get(i));
			}
		}

		// commodityOptionMapper.selectDistinctCommodityOptionListByCommodityId(goodid);

		// 构建树型数据
		for (int i = 0; i < distinctOpts.size(); i++) {

			CommodityOption optleve1 = distinctOpts.get(i);

			for (int j = 0; j < options.size(); j++) {

				CommodityOption optleve2 = options.get(j);
				if (optleve1.getOptionDefId().equals(optleve2.getOptionDefId())) {
					optleve1.getOptionvals().add(optleve2);
				}
			}
		}

		rst = new ArrayList<>();

		// 获取所有选项组合
		getCommodityOptionCompat(new ArrayList<CommodityOption>(), distinctOpts);

		// 已有实例详情
		CommodityInstance cq = new CommodityInstance();
		cq.setCommodityId(goodid);
		List<CommodityInstance> instances = commodityInstanceMapper.selectCommodityInstanceList(cq);

		for (List<CommodityOption> opts : rst) {

			// 添加商品实例-添加新的选项组合（目前库中不存在的组合）

			if (instances == null || instances.size() == 0) {
				// 没有实例直接添加
				// 实列
				addCommodityInstance(goodid, opts);

			} else {

				// 检查目前选项组合是否已经存在
				boolean has = isHasOptionInInstances(opts, instances);
				if (!has) {

					// 实列
					addCommodityInstance(goodid, opts);
				}

			}

		}

	}

	/**
	 * 添加商品实例及 选项实例
	 * 
	 * @param goodid
	 * @param opts
	 * @author zj
	 * @date 2018年6月8日
	 */
	@Transactional
	private void addCommodityInstance(String goodid, List<CommodityOption> opts) {
		// 实列
		CommodityInstance detail = new CommodityInstance();
		String detailid = UUIDUtil.getUUID();
		detail.setId(detailid);
		detail.setCommodityId(goodid);
		detail.setPrice(0D);// 默认0元
		detail.setStatus(2);// 下架

		commodityInstanceMapper.insertSelective(detail);

		// 商品选项实例
		for (CommodityOption commodityOption : opts) {

			CommondityInstanceOptions optionDetail = new CommondityInstanceOptions();
			optionDetail.setId(UUIDUtil.getUUID());
			optionDetail.setCommodityOptionId(commodityOption.getId());
			optionDetail.setCommondityInstanceId(detailid);
			// optionDetail.setSort(commodityOption.getSort());

			String md5=Md5Encrypt.MD5(commodityOption.getId()+commodityOption.getValue(),false);
			optionDetail.setOptionValMd5(md5);
			
			
			commondityInstanceOptionsMapper.insertSelective(optionDetail);

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
	public List<Commodity> selectCommodityList(Commodity optionDef) {
		return commodityMapper.selectCommodityList(optionDef);
	}

	/**
	 * 逻辑删除商品选项
	 * 
	 * @param id
	 * @return
	 * @author zj
	 * @date 2018年6月8日
	 */
	public int deleteOptionLogicalByPrimaryKey(String id) {
		CommodityOption option = new CommodityOption();
		option.setId(id);
		option.setStatus(2);
		return commodityOptionMapper.updateByPrimaryKeySelective(option);
	}

	@Override
	public Page<CommodityInstance> selectCommodityListByClassify(Integer level,
																 Integer sort,
																 Integer page,
																 String brand,
																 String option,
																 String classifyId,
																 Integer limit) {
		PageHelper.startPage(page, limit);
		List<CommodityInstance> list = commodityInstanceMapper.selectCommodityListByClassify(level ,sort ,brand ,option , classifyId);

		return (Page<CommodityInstance>) list;
	}



	@Override
	public List getOptionsByClassify(Integer level, String id) {
		Map conditions = new HashMap();
		conditions.put("level",level);
		conditions.put("classifyId",id);
		conditions.put("flag","opt");

		List options = commondityInstanceOptionsMapper.getOptionsByClassify(conditions);
		//商品选项值
		Map option;
		Map map = new HashMap();
		map.put("flag","optVal");
		List optVals;
		for(int i=0;null!=options && i<options.size();i++){
			option = (Map)options.get(i);
			map.put("optDefId",option.get("id"));
			optVals = commondityInstanceOptionsMapper.getOptionsByClassify(map);

			option.put("optVals",optVals);
		}

		return options;
	}
}
