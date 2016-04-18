package com.relcare.object;

public class TimeSlot {
	
	private int id;
	private int start;
	private int end;
	
	public TimeSlot(int id, int start, int end) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
