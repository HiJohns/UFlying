package com.UFlying.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UFlying.db.dao.user.HomeDao;
import com.UFlying.db.entity.HomeStatistics;
import com.UFlying.json.response.ResponseStatistics;

@Service
public class HomeService {

	@Autowired
	private HomeDao homeDao;

	public ResponseStatistics getStatistics() {
		ResponseStatistics responseStatistics = new ResponseStatistics();
		HomeStatistics statistics  = homeDao.getHomeStatistics();
		responseStatistics.setMembers(statistics.getMembers());
		responseStatistics.setDirectors(statistics.getDirectors());
		responseStatistics.setInsurance(statistics.getInsurance());
		responseStatistics.setMissions(statistics.getMissions());
		return responseStatistics;
	}

}
