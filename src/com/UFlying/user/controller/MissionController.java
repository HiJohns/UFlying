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
	@Autowired
	private AccountService accountService;
	
	
}
