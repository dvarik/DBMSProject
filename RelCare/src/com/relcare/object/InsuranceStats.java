package com.relcare.object;

public class InsuranceStats {

	int branchid;
	String branchCity;
	String year;
	String illnessName;
	String insuranceType;
	int cost;
	
	int patientCount;

	public InsuranceStats(int branchid, String branchCity, String year, String illnessName, String insuranceType,
			int cost, int patientCount) {
		super();
		this.branchid = branchid;
		this.branchCity = branchCity;
		this.year = year;
		this.illnessName = illnessName;
		this.insuranceType = insuranceType;
		this.cost = cost;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getIllnessName() {
		return illnessName;
	}

	public void setIllnessName(String illnessName) {
		this.illnessName = illnessName;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getPatientCount() {
		return patientCount;
	}

	public void setPatientCount(int patientCount) {
		this.patientCount = patientCount;
	}
	
	@Override
	public String toString() {

		return ("\nBranch:" + branchid + " - " + branchCity + ", Year:" + year + ",  Illness:" + illnessName + ", InsuranceType:"
				+ insuranceType + ", PatientCount:" + patientCount + ", Cost:" + cost);
	}
	
}
