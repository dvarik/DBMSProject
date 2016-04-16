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
import com.relcare.object.BranchDeptRevenue;
import com.relcare.object.DeptPatients;
import com.relcare.object.IllnessStats;
import com.relcare.object.InsuranceStats;

@Controller
public class MainController {

	@Autowired
	public static RelcareDao dao;

	/**
	 * Test method
	 * 
	 * @return test
	 */
	@RequestMapping("/test")
	public String test() {
		return "home";
	}

	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public
	 * String loginPage() { System.out.println("here"); return "login"; }
	 * 
	 * @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	 * public String home() { return "home"; }
	 */

	@RequestMapping("/getUser")
	public String getUser() {
		RelUserDetails userDetails = (RelUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return String.valueOf(userDetails.getUserid());
	}

	@RequestMapping(value = "/relcare/getAppointments", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getAvgDeptPatientsReport(@RequestParam(value = "userid") Integer userId) {
		List<DeptPatients> res = null;// dao.getAppointmentsForDoc(userId);
		Gson gson = new Gson();
		Type type = new TypeToken<List<DeptPatients>>() {
		}.getType();
		return gson.toJson(res, type);
	}

	@RequestMapping(value = "/relcare/getAvgDeptPatientsReport", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getAvgDeptPatientsReport() {
		List<DeptPatients> res = dao.getAvgPatientsPerDept();
		Gson gson = new Gson();
		Type type = new TypeToken<List<DeptPatients>>() {
		}.getType();
		return gson.toJson(res, type);
	}

	@RequestMapping(value = "/relcare/getTotalDeptPatientsReport", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getTotalDeptPatientsReport() {
		List<DeptPatients> res = dao.getTotalPatientsPerDept();
		Gson gson = new Gson();
		Type type = new TypeToken<List<DeptPatients>>() {
		}.getType();
		return gson.toJson(res, type);
	}

	@RequestMapping(value = "/relcare/getIllnessStatsReport", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getIllnessStats() {
		List<IllnessStats> res = dao.getIllnessStats();
		Gson gson = new Gson();
		Type type = new TypeToken<List<IllnessStats>>() {
		}.getType();
		return gson.toJson(res, type);
	}

	@RequestMapping(value = "/relcare/getInsuranceStatsReport", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getInsuranceStats() {
		List<InsuranceStats> res = dao.getInsuranceStats();
		Gson gson = new Gson();
		Type type = new TypeToken<List<InsuranceStats>>() {
		}.getType();
		return gson.toJson(res, type);
	}

	@RequestMapping(value = "/relcare/getBranchRevenueReport", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getBranchRevenueReport() {
		List<BranchDeptRevenue> res = dao.getRevenuePerDept();
		Gson gson = new Gson();
		Type type = new TypeToken<List<BranchDeptRevenue>>() {
		}.getType();
		return gson.toJson(res, type);
	}

}
