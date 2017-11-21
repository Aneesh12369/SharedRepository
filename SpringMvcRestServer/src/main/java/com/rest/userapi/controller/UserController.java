package com.rest.userapi.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.userapi.config.AppProperties;
import com.rest.userapi.exceptions.UnableToCreateResourceException;
import com.rest.userapi.exceptions.UserListNotFoundException;
import com.rest.userapi.exceptions.UserNotFoundException;
import com.rest.userapi.model.ResponseBean;
import com.rest.userapi.model.User;
import com.rest.userapi.service.UserService;
import com.rest.userapi.util.UserapiUtils;

@RestController
@PreAuthorize("hasRole('USER')")
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);

	public UserController() {
		log.info("Controller class configured...........");
	}

	@Autowired
	private UserService service;

	@Autowired
	private AppProperties props;

	@Autowired
	private UserapiUtils utils;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean<List<User>>> fetchUserList(@RequestHeader HttpHeaders headers) {

		List<User> userList = null;
		userList = service.getUserList();

		ResponseBean<List<User>> rb = new ResponseBean<>();
		if (userList == null || userList.isEmpty()) {

			throw new UserListNotFoundException("not found");

			/*
			 * userList = Collections.emptyList();
			 * rb.setDescription("content not found"); rb.setErrorCode(404);
			 * rb.setT(userList); //ResponseEntity<ResponseBean<List<User>>>
			 * entity = new ResponseEntity<>(rb,HttpStatus.NO_CONTENT); return
			 * ResponseEntity.status(HttpStatus.NOT_FOUND).body(rb); //return
			 * entity;
			 */
		}
		rb.setDescription("success");
		rb.setErrorCode(0);
		rb.setT(userList);
		return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json")
				.header("Cache-Control", "max-age=10").body(rb);

	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean<User>> fetchUser(@RequestHeader HttpHeaders headers, @PathVariable Integer id) {

		User user = null;
		if (null == id) {
			throw new IllegalArgumentException("illegal Argument type");
		}
		user = service.getUser(id);
		if (null == user) {
			throw new UserNotFoundException("user not found");
		}

		ResponseBean<User> bean = new ResponseBean<>();
		bean.setDescription("success");
		bean.setErrorCode(0);
		bean.setT(user);
		return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json").body(bean);

	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean<Object>> createUser(@RequestBody @Valid User user,BindingResult result,
			@RequestHeader HttpHeaders headers) {
		
		
		if(result.hasErrors()){
			List<String> message = new ArrayList<>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			for(FieldError e:fieldErrors){
				message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
			}
			
			ResponseBean<Object> rb = new ResponseBean<>();
			rb.setDescription("check the request");
			rb.setErrorCode(400);
			rb.setT(message.toString());
			
			return ResponseEntity.badRequest().body(rb);
		}
		
		String url = props.getEndPointUrl();

		User us = service.saveUser(user);
		if (null == us) {
			throw new UnableToCreateResourceException("Unable to create resource");
		}
		Integer id = Integer.parseInt(us.getId());
		URI uri = utils.getURI(url, id);
		ResponseBean<Object> rb = new ResponseBean<>();
		rb.setDescription("success");
		rb.setErrorCode(0);
		rb.setT(id);
		return ResponseEntity.created(uri).body(rb);

	}
	
	
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> createUser1(@RequestBody User user,@PathVariable Integer id,@RequestHeader HttpHeaders headers) {
		User muser = service.modifyUser(user, id);
	    return ResponseEntity.ok(muser);

	}
	
	
	
	
	

}
