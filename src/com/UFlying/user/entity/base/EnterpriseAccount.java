package com.UFlying.user.entity.base;

import java.sql.Date;
import java.sql.Timestamp;

public class EnterpriseAccount implements java.io.Serializable {

	private static final long serialVersionUID = -4071327510053602285L;
	//企业信息部分
	private long eid;
	private String companyName;
	private String companyPhone;
	private String emailAddress;
	private String province;
	private String city;
	private String address;
	private int status;
	private String businessLicenceNumber;//工商注册号
	private String taxRegistrationNumber;//组织机构代码
	private String businessLicenceUrl;//营业执照扫描件地址
	private String taxRegistrationUrl;//组织机构代码扫描件地址
	private String headImgUrl;
	//联系人信息部分
	private String name;
	private String mobilePhone;
	private int sex;
	private String idCardNumber;
	private String idCardUrl1;
	private String idCardUrl2;
	//数据库控制信息部分
	private String token;
	private int cookieLifecycle;
	private Date registerDate;
	private Date certifyDate;
	private Date contractDate;
	private Timestamp lastTimeStamp;
	public long getEid() {
		return eid;
	}
	public void setEid(long eid) {
		this.eid = eid;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getBusinessLicenceNumber() {
		return businessLicenceNumber;
	}
	public void setBusinessLicenceNumber(String businessLicenceNumber) {
		this.businessLicenceNumber = businessLicenceNumber;
	}
	public String getTaxRegistrationNumber() {
		return taxRegistrationNumber;
	}
	public void setTaxRegistrationNumber(String taxRegistrationNumber) {
		this.taxRegistrationNumber = taxRegistrationNumber;
	}
	public String getBusinessLicenceUrl() {
		return businessLicenceUrl;
	}
	public void setBusinessLicenceUrl(String businessLicenceUrl) {
		this.businessLicenceUrl = businessLicenceUrl;
	}
	public String getTaxRegistrationUrl() {
		return taxRegistrationUrl;
	}
	public void setTaxRegistrationUrl(String taxRegistrationUrl) {
		this.taxRegistrationUrl = taxRegistrationUrl;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
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
	public int getCookieLifecycle() {
		return cookieLifecycle;
	}
	public void setCookieLifecycle(int cookieLifecycle) {
		this.cookieLifecycle = cookieLifecycle;
	}	
	
}
