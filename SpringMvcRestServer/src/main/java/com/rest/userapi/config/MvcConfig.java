package com.rest.userapi.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableSwagger2
@ComponentScan(basePackages = { "com.rest.userapi" })
// @Import(AppProperties.class)
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public HttpComponentsClientHttpRequestFactory getRequestFactory(AppProperties props) {
		HttpComponentsClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory();
		rf.setConnectTimeout(props.getConnectTimeOut());
		rf.setReadTimeout(props.getReadTimeOut());
		return rf;
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer configurePropertyPlaceHolder() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.json().indentOutput(true);
		builder.failOnEmptyBeans(false);
		builder.autoDetectFields(true);
		converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
		super.configureMessageConverters(converters);
	}

	@Bean(autowire = Autowire.BY_TYPE)
	public RestTemplate createRestTemplate() {
		RestTemplate rt = new RestTemplate();
		rt.setErrorHandler(new MyResponseErrorHandler());
		return rt;
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		return new ApiInfo("User Restful Service", "Example Version", "1.0v", "http://google.com", "AnEesh", null,
				null);
	}

}
