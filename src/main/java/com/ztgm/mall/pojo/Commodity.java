package com.ztgm.mall.pojo;

import java.util.ArrayList;
import java.util.List;

import com.ztgm.mall.util.DateUtil;

public class Commodity extends BaseSortPojo {
	private String id;

	private String name;

	private String fstClassify;

	private String scdClassify;

	private String thdClassify;

	private String trademarkId;

	private String unit;

	private String code;

	private String merchantId;

	private String introduction;

	private Integer status = 1;

	private String createTime;

	private String customerCare;

	// query
	
	private String createTimeStr;
	// 关联
	private Brand brand = new Brand();

	private CommodityFirstClassify firstClass = new CommodityFirstClassify();
	private CommoditySecondClassify secondClass = new CommoditySecondClassify();
	private CommodityThirdClassify thirdClass = new CommodityThirdClassify();

	private List<CommodityAttribute> commodityAttributes = new ArrayList<>();
	private List<CommodityOption> commodityOptions = new ArrayList<>();
	private List<CommondityImage> commondityImages = new ArrayList<>();
	private List<CommondityAttach> commondityAttachs = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getFstClassify() {
		return fstClassify;
	}

	public void setFstClassify(String fstClassify) {
		this.fstClassify = fstClassify == null ? null : fstClassify.trim();
	}

	public String getScdClassify() {
		return scdClassify;
	}

	public void setScdClassify(String scdClassify) {
		this.scdClassify = scdClassify == null ? null : scdClassify.trim();
	}

	public String getThdClassify() {
		return thdClassify;
	}

	public void setThdClassify(String thdClassify) {
		this.thdClassify = thdClassify == null ? null : thdClassify.trim();
	}

	public String getTrademarkId() {
		return trademarkId;
	}

	public void setTrademarkId(String trademarkId) {
		this.trademarkId = trademarkId == null ? null : trademarkId.trim();
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId == null ? null : merchantId.trim();
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction == null ? null : introduction.trim();
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public List<CommodityAttribute> getCommodityAttributes() {
		return commodityAttributes;
	}

	public void setCommodityAttributes(List<CommodityAttribute> commodityAttributes) {
		this.commodityAttributes = commodityAttributes;
	}

	public List<CommodityOption> getCommodityOptions() {
		return commodityOptions;
	}

	public void setCommodityOptions(List<CommodityOption> commodityOptions) {
		this.commodityOptions = commodityOptions;
	}

	public List<CommondityImage> getCommondityImages() {
		return commondityImages;
	}

	public void setCommondityImages(List<CommondityImage> commondityImages) {
		this.commondityImages = commondityImages;
	}

	public CommodityFirstClassify getFirstClass() {
		return firstClass;
	}

	public void setFirstClass(CommodityFirstClassify firstClass) {
		this.firstClass = firstClass;
	}

	public CommoditySecondClassify getSecondClass() {
		return secondClass;
	}

	public void setSecondClass(CommoditySecondClassify secondClass) {
		this.secondClass = secondClass;
	}

	public CommodityThirdClassify getThirdClass() {
		return thirdClass;
	}

	public void setThirdClass(CommodityThirdClassify thirdClass) {
		this.thirdClass = thirdClass;
	}

	public List<CommondityAttach> getCommondityAttachs() {
		return commondityAttachs;
	}

	public void setCommondityAttachs(List<CommondityAttach> commondityAttachs) {
		this.commondityAttachs = commondityAttachs;
	}

	public String getCustomerCare() {
		return customerCare;
	}

	public void setCustomerCare(String customerCare) {
		this.customerCare = customerCare;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
		this.createTimeStr=DateUtil.getDateStr(DateUtil.getDate(createTime,"yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
}