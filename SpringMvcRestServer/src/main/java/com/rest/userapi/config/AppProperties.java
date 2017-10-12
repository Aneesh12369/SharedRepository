package com.rest.userapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Configuration
@PropertySource("classpath:app.properties")
@Data
public class AppProperties {

	@Value("${endpoint.user.getUrl}")
	private String endPointUrl;
	
	@Value("${http.readTimeOut}")
	private Integer readTimeOut;
	
	@Value("${http.connectTimeOut}")
	private Integer connectTimeOut;
	
	
}
