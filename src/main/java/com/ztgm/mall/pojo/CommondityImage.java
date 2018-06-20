package com.ztgm.mall.pojo;

public class CommondityImage {
    private String id;

    private String commondityId;

    private String fileId;
    
    private Integer sort;
    
    private SvrFileInfo fileinfo;

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

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

	public SvrFileInfo getFileinfo() {
		return fileinfo;
	}

	public void setFileinfo(SvrFileInfo fileinfo) {
		this.fileinfo = fileinfo;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}