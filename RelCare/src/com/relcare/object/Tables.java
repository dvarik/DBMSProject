package com.relcare.object;

public class Tables {

	String tableName;
	int count;
	public Tables(String tableName, int count) {
		super();
		this.tableName = tableName;
		this.count = count;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
	
}
