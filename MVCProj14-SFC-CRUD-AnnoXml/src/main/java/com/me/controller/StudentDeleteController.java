package com.me.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.me.dto.StudentDto;
import com.me.service.StudentService;
import com.me.util.CacheUtil;

@Controller("/deleteStudent.htm")
public class StudentDeleteController extends AbstractController {
	@Autowired
	private StudentService service;
	

	@Override

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println(CacheUtil.getListCache().get("StudentsList"));
		int id = 0;
		List<StudentDto> ldto = null;
		id = Integer.parseInt(request.getParameter("id"));
		int result = service.removeStudent(id);
		if (result > 0) {
			System.out.println("deleted student " + id);
		} else {
			System.out.println("not found");
		}
		ModelAndView mav = new ModelAndView();
		ldto = service.getList();
		
		mav.addObject("listStudents",ldto );
		mav.setViewName("listStudents");
		return mav;
	}

}
