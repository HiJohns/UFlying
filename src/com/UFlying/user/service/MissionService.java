package com.UFlying.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UFlying.user.dao.MissionDao;
import com.UFlying.user.entity.base.MissionFee;

@Service
public class MissionService {

	@Autowired
	private MissionDao missionDao;
	
	public List<MissionFee> getMissionFee(){
		List<MissionFee> list = missionDao.getMissionFee();
		return list;
	}


}
