package com.ztgm.mall.pojo;

import java.util.ArrayList;
import java.util.List;

public class CommodityOption {
    private String id;

    private String commondityId;

    private String optionDefId;

    private String value;

    private Integer sort;
    
	private Integer status;
    
    private OptionDef option;
    
    //同一商品-同一选项下的所有值
    private List<CommodityOption> optionvals=new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCommondityId() {
        return commondityId;
    }

    public void setCommondityId(String commondityId) {
        this.commondityId = commondityId == null ? null : commondityId.trim();
    }

    public String getOptionDefId() {
        return optionDefId;
    }

    public void setOptionDefId(String optionDefId) {
        this.optionDefId = optionDefId == null ? null : optionDefId.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

	public OptionDef getOption() {
		return option;
	}

	public void setOption(OptionDef option) {
		this.option = option;
	}

	public List<CommodityOption> getOptionvals() {
		return optionvals;
	}

	public void setOptionvals(List<CommodityOption> optionvals) {
		this.optionvals = optionvals;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}