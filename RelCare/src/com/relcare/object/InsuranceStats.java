package com.relcare.object;

public class InsuranceStats {

	int branchid;
	String branchCity;
	String illnessName;
	int patientCount;

	public InsuranceStats(int branchid, String branchCity, String illnessName,int patientCount) {
		super();
		this.branchid = branchid;
		this.branchCity = branchCity;
		this.illnessName = illnessName;
		this.patientCount = patientCount;
	}

	public int getBranchid() {
		return branchid;
	}

	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}

	public String getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

	public String getIllnessName() {
		return illnessName;
	}

	public void setIllnessName(String illnessName) {
		this.illnessName = illnessName;
	}

	public int getPatientCount() {
		return patientCount;
	}

	public void setPatientCount(int patientCount) {
		this.patientCount = patientCount;
	}
	
}
