package com.relcare.object;

public class DeptPatients {

	int branchid;

	String branchCity;

	int deptid;

	String deptName;

	double avgPatients;

	int totalPatients;
	
	int year;

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

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public double getAvgPatients() {
		return avgPatients;
	}

	public void setAvgPatients(double avgPatients) {
		this.avgPatients = avgPatients;
	}

	public DeptPatients(int branchid, String branchCity, int deptid, String deptName, double avgPatients) {
		super();
		this.branchid = branchid;
		this.branchCity = branchCity;
		this.deptid = deptid;
		this.deptName = deptName;
		this.avgPatients = avgPatients;
	}

	public int getTotalPatients() {
		return totalPatients;
	}

	public void setTotalPatients(int totalPatients) {
		this.totalPatients = totalPatients;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public DeptPatients(int branchid, String branchCity, int deptid, String deptName, int total, int year) {
		super();
		this.branchid = branchid;
		this.branchCity = branchCity;
		this.deptid = deptid;
		this.deptName = deptName;
		this.totalPatients = total;
		this.year = year;
	}

	@Override
	public String toString() {

		return ("\nBranch:" + branchid + " - " + branchCity + ", Dept:" + deptid + " - " + deptName
				+ (avgPatients == 0.0D ? "" : ", Average patients per year:" + avgPatients)
				+ ((totalPatients == 0) ? "" : ", Total patients per year:" + totalPatients));
	}

}
