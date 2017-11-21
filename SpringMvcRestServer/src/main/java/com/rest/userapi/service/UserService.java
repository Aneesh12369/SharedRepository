package com.rest.userapi.service;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rest.userapi.config.AppProperties;
import com.rest.userapi.model.User;
import com.rest.userapi.util.UserapiUtils;

@Service
public class UserService {

	private Logger log = LoggerFactory.getLogger(UserService.class);

	public UserService() {
		log.info("Service class constructor...........");
	}

	@Autowired
	private UserapiUtils utils;

	@Autowired
	private AppProperties props;

	@Autowired
	private RestTemplate template;

	public List<User> getUserList() {
		String url = props.getEndPointUrl();
		List<User> list = Collections.emptyList();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<List<User>> responseEntity = template.exchange(url, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<User>>() {
				});
		list = responseEntity.getBody();
		return list;

	}

	public User getUser(Integer id) {

		String url = props.getEndPointUrl();
		URI uri = utils.getURI(url, id);
		User user = null;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		HttpEntity<?> entity = new HttpEntity<>(headers);
		// try{
		ResponseEntity<User> rEntity = template.exchange(uri, HttpMethod.GET, entity, User.class);
		user = rEntity.getBody();
		return user;
		/*
		 * }catch(HttpClientErrorException ex){ HttpStatus statusCode =
		 * ex.getStatusCode(); String message = ex.getMessage();
		 * if(statusCode==HttpStatus.NOT_FOUND){ throw new
		 * RequestedResourceNotFoundException(message); } return user; }
		 */

	}

	public User saveUser(User user){
		String url = props.getEndPointUrl();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<User> entity = new HttpEntity<User>(user, headers);
	    ResponseEntity<User> exchange = template.exchange(url, HttpMethod.POST, entity,User.class);
	   System.out.println(exchange);
	   return exchange.getBody();
		
	}
	
	
	public User modifyUser(User user,int id){
		String url = props.getEndPointUrl();
		URI uri = utils.getURI(url, id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<User> entity = new HttpEntity<User>(user, headers);
	    ResponseEntity<User> exchange = template.exchange(uri, HttpMethod.PUT, entity,User.class);
	   
	   return exchange.getBody();
		
	}
	

}
