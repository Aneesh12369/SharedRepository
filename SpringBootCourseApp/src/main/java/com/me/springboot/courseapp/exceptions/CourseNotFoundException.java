package com.me.springboot.courseapp.exceptions;

public class CourseNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4867274859791451527L;

	public CourseNotFoundException(String message){
		super(message);
	}
}
