package com.rest.userapi.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import com.rest.userapi.model.ResponseBean;
import com.rest.userapi.util.UserapiUtils;

public class MyAuthenticationEntryPoint extends BasicAuthenticationEntryPoint{
	
	//@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.addHeader("WWW-Authenticate", "Basic realm="+getRealmName());
		ResponseBean<String> rb = new ResponseBean<>();
		rb.setErrorCode(401);
		rb.setDescription(authException.getMessage());
		rb.setT("auth failed");
		String jsonString = UserapiUtils.getJsonString(rb);
		PrintWriter writer = response.getWriter();
		writer.print(jsonString);
		
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("userapi");
		super.afterPropertiesSet();
	}

}
