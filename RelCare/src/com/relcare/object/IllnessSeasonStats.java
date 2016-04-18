package com.relcare.object;

public class IllnessSeasonStats {

	String state;
	String illnessName;
	int springCount;
	int summerCount;
	int fallCount;
	int winterCount;
	
	public IllnessSeasonStats(String state, String illnessName, int spring,int summer,int fall,int winter) {
		super();
		this.state = state;
		this.illnessName = illnessName;
		this.springCount = spring;
		this.summerCount = summer;
		this.fallCount = fall;
		this.winterCount = winter;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIllnessName() {
		return illnessName;
	}

	public void setIllnessName(String illnessName) {
		this.illnessName = illnessName;
	}

}
