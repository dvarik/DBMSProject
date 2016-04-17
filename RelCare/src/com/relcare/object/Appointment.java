package com.relcare.object;

import java.sql.Date;

public class Appointment {
	
	private int appointmentId;
	private int id;
	private String name;
	private int startTime;
	private int endTime;
	private Date appointmentDate;
	
	public Appointment(int appointmentId, int id,
			String name, int startTime,
			int endTime, Date appointmentDate) {
		super();
		this.appointmentId = appointmentId;
		this.id = id;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.appointmentDate = appointmentDate;
	}

	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", patientId="
				+ id + ", PatientFirstName=" + name + ", startTime="
				+ startTime + ", endTime=" + endTime + ", appointmentDate="
				+ appointmentDate + "]";
	}
}