package com.UFlying.user.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.UFlying.config.Constants;
import com.UFlying.user.entity.base.IndividualAccount;
import com.UFlying.user.util.PropertiesHelper;

@Repository
public class IndividualAccountMapper implements RowMapper<IndividualAccount> {

	private static final String ACCOUNT_IMAGE_URL = PropertiesHelper.get("account.image.url").toString();
	
	public IndividualAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		IndividualAccount account = new IndividualAccount();
		account.setUid(rs.getInt("uid"));
		account.setName(rs.getString("name"));
		account.setSex(rs.getInt("sex"));
		account.setStatus(rs.getInt("status"));
		account.setUnsigned(rs.getInt("unsigned"));
		account.setEmailAddress(rs.getString("email_address"));
		account.setQq(rs.getString("qq"));
		account.setAddress(rs.getString("address"));
		account.setProvince(rs.getString("address_province"));
		account.setCity(rs.getString("address_city"));
		account.setToken(rs.getString("token"));
		account.setMobilePhone(rs.getString("mobile_phone"));
		account.setIdCardNumber(rs.getString("id_card_number"));
		account.setHeadImgUrl((null==rs.getString("head_image_url"))?null:ACCOUNT_IMAGE_URL + rs.getString("head_image_url"));
		account.setIdCardUrl1((null==rs.getString("id_card_url_1"))?null:ACCOUNT_IMAGE_URL + rs.getString("id_card_url_1"));
		account.setIdCardUrl2((null==rs.getString("id_card_url_2"))?null:ACCOUNT_IMAGE_URL + rs.getString("id_card_url_2"));
		account.setExperience(rs.getInt("experience"));
		account.setCookieLifecycle(Constants.COOKIE_TOKEN_MAX_AGE);
		account.setRegisterDate(rs.getDate("register_date"));
		account.setCertifyDate(rs.getDate("certify_date"));
		account.setContractDate(rs.getDate("contract_date"));
		account.setContractVersion(rs.getString("contract_version"));
		account.setLastTimeStamp(rs.getTimestamp("lasttimestamp"));
		return account;
	}

}
