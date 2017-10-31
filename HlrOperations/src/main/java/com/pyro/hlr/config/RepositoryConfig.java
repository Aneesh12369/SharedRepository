package com.pyro.hlr.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class RepositoryConfig {

	@Bean
	@Autowired
	public JdbcTemplate creatTemplate(DataSource data) {
		return new JdbcTemplate(data);
	}

}
