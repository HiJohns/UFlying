package com.UFlying.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.UFlying.user.dao.common.BaseDao;
import com.UFlying.user.dao.mapper.HomeStatisticsMapper;
import com.UFlying.user.entity.base.HomeStatistics;

/** 首页DAO */
@Repository
public class HomeDao extends BaseDao {

	@Autowired
	private HomeStatisticsMapper homeStatisticsMapper;

	/** 首页统计信息 */
	public HomeStatistics getHomeStatistics() {
//		String sql = "";
//		List<HomeStatistics> list = this.getJdbcTemplate().query(sql, homeStatisticsMapper);
//		if (list.size() > 0) {
//			return list.get(0);
//		}
//		return null;
		HomeStatistics rtn = new HomeStatistics();
		rtn.setMembers(45298);
		rtn.setDirectors(15);
		rtn.setInsurance(856);
		rtn.setMissions(397);
		return rtn;
	}

}
