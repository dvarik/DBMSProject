package com.relcare.object;

import java.sql.Date;

public class Bill {

	private Date d;
	private String docFname;
	private String docLname;
	private int fees;
	private float insuranceCoverage;
	private float amount;
	private String status;
	
	public Bill(String docFname, String docLname,Date d,int fees, float amount, int status) {
		super();
		this.d = d;
		this.docFname = docFname;
		this.docLname = docLname;
		this.fees = fees;
		this.insuranceCoverage =  fees - amount;
		this.amount = amount;
		if(status == 1)
			this.status = "Paid";
		else
			this.status = "Due";
	}

	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}

	public String getDocFame() {
		return docFname;
	}
	public void setDocFame(String docFname) {
		this.docFname = docFname;
	}

	public String getDocLname() {
		return docLname;
	}
	public void setDocLname(String docLname) {
		this.docLname = docLname;
	}

	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}

	public float getInsuranceCoverage() {
		return insuranceCoverage;
	}
	public void setInsuranceCoverage(float insuranceCoverage) {
		this.insuranceCoverage = insuranceCoverage;
	}

	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
