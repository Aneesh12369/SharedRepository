package com.rest.userapi.util;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserapiUtils {

	
	
	public URI getURI(String url,Integer id){
		URI uri = null;
		if(null!=id)
			uri = UriComponentsBuilder.fromHttpUrl(url).path("/{id}").buildAndExpand(id).toUri();
		else
			uri = UriComponentsBuilder.fromHttpUrl(url).build().toUri();
		return uri;
		
	}
	
	
	
	public static String getJsonString(Object clazz) throws JsonProcessingException{
		ObjectMapper obj = new ObjectMapper();
		return obj.writeValueAsString(clazz);
	}
	

}
