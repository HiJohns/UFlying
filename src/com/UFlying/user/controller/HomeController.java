package com.UFlying.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.UFlying.user.entity.response.ResponseStatistics;
import com.UFlying.user.service.HomeService;

@Controller
public class HomeController {

	@Autowired
	private HomeService homeService;


	/** 获取首页统计信息 */
	@RequestMapping(value = "/statistics", method = RequestMethod.POST)
	@ResponseBody
	public ResponseStatistics getStatistics() {
		return homeService.getStatistics();
	}

}
