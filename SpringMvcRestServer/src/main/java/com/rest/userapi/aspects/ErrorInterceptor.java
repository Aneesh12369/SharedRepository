package com.rest.userapi.aspects;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rest.userapi.exceptions.RequestedResourceNotFoundException;
import com.rest.userapi.exceptions.UnableToCreateResourceException;
import com.rest.userapi.exceptions.UserListNotFoundException;
import com.rest.userapi.exceptions.UserNotFoundException;
import com.rest.userapi.model.ResponseBean;
import com.rest.userapi.model.User;

@ControllerAdvice
@ResponseBody
public class ErrorInterceptor extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = UserListNotFoundException.class)
	public ResponseEntity<ResponseBean<List<User>>> handleNullUserList(UserListNotFoundException ex) {

		List<User> lUser = Collections.emptyList();
		ResponseBean<List<User>> rb = new ResponseBean<>();
		rb.setDescription(ex.getMessage());
		rb.setErrorCode(404);
		rb.setT(lUser);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Content-Type", "application/json").body(rb);

	}

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<ResponseBean<User>> handleUserNotFound(UserNotFoundException ex) {

		User user = null;
		ResponseBean<User> rb = new ResponseBean<>();
		rb.setDescription(ex.getMessage());
		rb.setErrorCode(404);
		rb.setT(user);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Content-Type", "application/json").body(rb);

	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		ResponseBean<Object> rb = new ResponseBean<>();
		rb.setErrorCode(400);
		rb.setDescription(ex.getMessage());
		
		return new ResponseEntity<Object>(rb,HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(RequestedResourceNotFoundException.class)	
	public ResponseEntity<ResponseBean<Object>> handleTypeMismatch(RequestedResourceNotFoundException ex) {
		
		ResponseBean<Object> rb = new ResponseBean<>();
		rb.setErrorCode(400);
		rb.setDescription(ex.getMessage());
		return new ResponseEntity<ResponseBean<Object>>(rb,HttpStatus.BAD_REQUEST);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ResponseBean<Object> rb = new ResponseBean<>();
		rb.setErrorCode(404);
		rb.setDescription(ex.getMessage());
		
		return new ResponseEntity<Object>(rb,HttpStatus.NOT_FOUND);
		//return super.handleNoHandlerFoundException(ex, headers, status, request);
	}
	
	
	
	@ExceptionHandler(UnableToCreateResourceException.class)	
	public ResponseEntity<ResponseBean<Object>> handleNotCreate(UnableToCreateResourceException ex) {
		
		ResponseBean<Object> rb = new ResponseBean<>();
		rb.setErrorCode(422);
		rb.setDescription(ex.getMessage());
		return new ResponseEntity<ResponseBean<Object>>(rb,HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResponseBean<Object> rb = new ResponseBean<>();
		rb.setErrorCode(405);
		rb.setDescription(ex.getMessage());
		return new ResponseEntity<Object>(rb,HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResponseBean<Object> rb = new ResponseBean<>();
		rb.setErrorCode(405);
		rb.setDescription(ex.getMessage());
		return new ResponseEntity<Object>(rb,HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(AccessDeniedException.class)
    public String exception(AccessDeniedException e) {
        return "{\"status\":\"access denied\"}";
    } 
	
	
	
	
	
	

	

}
