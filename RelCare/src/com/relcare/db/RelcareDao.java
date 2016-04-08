package com.relcare.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.relcare.object.DeptPatients;
import com.relcare.object.IllnessStats;
import com.relcare.object.InsuranceStats;
import com.relcare.object.TotalCostPerBranchDept;

@Component("RelCareDAO")
public class RelcareDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<TotalCostPerBranchDept> getRevenuePerDept() {
		List<TotalCostPerBranchDept> a = jdbcTemplate.query(QueryConstants.REVENUE_PER_DEPT,
				new RowMapper<TotalCostPerBranchDept>() {

					@Override
					public TotalCostPerBranchDept mapRow(ResultSet rs, int arg1) throws SQLException {

						TotalCostPerBranchDept row = new TotalCostPerBranchDept(rs.getInt("branchid"),
								rs.getString("city"), rs.getInt("deptid"), rs.getString("name"),
								rs.getInt("totalCost"));

						return row;
					}
				});
		return a;
	}
	
	
	public List<DeptPatients> getAvgPatientsPerDept() {

		List<DeptPatients> b = jdbcTemplate.query(QueryConstants.AVG_DEPT_PATIENTS,
				new RowMapper<DeptPatients>() {

					@Override
					public DeptPatients mapRow(ResultSet rs, int arg1) throws SQLException {

						DeptPatients row = new DeptPatients(rs.getInt("branchid"), rs.getString("city"),
								rs.getInt("deptid"), rs.getString("name"), rs.getInt("avgP"));

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

						InsuranceStats row = new InsuranceStats(rs.getInt("branchid"), rs.getString("city"), rs.getString("year"),
								rs.getString("illnessname"), rs.getString("insurancetype"), rs.getInt("cost"), rs.getInt("c") );

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

						IllnessStats row = new IllnessStats(rs.getString("state"), rs.getString("illnessname"), Arrays.asList(rs.getInt("0-5"),
								rs.getInt("6-12"), rs.getInt("13-19"), rs.getInt("20-40"), rs.getInt("Above40")) );

						return row;
					}
				});

		return b;

	}
}
