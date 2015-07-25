package com.UFlying.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.UFlying.user.dao.common.CommonDao;
import com.UFlying.user.dao.mapper.MissionMapper;

/** 首页DAO */
@Repository
public class MissionDao extends CommonDao {

	@Autowired
	private MissionMapper missionMapper;

}
