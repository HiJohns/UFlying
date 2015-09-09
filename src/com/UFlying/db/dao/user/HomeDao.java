package com.UFlying.db.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.UFlying.db.dao.BaseDao;
import com.UFlying.db.entity.HomeStatistics;
import com.UFlying.db.mapper.user.HomeStatisticsMapper;

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
