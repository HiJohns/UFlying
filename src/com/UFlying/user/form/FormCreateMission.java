package com.UFlying.user.form;

import java.sql.Date;
import java.sql.Time;

public class FormCreateMission {
	//登录用
	private String user;
	private String password;
	//任务用
	private int missionType;
	private Date missionDate;
	private Time startTime;
	private Time endTime;
	private String address;
	private String province;
	private String city;
	private int place;//1：室内，2：室外，3:室内＋室外
	private String remark;//其他要求备注
	private String misssionId;
	private int payment;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMissionType() {
		return missionType;
	}
	public void setMissionType(int missionType) {
		this.missionType = missionType;
	}
	public Date getMissionDate() {
		return missionDate;
	}
	public void setMissionDate(Date missionDate) {
		this.missionDate = missionDate;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMisssionId() {
		return misssionId;
	}
	public void setMisssionId(String misssionId) {
		this.misssionId = misssionId;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	
	
	
	

}
