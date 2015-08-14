package com.UFlying.user.controller;

import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.UFlying.user.entity.base.EnterpriseAccount;
import com.UFlying.user.entity.base.IndividualAccount;
import com.UFlying.user.entity.base.MissionType;
import com.UFlying.user.entity.response.ResponseCreateMission;
import com.UFlying.user.form.FormCreateMission;
import com.UFlying.user.service.AccountService;
import com.UFlying.user.service.MissionService;

@Controller
public class MissionController {

	@Autowired
	private MissionService missionService;
	@Autowired
	private AccountService accountService;

	/** 获取任务费率表 */
	@RequestMapping(value = "/mission_fee", method = RequestMethod.POST)
	@ResponseBody
	public List<MissionType> getMissionFee() {
		return missionService.getMissionFee();
	}

	/** 发布新任务 */
	@RequestMapping(value = "/create_mission", method = RequestMethod.POST)
	@ResponseBody
	public ResponseCreateMission createMission(@RequestBody FormCreateMission form, @CookieValue(required = false)  String token) {
		Object account = null;
		ResponseCreateMission response = new ResponseCreateMission();
		// 若cookie中存在token，则表示该用户登录过，只需要验证token即可
		if (StringUtils.isNotBlank(token)) {
			IndividualAccount individualAccount = accountService.getIndividualAccountByToken(token);
			if (individualAccount != null) {
				account = individualAccount;
				form.setAccountType(0);
				form.setAccountId(individualAccount.getUid());
				token = individualAccount.getToken();
			} else {
				EnterpriseAccount enterpriseAccount = accountService.getEnterpriseAccountByToken(token);
				if (enterpriseAccount != null) {
					account = enterpriseAccount;
					form.setAccountType(1);
					form.setAccountId(enterpriseAccount.getEid());
					token = enterpriseAccount.getToken();
				}
			}
			// 若cookie中存在token，但根据token未找到用户，则表示该token不合法，删除之
			if (account == null) {
				Cookie cookie = new Cookie("token", null);
				cookie.setMaxAge(0);
				cookie.setPath("/");
			}
		}
		if (account != null) {
			response = missionService.createMission(form);
		}
		return response;
	}

}
