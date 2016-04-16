package com.relcare.db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDbConnection {

	public static void main(String[] args) {

		try {
			
		ApplicationContext ac = new ClassPathXmlApplicationContext("/com/test/applicationContext.xml");
		//DataSource dataSource = (DataSource) ac.getBean("dataSource");
		//JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		RelcareDao dao = (RelcareDao) ac.getBean("RelCareDAO");
		
		
		System.out.println(dao.authenticateUser("jsanders4c@e-recht24.de", "j732mp"));
		System.out.println(dao.authenticateUser("test@gmail.com", "test123"));
			
/*		PrintWriter writer = new PrintWriter("D:\\avg.txt", "UTF-8");
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
*/
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
