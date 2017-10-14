package com.rest.userapi.aspects;

import java.net.URI;
import java.util.Arrays;
import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	/*
	 * @Pointcut("execution(* com.rest.userapi.controller.UserController.*(..))"
	 * ) public void logControllerPointCut(){
	 * 
	 * }
	 */

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void allControllerClasses() {

	}

	@Pointcut("execution(* *.*(..))")
	public void allMethods() {

	}

	@Before("allControllerClasses() && allMethods() && args(headers)")
	public void logBeforAdvice(JoinPoint jp,HttpHeaders headers) {

		logger.info("Calling method {}", jp.getSignature().getName());
		logger.info("Class name {}", jp.getSignature().getDeclaringTypeName());
		logger.info("Arguments  {}", Arrays.toString(jp.getArgs()));
		

		if (null != headers) {
			Set<String> keySet = headers.keySet();
			logger.info("######Headesr########");
			for (String s : keySet) {
				logger.info("Header Name {}...........Header Value {}", s, headers.get(s));
			}

		}

	}

	@Around("allControllerClasses() && allMethods()")
	public Object userCotrollerLogAdvice(ProceedingJoinPoint pjp) throws Throwable {
		Object obj = null;
		logger.info("calling method ::::{}", pjp.getSignature().getName());
		logger.info("calling method arguments::::{}", Arrays.toString(pjp.getArgs()));
		try {
			obj = pjp.proceed();
			logger.info("Returning ::: {}", obj);
			return obj;
		} catch (Throwable e) {
			logger.error("Exception in loggerAdvice ", e);
			throw e;
		}

	}
	
	@Before("within(com.rest.userapi.service.UserService)")
	public void serviceLogger(JoinPoint jp){
		
		logger.info("callig service class method {}",jp.getSignature().getName());
		logger.info("callig service class method with arguments {}",Arrays.toString(jp.getArgs()));
		
		
	}
	
	
	
	
	@AfterReturning(pointcut="execution(* com.rest.userapi.util.UserapiUtils.*(..))",returning="uri")
	public void uriLogger(JoinPoint jp,URI uri){
		
		logger.info("callig util class method {}",jp.getSignature().getName());
		logger.info("callig util class method with arguments {}",Arrays.toString(jp.getArgs()));
		logger.info("callig util class method returning arguments {}",uri);
		
		
	}
	
	
	
	
	
	
	

}
