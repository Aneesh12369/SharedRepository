package com.me.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.bo.StudentBo;
import com.me.dao.StudentOperationsDao;
import com.me.dto.StudentDto;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentOperationsDao dao;

	@Override
	public int generateId() {
		int id =dao.fetchId();
		return id;
	}

	@Override
	public List<StudentDto> getList() {
		List<StudentDto> listStudentsDto = null;
		listStudentsDto = new ArrayList<StudentDto>();
		List<StudentBo> listStudentsBo = null;
		listStudentsBo = dao.fetchList();
		for (StudentBo bo : listStudentsBo) {
			StudentDto dto = new StudentDto();
			BeanUtils.copyProperties(bo, dto);
			listStudentsDto.add(dto);
		}

		return listStudentsDto;
	}

	@Override
	public int registerStudent(StudentDto dto) {
		StudentBo bo = new StudentBo();
		BeanUtils.copyProperties(dto, bo);
		int inserted = dao.insertStudent(bo);
		return inserted;
	}

	@Override
	public int editStudent(StudentDto dto) {
		StudentBo bo = new StudentBo();
		BeanUtils.copyProperties(dto, bo);
		int result = dao.updateStudent(bo);
		return result;
	}

	@Override
	public int removeStudent(int sno) {
		int deleted;
		deleted = dao.deleteStudent(sno);
		return deleted;
	}

	@Override
	public StudentDto getStudentDetails(int sno) {
		StudentBo studentBo = null;
		StudentDto studentDto = null;
		studentBo = dao.fetchStudentDetails(sno);
		studentDto = new StudentDto();
		BeanUtils.copyProperties(studentBo, studentDto);
		return studentDto;
	}

}
