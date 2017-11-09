package com.me.springboot.courseapp.topic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.me.springboot.courseapp.exceptions.CourseNotFoundException;
import com.me.springboot.courseapp.exceptions.IllegalTopicJsonArgumentException;
import com.me.springboot.courseapp.util.ErrorDescription;

@RestControllerAdvice
public class TopicControllerAdvice {

	@ExceptionHandler({CourseNotFoundException.class})
	public ResponseEntity<ErrorDescription> handleInvalidId(CourseNotFoundException ex) {
		ErrorDescription error = new ErrorDescription();
		error.setErrorcode(4001);
		error.setDescripton(ex.getLocalizedMessage());
		return new ResponseEntity<ErrorDescription>(error,HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler({IllegalTopicJsonArgumentException.class,InvalidFormatException.class})
	public ResponseEntity<ErrorDescription> handleInvalidArgumets(Exception ex) {
		ErrorDescription error = new ErrorDescription();
		error.setErrorcode(4001);
		error.setDescripton(ex.getLocalizedMessage());
		return new ResponseEntity<ErrorDescription>(error,HttpStatus.BAD_REQUEST);
		
	}
}
