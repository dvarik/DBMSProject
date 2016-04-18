package com.relcare.object;

public class Data {
	
	private int id;
	private String name;
	
	public Data(int docId, String docName) {
		super();
		this.id = docId;
		this.name = docName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int docId) {
		this.id = docId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String docName) {
		this.name = docName;
	}
	
	
}
