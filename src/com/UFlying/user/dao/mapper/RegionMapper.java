package com.UFlying.user.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.UFlying.user.entity.base.Region;

@Repository
public class RegionMapper implements RowMapper<Region> {

	@Override
	public Region mapRow(ResultSet rs, int rowNum) throws SQLException {
		Region region = new Region();
		region.setRegionId(rs.getInt("region_id"));
		region.setParentId(rs.getInt("parent_id"));
		region.setRegionName(rs.getString("region_name"));
		region.setRegionType(rs.getInt("region_type"));
		return region;
	}

}
