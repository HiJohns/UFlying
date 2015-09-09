package com.UFlying.db.entity;

public class Region implements java.io.Serializable {
	
	private static final long serialVersionUID = 410672181188330845L;

	private int regionId;
	private int parentId;
	private String regionName;
	private int regionType;
	
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public int getRegionType() {
		return regionType;
	}
	public void setRegionType(int regionType) {
		this.regionType = regionType;
	}

	
}
