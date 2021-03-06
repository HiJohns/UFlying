package com.UFlying.db.mapper.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.UFlying.db.entity.HomeStatistics;

@Repository
public class HomeStatisticsMapper implements RowMapper<HomeStatistics> {

	public HomeStatistics mapRow(ResultSet rs, int rowNum) throws SQLException {
		HomeStatistics statistics = new HomeStatistics();
		statistics.setMembers(rs.getInt("members"));
		statistics.setDirectors(rs.getInt("directors"));
		statistics.setInsurance(rs.getInt("insurance"));
		statistics.setMissions(rs.getInt("missions"));
		return statistics;
	}

}
