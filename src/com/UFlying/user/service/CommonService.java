package com.UFlying.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UFlying.user.dao.common.CommonDao;
import com.UFlying.user.entity.base.Region;

@Service
public class CommonService {
	@Autowired
	private CommonDao commonDao;
	
	public List<Region> getRegion(){
		List<Region> list = commonDao.getRegion();
		return list;
	}

	
}
