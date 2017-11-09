package com.me.springboot.courseapp.course;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.me.springboot.courseapp.exceptions.CourseNotFoundException;
import com.me.springboot.courseapp.exceptions.IllegalCourseJsonArgumentException;
import com.me.springboot.courseapp.util.ErrorDescription;

@RestControllerAdvice
public class CourseControllerAdvice {

	@ExceptionHandler({CourseNotFoundException.class})
	public ResponseEntity<ErrorDescription> handleInvalidId(CourseNotFoundException ex) {
		ErrorDescription error = new ErrorDescription();
		error.setErrorcode(4001);
		error.setDescripton(ex.getLocalizedMessage());
		
		
		return new ResponseEntity<ErrorDescription>(error,HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler({IllegalCourseJsonArgumentException.class,InvalidFormatException.class})
	public ResponseEntity<ErrorDescription> handleInvalidArgumets(Exception ex) {
		ErrorDescription error = new ErrorDescription();
		error.setErrorcode(4001);
		error.setDescripton(ex.getLocalizedMessage());
		return new ResponseEntity<ErrorDescription>(error,HttpStatus.BAD_REQUEST);
		
	}
}
