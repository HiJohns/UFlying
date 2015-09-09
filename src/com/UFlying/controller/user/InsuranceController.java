package com.UFlying.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.UFlying.db.entity.Device;
import com.UFlying.service.user.InsuranceService;

@Controller
public class InsuranceController {

	@Autowired
	private InsuranceService insuranceService;
	
	/** 获取全设备机型列表 */
	@RequestMapping(value = "/device", method = RequestMethod.POST)
	@ResponseBody
	public List<Device> getDevice() {
		return insuranceService.getDevice();
	}

}
