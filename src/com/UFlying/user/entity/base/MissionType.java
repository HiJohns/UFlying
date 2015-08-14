package com.UFlying.user.entity.base;

import java.io.Serializable;

public class MissionType implements Serializable {
	private static final long serialVersionUID = 4280630088415784061L;
	
	private int missionType;//任务类型编号
	private String typeName;//任务类型名称
	private String typeInitials;//任务类型缩写
	private int standardFee;//标准费率（元）
	private int standardDuration;//标准费率对应任务时长（分钟）
	private int extraFee;//超出标准部分的费率（元）
	private int extraDuration;//超出费率对应时长（分钟）
	private int contractType;//所使用的合同类型
	
	public int getMissionType() {
		return missionType;
	}
	public void setMissionType(int missionType) {
		this.missionType = missionType;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeInitials() {
		return typeInitials;
	}
	public void setTypeInitials(String typeInitials) {
		this.typeInitials = typeInitials;
	}
	public int getStandardFee() {
		return standardFee;
	}
	public void setStandardFee(int standardFee) {
		this.standardFee = standardFee;
	}
	public int getStandardDuration() {
		return standardDuration;
	}
	public void setStandardDuration(int standardDuration) {
		this.standardDuration = standardDuration;
	}
	public int getExtraFee() {
		return extraFee;
	}
	public void setExtraFee(int extraFee) {
		this.extraFee = extraFee;
	}
	public int getExtraDuration() {
		return extraDuration;
	}
	public void setExtraDuration(int extraDuration) {
		this.extraDuration = extraDuration;
	}
	public int getContractType() {
		return contractType;
	}
	public void setContractType(int contractType) {
		this.contractType = contractType;
	}


}
