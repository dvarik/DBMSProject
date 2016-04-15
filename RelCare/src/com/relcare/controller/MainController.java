package com.relcare.controller;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.relcare.db.RelcareDao;
import com.relcare.object.DeptPatients;

@Controller
public class MainController {
	
	@Autowired
	public static RelcareDao dao;
	
	/**
     * Test method
     * @return test
     */
    @RequestMapping("/test")
    public String test()
    {
        return "hello";
    }
    
    @RequestMapping(value="/relcare/getAvgDeptPatientsReport", method=RequestMethod.GET, produces="application/json")
    @ResponseBody
    public String getAvgDeptPatientsReport()
    {
        List<DeptPatients> res = dao.getAvgPatientsPerDept();
        Gson gson = new Gson();
        Type type = new TypeToken<List<DeptPatients>>() {}.getType();
        return gson.toJson(res, type);
    }
  
    
    
}
