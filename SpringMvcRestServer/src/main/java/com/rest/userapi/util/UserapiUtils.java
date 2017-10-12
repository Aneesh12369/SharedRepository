package com.rest.userapi.util;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

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
	

}
