package com.relcare.object;

import java.sql.Date;

public class DiagnosisHistory {
	
	private int diagnosisId;
	private int patientId;
	private String patientFirstName;
	private String patientLastName;
	private String docName;
	private int docId;
	private Date appointmentDate;
	private String illnessDesc;
	private String medicines;
	
	public DiagnosisHistory(int diagnosisId, String docName, int docId,
			Date appointmentDate, String illnessDesc, String medicines) {
		super();
		this.diagnosisId = diagnosisId;
		this.docName = docName;
		this.docId = docId;
		this.appointmentDate = appointmentDate;
		this.illnessDesc = illnessDesc;
		this.medicines = medicines;
	}

	public DiagnosisHistory(int dia, int patientId,
			String patientFirstName, String patientLastName, Date appointmentDate, String medList, String illness) {
		super();
		this.diagnosisId = dia;
		this.patientId = patientId;
		this.patientFirstName = patientFirstName;
		this.patientLastName = patientLastName;
		this.appointmentDate = appointmentDate;
		this.medicines = medList;
		this.illnessDesc = illness;
	}

	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	@Override
	public String toString() {
		return "Diagnosis [appointmentId=" + diagnosisId + ", patientId="
				+ patientId + ", PatientFirstName=" + patientFirstName
				+ ", PatientLastName=" + patientLastName + ", appointmentDate="
				+ appointmentDate + "]";
	}

	public int getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(int diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public String getIllnessDesc() {
		return illnessDesc;
	}

	public void setIllnessDesc(String illnessDesc) {
		this.illnessDesc = illnessDesc;
	}

	public String getMedicines() {
		return medicines;
	}

	public void setMedicines(String medicines) {
		this.medicines = medicines;
	}
	
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}

	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
}
