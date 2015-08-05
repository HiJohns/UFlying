package com.UFlying.user.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.UFlying.user.entity.base.EnterpriseAccount;
import com.UFlying.user.entity.base.IndividualAccount;
import com.UFlying.user.entity.response.ResponseLoginInfo;
import com.UFlying.user.service.AccountService;
import com.UFlying.user.service.MissionService;

@Controller
public class MissionController {

	@Autowired
	private MissionService missionService;
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

}
