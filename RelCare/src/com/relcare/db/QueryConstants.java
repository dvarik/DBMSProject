package com.relcare.db;

public class QueryConstants {

	
	final static String REVENUE_PER_DEPT = "select tc.deptid, tc.name, br.branchid, br.city, tc.totalcost "
			+ "from branch br join "
			+ "(select dt.deptid, dt.name, dt.branchid, sum(b.cost) as totalcost from "
			+ "department dt left join doctors dr on dt.deptid = dr.DEPARTMENTID "
			+ "join appointment a on a.doctorid = dr.doctorid "
			+ "join bill b on b.appointmentid = a.appointmentid "
			+ "group by dt.deptid, dt.name, dt.branchid) tc "
			+ "on br.branchid = tc.branchid";
	
	final static String AVG_DEPT_PATIENTS = "select tc.deptid, tc.name, tc.branchid, br.city, avg(tc.c) as avgP "
			+ "from branch br left join "
			+ "( select count(distinct(a.patientid)) as c,  d.deptid, d.name, d.branchid, to_char(a.appointmentdate, 'YYYY') as year "
			+ "from department d left join doctors dr on d.deptid = dr.departmentid "
			+ "join appointment a on a.doctorid = dr.doctorid "
			+ "group by d.deptid, d.name, d.branchid, to_char(a.appointmentdate, 'YYYY') ) tc "
			+ "on br.branchid = tc.branchid "
			+ "group by tc.deptid, tc.name, tc.branchid, br.city";
	
	final static String COUNT_DEPT_PATIENTS_PER_YEAR = "select tc.deptid, tc.name, br.branchid, br.city, tc.c, tc.year "
			+ "from branch br join "
			+ "( select count(distinct(a.patientid)) as c,  d.deptid, d.name, d.branchid, to_char(a.appointmentdate, 'YYYY') as year "
			+ "from department d left join doctors dr on d.deptid = dr.departmentid "
			+ "join appointment a on a.doctorid = dr.doctorid "
			+ "group by d.deptid, d.name, d.branchid, to_char(a.appointmentdate, 'YYYY') ) tc "
			+ "on br.branchid = tc.branchid";
	
	final static String INSURANCE_STATS_PER_BRANCH_YEARLY = "select tc.*, br.city "
			+ "from branch br join "
			+ "( select count(a.patientid) as c, b.COST ,d.branchid, to_char(a.appointmentdate, 'YYYY') as year, "
			+ "p.insurancetype, dia.illnessname "
			+ "from department d left join doctors dr on d.deptid = dr.DEPARTMENTID "
			+ "join appointment a on a.doctorid = dr.doctorid "
			+ "join patient p on p.patientid = a.patientid "
			+ "join bill b on b.appointmentid = a.appointmentid "
			+ "join diagnosis dia on dia.DIAGNOSISID = a.appointmentid "
			//where b.cost = 0 
			+ "group by d.branchid, p.insurancetype, dia.illnessname, to_char(a.appointmentdate, 'YYYY'), b.cost) tc "
			+ "on br.branchid = tc.branchid";
	
	final static String ILLNESS_STATS_PER_STATE_PER_AGEGRP = "select p.state, dia.illnessname, "
			+ "sum(case when trunc(months_between(sysdate, p.dateofbirth)/12) <= 5 then 1 else 0 end) as \"0-5\", "
			+ "sum(case when trunc(months_between(sysdate, p.dateofbirth)/12) > 5 and trunc(months_between(sysdate, p.dateofbirth)/12) <= 12 then 1 else 0 end) as \"6-12\", "
			+ "sum(case when trunc(months_between(sysdate, p.dateofbirth)/12) > 12 and trunc(months_between(sysdate, p.dateofbirth)/12) <= 19  then 1 else 0 end) as \"13-19\", "
			+ "sum(case when trunc(months_between(sysdate, p.dateofbirth)/12) > 19 and trunc(months_between(sysdate, p.dateofbirth)/12) <= 40  then 1 else 0 end) as \"20-40\", "
			+ "sum(case when trunc(months_between(sysdate, p.dateofbirth)/12) > 40 then 1 else 0 end) as \"Above40\" "
			+ "from userfile p "
			+ "join appointment a on p.useid = a.patientid "
			+ "join diagnosis dia on a.appointmentid = dia.DIAGNOSISID "
			+ "group by p.state, dia.illnessname";
	
	
}