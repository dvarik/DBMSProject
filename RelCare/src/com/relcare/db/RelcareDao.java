package com.relcare.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.relcare.authenticator.RelUserDetails;
import com.relcare.object.BranchDeptRevenue;
import com.relcare.object.DeptPatients;
import com.relcare.object.IllnessStats;
import com.relcare.object.InsuranceStats;

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

	public List<DeptPatients> getAvgPatientsPerDept() {

		List<DeptPatients> b = jdbcTemplate.query(QueryConstants.AVG_DEPT_PATIENTS, new RowMapper<DeptPatients>() {

			@Override
			public DeptPatients mapRow(ResultSet rs, int arg1) throws SQLException {

				DeptPatients row = new DeptPatients(rs.getInt("branchid"), rs.getString("city"), rs.getInt("deptid"),
						rs.getString("name"), rs.getInt("avgP"));

				return row;
			}
		});

		return b;

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

		List<InsuranceStats> b = jdbcTemplate.query(QueryConstants.INSURANCE_STATS_PER_BRANCH_YEARLY,
				new RowMapper<InsuranceStats>() {

					@Override
					public InsuranceStats mapRow(ResultSet rs, int arg1) throws SQLException {

						InsuranceStats row = new InsuranceStats(rs.getInt("branchid"), rs.getString("city"),
								rs.getString("year"), rs.getString("illnessname"), rs.getString("insurancetype"),
								rs.getInt("cost"), rs.getInt("c"));

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
								Arrays.asList(rs.getInt("0-5"), rs.getInt("6-12"), rs.getInt("13-19"),
										rs.getInt("20-40"), rs.getInt("Above40")));

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
	
	public List<Appointment> getAppointmentForDoctor(int docId) {
		List<Appointment> apt = jdbcTemplate.query(QueryConstants.APPOINTMENT_FOR_DOC,
				new RowMapper<Appointment>() {
					@Override
					public Appointment mapRow(ResultSet rs, int arg1) throws SQLException {

						Appointment row = new Appointment(rs.getInt("appointmentId"), rs.getInt("patientId"), 
								rs.getString("fname"), rs.getString("lname"),
								rs.getInt("starttime"),rs.getInt("endtime"),rs.getDate("appointmentdate"));

						return row;
					}
				},
				docId);
		return apt;
	}
}
