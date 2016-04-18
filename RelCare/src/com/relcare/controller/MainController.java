package com.relcare.controller;

import java.lang.reflect.Type;
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
import com.relcare.object.IllnessStats;
import com.relcare.object.InsuranceStats;
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
			@RequestParam("email") String email, @RequestParam("pword") String pword) {
		
		boolean res = dao.registerUser(fname,lname,email,pword,"patient");

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

	@RequestMapping(value="/getAppointmentsForPatient", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getUpcomingAppointmentsForPatient() {
		int userid = Integer.parseInt(getUserId());
		List<Appointment> profile = dao.getPatientAppointments(userid);
		Type type = new TypeToken<List<Appointment>>() {
		}.getType();
		return new Gson().toJson(profile, type);
	}
	
	@RequestMapping(value="/getDiagnosisHistoryOfPatient", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getDiagnosisHistoryOfPatient() {
		int userid = Integer.parseInt(getUserId());
		List<DiagnosisHistory> profile = dao.getDiagnosisPatient(userid);
		Type type = new TypeToken<List<Appointment>>() {
		}.getType();
		return new Gson().toJson(profile, type);
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
	
	@RequestMapping(value = "/getAvgDeptPatientsReport", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getAvgDeptPatientsReport() {
		List<DeptPatients> res = dao.getAvgPatientsPerDept();
		Gson gson = new Gson();
		Type type = new TypeToken<List<DeptPatients>>() {
		}.getType();
		return gson.toJson(res, type);
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

	@RequestMapping(value = "/getIllnessStatsReport", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getIllnessStats() {
		List<IllnessStats> res = dao.getIllnessStats();
		Gson gson = new Gson();
		Type type = new TypeToken<List<IllnessStats>>() {
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
}
