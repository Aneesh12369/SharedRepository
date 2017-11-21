package com.rest.userapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/*@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)*/
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("aneesh").password("hello").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("user").password("api").roles("USER");
		
	}
	
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
		.disable()
		.authorizeRequests()
		//.antMatchers("/userapi/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().httpBasic().authenticationEntryPoint(configureEntryPoint())
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	
	@Bean
	public MyAuthenticationEntryPoint configureEntryPoint(){
		System.out.println("This is called...........");
		return new MyAuthenticationEntryPoint();
	}
	
	
	
	
	

}
