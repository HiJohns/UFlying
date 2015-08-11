package com.UFlying.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.UFlying.user.dao.common.BaseDao;
import com.UFlying.user.dao.mapper.MissionFeeMapper;
import com.UFlying.user.dao.mapper.MissionMapper;
import com.UFlying.user.entity.base.MissionFee;

/** 首页DAO */
@Repository
public class MissionDao extends BaseDao {

	@Autowired
	private MissionMapper missionMapper;
	@Autowired
	private MissionFeeMapper missionFeeMapper;
	
	public List<MissionFee> getMissionFee(){
		String sql = "select * from mission_fee";
		List<MissionFee> list = this.getJdbcTemplate().query(sql, missionFeeMapper);
		if (list.size() > 0) {
			return list;
		}
		return null;		
	}
}
