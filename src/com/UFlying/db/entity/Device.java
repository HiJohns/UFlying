package com.UFlying.db.entity;

import java.io.Serializable;

public class Device implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7431427216292469030L;
	
	private int deviceId ;
	private int brandId;
	private String brandName;
	private String brandShortName;
	private String brandEnglishName;
	private String deviceName;
	private String model;
	private int weight;
	private int price;
	private String type;
	private String photoUrl;
	
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandShortName() {
		return brandShortName;
	}
	public void setBrandShortName(String brandShortName) {
		this.brandShortName = brandShortName;
	}
	public String getBrandEnglishName() {
		return brandEnglishName;
	}
	public void setBrandEnglishName(String brandEnglishName) {
		this.brandEnglishName = brandEnglishName;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
}
