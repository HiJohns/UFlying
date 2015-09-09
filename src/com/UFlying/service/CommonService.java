package com.UFlying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UFlying.db.dao.CommonDao;
import com.UFlying.db.entity.Contract;
import com.UFlying.db.entity.Region;

@Service
public class CommonService {
	@Autowired
	private CommonDao commonDao;
	
	public List<Region> getRegion(){
		List<Region> list = commonDao.getRegion();
		return list;
	}

	public Contract getContract(int contractType){
		return commonDao.getContract(contractType);
	}
}
