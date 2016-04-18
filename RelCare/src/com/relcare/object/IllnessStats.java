package com.relcare.object;

import java.util.ArrayList;
import java.util.List;

public class IllnessStats {

	String state;
	String illnessName;
	int below6;
	int six12;
	int thirteen19;
	int twenty40;
	int above40;
	
	List<Integer> agegroups = new ArrayList<Integer>();
	
	public IllnessStats(String state, String illnessName, int below6,int six,int thir,int twen, int abv) {
		super();
		this.state = state;
		this.illnessName = illnessName;
		this.below6 = below6;
		this.six12 = six;
		this.thirteen19 = thir;
		this.twenty40 = twen;
		this.above40 = abv;
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

	public List<Integer> getAgegroups() {
		return agegroups;
	}

	public void setAgegroups(ArrayList<Integer> agegroups) {
		this.agegroups = agegroups;
	}
	
	
	@Override
	public String toString() {

		return ("\nState:" + state + ", Illness: " + illnessName + ", 0-5years: " + agegroups.get(0) + ", 0-5years: " + agegroups.get(0) +
				", 6-12years: " + agegroups.get(1) +
				", 13-19years: " + agegroups.get(2) +
				", 20-40years: " + agegroups.get(3) + ", Above40:" + agegroups.get(4));
	}
	
	
	
}
