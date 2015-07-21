package com.UFlying.config;

/** 第三方责任险类型 */
public class ThirdpartyInsuranceType {

	private int id; // 编号
	private int fee; // 保费
	private String description; // 描述

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
