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
import com.me.util.JsonUtil;

@Controller("/listStudents.htm")
public class StudentListController extends AbstractController {
	@Autowired
	private StudentService service;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("Controllerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
		ModelAndView mav = new ModelAndView();
		List<StudentDto> listStudents = null;
		listStudents = service.getList();
		System.out.println(listStudents);

		mav.addObject("listStudents", listStudents);

		mav.setViewName("listStudents");
		return mav;
	}

}
