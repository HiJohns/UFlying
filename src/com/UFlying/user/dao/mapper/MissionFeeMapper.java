package com.UFlying.user.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.UFlying.user.entity.base.MissionFee;

@Repository
public class MissionFeeMapper implements RowMapper<MissionFee> {
	
	@Override
	public MissionFee mapRow(ResultSet rs, int rowNum) throws SQLException {

		MissionFee missionFee = new MissionFee();
		missionFee.setMissionType(rs.getInt("mission_type"));
		missionFee.setTypeName(rs.getString("type_name"));
		missionFee.setTypeInitials(rs.getString("type_initials"));
		missionFee.setStandardFee(rs.getInt("standard_fee"));
		missionFee.setStandardDuration(rs.getInt("standard_duration"));
		missionFee.setExtraFee(rs.getInt("extra_fee"));
		missionFee.setExtraDuration(rs.getInt("extra_duration"));
		return missionFee;
	}
}
