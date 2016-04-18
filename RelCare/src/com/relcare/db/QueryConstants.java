package com.relcare.db;

public class QueryConstants {

	final static String REVENUE_PER_DEPT = "select tc.deptid, tc.name, br.branchid, br.city, tc.totalcost "
			+ "from branch br left join " + "(select dt.deptid, dt.name, dt.branchid, sum(b.cost) as totalcost from "
			+ "department dt left join doctors dr on dt.deptid = dr.DEPARTMENTID "
			+ "join appointment a on a.doctorid = dr.doctorid " + "join bill b on b.appointmentid = a.appointmentid "
			+ "group by dt.deptid, dt.name, dt.branchid) tc " + "on br.branchid = tc.branchid";
	
	final static String COUNT_DEPT_PATIENTS_PER_YEAR = "select tc.deptid, tc.name, br.branchid, br.state || ' - ' || br.city as city, tc.c, tc.year "
			+ "from branch br left join "
			+ "( select count(distinct(a.patientid)) as c,  d.deptid, d.name, d.branchid, to_char(a.appointmentdate, 'YYYY') as year "
			+ "from department d left join doctors dr on d.deptid = dr.departmentid "
			+ "join appointment a on a.doctorid = dr.doctorid "
			+ "group by d.deptid, d.name, d.branchid, to_char(a.appointmentdate, 'YYYY') ) tc "
			+ "on br.branchid = tc.branchid order by city,tc.name";

	final static String INSURANCE_STATS = "select dia.illnessname, br.branchid, br.state || ' - ' || br.city as city, count(a.patientid) as c"
			+ " from branch br left join department d on d.BRANCHID = br.BRANCHID "
			+ "join doctors dr on d.deptid = dr.DEPARTMENTID "
			+ "join appointment a on a.doctorid = dr.doctorid "
			+ "join patient p on p.patientid = a.patientid "
			+ "join diagnosis dia on dia.DIAGNOSISID = a.appointmentid "
			+ "where p.INSURANCETYPE is not null "
			+ "group by dia.illnessname,br.branchid,br.state || ' - ' || br.city "
			+ "order by dia.ILLNESSNAME";

	final static String ILLNESS_STATS_PER_STATE_PER_AGEGRP = "select p.state, dia.illnessname, "
			+ "sum(case when trunc(months_between(sysdate, p.dateofbirth)/12) <= 5 then 1 else 0 end) as \"0-5\", "
			+ "sum(case when trunc(months_between(sysdate, p.dateofbirth)/12) > 5 and trunc(months_between(sysdate, p.dateofbirth)/12) <= 12 then 1 else 0 end) as \"6-12\", "
			+ "sum(case when trunc(months_between(sysdate, p.dateofbirth)/12) > 12 and trunc(months_between(sysdate, p.dateofbirth)/12) <= 19  then 1 else 0 end) as \"13-19\", "
			+ "sum(case when trunc(months_between(sysdate, p.dateofbirth)/12) > 19 and trunc(months_between(sysdate, p.dateofbirth)/12) <= 40  then 1 else 0 end) as \"20-40\", "
			+ "sum(case when trunc(months_between(sysdate, p.dateofbirth)/12) > 40 then 1 else 0 end) as \"Above40\" "
			+ "from userfile p " + "join appointment a on p.useid = a.patientid "
			+ "join diagnosis dia on a.appointmentid = dia.DIAGNOSISID " 
			+ "group by dia.illnessname,p.state order by dia.illnessname, p.state";

	public static final String AUTHENTICATE_USER = "select count(*) as c from userfile where email = ? and password = ?";

	public static final String LOAD_USER = "select * from userfile where email = ?";
	
	public static final String REGISTER_USER = "insert into userfile(useid,fname,lname,email,password,role) values(16002,?,?,?,?,?)";
	
	final static String APPOINTMENT_FOR_DOC = "select a.appointmentId,a.patientId,u.fname || ' ' || u.lname As fullname, "
			+ "a.appointmentdate,t.starttime,t.endtime,a.status, "
			+ "case when (sysdate < a.appointmentdate) then 'true' else 'false' end as canCancel "
			+ "from appointment a join timeslot t on a.timeslotid = t.timeslotid "
			+ "join userfile u on a.patientid = u.useid "
			+ "where a.doctorid = ? and a.status = 0 order by a.appointmentdate asc, t.starttime asc";

	final static String USER_PROFILE = "select u.fname,u.lname,u.city,u.state,u.zip,u.gender,u.dateofbirth,u.role "
			+ "from userfile u where u.useid = ?";
	
	public static final String GET_INSURANCE = "select insurancetype from patient where patientid = ?";

	final static String PAYMENT_HISTORY = "select u.fname||' ' ||u.lname as docName,a.appointmentdate,r.fees,b.cost,b.status "
			+ "from appointment a join bill b on a.appointmentid = b.appointmentid "
			+ "join doctors d on d.doctorid = a.doctorid "
			+ "join rank r on d.rankid = r.rankid "
			+ "join userfile u on d.doctorid = u.useid "
			+ "where a.patientid = ?";
	
	final static String UPCOMING_APPOINTMENT_PATIENTS = "select a.appointmentid,	a.doctorid, u.fname || ' ' || u.lname as doctorName,"
			+ "a.appointmentdate,t.starttime,t.endtime,a.status, "
			+ "case when (sysdate < a.appointmentdate) then 'true' else 'false' end as canCancel "
			+ "from appointment a join userfile u on u.useid = a.doctorid "
			+ "join timeslot t on a.timeslotid = t.timeslotid "
			+ "where a.patientid = ? and a.status = 0 order by a.appointmentdate asc, t.starttime asc";

	final static String PAST_APPOINTMENT_PATIENTS = "select a.appointmentid,	a.doctorid, u.fname || ' ' || u.lname as doctorName,"
			+ "a.appointmentdate,t.starttime,t.endtime,a.status, "
			+ "case when (sysdate < a.appointmentdate) then 'true' else 'false' end as canCancel "
			+ "from appointment a join userfile u on u.useid = a.doctorid "
			+ "join timeslot t on a.timeslotid = t.timeslotid "
			+ "where a.patientid = ? and a.status = 1 order by a.appointmentdate asc, t.starttime asc";

	public static final String DIAGNOSIS_HISTORY = "select p.fname,p.lname,d.*,a.APPOINTMENTDATE,a.PATIENTID,meds.medslist "
			+ "from diagnosis d "
			+ "join appointment a on a.appointmentid = d.diagnosisid "
			+ "join userfile p on p.useid = a.PATIENTID "
			+ "join (select diagnosisid, ltrim(max(sys_connect_by_path(medicinename, ';' )), ';') medslist "
			+ "from (select medicinename, diagnosisid, row_number() over "
			+ "(partition by diagnosisid order by medicinename) rn from MEDICINEPRESCRIBED) "
			+ "start with rn = 1 connect by prior rn = rn-1 "
			+ "and prior diagnosisid = diagnosisid group by diagnosisid order by diagnosisid) meds "
			+ "on meds.diagnosisid = d.DIAGNOSISID "
			+ "where a.patientid = ? order by a.appointmentdate desc";
	
	public static final String PAT_DIAGNOSIS = "select u.fname || ' ' || u.lname as docName, d.diagnosisid,d.illnessname, "
			+ "a.APPOINTMENTDATE,a.doctorid,meds.medslist "
			+ "from diagnosis d "
			+ "join appointment a on a.appointmentid = d.diagnosisid "
			+ "join userfile u on u.useid = a.doctorid "
			+ "join (select diagnosisid, ltrim(max(sys_connect_by_path(medicinename, ';' )), ';') medslist "
			+ "from (select medicinename, diagnosisid, row_number() over "
			+ "(partition by diagnosisid order by medicinename) rn from MEDICINEPRESCRIBED) "
			+ "start with rn = 1 connect by prior rn = rn-1 "
			+ "and prior diagnosisid = diagnosisid group by diagnosisid order by diagnosisid) meds "
			+ "on meds.diagnosisid = d.DIAGNOSISID "
			+ "where a.patientid = ? and a.appointmentid = ?";
	
	public static final String GET_LOCATION = "select branchid,city,state from branch";

	public static final String GET_DOC = "select u.fname||' '||u.lname as docName,d1.doctorid "
			+ "from department d "
			+ "join branch b on b.branchid = d.branchid "
			+ "join doctors d1 on d1.branchid = b.branchid "
			+ "join userfile u on u.useid = d1.doctorid "
			+ "where b.state = ? and b.city = ? and d.deptid = ?";

	public static final String GET_DEPT = "select * from department d "
			+ "join branch b on b.branchid = d.branchid "
			+ "where b.state = ? and b.city = ?";

	public static final String ENTER_APT = "insert into appointment(doctorId,patientId,appointmentdate,timeslotid) values(?,?,?,?)";
			
	public static final String GET_PATIENTS_FOR_DOC = "select ap.PATIENTID,u.fname || ' ' || u.lname As FullName,"
			+ " u.gender, u.dateofbirth as dob"
			+ " from userfile u,appointment ap where ap.PATIENTID = u.useid and ap.DOCTORID = ?";

	public static final String ENTER_DIAG = "insert into diagnosis values(?,?)";

	public static final String ENTER_MEDS = "INSERT INTO MEDICINEPRESCRIBED VALUES(?,?)";

	public static final String GET_BRANCHES = "select br.branchid, br.state, br.city "
			+ "from branch br order by br.state,br.city";

	public static final String GET_TIME = "select * from timeslot t "
			+ "where t.timeslotid not in (select a.timeslotid from appointment a "
			+ "where appointmentdate = ? and a.doctorid = ?)";

	public static final String CANCEL_APT = "update appointment set status = ? where appointmentid = ?";
				
}
