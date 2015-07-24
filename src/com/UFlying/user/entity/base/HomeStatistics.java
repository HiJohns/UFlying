package com.UFlying.user.entity.base;

import java.io.Serializable;

public class HomeStatistics implements Serializable {

	private static final long serialVersionUID = -2731653756418064633L;
	
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
