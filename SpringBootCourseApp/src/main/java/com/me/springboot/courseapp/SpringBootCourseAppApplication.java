package com.me.springboot.courseapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class SpringBootCourseAppApplication extends SpringBootServletInitializer{
	private static Logger log = LoggerFactory.getLogger(SpringBootCourseAppApplication.class);
	
	
	
/*
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootCourseAppApplication.class);
		

	}*/
	
	



	public static void main(String[] args) {
		log.info("Starting My Application...............");
		//SpringApplication.run(SpringBootCourseAppApplication.class, args);
		SpringApplication sp = new SpringApplication(new Object[]{SpringBootCourseAppApplication.class});
		sp.run(args);
	
	}
}
