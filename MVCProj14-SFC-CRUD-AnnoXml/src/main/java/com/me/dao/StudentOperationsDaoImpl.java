package com.me.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import com.me.bo.StudentBo;

@Repository("studentDao")
public class StudentOperationsDaoImpl implements StudentOperationsDao {

	@Autowired
	private JdbcTemplate jt;
	private static final String FETCH_ALL_RECORDS = "SELECT SNO,SNAME,SADD,AGE FROM STUDENT";
	private static final String DELETE_STUDENT_RECORD = "DELETE FROM STUDENT WHERE SNO = ?";
	private static final String FETCH_STUDENT_RECORD = "SELECT SNO,SNAME,SADD,AGE FROM STUDENT WHERE SNO = ?";
	private static final String UPDATE_STUDENT_RECORD = "UPDATE STUDENT SET SNAME=?,SADD=?,AGE=? WHERE SNO = ?";
	private static final String GENERATE_STUDENT_ID = "SELECT MAX(SNO) FROM STUDENT";
	private static final String INSERT_STUDENT_RECORD = "INSERT INTO STUDENT (SNO,SNAME,SADD,AGE) VALUES (?,?,?,?)";
	@Override
	public int fetchId() {
		int max = jt.queryForInt(GENERATE_STUDENT_ID);
		max = ++max;
		return max;
	}

	@Override
	public List<StudentBo> fetchList() {
		List<StudentBo> studentList = null;
		studentList = new ArrayList<StudentBo>();
		studentList = jt.query(FETCH_ALL_RECORDS, new RowMapper<StudentBo>() {

			@Override
			public StudentBo mapRow(ResultSet rs, int rowNum) throws SQLException {
				StudentBo bo = new StudentBo();
				bo.setId(rs.getInt("SNO"));
				bo.setName(rs.getString("SNAME"));
				bo.setAddress(rs.getString("SADD"));
				bo.setAge(rs.getInt("AGE"));
				return bo;
			}

		});
		return studentList;
	}

	@Override
	public int insertStudent(StudentBo bo) {
		int insert = jt.update(INSERT_STUDENT_RECORD, bo.getId(),bo.getName(),bo.getAddress(),bo.getAge());
		
		return insert;
	}

	@Override
	public int updateStudent(final StudentBo bo) {
		int up = jt.update(UPDATE_STUDENT_RECORD, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bo.getName());
				ps.setString(2, bo.getAddress());
				ps.setInt(3, bo.getAge());
				ps.setInt(4, bo.getId());
			}
		});
		
		return up;
	}

	@Override
	public int deleteStudent(int sno) {
		int result;
		result = jt.update(DELETE_STUDENT_RECORD, sno);
		return result;
	}

	@Override
	public StudentBo fetchStudentDetails(int sno) {
		StudentBo sBo = null;
		sBo = jt.queryForObject(FETCH_STUDENT_RECORD, new RowMapper<StudentBo>() {

			@Override
			public StudentBo mapRow(ResultSet rs, int rowNum) throws SQLException {
				StudentBo bo = new StudentBo();
				bo.setId(rs.getInt("SNO"));
				bo.setName(rs.getString("SNAME"));
				bo.setAddress(rs.getString("SADD"));
				bo.setAge(rs.getInt("AGE"));
				return bo;
			}

		},sno);
		return sBo;
	}

}
