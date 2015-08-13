package com.UFlying.user.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.UFlying.user.dao.common.BaseDao;
import com.UFlying.user.dao.mapper.MissionFeeMapper;
import com.UFlying.user.dao.mapper.MissionMapper;
import com.UFlying.user.entity.base.MissionFee;
import com.UFlying.user.form.FormCreateMission;

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
	
	public int createMission(final FormCreateMission form){
		String sql = "insert into mission (mission_id, orderer_type, orderer_id, mission_type," +
				" place, mission_date, start_time, end_time, address_province, address_city, address, " +
				"remark, peyment, status, order_datetime) values (values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return this.getJdbcTemplate().update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, form.getMissionId());
				ps.setInt(2, form.getAccountType());
				ps.setLong(3, form.getAccountId());
				ps.setInt(4, form.getMissionType());
				ps.setInt(5, form.getPlace());
				ps.setDate(6, form.getMissionDate());
				ps.setTime(7, form.getStartTime());
				ps.setTime(8, form.getEndTime());
				ps.setString(9, form.getProvince());
				ps.setString(10, form.getCity());
				ps.setString(11, form.getAddress());
				ps.setString(12, form.getRemark());
				ps.setInt(13, form.getPayment());
				ps.setInt(14, 0);
				ps.setTimestamp(15, new Timestamp(Calendar.getInstance().getTimeInMillis()));
			}
		});


	}
}
