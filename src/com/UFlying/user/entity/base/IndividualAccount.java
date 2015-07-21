package com.UFlying.user.entity.base;

import java.sql.Date;
import java.sql.Timestamp;

public class IndividualAccount implements java.io.Serializable {

	private static final long serialVersionUID = -5056169087474238974L;

	private long uid;
	private String name;
	private String token;
	private int sex;
	private int status;
	private int unsigned;
	private String mobilePhone;
	private String emailAddress;
	private String address;
	private String province;
	private String city;
	private String idCardNumber;
	private String idCardUrl1;
	private String idCardUrl2;
	private String headImgUrl;
	private int experience;
	private Date registerDate;
	private Date certifyDate;
	private Date contractDate;
	private String contractVersion;
	private Timestamp lastTimeStamp;
	
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getCertifyDate() {
		return certifyDate;
	}

	public void setCertifyDate(Date certifyDate) {
		this.certifyDate = certifyDate;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public Timestamp getLastTimeStamp() {
		return lastTimeStamp;
	}

	public void setLastTimeStamp(Timestamp lastTimeStamp) {
		this.lastTimeStamp = lastTimeStamp;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUnsigned() {
		return unsigned;
	}

	public void setUnsigned(int unsigned) {
		this.unsigned = unsigned;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getIdCardUrl1() {
		return idCardUrl1;
	}

	public void setIdCardUrl1(String idCardUrl1) {
		this.idCardUrl1 = idCardUrl1;
	}

	public String getIdCardUrl2() {
		return idCardUrl2;
	}

	public void setIdCardUrl2(String idCardUrl2) {
		this.idCardUrl2 = idCardUrl2;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
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

	public String getContractVersion() {
		return contractVersion;
	}

	public void setContractVersion(String contractVersion) {
		this.contractVersion = contractVersion;
	}

}
