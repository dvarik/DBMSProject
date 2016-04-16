package com.relcare.object;

public class BranchDeptRevenue {

	int branchid;
	
	String branchCity;
	
	int deptid;
	
	String deptName;
	
	int totalCost;
	
  	public BranchDeptRevenue(int branchid, String branchCity, int deptid, String deptName, int totalCost) {
		super();
		this.branchid = branchid;
		this.branchCity = branchCity;
		this.deptid = deptid;
		this.deptName = deptName;
		this.totalCost = totalCost;
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

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	
	@Override
	public String toString() {
		
		return ("\nBranch:" + branchid + " - " + branchCity + ", Dept:" + deptid + " - " + deptName + ", Total Revenue:" + totalCost);
	}
	
	
	
}
