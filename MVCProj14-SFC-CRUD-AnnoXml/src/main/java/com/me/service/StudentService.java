package com.me.service;

import java.util.List;

import com.me.dto.StudentDto;

public interface StudentService {
	public int generateId();
	public List<StudentDto> getList();
	public int registerStudent(StudentDto dto);
	public int editStudent(StudentDto dto);
	public int removeStudent(int sno);
	public StudentDto getStudentDetails(int sno);

}
