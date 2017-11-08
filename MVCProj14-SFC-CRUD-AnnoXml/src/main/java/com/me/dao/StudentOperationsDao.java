package com.me.dao;

import java.util.List;

import com.me.bo.StudentBo;

public interface StudentOperationsDao {
	public int fetchId();

	public List<StudentBo> fetchList();

	public int insertStudent(StudentBo bo);

	public int updateStudent(StudentBo bo);

	public int deleteStudent(int sno);

	public StudentBo fetchStudentDetails(int sno);
}
