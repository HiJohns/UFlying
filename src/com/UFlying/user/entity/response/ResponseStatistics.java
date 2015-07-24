package com.UFlying.user.entity.response;

public class ResponseStatistics extends AbstractReturn {
	private int members;
	private int directors;
	private int insurance;
	private int missions;
	
	public int getMembers() {
		return members;
	}
	public void setMembers(int members) {
		this.members = members;
	}
	public int getDirectors() {
		return directors;
	}
	public void setDirectors(int directors) {
		this.directors = directors;
	}
	public int getInsurance() {
		return insurance;
	}
	public void setInsurance(int insurance) {
		this.insurance = insurance;
	}
	public int getMissions() {
		return missions;
	}
	public void setMissions(int missions) {
		this.missions = missions;
	}
	
	
}
