package com.UFlying.db.mapper.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.UFlying.db.entity.Mission;

@Repository
public class MissionMapper implements RowMapper<Mission> {

	@Override
	public Mission mapRow(ResultSet rs, int rowNum) throws SQLException {
		Mission mission = new Mission();
		mission.setAddress(rs.getString("address"));
		mission.setAddressCity(rs.getString("address_city"));
		mission.setAddressProvince(rs.getString("address_province"));
		mission.setCarryDatetime(rs.getTimestamp("carry_datetime"));
		mission.setCarryerId(rs.getInt("carryer_id"));
		mission.setComment(rs.getString("comment"));
		mission.setEndDatetime(rs.getTimestamp("end_datetime"));
		mission.setEndTime(rs.getTime("end_time"));
		mission.setFinishedDatetime(rs.getTimestamp("finished_datetime"));
		mission.setLasttimestamp(rs.getTimestamp("lasttimestamp"));
		mission.setMissionDate(rs.getDate("mission_date"));
		mission.setMissionId(rs.getString("mission_id"));
		mission.setMissionType(rs.getInt("mission_type"));
		mission.setOrderDatetime(rs.getTimestamp("order_datetime"));
		mission.setOrdererId(rs.getInt("orderer_id"));
		mission.setPayDatetime(rs.getTimestamp("pay_datetime"));
		mission.setPeyment(rs.getInt("peyment"));
		mission.setPlace(rs.getInt("place"));
		mission.setRemark(rs.getString("remark"));
		mission.setStartDatetime(rs.getTimestamp("start_datetime"));
		mission.setStartTime(rs.getTime("start_time"));
		mission.setStatus(rs.getInt("status"));
		return mission;
	}

}
