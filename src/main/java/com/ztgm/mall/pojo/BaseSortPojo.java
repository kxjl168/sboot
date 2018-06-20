package com.ztgm.mall.pojo;

/**
 * 列表查询排序字段
 * @author zj
 * @date 2018年6月13日
 *
 */
public class BaseSortPojo {
	// 排序字段
	private String sortName;
	private String sortOrder;
	
	

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

}
