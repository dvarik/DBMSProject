package com.relcare.object;

import java.sql.Date;

public class Appointment {
	
	private int appointmentId;
	private int patientId;
	private String PatientFirstName;
	private String PatientLastName;
	private int startTime;
	private int endTime;
	private Date appointmentDate;
	
	public Appointment(int appointmentId, int patientId,
			String patientFirstName, String patientLastName, int startTime,
			int endTime, Date appointmentDate) {
		super();
		this.appointmentId = appointmentId;
		this.patientId = patientId;
		PatientFirstName = patientFirstName;
		PatientLastName = patientLastName;
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

	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatientFirstName() {
		return PatientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		PatientFirstName = patientFirstName;
	}

	public String getPatientLastName() {
		return PatientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		PatientLastName = patientLastName;
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
				+ patientId + ", PatientFirstName=" + PatientFirstName
				+ ", PatientLastName=" + PatientLastName + ", startTime="
				+ startTime + ", endTime=" + endTime + ", appointmentDate="
				+ appointmentDate + "]";
	}
}
