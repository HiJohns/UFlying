package com.UFlying.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UFlying.user.dao.MissionDao;

@Service
public class MissionService {

	@Autowired
	private MissionDao missionDao;

}
