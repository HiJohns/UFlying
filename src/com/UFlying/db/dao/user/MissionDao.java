package com.UFlying.db.dao.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.UFlying.db.dao.BaseDao;
import com.UFlying.db.entity.MissionType;
import com.UFlying.db.mapper.user.MissionMapper;
import com.UFlying.db.mapper.user.MissionTypeMapper;
import com.UFlying.form.user.FormCreateMission;

/** 任务DAO */
@Repository
public class MissionDao extends BaseDao {

	@Autowired
	private MissionMapper missionMapper;
	@Autowired
	private MissionTypeMapper missionFeeMapper;
	
	public List<MissionType> getMissionFee(){
		String sql = "select * from mission_type";
		List<MissionType> list = this.getJdbcTemplate().query(sql, missionFeeMapper);
		if (list.size() > 0) {
			return list;
		}
		return null;		
	}
	
	public int createMission(final FormCreateMission form){
		String sql = "insert into mission (mission_id, orderer_type, orderer_id, mission_type," +
				" place, mission_date, start_time, end_time, address_province, address_city, address, " +
				"remark, peyment, status, order_datetime) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
