package com.UFlying.user.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.UFlying.user.dao.common.CommonDao;
import com.UFlying.user.dao.mapper.EnterpriseAccountMapper;
import com.UFlying.user.dao.mapper.IndividualAccountMapper;
import com.UFlying.user.entity.base.EnterpriseAccount;
import com.UFlying.user.entity.base.IndividualAccount;

/** 用户表，包括个人用户和企业用户 */
@Repository
public class AccountDao extends CommonDao {

	@Autowired
	private IndividualAccountMapper individualAccountMapper;

	@Autowired
	private EnterpriseAccountMapper enterpriseAccountMapper;

	/** 通过id查询个人用户 */
	public IndividualAccount getIndividualAccountById(long uid) {
		String sql = "select * from individual_user_account where uid = ?";
		List<IndividualAccount> list = this.getJdbcTemplate().query(sql, individualAccountMapper, uid);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/** 通过id查询企业用户 */
	public EnterpriseAccount getEnterpriseAccountById(long eid) {
		String sql = "select * from enterprise_user_account where eid = ?";
		List<EnterpriseAccount> list = this.getJdbcTemplate().query(sql, enterpriseAccountMapper, eid);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/** 通过token查询个人用户 */
	public IndividualAccount getIndividualAccountByToken(String token) {
		String sql = "select * from individual_user_account where token = ?";
		List<IndividualAccount> list = this.getJdbcTemplate().query(sql, individualAccountMapper, token);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/** 通过token查询企业用户 */
	public EnterpriseAccount getEnterpriseAccountByToken(String token) {
		String sql = "select * from enterprise_user_account where token = ?";
		List<EnterpriseAccount> list = this.getJdbcTemplate().query(sql, enterpriseAccountMapper, token);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/** 通过phone查询个人用户 */
	public IndividualAccount getIndividualAccountByPhone(String phone) {
		String sql = "select * from individual_user_account where mobile_phone = ?";
		List<IndividualAccount> list = this.getJdbcTemplate().query(sql, individualAccountMapper, phone);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<IndividualAccount> getIndividualAccountList(int size, int offset) {
		String sql = "select * from individual_user_account limit ? offset ?";
		return this.getJdbcTemplate().query(sql, individualAccountMapper, size, offset);
	}

	public int getIndividualAccountCount() {
		String sql = "select count(*) from individual_user_account";
		return this.getJdbcTemplate().queryForObject(sql, Integer.class);
	}

	/** 通过phone查询企业用户 */
	public EnterpriseAccount getEnterpriseAccountByPhone(String phone) {
		String sql = "select * from enterprise_user_account where mobile_phone = ?";
		List<EnterpriseAccount> list = this.getJdbcTemplate().query(sql, enterpriseAccountMapper, phone);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/** 通过身份证号查询个人用户 */
	public IndividualAccount getIndividualAccountByIdCardNumber(String idCardNumber) {
		String sql = "select * from individual_user_account where id_card_number = ?";
		List<IndividualAccount> list = this.getJdbcTemplate().query(sql, individualAccountMapper, idCardNumber);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/** 通过营业执照查询企业用户 */
	public EnterpriseAccount getEnterpriseAccountByEnterpriseCardNumber(String enterpriseCardNumber) {
		String sql = "select * from enterprise_user_account where enterprise_card_number = ?";
		List<EnterpriseAccount> list = this.getJdbcTemplate().query(sql, enterpriseAccountMapper, enterpriseCardNumber);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/** 通过组织机构查询企业用户 */
	public EnterpriseAccount getEnterpriseAccountByOrganizeCode(String organizeCode) {
		String sql = "select * from enterprise_user_account where organize_code = ?";
		List<EnterpriseAccount> list = this.getJdbcTemplate().query(sql, enterpriseAccountMapper, organizeCode);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<EnterpriseAccount> getEnterpriseAccountList(int size, int offset) {
		String sql = "select * from enterprise_user_account limit ? offset ?";
		return this.getJdbcTemplate().query(sql, enterpriseAccountMapper, size, offset);
	}

	public int getEnterpriseAccountCount() {
		String sql = "select count(*) from enterprise_user_account";
		return this.getJdbcTemplate().queryForObject(sql, Integer.class);
	}

	/** 个人用户登录 */
	public IndividualAccount loginIndividualAccount(String login, String password) {
		String sql = "select * from individual_user_account where (mobile_phone = ? or uid = ?) and password = ?";
		List<IndividualAccount> list = this.getJdbcTemplate().query(sql, individualAccountMapper, login, login, password);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/** 企业用户登录 */
	public EnterpriseAccount loginEnterpriseAccount(String login, String password) {
		String sql = "select * from enterprise_user_account where (mobile_phone = ? or eid = ?) and password = ?";
		List<EnterpriseAccount> list = this.getJdbcTemplate().query(sql, enterpriseAccountMapper, login, login, password);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/** 个人用户注册 */
	public int registerIndividualAccount(IndividualAccount account, String password) {
		String sql = "insert into individual_user_account (mobile_phone, password, token, register_date) values (?, ?, ?, ?)";
		return this.getJdbcTemplate().update(sql, account.getMobilePhone(), password, account.getToken(), Calendar.getInstance());
	}

	/** 企业用户注册 */
	public int registerEnterpriseAccount(EnterpriseAccount account, String password) {
		String sql = "insert into enterprise_user_account (mobile_phone, password, token, register_date) values (?, ?, ?, ?)";
		return this.getJdbcTemplate().update(sql, account.getMobilePhone(), password, account.getToken(), Calendar.getInstance());
	}

//	/** 完成个人用户 */
//	public int completeIndividualAccount(IndividualAccount account) {
//		String sql = "update individual_user_account set name = ?, email_address = ?, "
//				+ "id_card_number = ?, id_card_url_1 = ?, id_card_url_2 = ? where uid = ?";
//		return this.getJdbcTemplate().update(sql, account.getName(), account.getEmailAddress(), account.getIdCardNumber(),
//				account.getIdCardUrl1(), account.getIdCardUrl2(), account.getUid());
//	}

//	/** 完成企业用户 */
//	public int completeEnterpriseAccount(EnterpriseAccount account) {
//		String sql = "update enterprise_user_account set name = ?, enterprise_card_number = ?, "
//				+ "business_licence_url = ?, tax_registration_url = ? where eid = ?";
//		return this.getJdbcTemplate().update(sql, account.getName(), account.getEnterpriseCardNumber(), account.getBusinessLicenceUrl(),
//				account.getTaxRegistrationUrl(), account.getEid());
//	}

	/** 修改个人用户 */
	public int updateIndividualAccount(final IndividualAccount account) {
		String sql = "update individual_user_account set name = ?, email_address = ?, mobile_phone = ?, id_card_number = ?, "
				+ "id_card_url_1 = ?, id_card_url_2 = ?, address = ?, address_province = ?, address_city = ?, sex = ?, "
				+ "head_image_url = ?, experience = ?, status = ?, qq = ? where token = ?";
		return this.getJdbcTemplate().update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, account.getName());
				ps.setString(2, account.getEmailAddress());
				ps.setString(3, account.getMobilePhone());
				ps.setString(4, account.getIdCardNumber());
				ps.setString(5, account.getIdCardUrl1());
				ps.setString(6, account.getIdCardUrl2());
				ps.setString(7, account.getAddress());
				ps.setString(8, account.getProvince());
				ps.setString(9, account.getCity());
				ps.setInt(10, account.getSex());
				ps.setString(11, account.getHeadImgUrl());
				ps.setInt(12, account.getExperience());
				ps.setInt(13, account.getStatus());
				ps.setString(14, account.getQq());
				ps.setString(15, account.getToken());
			}
		});
	}

	/** 修改企业用户 */
	public int updateEnterpriseAccount(final EnterpriseAccount account) {
		String sql = "update enterprise_user_account set mobile_phone = ?, name = ?, email_address = ?, enterprise_card_number = ?, "
				+ "business_licence_url = ?, tax_registration_url = ?, company_name = ?, company_phone = ?, address_province = ?, "
				+ "address_city = ?, address = ?, organize_code = ?, sex = ?, id_card_number = ?, id_card_url_1 = ?, id_card_url_2 = ?, "
				+ "head_image_url = ?, status = ? where token = ?";
		return this.getJdbcTemplate().update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, account.getMobilePhone());
				ps.setString(2, account.getName());
				ps.setString(3, account.getEmailAddress());
				ps.setString(4, account.getBusinessLicenceNumber());
				ps.setString(5, account.getBusinessLicenceUrl());
				ps.setString(6, account.getTaxRegistrationUrl());
				ps.setString(7, account.getCompanyName());
				ps.setString(8, account.getCompanyPhone());
				ps.setString(9, account.getProvince());
				ps.setString(10, account.getCity());
				ps.setString(11, account.getAddress());
				ps.setString(12, account.getTaxRegistrationNumber());
				ps.setInt(13, account.getSex());
				ps.setString(14, account.getIdCardNumber());
				ps.setString(15, account.getIdCardUrl1());
				ps.setString(16, account.getIdCardUrl2());
				ps.setString(17, account.getHeadImgUrl());
				ps.setInt(18, account.getStatus());
				ps.setString(19, account.getToken());
			}
		});
	}

	/** 重设个人用户密码 */
	public int changePasswordIndividualAccount(long uid, String password, String token) {
		String sql = "update individual_user_account set password = ?, token = ? where uid = ?";
		return this.getJdbcTemplate().update(sql, password, token, uid);
	}

	/** 重设企业用户密码 */
	public int changePasswordEnterpriseAccount(long eid, String password, String token) {
		String sql = "update enterprise_user_account set password = ?, token = ? where uid = ?";
		return this.getJdbcTemplate().update(sql, password, token, eid);
	}

	/** 验证手机号是否存在 */
	public boolean checkPhoneExists(String phone, String token) {
		String sql1 = "select * from individual_user_account where mobile_phone = ? and token <> ?";
		String sql2 = "select * from enterprise_user_account where mobile_phone = ? and token <> ?";
		List<IndividualAccount> list1 = this.getJdbcTemplate().query(sql1, individualAccountMapper, phone, token);
		List<EnterpriseAccount> list2 = this.getJdbcTemplate().query(sql2, enterpriseAccountMapper, phone, token);
		return list1.size() > 0 || list2.size() > 0;
	}

	/** 验证邮箱是否存在 */
	public boolean checkEmailExists(String email, String token) {
		String sql1 = "select * from individual_user_account where email_address = ? and token <> ?";
		String sql2 = "select * from enterprise_user_account where email_address = ? and token <> ?";
		List<IndividualAccount> list1 = this.getJdbcTemplate().query(sql1, individualAccountMapper, email, token);
		List<EnterpriseAccount> list2 = this.getJdbcTemplate().query(sql2, enterpriseAccountMapper, email, token);
		return list1.size() > 0 || list2.size() > 0;
	}

	/** 验证身份证号是否存在 */
	public boolean checkIdCardExists(String idCard) {
		String sql1 = "select * from individual_user_account where id_card_number = ?";
		String sql2 = "select * from enterprise_user_account where id_card_number = ?";
		List<IndividualAccount> list1 = this.getJdbcTemplate().query(sql1, individualAccountMapper, idCard);
		List<EnterpriseAccount> list2 = this.getJdbcTemplate().query(sql2, enterpriseAccountMapper, idCard);
		return list1.size() > 0 || list2.size() > 0;
	}

	/** 验证QQ号是否存在 */
	public boolean checkQQExists(String qq) {
		String sql = "select * from individual_user_account where qq = ?";
		List<IndividualAccount> list = this.getJdbcTemplate().query(sql, individualAccountMapper, qq);
		return list.size() > 0;
	}

	/** 验证组织机构代码是否存在 */
	public boolean checkEnterpriseCardExists(String enterpriseCard) {
		String sql = "select * from enterprise_user_account where enterprise_card_number = ?";
		List<EnterpriseAccount> list = this.getJdbcTemplate().query(sql, enterpriseAccountMapper, enterpriseCard);
		return list.size() > 0;
	}

//	public int updateIndividualAccountAdmin(final IndividualAccount account) {
//		String sql = "update individual_user_account set name = ?, mobile_phone = ?, email_address = ?, "
//				+ "id_card_number = ?, id_card_url_1 = ?, id_card_url_2 = ? where uid = ?";
//		return this.getJdbcTemplate().update(sql, new PreparedStatementSetter() {
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setString(1, account.getName());
//				ps.setString(2, account.getMobilePhone());
//				ps.setString(3, account.getEmailAddress());
//				ps.setString(4, account.getIdCardNumber());
//				ps.setString(5, account.getIdCardUrl1());
//				ps.setString(6, account.getIdCardUrl2());
//				ps.setLong(7, account.getUid());
//			}
//		});
//	}

//	public int updateEnterpriseAccountAdmin(final EnterpriseAccount account) {
//		String sql = "update enterprise_user_account set name = ?, mobile_phone = ?, email_address = ?, "
//				+ "enterprise_card_number = ?, business_licence_url = ?, tax_registration_url = ? where eid = ?";
//		return this.getJdbcTemplate().update(sql, new PreparedStatementSetter() {
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setString(1, account.getName());
//				ps.setString(2, account.getMobilePhone());
//				ps.setString(3, account.getEmailAddress());
//				ps.setString(4, account.getEnterpriseCardNumber());
//				ps.setString(5, account.getBusinessLicenceUrl());
//				ps.setString(6, account.getTaxRegistrationUrl());
//				ps.setLong(7, account.getEid());
//			}
//		});
//	}

}
