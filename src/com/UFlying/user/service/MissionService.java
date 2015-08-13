package com.UFlying.user.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UFlying.user.dao.MissionDao;
import com.UFlying.user.entity.base.MissionFee;
import com.UFlying.user.entity.response.ResponseCreateMission;
import com.UFlying.user.form.FormCreateMission;

@Service
public class MissionService {

	@Autowired
	private MissionDao missionDao;
	
	/** 获取任务费率*/
	public List<MissionFee> getMissionFee(){
		List<MissionFee> list = missionDao.getMissionFee();
		return list;
	}

	/** 生成任务流水号
	 *  流水号格式；任务类型缩写＋YYMMDD＋会员类型 + 会员编号＋hhmmss＋4位随机数*/
	private String getMissionId(String typeInitials,long accountId, int accountType){
		SimpleDateFormat formaterDate = new SimpleDateFormat("yyMMdd");
		SimpleDateFormat formaterTime = new SimpleDateFormat("hhmmss");
		Date now = new Date();
		String missionId = typeInitials + formaterDate.format(now) + 
				accountType + accountId + formaterTime.format(now) + 
				Long.toString(Math.abs(new Random().nextLong()), 10).substring(0,4);
		return missionId;
	}

	/** 生成新任务*/
	public ResponseCreateMission createMission(FormCreateMission form) {
		String typeInitials = form.getTypeInitials();
		long accountId = form.getAccountId();
		int accountType = form.getAccountType();
		String missionId = getMissionId(typeInitials, accountId,accountType);
		form.setMissionId(missionId);
		ResponseCreateMission response = new ResponseCreateMission();
		response.setStatus(0);
		response.setMissionId(missionId);
		// 保存信息
		int rows = 0;
		try {
			rows = 	missionDao.createMission(form);
		} catch (Exception e) {
			response.setStatus(-1);
			response.setReason("数据库异常，更新失败");
			response.setMissionId(null);
		}
		if (rows <= 0) {
			response.setStatus(-1);
			response.setReason("数据库异常，更新失败");
			response.setMissionId(null);
		}

		return response;
	}
	
}
