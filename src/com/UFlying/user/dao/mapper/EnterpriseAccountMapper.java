package com.UFlying.user.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.UFlying.user.entity.base.EnterpriseAccount;


@Repository
public class EnterpriseAccountMapper implements RowMapper<EnterpriseAccount> {

	public EnterpriseAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		EnterpriseAccount account = new EnterpriseAccount();
		account.setEid(rs.getInt("eid"));
		account.setName(rs.getString("name"));
		account.setToken(rs.getString("token"));
		account.setCompanyName(rs.getString("company_name"));
		account.setCompanyPhone(rs.getString("company_phone"));
		account.setEmailAddress(rs.getString("email_address"));
		account.setMobilePhone(rs.getString("mobile_phone"));
		account.setBusinessLicenceNumber(rs.getString("enterprise_card_number"));
		account.setTaxRegistrationNumber(rs.getString("organize_code"));
		account.setBusinessLicenceUrl(rs.getString("business_licence_url"));
		account.setTaxRegistrationUrl(rs.getString("tax_registration_url"));
		account.setHeadImgUrl(rs.getString("head_image_url"));
		account.setProvince(rs.getString("address_province"));
		account.setCity(rs.getString("address_city"));
		account.setAddress(rs.getString("address"));
		account.setSex(rs.getInt("sex"));
		account.setStatus(rs.getInt("status"));
		account.setIdCardNumber(rs.getString("id_card_number"));
		account.setIdCardUrl1(rs.getString("id_card_url_1"));
		account.setIdCardUrl2(rs.getString("id_card_url_2"));
		account.setRegisterDate(rs.getDate("register_date"));
		account.setCertifyDate(rs.getDate("certify_date"));
		account.setContractDate(rs.getDate("contract_date"));
		account.setLastTimeStamp(rs.getTimestamp("lasttimestamp"));
		return account;
	}

}
