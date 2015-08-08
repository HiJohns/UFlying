package com.UFlying.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.UFlying.user.dao.common.BaseDao;
import com.UFlying.user.dao.mapper.MissionMapper;

/** 首页DAO */
@Repository
public class MissionDao extends BaseDao {

	@Autowired
	private MissionMapper missionMapper;

}
