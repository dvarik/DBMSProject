package com.relcare.controller;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.relcare.authenticator.RelUserDetails;
import com.relcare.db.RelcareDao;
import com.relcare.object.Appointment;
import com.relcare.object.Bill;
import com.relcare.object.BranchDeptRevenue;
import com.relcare.object.DeptPatients;
import com.relcare.object.DiagnosisHistory;
import com.relcare.object.IllnessSeasonStats;
import com.relcare.object.Data;
import com.relcare.object.IllnessStats;
import com.relcare.object.InsuranceStats;
import com.relcare.object.Location;
import com.relcare.object.TimeSlot;
import com.relcare.object.UserProfile;

@Controller
public class MainController {

	@Autowired
	public RelcareDao dao;

	/**
	 * Test method
	 * 
	 * @return test
	 */
	@RequestMapping("/test")
	public String test() {
		return "home.html";
	}

	@RequestMapping("/registeruser")
	public String register(@RequestParam("fname") String fname, @RequestParam("lname") String lname,
			@RequestParam("email") String email, @RequestParam("pword") String pword, @RequestParam("dob") String dob, 
			@RequestParam("dcity") String dcity, @RequestParam("dstate") String dstate, @RequestParam("dzip") String dzip,
			@RequestParam("gender") String gender) throws ParseException {
		
		boolean res = dao.registerUser(fname,lname,email,pword,dob,gender,dcity,dstate,dzip,"patient");

		return "login.jsp?registered=" + (res ? "true" : "false");
	}
	
	@RequestMapping("/reg")
	public String registerPage() {
		System.out.println("page");
		return "register.jsp";
	}
	
	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public
	 * String loginPage() { System.out.println("here"); return "login"; }
	 * 
	 * @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	 * public String home() { return "home"; }
	 */

	@RequestMapping("/getUserId")
	public String getUserId() {
		RelUserDetails userDetails = (RelUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return String.valueOf(userDetails.getUserid());
	}
	
	@RequestMapping(value="/getProfile", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getUserProfile() {
		int userid = Integer.parseInt(getUserId());
		UserProfile profile = dao.getUserProfile(userid);
		Type type = new TypeToken<UserProfile>() {
		}.getType();
		return new Gson().toJson(profile, type);
	}

	@RequestMapping(value = "/getAppointmentsForDoctor", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getAppointmentListForDoctor() {
		List<Appointment> res = dao.getAppointmentsForDoctor(Integer.parseInt(getUserId()));
		Gson gson = new Gson();
		Type type = new TypeToken<List<Appointment>>() {
		}.getType();
		return gson.toJson(res, type);
	}
	
	@RequestMapping(value = "/saveDiagnosisReport", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String saveDiagnosisReport(@RequestParam("id") int id, @RequestParam("illness") String illness,
			@RequestParam("meds") String meds) {
		//trigger to change appt stat to 1
		return String.valueOf(dao.saveDiagnosisReportid(id, illness, meds));
	}
	
	
	@RequestMapping(value="/getPatientsForDoc", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getPatientsForDoc() {
		List<UserProfile> pats = dao.getPatientsForDoc(getUserId());
		Type type = new TypeToken<List<UserProfile>>() {
		}.getType();
		return new Gson().toJson(pats, type);
	}
	
	@RequestMapping(value="/getDiagnosisHistory", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getDiagnosisHistory(@RequestParam(value = "patientid") Integer patientid) {
		List<DiagnosisHistory> profile = dao.getDiagnosisHistory(patientid);
		Type type = new TypeToken<List<DiagnosisHistory>>() {
		}.getType();
		return new Gson().toJson(profile, type);
	}

	@RequestMapping(value="/getUpcomingAppointmentsForPatient", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getUpcomingAppointmentsForPatient() {
		int userid = Integer.parseInt(getUserId());
		List<Appointment> profile = dao.getPatientUpcomingAppointments(userid);
		Type type = new TypeToken<List<Appointment>>() {
		}.getType();
		return new Gson().toJson(profile, type);
	}
	
	@RequestMapping(value="/getPastAppointmentsForPatient", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getPastAppointmentsForPatient() {
		int userid = Integer.parseInt(getUserId());
		List<Appointment> profile = dao.getPatientPastAppointments(userid);
		Type type = new TypeToken<List<Appointment>>() {
		}.getType();
		return new Gson().toJson(profile, type);
	}
	
	@RequestMapping(value="/getDiagnosisHistoryOfPatient", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getDiagnosisHistoryOfPatient(@RequestParam(value = "appointmentid") Integer appointmentid) {
		int userid = Integer.parseInt(getUserId());
		DiagnosisHistory profile = dao.getDiagnosisPatient(userid,appointmentid);
		Type type = new TypeToken<DiagnosisHistory>() {
		}.getType();
		return new Gson().toJson(profile, type);
	}
	
	@RequestMapping(value="/getLocation", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getLocation() {
		List<Location> loc = dao.getLocation();
		Type type = new TypeToken<List<Location>>() {
		}.getType();
		return new Gson().toJson(loc, type);
	}
	
	@RequestMapping(value="/getDoc", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getDoc(@RequestParam(value = "state") String state,@RequestParam(value = "dept") int dept,
			@RequestParam(value = "city") String city) {
		List<Data> loc = dao.getDoc(state,city,dept);
		Type type = new TypeToken<List<Data>>() {
		}.getType();
		return new Gson().toJson(loc, type);
	}
	
	@RequestMapping(value="/getDept", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getDept(@RequestParam(value = "state") String state,@RequestParam(value = "city") String city) {
		List<Data> loc = dao.getDept(state,city);
		Type type = new TypeToken<List<Data>>() {
		}.getType();
		return new Gson().toJson(loc, type);
	}
	
	@RequestMapping(value="/getTimeSlot", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getTimeSlot(@RequestParam(value = "dateStr") String dateStr,@RequestParam(value = "docId") int docId) throws ParseException {
		List<TimeSlot> loc = dao.getTimeSlot(dateStr,docId);
		Type type = new TypeToken<List<TimeSlot>>() {
		}.getType();
		return new Gson().toJson(loc, type);
	}
	
	@RequestMapping(value = "/saveAppointment", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String saveAppointment(@RequestParam("docId") int docid, @RequestParam("aptdate") String date,
			@RequestParam("time") int timeId) throws ParseException {
		int userid = Integer.parseInt(getUserId());
		return String.valueOf(dao.saveAppointment(docid,userid, date, timeId));
	}

	@RequestMapping(value = "/cancelAppointment", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String cancelAppointment(@RequestParam("aptId") int aptid) {
		return String.valueOf(dao.cancelAppointment(aptid));
	}

	@RequestMapping(value="/getRank", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getRank() {
		List<Data> loc = dao.getRank();
		Type type = new TypeToken<List<Data>>() {
		}.getType();
		return new Gson().toJson(loc, type);
	}
	
	//{fname:rep.fname,lname:rep.lname,dcity:rep.city,dstate:rep.state,dzip:rep.zip,
    	//gender:rep.gender,dob:date,email:rep.email,pass:rep.password,reg:rep.regnum,deRegisterDoc
    	//state:rep.stateB,branch:rep.branch,dept:rep.dept,rankid:rep.rank}
	
	@RequestMapping(value = "/registerDoc", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String registerDoc(@RequestParam("fname") String fname, @RequestParam("lname") String lname,  
			@RequestParam("dcity") String dcity, @RequestParam("dstate") String dstate, @RequestParam("zip") String dzip,
			@RequestParam("gender") String gender,@RequestParam("dob") String dob, @RequestParam("email") String email, 
			@RequestParam("pass") String pass,@RequestParam("reg") Integer reg, @RequestParam("state") String state, 
			@RequestParam("branch") String branch, @RequestParam("dept") String dept,@RequestParam("rankid") Integer rankid) throws ParseException {
		return String.valueOf(dao.registerDoc(fname,lname,dcity,dstate,dzip,gender,dob,email,pass,
				reg,state,branch,dept,rankid));
	}
	
	@RequestMapping(value = "/deRegisterDoc", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String deRegisterDoc(@RequestParam("doctorId") int id ) {
		return String.valueOf(dao.deRegisterDoc(id));
	}
	
	@RequestMapping(value="/getPaymentHistory", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getPaymentHistory() {
		int userid = Integer.parseInt(getUserId());
		System.out.println(userid);
		List<Bill> bill = dao.getPaymentHistory(userid);
		Type type = new TypeToken<List<Bill>>() {
		}.getType();
		return new Gson().toJson(bill, type);
	}
	
	@RequestMapping(value = "/getTotalDeptPatientsReport", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getTotalDeptPatientsReport() {
		List<DeptPatients> res = dao.getTotalPatientsPerDept();
		Gson gson = new Gson();
		Type type = new TypeToken<List<DeptPatients>>() {
		}.getType();
		return gson.toJson(res, type);
	}

	@RequestMapping(value = "/getIllnessStatsPerAge", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getIllnessStats() {
		List<IllnessStats> res = dao.getIllnessStats();
		Gson gson = new Gson();
		Type type = new TypeToken<List<IllnessStats>>() {
		}.getType();
		return gson.toJson(res, type);
	}
	
	@RequestMapping(value = "/getIllnessStatsPerSeason", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getIllnessStatsPerSeason() {
		List<IllnessSeasonStats> res = dao.getIllnessStatsPerSeason();
		Gson gson = new Gson();
		Type type = new TypeToken<List<IllnessSeasonStats>>() {
		}.getType();
		return gson.toJson(res, type);
	}
	
	@RequestMapping(value = "/getInsuranceStatsReport", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getInsuranceStats() {
		List<InsuranceStats> res = dao.getInsuranceStats();
		Gson gson = new Gson();
		Type type = new TypeToken<List<InsuranceStats>>() {
		}.getType();
		return gson.toJson(res, type);
	}

	@RequestMapping(value = "/getBranchRevenueReport", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getBranchRevenueReport() {
		List<BranchDeptRevenue> res = dao.getRevenuePerDept();
		Gson gson = new Gson();
		Type type = new TypeToken<List<BranchDeptRevenue>>() {
		}.getType();
		return gson.toJson(res, type);
	}
	
	@RequestMapping(value = "/getBranches", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getBranches() {
		List<BranchDeptRevenue> res = dao.getBranches();
		Gson gson = new Gson();
		Type type = new TypeToken<List<BranchDeptRevenue>>() {
		}.getType();
		return gson.toJson(res, type);
	}
}
