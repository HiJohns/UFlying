package com.UFlying.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.UFlying.user.entity.base.MissionFee;
import com.UFlying.user.service.MissionService;

@Controller
public class MissionController {

	@Autowired
	private MissionService missionService;
	
	/** 获取任务费率表 */
	@RequestMapping(value = "/mission_fee", method = RequestMethod.POST)
	@ResponseBody
	public List<MissionFee> getMissionFee() {
		return missionService.getMissionFee();

	}

}
