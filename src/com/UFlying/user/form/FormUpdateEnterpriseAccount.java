package com.UFlying.user.form;

import org.springframework.web.multipart.MultipartFile;

public class FormUpdateEnterpriseAccount {
	//企业相关信息部分
	private long eid;
	private String companyName;
	private String companyPhone;
	private String email;
	private String address;
	private String province;
	private String city;
	private int status;
	private String businessLicenceNumber;
	private String taxRegistrationNumber;
	private MultipartFile businessLicenceImg;
	private MultipartFile taxRegistrationImg;
	private String businessLicenceUrl;
	private String taxRegistrationUrl;
	private MultipartFile photo;//企业Logo
	private String photoUrl;
	//联系人相关信息部分
	private String name;
	private String phone;
	private int sex;
	private String idCardNumber;
	private MultipartFile identity_front;
	private MultipartFile identity_back;
	private String idCardUrl1;
	private String idCardUrl2;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public MultipartFile getBusinessLicenceImg() {
		return businessLicenceImg;
	}
	public void setBusinessLicenceImg(MultipartFile businessLicenceImg) {
		this.businessLicenceImg = businessLicenceImg;
	}
	public MultipartFile getTaxRegistrationImg() {
		return taxRegistrationImg;
	}
	public void setTaxRegistrationImg(MultipartFile taxRegistrationImg) {
		this.taxRegistrationImg = taxRegistrationImg;
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
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public MultipartFile getIdentity_front() {
		return identity_front;
	}
	public void setIdentity_front(MultipartFile identity_front) {
		this.identity_front = identity_front;
	}
	public MultipartFile getIdentity_back() {
		return identity_back;
	}
	public void setIdentity_back(MultipartFile identity_back) {
		this.identity_back = identity_back;
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

}
