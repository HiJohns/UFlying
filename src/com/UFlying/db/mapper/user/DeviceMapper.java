package com.UFlying.db.mapper.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.UFlying.db.entity.Device;

@Repository
public class DeviceMapper implements RowMapper<Device> {

	@Override
	public Device mapRow(ResultSet rs, int rowNum) throws SQLException {
		Device device = new Device();
		device.setBrandId(rs.getInt("bid"));
		device.setBrandName(rs.getString("bname"));
		device.setBrandShortName(rs.getString("bshort"));
		device.setBrandEnglishName(rs.getString("benglish_name"));
		device.setDeviceId(rs.getInt("did"));
		device.setDeviceName(rs.getString("dname"));
		device.setModel(rs.getString("model"));
		device.setPrice(rs.getInt("price"));
		device.setType(rs.getString("type"));
		device.setWeight(rs.getInt("weight"));
		device.setPhotoUrl(rs.getString("photo_url"));
		return device;
	}
	
}
