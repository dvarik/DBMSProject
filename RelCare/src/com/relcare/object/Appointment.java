package com.relcare.object;

import java.sql.Date;

public class Appointment {
	
	private int appointmentId;
	private int id;
	private String name;
	private int startTime;
	private int endTime;
	private Date appointmentDate;
	private Status stat;
	private boolean canCancel;
	
	public enum Status {

		BOOKED(0, "Booked"), COMPLETED(1, "Completed"), CANCELLED(2, "Cancelled");

		private int statusInt;
		private String statusLabel;

		Status(int statInt, String statLabel) {
			this.statusInt = statInt;
			this.statusLabel = statLabel;
		}

		public static Status getEnumFromTypeInt(int statInt) {

			Status stat = null;
			for (Status s : Status.values()) {
				if (s.statusInt == statInt)
					return s;
			}
			return stat;

		}

		public String getStatusLabel() {
			return statusLabel;
		}

	}
	
	public Appointment(int appointmentId, int id, String name, int startTime, int endTime, Date appointmentDate) {
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
	
	public void setPatientFirstName(String patientFirstName) {
		name = patientFirstName;
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

	public Status getStat() {
		return stat;
	}

	public void setStat(Status stat) {
		this.stat = stat;
	}

	public boolean isCanCancel() {
		return canCancel;
	}

	public void setCanCancel(boolean canCancel) {
		this.canCancel = canCancel;
	}
}
