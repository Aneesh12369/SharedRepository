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

@Controller("documentWriter")
public class GenerateDocumentController extends AbstractController {
	@Autowired
	private StudentService service;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String type = request.getParameter("type");
		List<StudentDto> list = service.getList();
		if (type.equalsIgnoreCase("pdf")) {
			return new ModelAndView("pdfSheet", "studentList", list);
		} else {
			return new ModelAndView("excelSheet", "studentList", list);
		}

	}

}
