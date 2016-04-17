package com.relcare.object;

import java.sql.Date;

public class UserProfile {

	private int id;
	private String fname;
	private String lname;
	private Date dob;
	private String gender;
	private String city;
	private String state;
	private String zip;
	private String role;
	private String insurance;
	
	public UserProfile(){
		super();
	}
	public UserProfile(String fname, String lname, Date dob, String gender,
			String city, String state, String zip, String insurance, String role) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.gender = gender;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.insurance = insurance;
		this.setRole(role);
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

	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
