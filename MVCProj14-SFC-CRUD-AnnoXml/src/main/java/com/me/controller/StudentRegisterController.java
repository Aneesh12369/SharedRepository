package com.me.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.me.domain.StudentCommand;
import com.me.dto.StudentDto;
import com.me.service.StudentService;
import com.me.validator.StudentCommandValidator;

@SuppressWarnings("deprecation")
@Controller("/registerStudent.htm")
public class StudentRegisterController extends SimpleFormController{
	@Autowired
	private StudentService service;
	
	public StudentRegisterController(){
		setCommandClass(StudentCommand.class);
		setCommandName("registerCommand");
		setFormView("register");
		setSessionForm(true);
		setValidator(new StudentCommandValidator());
	}
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		StudentCommand cmd = (StudentCommand) command;
		StudentDto dto =new StudentDto();
		ModelAndView mav = new ModelAndView();
		BeanUtils.copyProperties(cmd, dto);
		int registered = service.registerStudent(dto);
		System.out.println("Student registered "+cmd.getId());
		List<StudentDto> listStudents = null;
		listStudents = service.getList();
		mav.addObject("listStudents", listStudents);
		mav.setViewName("listStudents");
		mav.addObject("registerCommand", cmd);
		return mav;
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		StudentCommand cmd = new StudentCommand();
		cmd.setId(service.generateId());
		return cmd;
	}
	@Override
	protected ModelAndView handleInvalidSubmit(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return new ModelAndView("home");
	}

}
