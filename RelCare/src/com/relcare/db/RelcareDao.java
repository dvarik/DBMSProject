package com.relcare.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.relcare.authenticator.RelUserDetails;
import com.relcare.object.TimeSlot;
import com.relcare.object.Appointment;
import com.relcare.object.Bill;
import com.relcare.object.BranchDeptRevenue;
import com.relcare.object.Data;
import com.relcare.object.DeptPatients;
import com.relcare.object.DiagnosisHistory;
import com.relcare.object.IllnessStats;
import com.relcare.object.InsuranceStats;
import com.relcare.object.Location;
import com.relcare.object.UserProfile;

@Component("RelCareDAO")
public class RelcareDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<BranchDeptRevenue> getRevenuePerDept() {
		List<BranchDeptRevenue> a = jdbcTemplate.query(QueryConstants.REVENUE_PER_DEPT,
				new RowMapper<BranchDeptRevenue>() {

					@Override
					public BranchDeptRevenue mapRow(ResultSet rs, int arg1) throws SQLException {

						BranchDeptRevenue row = new BranchDeptRevenue(rs.getInt("branchid"), rs.getString("city"),
								rs.getInt("deptid"), rs.getString("name"), rs.getInt("totalCost"));

						return row;
					}
				});
		return a;
	}

	public List<DeptPatients> getTotalPatientsPerDept() {

		List<DeptPatients> b = jdbcTemplate.query(QueryConstants.COUNT_DEPT_PATIENTS_PER_YEAR,
				new RowMapper<DeptPatients>() {

					@Override
					public DeptPatients mapRow(ResultSet rs, int arg1) throws SQLException {

						DeptPatients row = new DeptPatients(rs.getInt("branchid"), rs.getString("city"),
								rs.getInt("deptid"), rs.getString("name"), rs.getInt("c"), rs.getInt("year"));

						return row;
					}
				});

		return b;

	}

	public List<InsuranceStats> getInsuranceStats() {

		List<InsuranceStats> b = jdbcTemplate.query(QueryConstants.INSURANCE_STATS,
				new RowMapper<InsuranceStats>() {

					@Override
					public InsuranceStats mapRow(ResultSet rs, int arg1) throws SQLException {

						InsuranceStats row = new InsuranceStats(rs.getInt("branchid"), rs.getString("city"),
								rs.getString("illnessname"),  rs.getInt("c"));

						return row;
					}
				});

		return b;

	}

	public List<IllnessStats> getIllnessStats() {

		List<IllnessStats> b = jdbcTemplate.query(QueryConstants.ILLNESS_STATS_PER_STATE_PER_AGEGRP,
				new RowMapper<IllnessStats>() {

					@Override
					public IllnessStats mapRow(ResultSet rs, int arg1) throws SQLException {

						IllnessStats row = new IllnessStats(rs.getString("state"), rs.getString("illnessname"),
								rs.getInt("0-5"), rs.getInt("6-12"), rs.getInt("13-19"),
										rs.getInt("20-40"), rs.getInt("Above40"));

						return row;
					}
				});

		return b;

	}

	public boolean authenticateUser(String username, String password) throws Exception {

		int count = jdbcTemplate.queryForObject(QueryConstants.AUTHENTICATE_USER, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {

				return rs.getInt("c");
			}
		}, username, password);

		return count == 1 ? true : false;
	}

	public UserDetails loadUser(String userName) {
		return jdbcTemplate.queryForObject(QueryConstants.LOAD_USER, new RowMapper<RelUserDetails>() {

			@Override
			public RelUserDetails mapRow(ResultSet rs, int arg1) throws SQLException {

				RelUserDetails r = new RelUserDetails(rs.getInt("useid"), rs.getString("email"),
						rs.getString("password"), rs.getString("role"));
				return r;
			}
		}, userName);
	}

	public boolean registerUser(String fname, String lname, String email, String pword, String role) {
		
		// define query arguments
		Object[] params = new Object[] { fname, lname, email, pword, role };
		// define SQL types of the arguments
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		// execute insert query to insert the data
		int row = jdbcTemplate.update(QueryConstants.REGISTER_USER, params, types);

		return (row == 1);
	}
	
	public List<Appointment> getAppointmentsForDoctor(int docId) {
		List<Appointment> apt = jdbcTemplate.query(QueryConstants.APPOINTMENT_FOR_DOC,
				new RowMapper<Appointment>() {
					@Override
					public Appointment mapRow(ResultSet rs, int arg1) throws SQLException {
						Appointment row = new Appointment(rs.getInt("appointmentId"), rs.getInt("patientId"), 
								rs.getString("fullname"), rs.getInt("starttime"),rs.getInt("endtime"),rs.getDate("appointmentdate"));
						row.setStat(Appointment.Status.getEnumFromTypeInt(rs.getInt("status")));
						row.setCanCancel(rs.getString("canCancel").equals("true"));
						return row;
					}
				},
				docId);
		return apt;
	}
	
	public UserProfile getUserProfile(int pId) {
		UserProfile profile = jdbcTemplate.queryForObject(QueryConstants.USER_PROFILE, new RowMapper<UserProfile>() {
			@Override
			public UserProfile mapRow(ResultSet rs, int arg1) throws SQLException {
				UserProfile row = new UserProfile(rs.getString("fname"), rs.getString("lname"),
						rs.getDate("dateofbirth"), rs.getString("gender"), rs.getString("city"), rs.getString("state"),
						rs.getString("zip"), null, rs.getString("role"));
				return row;
			}
		}, pId);
		
		if (profile.getRole().equals("Patient")) {

			String insuranceType = jdbcTemplate.queryForObject(QueryConstants.GET_INSURANCE, new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int arg1) throws SQLException {
					return rs.getString("insuranceType");
				}
			}, pId);
			profile.setInsurance(insuranceType);
		}

		return profile;
	}
	
	public List<Bill> getPaymentHistory(int pId) {
		List<Bill> profile = jdbcTemplate.query(QueryConstants.PAYMENT_HISTORY,
				new RowMapper<Bill>() {
					@Override
					public Bill mapRow(ResultSet rs, int arg1) throws SQLException {
						Bill row = new Bill(rs.getString("docName"),rs.getDate("appointmentdate"),
								rs.getInt("fees"),rs.getFloat("cost"),rs.getInt("status"));
						return row;
					}
				},
				pId);
		return profile;
	}
	
	public List<DiagnosisHistory> getDiagnosisHistory(Integer patientid) {
		List<DiagnosisHistory> history = jdbcTemplate.query(QueryConstants.DIAGNOSIS_HISTORY,
				new RowMapper<DiagnosisHistory>() {
					@Override
					public DiagnosisHistory mapRow(ResultSet rs, int arg1) throws SQLException {
						DiagnosisHistory row = new DiagnosisHistory(rs.getInt("diagnosisid"),rs.getInt("patientid"),
								rs.getString("fname"),rs.getString("lname"),rs.getDate("appointmentdate"),
								rs.getString("medslist"),rs.getString("illnessname"));
						return row;
					}
				}, patientid);
		return history;
	}

	public List<UserProfile> getPatientsForDoc(String userId) {
		List<UserProfile> pats = jdbcTemplate.query(QueryConstants.GET_PATIENTS_FOR_DOC,
				new RowMapper<UserProfile>() {
					@Override
					public UserProfile mapRow(ResultSet rs, int arg1) throws SQLException {
						UserProfile row = new UserProfile();
						row.setId(rs.getInt("patientid"));
						row.setFname(rs.getString("fullname"));
						row.setDob(rs.getDate("dob"));
						row.setGender(rs.getString("gender"));
						return row;
					}
				}, userId);
		return pats;
	}
	
	public List<Appointment> getPatientUpcomingAppointments(int pId) {
		List<Appointment> profile = jdbcTemplate.query(QueryConstants.UPCOMING_APPOINTMENT_PATIENTS,
				new RowMapper<Appointment>() {
					@Override
					public Appointment mapRow(ResultSet rs, int arg1) throws SQLException {
						Appointment row = new Appointment(rs.getInt("appointmentid"),rs.getInt("doctorid"),
								rs.getString("doctorName"), rs.getInt("starttime"),rs.getInt("endtime"),
								rs.getDate("appointmentdate"));
						row.setStat(Appointment.Status.getEnumFromTypeInt(rs.getInt("status")));
						row.setCanCancel(rs.getString("canCancel").equals("true"));
						return row;
					}
				}, pId);
		return profile;
	}
	
	public List<Appointment> getPatientPastAppointments(int pId) {
		List<Appointment> profile = jdbcTemplate.query(QueryConstants.PAST_APPOINTMENT_PATIENTS,
				new RowMapper<Appointment>() {
					@Override
					public Appointment mapRow(ResultSet rs, int arg1) throws SQLException {
						Appointment row = new Appointment(rs.getInt("appointmentid"),rs.getInt("doctorid"),
								rs.getString("doctorName"), rs.getInt("starttime"),rs.getInt("endtime"),
								rs.getDate("appointmentdate"));
						row.setStat(Appointment.Status.getEnumFromTypeInt(rs.getInt("status")));
						row.setCanCancel(rs.getString("canCancel").equals("true"));
						return row;
					}
				}, pId);
		return profile;
	}
	
	public DiagnosisHistory getDiagnosisPatient(int id,int appointmentid) {
		DiagnosisHistory profile = jdbcTemplate.queryForObject(QueryConstants.PAT_DIAGNOSIS,
				new RowMapper<DiagnosisHistory>() {
					@Override
					public DiagnosisHistory mapRow(ResultSet rs, int arg1) throws SQLException {
						DiagnosisHistory row = new DiagnosisHistory(rs.getInt("diagnosisid"),
								rs.getString("docName"), rs.getInt("doctorId"),
								rs.getDate("appointmentdate"),rs.getString("illnessname"),rs.getString("medslist"));
						return row;
					}
				},id,appointmentid);
		return profile;
	}
	
	public boolean saveDiagnosisReportid(final int id, String illness, String meds) {
		
		// define query arguments
		Object[] params = new Object[] { id, illness };
		
		// define SQL types of the arguments
		int[] types = new int[] { Types.INTEGER, Types.VARCHAR };
				// execute insert query to insert the data
		
		int row = jdbcTemplate.update(QueryConstants.ENTER_DIAG, params, types);
		if(row == 1){
			final String[] medList = meds.split(",");
			jdbcTemplate.batchUpdate(QueryConstants.ENTER_MEDS, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setInt(1, id);
					ps.setString(2, medList[i] );
					
				}
				
				@Override
				public int getBatchSize() {
					return medList.length;
				}
			});
		}
		
		return (row == 1);
		
	}
	
	public List<BranchDeptRevenue> getBranches() {
		List<BranchDeptRevenue> a = jdbcTemplate.query(QueryConstants.GET_BRANCHES,
				new RowMapper<BranchDeptRevenue>() {

					@Override
					public BranchDeptRevenue mapRow(ResultSet rs, int arg1) throws SQLException {

						BranchDeptRevenue row = new BranchDeptRevenue(rs.getInt("branchid"),
								rs.getString("state") + " - " + rs.getString("city"), 0, null, 0);

						return row;
					}
				});
		return a;
	}

	public List<Location> getLocation() {
		List<Location> loc = jdbcTemplate.query(QueryConstants.GET_LOCATION,
				new RowMapper<Location>() {
					@Override
					public Location mapRow(ResultSet rs, int arg1) throws SQLException {
						Location row = new Location(rs.getInt("branchid"),rs.getString("city"),rs.getString("state"));
						return row;
					}
				});
		return loc;
	}

	public List<Data> getDoc(String  state, String  city, int  dept) {
		List<Data> doc = jdbcTemplate.query(QueryConstants.GET_DOC,
				new RowMapper<Data>() {
					@Override
					public Data mapRow(ResultSet rs, int arg1) throws SQLException {
						Data row = new Data(rs.getInt("doctorid"),rs.getString("docName"));
						return row;
					}
				},state,city,dept);
		return doc;
	}

	
	public List<Data> getDept(String state, String city) {
		List<Data> dept = jdbcTemplate.query(QueryConstants.GET_DEPT,
				new RowMapper<Data>() {
					@Override
					public Data mapRow(ResultSet rs, int arg1) throws SQLException {
						Data row = new Data(rs.getInt("deptid"),rs.getString("name"));
						return row;
					}
				},state,city);
		return dept;
	}
	

	public List<TimeSlot> getTimeSlot(String dateStr,int doctor) throws ParseException {
		DateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		Date d = format.parse(dateStr);
		java.sql.Date date = new java.sql.Date(d.getTime()); 
		List<TimeSlot> time = jdbcTemplate.query(QueryConstants.GET_TIME,
				new RowMapper<TimeSlot>() {
					@Override
					public TimeSlot mapRow(ResultSet rs, int arg1) throws SQLException {
						TimeSlot row = new TimeSlot(rs.getInt("timeslotid"),rs.getInt("starttime"),rs.getInt("endtime"));
						return row;
					}
				},date,doctor);
		return time;
	}

	public boolean saveAppointment(int docId, int patientId, String dateStr, int time) throws ParseException {
		DateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		Date d = format.parse(dateStr);
		java.sql.Date date = new java.sql.Date(d.getTime()); 

		// define query arguments
		Object[] params = new Object[] { docId,patientId, date, time };
		
		// define SQL types of the arguments
		int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.DATE, Types.INTEGER };
		
		// execute insert query to insert the data
		int row = jdbcTemplate.update(QueryConstants.ENTER_APT, params, types);
		
		return (row == 1);
	}

	public boolean cancelAppointment(int aptid) {
		// define query arguments
			int status = 2;
				Object[] params = new Object[] { status, aptid };
				
				// define SQL types of the arguments
				int[] types = new int[] { Types.INTEGER, Types.INTEGER };
				
				// execute insert query to insert the data
				int row = jdbcTemplate.update(QueryConstants.CANCEL_APT, params, types);
				
				return (row == 1);
	}
}
