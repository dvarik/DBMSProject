package com.relcare.object;

import java.sql.Date;

public class PatientAppointment {

	private Date d;
	private String fname;
	private String lname;
	private int start;
	private int end;
	
	public PatientAppointment(Date d, String fname, String lname, int start,
			int end) {
		super();
		this.d = d;
		this.fname = fname;
		this.lname = lname;
		this.start = start;
		this.end = end;
	}

	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}

	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
}
