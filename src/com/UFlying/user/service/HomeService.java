package com.UFlying.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UFlying.user.dao.HomeDao;
import com.UFlying.user.entity.base.HomeStatistics;
import com.UFlying.user.entity.response.ResponseStatistics;

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
