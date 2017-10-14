package com.rest.userapi.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages={"com.rest.userapi"})
//@Import(AppProperties.class)
public class MvcConfig extends WebMvcConfigurerAdapter{
	

	
	@Bean
	public HttpComponentsClientHttpRequestFactory getRequestFactory(AppProperties props){
		HttpComponentsClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory();
		rf.setConnectTimeout(props.getConnectTimeOut());
		rf.setReadTimeout(props.getReadTimeOut());
		return rf;
	}
	
	@Bean
	public PropertySourcesPlaceholderConfigurer configurePropertyPlaceHolder(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	
	
	
	
	
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.json().indentOutput(true);
		builder.failOnEmptyBeans(false);
		builder.autoDetectFields(true);
		converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
		super.configureMessageConverters(converters);
	}

	@Bean(autowire=Autowire.BY_TYPE)
	public RestTemplate createRestTemplate(){
		RestTemplate rt = new RestTemplate();
		rt.setErrorHandler(new MyResponseErrorHandler());
		return  rt;
	}

}
