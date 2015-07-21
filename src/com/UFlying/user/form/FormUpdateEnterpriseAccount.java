package com.UFlying.user.form;

import org.springframework.web.multipart.MultipartFile;

public class FormUpdateEnterpriseAccount {

	private long eid;
	private String name;
	private String phone;
	private int sex;
	private String email;
	private String idCardNumber;
	private String address;
	private String province;
	private String city;
	private int status;
	private MultipartFile photo;
	private MultipartFile identity_front;
	private MultipartFile identity_back;
	private String photoUrl;
	private String idCardUrl1;
	private String idCardUrl2;
	private String enterpriseCardNumber;
	private String enterpriseNo1;
	private String enterpriseNo2;
	private MultipartFile businessLicenceImg;
	private MultipartFile taxRegistrationImg;
	
	public long getEid() {
		return eid;
	}

	public void setEid(long eid) {
		this.eid = eid;
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

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
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

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
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

	public String getEnterpriseNo1() {
		return enterpriseNo1;
	}

	public void setEnterpriseNo1(String enterpriseNo1) {
		this.enterpriseNo1 = enterpriseNo1;
	}

	public String getEnterpriseNo2() {
		return enterpriseNo2;
	}

	public void setEnterpriseNo2(String enterpriseNo2) {
		this.enterpriseNo2 = enterpriseNo2;
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

	public String getEnterpriseCardNumber() {
		return enterpriseCardNumber;
	}

	public void setEnterpriseCardNumber(String enterpriseCardNumber) {
		this.enterpriseCardNumber = enterpriseCardNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
