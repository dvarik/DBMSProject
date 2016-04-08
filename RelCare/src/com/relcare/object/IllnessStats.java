package com.relcare.object;

import java.util.ArrayList;
import java.util.List;

public class IllnessStats {

	String state;
	String illnessName;

	List<Integer> agegroups = new ArrayList<Integer>();
	
	public IllnessStats(String state, String illnessName, List<Integer> agegroups) {
		super();
		this.state = state;
		this.illnessName = illnessName;
		this.agegroups = agegroups;
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
