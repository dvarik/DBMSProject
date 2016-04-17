package com.relcare.object;

import java.sql.Date;

public class Bill {

	private Date d;
	private String docName;
	private int fees;
	private float insuranceCoverage;
	private float amount;
	private String status;
	
	public Bill(String docName,Date d,int fees, float amount, int status) {
		super();
		this.d = d;
		this.docName = docName;
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

	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
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
