package com.relcare.object;

public class Location {

	private String city;
	private String state;
	private int id;

	public Location(int id,String city, String state) {
		super();
		this.id = id;
		this.city = city;
		this.state = state;
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
