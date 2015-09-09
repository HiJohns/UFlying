package com.UFlying.db.dao.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.UFlying.db.dao.BaseDao;
import com.UFlying.db.entity.Device;
import com.UFlying.db.mapper.user.DeviceMapper;

/** 保险DAO */
@Repository
public class InsuranceDao extends BaseDao {

	@Autowired
	private DeviceMapper deviceMapper;
	
	public List<Device> getDevice(){
		String sql = "select d.did, b.bid, b.name bname, b.short_name bshort, "
				+ "b.e_name benglish_name, d.name dname, d.model,"
				+ "d.price, d.type, d.weight, d.photo_url from "
				+ "uav_brand b, uav_device d where b.bid = d.bid order by d.did";
		List<Device> list = this.getJdbcTemplate().query(sql, deviceMapper);
		if (list.size() > 0) {
			return list;
		}
		return null;		
	}

}
