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
@Controller("/editStudent.htm")
public class EditStudentController extends SimpleFormController {

	@Autowired
	private StudentService service;

	public EditStudentController() {
		setCommandClass(StudentCommand.class);
		setCommandName("editStudentCommand");
		setFormView("editStudent");
		setSessionForm(true);
		setValidator(new StudentCommandValidator());

	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		StudentCommand command = null;
		command = new StudentCommand();
		int no = 0;
		no = Integer.parseInt(request.getParameter("id"));
		StudentDto sdto = service.getStudentDetails(no);
		BeanUtils.copyProperties(sdto, command);
		return command;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		ModelAndView mav = null;
		mav = new ModelAndView();
		StudentCommand cmd = (StudentCommand) command;
		StudentDto dto = new StudentDto();
		BeanUtils.copyProperties(cmd, dto);
		int updated = service.editStudent(dto);
		System.out.println("Student updated " + updated);
		List<StudentDto> listStudents = null;
		listStudents = service.getList();

		mav.addObject("listStudents", listStudents);

		mav.setViewName("listStudents");
		return mav;

	}

	@Override
	protected ModelAndView handleInvalidSubmit(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return new ModelAndView("home");
	}

}
