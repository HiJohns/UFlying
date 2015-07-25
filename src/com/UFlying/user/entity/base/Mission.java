package com.UFlying.user.entity.base;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Mission implements Serializable {
	private static final long serialVersionUID = -2663340649108527831L;
	
	private String missionId;
	private int ordererId;
	private int missionType;
	private int place;
	private Date missionDate;
	private Time startTime;
	private Time endTime;
	private String addressProvince;
	private String addressCity;
	private String address;
	private String remark;
	private int peyment;
	private int status;
	private int carryerId;
	private Timestamp orderDatetime;
	private Timestamp payDatetime;
	private Timestamp carryDatetime;
	private Timestamp startDatetime;
	private Timestamp endDatetime;
	private Timestamp finishedDatetime;
	private String comment;
	private Timestamp lasttimestamp;
	public String getMissionId() {
		return missionId;
	}
	public void setMissionId(String missionId) {
		this.missionId = missionId;
	}
	public int getOrdererId() {
		return ordererId;
	}
	public void setOrdererId(int ordererId) {
		this.ordererId = ordererId;
	}
	public int getMissionType() {
		return missionType;
	}
	public void setMissionType(int missionType) {
		this.missionType = missionType;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
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
	public String getAddressProvince() {
		return addressProvince;
	}
	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}
	public String getAddressCity() {
		return addressCity;
	}
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getPeyment() {
		return peyment;
	}
	public void setPeyment(int peyment) {
		this.peyment = peyment;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCarryerId() {
		return carryerId;
	}
	public void setCarryerId(int carryerId) {
		this.carryerId = carryerId;
	}
	public Timestamp getOrderDatetime() {
		return orderDatetime;
	}
	public void setOrderDatetime(Timestamp orderDatetime) {
		this.orderDatetime = orderDatetime;
	}
	public Timestamp getPayDatetime() {
		return payDatetime;
	}
	public void setPayDatetime(Timestamp payDatetime) {
		this.payDatetime = payDatetime;
	}
	public Timestamp getCarryDatetime() {
		return carryDatetime;
	}
	public void setCarryDatetime(Timestamp carryDatetime) {
		this.carryDatetime = carryDatetime;
	}
	public Timestamp getStartDatetime() {
		return startDatetime;
	}
	public void setStartDatetime(Timestamp startDatetime) {
		this.startDatetime = startDatetime;
	}
	public Timestamp getEndDatetime() {
		return endDatetime;
	}
	public void setEndDatetime(Timestamp endDatetime) {
		this.endDatetime = endDatetime;
	}
	public Timestamp getFinishedDatetime() {
		return finishedDatetime;
	}
	public void setFinishedDatetime(Timestamp finishedDatetime) {
		this.finishedDatetime = finishedDatetime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Timestamp getLasttimestamp() {
		return lasttimestamp;
	}
	public void setLasttimestamp(Timestamp lasttimestamp) {
		this.lasttimestamp = lasttimestamp;
	}

}
