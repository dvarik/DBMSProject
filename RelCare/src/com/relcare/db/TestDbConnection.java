package com.relcare.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDbConnection {

	public static void main(String[] args) {

		try {
			
		ApplicationContext ac = new ClassPathXmlApplicationContext("/com/test/applicationContext.xml");
		//DataSource dataSource = (DataSource) ac.getBean("dataSource");
		//JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		RelcareDao dao = (RelcareDao) ac.getBean("RelCareDAO");
			
		PrintWriter writer = new PrintWriter("D:\\avg.txt", "UTF-8");
		writer.write(dao.getAvgPatientsPerDept().toString());
		writer.close();
		writer = new PrintWriter("D:\\revenue.txt", "UTF-8");
		writer.write(dao.getRevenuePerDept().toString());
		writer.close();
		writer = new PrintWriter("D:\\patients.txt", "UTF-8");
		writer.write(dao.getTotalPatientsPerDept().toString());
		writer.close();
		writer = new PrintWriter("D:\\insurance.txt", "UTF-8");
		writer.write(dao.getInsuranceStats().toString());
		writer.close();
		writer = new PrintWriter("D:\\illnessgroups.txt", "UTF-8");
		writer.write(dao.getIllnessStats().toString());
		writer.close();
		System.out.println("Done");
		//System.out.println(dao.getAvgPatientsPerDept());
			//System.out.println(dao.getRevenuePerDept());
			//System.out.println(dao.getTotalPatientsPerDept());
		//System.out.println(dao.getInsuranceStats());
		//System.out.println(dao.getIllnessStats());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
