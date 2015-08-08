package com.UFlying.user.dao.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.UFlying.user.dao.mapper.RegionMapper;
import com.UFlying.user.entity.base.Region;

@Repository
public class CommonDao extends BaseDao {
	
	@Autowired
	private RegionMapper regionMapper;
	
	public List<Region> getRegion(){
		String sql = "select * from region where region_type in (?,?,?)";
		List<Region> list = this.getJdbcTemplate().query(sql, regionMapper, 0,1,2);
		if (list.size() > 0) {
			return list;
		}
		return null;		
	}

}
