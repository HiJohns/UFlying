package com.UFlying.user.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.UFlying.user.entity.base.MissionType;

@Repository
public class MissionTypeMapper implements RowMapper<MissionType> {
	
	@Override
	public MissionType mapRow(ResultSet rs, int rowNum) throws SQLException {
		MissionType missionType = new MissionType();
		missionType.setMissionType(rs.getInt("mission_type"));
		missionType.setStandardFee(rs.getInt("standard_fee"));
		missionType.setStandardDuration(rs.getInt("standard_duration"));
		missionType.setExtraFee(rs.getInt("extra_fee"));
		missionType.setExtraDuration(rs.getInt("extra_duration"));
		missionType.setTypeName(rs.getString("type_name"));
		missionType.setTypeInitials(rs.getString("type_initials"));
		missionType.setContractType(rs.getInt("contract_type"));
		return missionType;
	}
}
