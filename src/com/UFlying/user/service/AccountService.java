package com.UFlying.user.service;

import java.io.File;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.UFlying.exception.ServiceException;
import com.UFlying.user.dao.AccountDao;
import com.UFlying.user.entity.base.EnterpriseAccount;
import com.UFlying.user.entity.base.IndividualAccount;
import com.UFlying.user.form.FormChangePassword;
import com.UFlying.user.form.FormLogin;
import com.UFlying.user.form.FormRegisterEnterpriseAccount;
import com.UFlying.user.form.FormRegisterIndividualAccount;
import com.UFlying.user.form.FormResetPassword;
import com.UFlying.user.form.FormUpdateEnterpriseAccount;
import com.UFlying.user.form.FormUpdateIndividualAccount;
import com.UFlying.user.util.EncryptionUtils;
import com.UFlying.user.util.ImageUtil;
import com.UFlying.user.util.MemcacheUtil;
import com.UFlying.user.util.PropertiesHelper;
import com.UFlying.user.util.RandomKeyUtil;



@Service
public class AccountService {

	private static final String ACCOUNT_IMAGE_PATH = PropertiesHelper.get("account.image.path").toString();

	@Autowired
	private AccountDao accountDao;

	private MemcachedClient memcachedClient;

	@Autowired
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	/** 用户登录 */
	public Object login(FormLogin form) throws ServiceException {
		String user = form.getUser();
		String password = form.getPassword();
		// 表单验证
		if (StringUtils.isBlank(user)) {
			throw new ServiceException("请填写手机号或会员ID");
		}
		if (StringUtils.isBlank(password)) {
			throw new ServiceException("请输入密码");
		}
		user = StringUtils.trim(user);
		String encryptedPassword = EncryptionUtils.MD5(password);
		// 登录验证，并返回用户信息，区分个人用户和企业用户
		IndividualAccount individualAccount = accountDao.loginIndividualAccount(user, encryptedPassword);
		if (individualAccount != null) {
			return individualAccount;
		}
		EnterpriseAccount enterpriseAccount = accountDao.loginEnterpriseAccount(user, encryptedPassword);
		if (enterpriseAccount != null) {
			return enterpriseAccount;
		}
		throw new ServiceException("用户不存在或密码错误");
	}

	/** 注册个人用户 */
	public IndividualAccount registerIndividualAccount(FormRegisterIndividualAccount form) throws ServiceException {
		String phone = form.getPhone();
		String code = form.getCode();
		String password = form.getPassword();
		// 表单验证
		if (StringUtils.isBlank(phone)) {
			throw new ServiceException("请填写手机号");
		}
		if (StringUtils.isBlank(code)) {
			throw new ServiceException("请输入验证码");
		}
		if (StringUtils.isBlank(password)) {
			throw new ServiceException("请输入密码");
		}
		phone = StringUtils.trim(phone);
		code = StringUtils.trim(code);
		// 合法性验证
		if (accountDao.checkPhoneExists(phone)) {
			throw new ServiceException("该手机号已被注册");
		}
		// 验证码校验
		String key = MemcacheUtil.getVerifyCodeKey(phone);
		String storeCode = (String) memcachedClient.get(key);
		if (StringUtils.isBlank(storeCode) || !storeCode.equals(code)) {
			throw new ServiceException("验证码不正确或已失效");
		}
		// 保存用户
		IndividualAccount account = new IndividualAccount();
		String token = RandomKeyUtil.getToken();
		account.setMobilePhone(phone);
		account.setToken(token);
		String encryptedPassword = EncryptionUtils.MD5(password);
		int rows = 0;
		try {
			rows = accountDao.registerIndividualAccount(account, encryptedPassword);
		} catch (Exception e) {
			throw new ServiceException("用户注册失败");
		}
		// 返回保存的用户
		if (rows > 0) {
			return accountDao.getIndividualAccountByToken(token);
		} else {
			throw new ServiceException("用户注册失败");
		}
	}

	/** 注册企业用户 */
	public EnterpriseAccount registerEnterpriseAccount(FormRegisterEnterpriseAccount form) throws ServiceException {
		String phone = form.getPhone();
		String code = form.getCode();
		String password = form.getPassword();
		// 表单验证
		if (StringUtils.isBlank(phone)) {
			throw new ServiceException("请填写手机号");
		}
		if (StringUtils.isBlank(code)) {
			throw new ServiceException("请输入验证码");
		}
		if (StringUtils.isBlank(password)) {
			throw new ServiceException("请输入密码");
		}
		phone = StringUtils.trim(phone);
		code = StringUtils.trim(code);
		// 合法性验证
		if (accountDao.checkPhoneExists(phone)) {
			throw new ServiceException("该手机已被注册");
		}
		// 验证码校验
		String key = MemcacheUtil.getVerifyCodeKey(phone);
		String storeCode = (String) memcachedClient.get(key);
		if (StringUtils.isBlank(storeCode) || !storeCode.equals(code)) {
			throw new ServiceException("验证码不正确或已失效");
		}
		// 保存用户
		EnterpriseAccount account = new EnterpriseAccount();
		String token = RandomKeyUtil.getToken();
		account.setMobilePhone(phone);
		account.setToken(token);
		String encryptedPassword = EncryptionUtils.MD5(password);
		int rows = 0;
		try {
			rows = accountDao.registerEnterpriseAccount(account, encryptedPassword);
		} catch (Exception e) {
			throw new ServiceException("用户注册失败");
		}
		// 返回保存的用户
		if (rows > 0) {
			return accountDao.getEnterpriseAccountByToken(token);
		} else {
			throw new ServiceException("用户注册失败");
		}
	}

	/** 重置密码 */
	public void resetPassword(FormResetPassword form) throws ServiceException {
		String phone = form.getPhone();
		String code = form.getCode();
		String password1 = form.getPassword();
		// 表单验证
		if (StringUtils.isBlank(phone)) {
			throw new ServiceException("请填写手机号");
		}
		if (StringUtils.isBlank(code)) {
			throw new ServiceException("请输入验证码");
		}
		if (StringUtils.isBlank(password1)) {
			throw new ServiceException("请输入密码");
		}
		phone = StringUtils.trim(phone);
		code = StringUtils.trim(code);
		// 合法性验证
		if (!accountDao.checkPhoneExists(phone)) {
			throw new ServiceException("该手机号未被注册");
		}
		// 验证码校验
		String key = MemcacheUtil.getVerifyCodeKey(phone);
		String storeCode = (String) memcachedClient.get(key);
		if (StringUtils.isBlank(storeCode) || !storeCode.equals(code)) {
			throw new ServiceException("验证码不正确或已失效");
		}
		// 更新密码，区分个人用户和企业用户
		int rows = 0;
		IndividualAccount individualAccount = accountDao.getIndividualAccountByPhone(phone);
		if (individualAccount != null) {
			long uid = individualAccount.getUid();
			String encryptedPassword = EncryptionUtils.MD5(password1);
			String token = RandomKeyUtil.getToken();
			try {
				rows = accountDao.changePasswordIndividualAccount(uid, encryptedPassword, token);
			} catch (Exception e) {
				throw new ServiceException("密码重置失败");
			}
		} else {
			EnterpriseAccount enterpriseAccount = accountDao.getEnterpriseAccountByPhone(phone);
			if (enterpriseAccount != null) {
				long eid = enterpriseAccount.getEid();
				String encryptedPassword = EncryptionUtils.MD5(password1);
				String token = RandomKeyUtil.getToken();
				try {
					rows = accountDao.changePasswordEnterpriseAccount(eid, encryptedPassword, token);
				} catch (Exception e) {
					throw new ServiceException("密码重置失败");
				}
			} else {
				throw new ServiceException("该手机号未被注册");
			}
		}
		if (rows <= 0) {
			throw new ServiceException("密码重置失败");
		}
	}

	/** 修改密码 */
	public void changePassword(FormChangePassword form, String token) throws ServiceException {
		String oldPassword = form.getOldPassword();
		String password = form.getPassword();
		// 表单验证
		if (StringUtils.isBlank(token)) {
			throw new ServiceException("请先登录");
		}
		if (StringUtils.isBlank(oldPassword)) {
			throw new ServiceException("请输入原密码");
		}
		if (StringUtils.isBlank(password) ) {
			throw new ServiceException("请输入新密码");
		}
		token = StringUtils.trim(token);
		// 更新密码，区分个人用户和企业用户
		int rows = 0;
		IndividualAccount individualAccount = accountDao.getIndividualAccountByToken(token);
		if (individualAccount != null) {
			// 先验证原密码
			String phone = individualAccount.getMobilePhone();
			String oldEncryptedPassword = EncryptionUtils.MD5(oldPassword);
			IndividualAccount temp = accountDao.loginIndividualAccount(phone, oldEncryptedPassword);
			if (temp == null) {
				throw new ServiceException("原密码验证失败");
			}
			// 原密码验证通过，保存新密码
			long uid = individualAccount.getUid();
			String newEncryptedPassword = EncryptionUtils.MD5(password);
			String newToken = RandomKeyUtil.getToken();
			rows = accountDao.changePasswordIndividualAccount(uid, newEncryptedPassword, newToken);
		} else {
			EnterpriseAccount enterpriseAccount = accountDao.getEnterpriseAccountByToken(token);
			if (enterpriseAccount != null) {
				// 先验证原密码
				String email = enterpriseAccount.getEmailAddress();
				String oldEncryptedPassword = EncryptionUtils.MD5(oldPassword);
				EnterpriseAccount temp = accountDao.loginEnterpriseAccount(email, oldEncryptedPassword);
				if (temp == null) {
					throw new ServiceException("原密码验证失败");
				}
				// 原密码验证通过，保存新密码
				long eid = enterpriseAccount.getEid();
				String newEncryptedPassword = EncryptionUtils.MD5(password);
				String newToken = RandomKeyUtil.getToken();
				rows = accountDao.changePasswordEnterpriseAccount(eid, newEncryptedPassword, newToken);
			}
		}
		if (rows <= 0) {
			throw new ServiceException("密码修改失败");
		}
	}

//	/** 完善个人用户信息 */
//	public IndividualAccount completeIndividualAccount(FormUpdateIndividualAccount form, String token) throws ServiceException {
//		String name = form.getName();
//		String idCardNumber = form.getIdCardNumber();
//		String email = form.getEmail();
//		int sex = form.getSex();
//		String province = form.getProvince();
//		String city = form.getCity();
//		String address = form.getAddress();
//		int experience = form.getExperience();
//		MultipartFile photoImg = form.getPhoto();
//		MultipartFile idCardCoverImg = form.getIdentity_front();
//		MultipartFile idCardBackImg = form.getIdentity_back();
//		// 表单验证
//		if (StringUtils.isBlank(token)) {
//			throw new ServiceException("请先登录");
//		}
//		if (StringUtils.isBlank(name)) {
//			throw new ServiceException("请输入姓名");
//		}
//		if (StringUtils.isBlank(idCardNumber)) {
//			throw new ServiceException("请输入身份证号");
//		}
//		if (StringUtils.isBlank(email)) {
//			throw new ServiceException("请输入邮箱");
//		}
//		token = StringUtils.trim(token);
//		name = StringUtils.trim(name);
//		idCardNumber = StringUtils.trim(idCardNumber);
//		email = StringUtils.trim(email);
//		// 合法性校验
//		IndividualAccount individualAccount = accountDao.getIndividualAccountByToken(token);
//		if (individualAccount == null) {
//			throw new ServiceException("用户不存在");
//		}
//		if (accountDao.checkIdCardExists(idCardNumber)) {
//			throw new ServiceException("该身份证号已被占用");
//		}
//		if (accountDao.checkEmailExists(email)) {
//			throw new ServiceException("该邮箱已被占用");
//		}
//		// 图片处理
//		long uid = individualAccount.getUid();
//		String idCardUrl1 = null;
//		String idCardUrl2 = null;
//		String photoUrl = null;
//		if (photoImg != null && photoImg.isEmpty() == false){
//			String fileNamePrefix = 0 + "_" + uid + "_";
//			String random = RandomKeyUtil.getRandomFileName();
//			String originFileName = fileNamePrefix + random + "_origin.jpg";
//			String originFilePath = ACCOUNT_IMAGE_PATH + originFileName;
//			File originFile = new File(originFilePath);
//			String resizeFileName = fileNamePrefix + random + ".jpg";
//			String resizeFilePath = ACCOUNT_IMAGE_PATH + resizeFileName;
//			File resizeFile = new File(resizeFilePath);
//			try {
//				idCardCoverImg.transferTo(originFile);
//				ImageUtil.resize(originFile, resizeFile);
//			} catch (Exception e) {
//				throw new ServiceException("文件保存失败");
//			}
//			photoUrl = ACCOUNT_IMAGE_URL + resizeFileName;
//		}
//		if (idCardCoverImg != null && idCardCoverImg.isEmpty() == false) {
//			String fileNamePrefix = 0 + "_" + uid + "_";
//			String random = RandomKeyUtil.getRandomFileName();
//			String originFileName = fileNamePrefix + random + "_origin.jpg";
//			String originFilePath = ACCOUNT_IMAGE_PATH + originFileName;
//			File originFile = new File(originFilePath);
//			String resizeFileName = fileNamePrefix + random + ".jpg";
//			String resizeFilePath = ACCOUNT_IMAGE_PATH + resizeFileName;
//			File resizeFile = new File(resizeFilePath);
//			try {
//				idCardCoverImg.transferTo(originFile);
//				ImageUtil.resize(originFile, resizeFile);
//			} catch (Exception e) {
//				throw new ServiceException("文件保存失败");
//			}
//			idCardUrl1 = ACCOUNT_IMAGE_URL + resizeFileName;
//		}
//		if (idCardBackImg != null && idCardBackImg.isEmpty() == false) {
//			String fileNamePrefix = 0 + "_" + uid + "_";
//			String random = RandomKeyUtil.getRandomFileName();
//			String originFileName = fileNamePrefix + random + "_origin.jpg";
//			String originFilePath = ACCOUNT_IMAGE_PATH + originFileName;
//			File originFile = new File(originFilePath);
//			String resizeFileName = fileNamePrefix + random + ".jpg";
//			String resizeFilePath = ACCOUNT_IMAGE_PATH + resizeFileName;
//			File resizeFile = new File(resizeFilePath);
//			try {
//				idCardBackImg.transferTo(originFile);
//				ImageUtil.resize(originFile, resizeFile);
//			} catch (Exception e) {
//				throw new ServiceException("文件保存失败");
//			}
//			idCardUrl2 = ACCOUNT_IMAGE_URL + resizeFileName;
//		}
//		IndividualAccount account = new IndividualAccount();
//		account.setToken(token);
//		account.setUid(uid);
//		account.setName(name);
//		account.setSex(sex);
//		account.setProvince(province);
//		account.setCity(city);
//		account.setAddress(address);
//		account.setEmailAddress(email);
//		account.setIdCardNumber(idCardNumber);
//		account.setExperience(experience);
//		account.setHeadImgUrl(photoUrl);
//		account.setIdCardUrl1(idCardUrl1);
//		account.setIdCardUrl2(idCardUrl2);
//		// 保存信息
//		int rows = 0;
//		try {
//			rows = accountDao.completeIndividualAccount(account);
//		} catch (Exception e) {
//			throw new ServiceException("保存失败");
//		}
//		// 返回保存的用户
//		if (rows > 0) {
//			return accountDao.getIndividualAccountByToken(token);
//		} else {
//			throw new ServiceException("保存失败");
//		}
//	}

	/** 完善企业用户信息 */
//	public EnterpriseAccount completeEnterpriseAccount(FormUpdateEnterpriseAccount form, String token) throws ServiceException {
//		String name = form.getName();
//		String enterpriseNo1 = form.getEnterpriseNo1();
//		String enterpriseNo2 = form.getEnterpriseNo2();
//		MultipartFile businessLicenceImg = form.getBusinessLicenceImg();
//		MultipartFile taxRegistrationImg = form.getTaxRegistrationImg();
//		// 表单验证
//		if (StringUtils.isBlank(token)) {
//			throw new ServiceException("请先登录");
//		}
//		if (StringUtils.isBlank(name)) {
//			throw new ServiceException("请输入企业名称");
//		}
//		if (StringUtils.isBlank(enterpriseNo1) || StringUtils.isBlank(enterpriseNo2)) {
//			throw new ServiceException("请输入组织机构代码");
//		}
//		if (!enterpriseNo1.equalsIgnoreCase(enterpriseNo2)) {
//			throw new ServiceException("两次输入的组织机构代码不一致");
//		}
//		token = StringUtils.trim(token);
//		name = StringUtils.trim(name);
//		enterpriseNo1 = StringUtils.trim(enterpriseNo1);
//		enterpriseNo2 = StringUtils.trim(enterpriseNo2);
//		// 合法性校验
//		EnterpriseAccount enterpriseAccount = accountDao.getEnterpriseAccountByToken(token);
//		if (enterpriseAccount == null) {
//			throw new ServiceException("用户不存在");
//		}
//		if (accountDao.checkEnterpriseCardExists(enterpriseNo1)) {
//			throw new ServiceException("该组织机构代码代码已被占用");
//		}
//		// 图片处理
//		String businessLicenceUrl = null;
//		String taxRegistrationUrl = null;
//		long eid = enterpriseAccount.getEid();
//		if (businessLicenceImg != null && businessLicenceImg.isEmpty() == false) {
//			String fileNamePrefix = 1 + "_" + eid + "_";
//			String random = RandomKeyUtil.getRandomFileName();
//			String originFileName = fileNamePrefix + random + "_origin.jpg";
//			String originFilePath = ACCOUNT_IMAGE_PATH + originFileName;
//			File originFile = new File(originFilePath);
//			String resizeFileName = fileNamePrefix + random + ".jpg";
//			String resizeFilePath = ACCOUNT_IMAGE_PATH + resizeFileName;
//			File resizeFile = new File(resizeFilePath);
//			try {
//				businessLicenceImg.transferTo(originFile);
//				ImageUtil.resize(originFile, resizeFile);
//			} catch (Exception e) {
//				throw new ServiceException("文件保存失败");
//			}
//			businessLicenceUrl = ACCOUNT_IMAGE_URL + resizeFileName;
//		}
//		if (taxRegistrationImg != null && taxRegistrationImg.isEmpty() == false) {
//			String fileNamePrefix = 1 + "_" + eid + "_";
//			String random = RandomKeyUtil.getRandomFileName();
//			String originFileName = fileNamePrefix + random + "_origin.jpg";
//			String originFilePath = ACCOUNT_IMAGE_PATH + originFileName;
//			File originFile = new File(originFilePath);
//			String resizeFileName = fileNamePrefix + random + ".jpg";
//			String resizeFilePath = ACCOUNT_IMAGE_PATH + resizeFileName;
//			File resizeFile = new File(resizeFilePath);
//			try {
//				taxRegistrationImg.transferTo(originFile);
//				ImageUtil.resize(originFile, resizeFile);
//			} catch (Exception e) {
//				throw new ServiceException("文件保存失败");
//			}
//			taxRegistrationUrl = ACCOUNT_IMAGE_URL + resizeFileName;
//		}
//		EnterpriseAccount account = new EnterpriseAccount();
//		account.setEid(eid);
//		account.setName(name);
//		account.setEnterpriseCardNumber(enterpriseNo1);
//		account.setBusinessLicenceUrl(businessLicenceUrl);
//		account.setTaxRegistrationUrl(taxRegistrationUrl);
//		// 保存信息
//		int rows = 0;
//		try {
//			rows = accountDao.completeEnterpriseAccount(account);
//		} catch (Exception e) {
//			throw new ServiceException("保存失败");
//		}
//		// 返回保存的用户
//		if (rows > 0) {
//			return accountDao.getEnterpriseAccountByToken(token);
//		} else {
//			throw new ServiceException("保存失败");
//		}
//	}

	/** 通过token获取个人用户 */
	public IndividualAccount getIndividualAccountByToken(String token) {
		if (StringUtils.isBlank(token)) {
			return null;
		}
		token = StringUtils.trim(token);
		return accountDao.getIndividualAccountByToken(token);
	}

	/** 通过token获取企业用户 */
	public EnterpriseAccount getEnterpriseAccountByToken(String token) {
		if (StringUtils.isBlank(token)) {
			return null;
		}
		token = StringUtils.trim(token);
		return accountDao.getEnterpriseAccountByToken(token);
	}

	/** 更新个人用户信息 */
	public void updateIndividualAccount(FormUpdateIndividualAccount form, String token) throws ServiceException {
		String name = form.getName();
		String idCardNumber = form.getIdCardNumber();
		String email = form.getEmail();
		String qq = form.getQq();
		String phone = form.getPhone();
		int sex = form.getSex();
		String province = form.getProvince();
		String city = form.getCity();
		String address = form.getAddress();
		int experience = form.getExperience();
		int status = form.getStatus();
		MultipartFile photoImg = form.getPhoto();
		MultipartFile idCardCoverImg = form.getIdentity_front();
		MultipartFile idCardBackImg = form.getIdentity_back();

		// 表单验证
		if (StringUtils.isBlank(name)) {
			throw new ServiceException("请输入姓名");
		}
		if (StringUtils.isBlank(email)) {
			throw new ServiceException("请输入邮箱");
		}
		if (0 != status && StringUtils.isBlank(idCardNumber)) {
			throw new ServiceException("请输入身份证号");
		}
		if (StringUtils.isBlank(phone)) {
			throw new ServiceException("请输入手机号");
		}
		token = StringUtils.trim(token);
		name = StringUtils.trim(name);
		email = StringUtils.trim(email);
		idCardNumber = StringUtils.trim(idCardNumber);
		phone = StringUtils.trim(phone);
		IndividualAccount individualAccount = accountDao.getIndividualAccountByToken(token);
		if (individualAccount == null) {
			throw new ServiceException("用户不存在");
		}
		if (!StringUtils.equalsIgnoreCase(email, individualAccount.getEmailAddress())) {
			if (accountDao.checkEmailExists(email)) {
				throw new ServiceException("该邮箱已被占用");
			}
		}
		if (!StringUtils.equalsIgnoreCase(phone, individualAccount.getMobilePhone())) {
			if (accountDao.checkPhoneExists(phone)) {
				throw new ServiceException("该手机号已被占用");
			}
		}
		if (!StringUtils.equalsIgnoreCase(idCardNumber, individualAccount.getIdCardNumber())) {
			if (accountDao.checkIdCardExists(idCardNumber)) {
				throw new ServiceException("该身份证号已被占用");
			}
		}
		// 图片处理
		long uid = individualAccount.getUid();
		String idCardUrl1 = individualAccount.getIdCardUrl1();
		String idCardUrl2 = individualAccount.getIdCardUrl2();
		String photoUrl = individualAccount.getHeadImgUrl();
		if (photoImg != null && photoImg.isEmpty() == false){
			String fileNamePrefix = 0 + "_" + uid + "_";
			String random = RandomKeyUtil.getRandomFileName();
			String originFileName = fileNamePrefix + random + "_origin.jpg";
			String originFilePath = ACCOUNT_IMAGE_PATH + originFileName;
			File originFile = new File(originFilePath);
			String resizeFileName = fileNamePrefix + random + ".jpg";
			String resizeFilePath = ACCOUNT_IMAGE_PATH + resizeFileName;
			File resizeFile = new File(resizeFilePath);
			try {
				photoImg.transferTo(originFile);
				ImageUtil.resize(originFile, resizeFile);
			} catch (Exception e) {
				throw new ServiceException("证件照片保存失败");
			}
			photoUrl = resizeFileName;
		}
		if (idCardCoverImg != null && idCardCoverImg.isEmpty() == false) {
			String fileNamePrefix = 0 + "_" + uid + "_";
			String random = RandomKeyUtil.getRandomFileName();
			String originFileName = fileNamePrefix + random + "_origin.jpg";
			String originFilePath = ACCOUNT_IMAGE_PATH + originFileName;
			File originFile = new File(originFilePath);
			String resizeFileName = fileNamePrefix + random + ".jpg";
			String resizeFilePath = ACCOUNT_IMAGE_PATH + resizeFileName;
			File resizeFile = new File(resizeFilePath);
			try {
				idCardCoverImg.transferTo(originFile);
				ImageUtil.resize(originFile, resizeFile);
			} catch (Exception e) {
				throw new ServiceException("身份证正面照片保存失败");
			}
			idCardUrl1 = resizeFileName;
		}
		if (idCardBackImg != null && idCardBackImg.isEmpty() == false) {
			String fileNamePrefix = 0 + "_" + uid + "_";
			String random = RandomKeyUtil.getRandomFileName();
			String originFileName = fileNamePrefix + random + "_origin.jpg";
			String originFilePath = ACCOUNT_IMAGE_PATH + originFileName;
			File originFile = new File(originFilePath);
			String resizeFileName = fileNamePrefix + random + ".jpg";
			String resizeFilePath = ACCOUNT_IMAGE_PATH + resizeFileName;
			File resizeFile = new File(resizeFilePath);
			try {
				idCardBackImg.transferTo(originFile);
				ImageUtil.resize(originFile, resizeFile);
			} catch (Exception e) {
				throw new ServiceException("身份证背面照片保存失败");
			}
			idCardUrl2 = resizeFileName;
		}

		IndividualAccount account = new IndividualAccount();
		account.setToken(token);
		account.setUid(uid);
		account.setName(name);
		account.setMobilePhone(phone);
		account.setSex(sex);
		account.setProvince(province);
		account.setCity(city);
		account.setAddress(address);
		account.setStatus(status);
		account.setEmailAddress(email);
		account.setQq(qq);
		account.setIdCardNumber(idCardNumber);
		account.setExperience(experience);
		account.setHeadImgUrl(photoUrl);
		account.setIdCardUrl1(idCardUrl1);
		account.setIdCardUrl2(idCardUrl2);
		// 保存信息
		int rows = 0;
		try {
			rows = accountDao.updateIndividualAccount(account);
		} catch (Exception e) {
			throw new ServiceException("更新失败");
		}
		if (rows <= 0) {
			throw new ServiceException("更新失败");
		}
	}

	/** 更新企业用户信息 */
	public void updateEnterpriseAccount(FormUpdateEnterpriseAccount form, String token) throws ServiceException {
		String phone = form.getPhone();
		String email = form.getEmail();
		String name = form.getName();
		String enterpriseCardNumber = form.getEnterpriseCardNumber();
		MultipartFile businessLicenceImg = form.getBusinessLicenceImg();
		MultipartFile taxRegistrationImg = form.getTaxRegistrationImg();
		// 表单验证
		if (StringUtils.isBlank(phone)) {
			throw new ServiceException("请输入手机号");
		}
		if (StringUtils.isBlank(email)) {
			throw new ServiceException("请输入邮箱");
		}
		if (StringUtils.isBlank(name)) {
			throw new ServiceException("请输入企业名称");
		}
		if (StringUtils.isBlank(enterpriseCardNumber)) {
			throw new ServiceException("请输入机构代码");
		}
		phone = StringUtils.trim(phone);
		token = StringUtils.trim(token);
		email = StringUtils.trim(email);
		name = StringUtils.trim(name);
		enterpriseCardNumber = StringUtils.trim(enterpriseCardNumber);
		EnterpriseAccount enterpriseAccount = accountDao.getEnterpriseAccountByToken(token);
		if (enterpriseAccount == null) {
			throw new ServiceException("用户不存在");
		}
		if (!StringUtils.equals(phone, enterpriseAccount.getMobilePhone())) {
			if (accountDao.checkPhoneExists(phone)) {
				throw new ServiceException("该手机号已被占用");
			}
		}
		if (!StringUtils.equals(email, enterpriseAccount.getEmailAddress())) {
			if (accountDao.checkEmailExists(email)) {
				throw new ServiceException("该邮箱已被占用");
			}
		}
		if (!StringUtils.equals(enterpriseCardNumber, enterpriseAccount.getEnterpriseCardNumber())) {
			if (accountDao.checkEnterpriseCardExists(enterpriseCardNumber)) {
				throw new ServiceException("该机构代码已被占用");
			}
		}
		// 图片处理
		String businessLicenceUrl = enterpriseAccount.getBusinessLicenceUrl();
		String taxRegistrationUrl = enterpriseAccount.getTaxRegistrationUrl();
		long eid = enterpriseAccount.getEid();
		if (businessLicenceImg != null && businessLicenceImg.isEmpty() == false) {
			String fileNamePrefix = 1 + "_" + eid + "_";
			String random = RandomKeyUtil.getRandomFileName();
			String originFileName = fileNamePrefix + random + "_origin.jpg";
			String originFilePath = ACCOUNT_IMAGE_PATH + originFileName;
			File originFile = new File(originFilePath);
			String resizeFileName = fileNamePrefix + random + ".jpg";
			String resizeFilePath = ACCOUNT_IMAGE_PATH + resizeFileName;
			File resizeFile = new File(resizeFilePath);
			try {
				businessLicenceImg.transferTo(originFile);
				ImageUtil.resize(originFile, resizeFile);
			} catch (Exception e) {
				throw new ServiceException("文件保存失败");
			}
			businessLicenceUrl = resizeFileName;
		}
		if (taxRegistrationImg != null && taxRegistrationImg.isEmpty() == false) {
			String fileNamePrefix = 1 + "_" + eid + "_";
			String random = RandomKeyUtil.getRandomFileName();
			String originFileName = fileNamePrefix + random + "_origin.jpg";
			String originFilePath = ACCOUNT_IMAGE_PATH + originFileName;
			File originFile = new File(originFilePath);
			String resizeFileName = fileNamePrefix + random + ".jpg";
			String resizeFilePath = ACCOUNT_IMAGE_PATH + resizeFileName;
			File resizeFile = new File(resizeFilePath);
			try {
				taxRegistrationImg.transferTo(originFile);
				ImageUtil.resize(originFile, resizeFile);
			} catch (Exception e) {
				throw new ServiceException("文件保存失败");
			}
			taxRegistrationUrl = resizeFileName;
		}
		EnterpriseAccount account = new EnterpriseAccount();
		account.setToken(token);
		account.setMobilePhone(phone);
		account.setName(name);
		account.setEmailAddress(email);
		account.setEnterpriseCardNumber(enterpriseCardNumber);
		account.setBusinessLicenceUrl(businessLicenceUrl);
		account.setTaxRegistrationUrl(taxRegistrationUrl);
		// 保存信息
		int rows = 0;
		try {
			rows = accountDao.updateEnterpriseAccount(account);
		} catch (Exception e) {
			throw new ServiceException("更新失败");
		}
		if (rows <= 0) {
			throw new ServiceException("更新失败");
		}
	}

	/** 检测手机号是否存在 */
	public boolean checkPhoneExists(String phone) {
		return accountDao.checkPhoneExists(phone);
	}

	/** 将个人会员信息映射到前端表单类 */
	public Object individualAccountToForm(IndividualAccount account) {
		FormUpdateIndividualAccount form = new FormUpdateIndividualAccount();
		form.setName(account.getName());
		form.setUid(account.getUid()) ;
		form.setPhone(account.getMobilePhone());
		form.setIdCardNumber(account.getIdCardNumber());
		form.setProvince(account.getProvince());
		form.setCity(account.getCity());
		form.setAddress(account.getAddress());
		form.setEmail(account.getEmailAddress());
		form.setQq(account.getQq());
		form.setSex(account.getSex());
		form.setStatus(account.getStatus());
		form.setExperience(account.getExperience());
		form.setPhotoUrl(account.getHeadImgUrl());
		form.setIdCardUrl1(account.getIdCardUrl1());
		form.setIdCardUrl2(account.getIdCardUrl2());
		return form;
	}

	/** 将企业会员信息映射到前端表单类 */
	public Object enterpriseAccountToForm(EnterpriseAccount account) {
		FormUpdateEnterpriseAccount form = new FormUpdateEnterpriseAccount();
		form.setName(account.getName());
//		form.setEid(String.format("G%010d", account.getEid())) ;
		form.setEid(account.getEid()) ;
		form.setPhone(account.getMobilePhone());
		form.setIdCardNumber(account.getIdCardNumber());
		form.setProvince(account.getProvince());
		form.setCity(account.getCity());
		form.setAddress(account.getAddress());
		form.setEmail(account.getEmailAddress());
		form.setSex(account.getSex());
		form.setStatus(account.getStatus());
		form.setPhotoUrl(account.getHeadImgUrl());
		form.setIdCardUrl1(account.getIdCardUrl1());
		form.setIdCardUrl2(account.getIdCardUrl2());
		return form;
	}

//	public List<IndividualAccount> searchIndividualAccount(String phone, int pageNo) {
//		List<IndividualAccount> list = new ArrayList<IndividualAccount>();
//		if (StringUtils.isNoneBlank(phone)) {
//			IndividualAccount account = accountDao.getIndividualAccountByPhone(phone);
//			list.add(account);
//		} else {
//			int size = Pagination.DEFAULT_PAGE_SIZE;
//			int offset = size * (pageNo - 1);
//			list = accountDao.getIndividualAccountList(size, offset);
//		}
//		return list;
//	}
//
//	public int getIndividualAccountCount() {
//		return accountDao.getIndividualAccountCount();
//	}
//
//	public int getEnterpriseAccountCount() {
//		return accountDao.getEnterpriseAccountCount();
//	}
//
//	public List<EnterpriseAccount> searchEnterpriseAccount(String phone, int pageNo) {
//		List<EnterpriseAccount> list = new ArrayList<EnterpriseAccount>();
//		if (StringUtils.isNotBlank(phone)) {
//			EnterpriseAccount account = accountDao.getEnterpriseAccountByPhone(phone);
//			list.add(account);
//		} else {
//			int size = Pagination.DEFAULT_PAGE_SIZE;
//			int offset = size * (pageNo - 1);
//			list = accountDao.getEnterpriseAccountList(size, offset);
//		}
//		return list;
//	}
//
//	public IndividualAccount getIndividualAccountById(long uid) {
//		return accountDao.getIndividualAccountById(uid);
//	}
//
//	public EnterpriseAccount getEnterpriseAccountById(long eid) {
//		return accountDao.getEnterpriseAccountById(eid);
//	}
//
//	public IndividualAccount getIndividualAccountByPhone(String phone) {
//		return accountDao.getIndividualAccountByPhone(phone);
//	}
//
//	public EnterpriseAccount getEnterpriseAccountByPhone(String phone) {
//		return accountDao.getEnterpriseAccountByPhone(phone);
//	}
//
//	public int updateIndividualAccountAdmin(FormUpdateIndividualAccountAdmin form) throws ServiceException {
//		IndividualAccount originAccount = accountDao.getIndividualAccountById(form.getUid());
//		IndividualAccount account = new IndividualAccount();
//		account.setUid(form.getUid());
//		account.setName(form.getName());
//		account.setMobilePhone(form.getMobilePhone());
//		account.setEmailAddress(form.getEmailAddress());
//		account.setIdCardNumber(form.getIdCardNumber());
//		String idCardUrl1 = originAccount.getIdCardUrl1();
//		String idCardUrl2 = originAccount.getIdCardUrl2();
//		MultipartFile idCardCoverImg = form.getIdCardCoverImg();
//		MultipartFile idCardBackImg = form.getIdCardBackImg();
//		long uid = form.getUid();
//		if (idCardCoverImg != null && idCardCoverImg.isEmpty() == false) {
//			String fileNamePrefix = 0 + "_" + uid + "_";
//			String random = RandomKeyUtil.getRandomFileName();
//			String originFileName = fileNamePrefix + random + "_origin.jpg";
//			String originFilePath = ACCOUNT_IMAGE_PATH + originFileName;
//			File originFile = new File(originFilePath);
//			String resizeFileName = fileNamePrefix + random + ".jpg";
//			String resizeFilePath = ACCOUNT_IMAGE_PATH + resizeFileName;
//			File resizeFile = new File(resizeFilePath);
//			try {
//				idCardCoverImg.transferTo(originFile);
//				ImageUtil.resize(originFile, resizeFile);
//			} catch (Exception e) {
//				throw new ServiceException("文件保存失败");
//			}
//			idCardUrl1 = ACCOUNT_IMAGE_URL + resizeFileName;
//		}
//		if (idCardBackImg != null && idCardBackImg.isEmpty() == false) {
//			String fileNamePrefix = 0 + "_" + uid + "_";
//			String random = RandomKeyUtil.getRandomFileName();
//			String originFileName = fileNamePrefix + random + "_origin.jpg";
//			String originFilePath = ACCOUNT_IMAGE_PATH + originFileName;
//			File originFile = new File(originFilePath);
//			String resizeFileName = fileNamePrefix + random + ".jpg";
//			String resizeFilePath = ACCOUNT_IMAGE_PATH + resizeFileName;
//			File resizeFile = new File(resizeFilePath);
//			try {
//				idCardBackImg.transferTo(originFile);
//				ImageUtil.resize(originFile, resizeFile);
//			} catch (Exception e) {
//				throw new ServiceException("文件保存失败");
//			}
//			idCardUrl2 = ACCOUNT_IMAGE_URL + resizeFileName;
//		}
//		account.setIdCardUrl1(idCardUrl1);
//		account.setIdCardUrl2(idCardUrl2);
//		return accountDao.updateIndividualAccountAdmin(account);
//	}
//
//	public int updateEnterpriseAccountAdmin(FormUpdateEnterpriseAccountAdmin form) throws ServiceException {
//		EnterpriseAccount originAccount = accountDao.getEnterpriseAccountById(form.getEid());
//		EnterpriseAccount account = new EnterpriseAccount();
//		account.setEid(form.getEid());
//		account.setName(form.getName());
//		account.setMobilePhone(form.getMobilePhone());
//		account.setEmailAddress(form.getEmailAddress());
//		account.setEnterpriseCardNumber(form.getEnterpriseCardNumber());
//		String businessLicenceUrl = originAccount.getBusinessLicenceUrl();
//		String taxRegistrationUrl = originAccount.getTaxRegistrationUrl();
//		MultipartFile businessLicence = form.getBusinessLicence();
//		MultipartFile taxRegistration = form.getTaxRegistration();
//		long eid = form.getEid();
//		if (businessLicence != null && businessLicence.isEmpty() == false) {
//			String fileNamePrefix = 1 + "_" + eid + "_";
//			String random = RandomKeyUtil.getRandomFileName();
//			String originFileName = fileNamePrefix + random + "_origin.jpg";
//			String originFilePath = ACCOUNT_IMAGE_PATH + originFileName;
//			File originFile = new File(originFilePath);
//			String resizeFileName = fileNamePrefix + random + ".jpg";
//			String resizeFilePath = ACCOUNT_IMAGE_PATH + resizeFileName;
//			File resizeFile = new File(resizeFilePath);
//			try {
//				businessLicence.transferTo(originFile);
//				ImageUtil.resize(originFile, resizeFile);
//			} catch (Exception e) {
//				throw new ServiceException("文件保存失败");
//			}
//			businessLicenceUrl = ACCOUNT_IMAGE_URL + resizeFileName;
//		}
//		if (taxRegistration != null && taxRegistration.isEmpty() == false) {
//			String fileNamePrefix = 1 + "_" + eid + "_";
//			String random = RandomKeyUtil.getRandomFileName();
//			String originFileName = fileNamePrefix + random + "_origin.jpg";
//			String originFilePath = ACCOUNT_IMAGE_PATH + originFileName;
//			File originFile = new File(originFilePath);
//			String resizeFileName = fileNamePrefix + random + ".jpg";
//			String resizeFilePath = ACCOUNT_IMAGE_PATH + resizeFileName;
//			File resizeFile = new File(resizeFilePath);
//			try {
//				taxRegistration.transferTo(originFile);
//				ImageUtil.resize(originFile, resizeFile);
//			} catch (Exception e) {
//				throw new ServiceException("文件保存失败");
//			}
//			taxRegistrationUrl = ACCOUNT_IMAGE_URL + resizeFileName;
//		}
//		account.setBusinessLicenceUrl(businessLicenceUrl);
//		account.setTaxRegistrationUrl(taxRegistrationUrl);
//		return accountDao.updateEnterpriseAccountAdmin(account);
//	}

}
