package com.UFlying.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UFlying.db.dao.user.InsuranceDao;
import com.UFlying.db.entity.Device;

@Service
public class InsuranceService {

	@Autowired
	InsuranceDao insuranceDao;
	
	/** 获取全设备机型列表*/
	public List<Device> getDevice(){
		List<Device> list = insuranceDao.getDevice();
		return list;
	}

}
