package com.UFlying.user.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.UFlying.exception.ServiceException;
import com.UFlying.user.entity.base.Contract;
import com.UFlying.user.entity.base.EnterpriseAccount;
import com.UFlying.user.entity.base.IndividualAccount;
import com.UFlying.user.entity.base.Region;
import com.UFlying.user.entity.response.ResponseLoginInfo;
import com.UFlying.user.form.FormContract;
import com.UFlying.user.form.FormLogin;
import com.UFlying.user.service.AccountService;
import com.UFlying.user.service.CommonService;

@Controller
public class CommonController {
	
	@Autowired
	private CommonService service;
	
	@Autowired
	private AccountService accountService;

	/** 获取登录状况 */
	@RequestMapping(value = "/login_info", method = RequestMethod.POST)
	@ResponseBody
	public ResponseLoginInfo loginInfo(@CookieValue(required = false)  String token) {
		Object account = null;
		// 若cookie中存在token，则表示该用户登录过，只需要验证token即可
		if (StringUtils.isNotBlank(token)) {
			IndividualAccount individualAccount = accountService.getIndividualAccountByToken(token);
			if (individualAccount != null) {
				account = individualAccount;
				token = individualAccount.getToken();
			} else {
				EnterpriseAccount enterpriseAccount = accountService.getEnterpriseAccountByToken(token);
				if (enterpriseAccount != null) {
					account = enterpriseAccount;
					token = enterpriseAccount.getToken();
				}
			}
		}
		
		ResponseLoginInfo response = new ResponseLoginInfo();
		if (account != null) {
			if (account.getClass().equals(IndividualAccount.class)){
				response.setType(0);
			}else{
				response.setType(1);
			}
			response.setAccount(account);
		}
		return response;
	}

	/** 登录，自动区分个人用户、企业用户 */
	@RequestMapping(value = "/ajax_login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseLoginInfo ajaxLogin(@RequestBody FormLogin form) {
		ResponseLoginInfo response = new ResponseLoginInfo();
		Object account = null;
		try {
			account = accountService.login(form);
		} catch (ServiceException e) {
			response.setStatus(1);
			response.setReason(e.getMessage());
		}
		if (account != null) {
			response.setStatus(0);
			if (account.getClass().equals(IndividualAccount.class)){
				response.setType(0);
			}else{
				response.setType(1);
			}
			response.setAccount(account);
		}
		return response;
	}

	/** 获取省市列表 */
	@RequestMapping(value = "/region", method = RequestMethod.POST)
	@ResponseBody
	public List<Region> getRegion() {
		return service.getRegion();

	}
	
	/** 获取合同文本 */
	@RequestMapping(value = "/contract", method = RequestMethod.POST)
	@ResponseBody
	public Contract getContract(@RequestBody FormContract form) {
		return service.getContract(form.getContractType());
	}
}
