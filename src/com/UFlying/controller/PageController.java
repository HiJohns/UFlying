package com.UFlying.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
	
	@RequestMapping(value = "/mission_page/**", method = RequestMethod.GET)
	public String getMissionPageAnySub() {
		return "Mission";
	}
	
	@RequestMapping(value = "/insurance_page/**", method = RequestMethod.GET)
	public String getInsurancePageAnySub() {
		return "Insurance";
	}
	
}
