package com.example.demo.config.mongoconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class DbEmployeeConfig extends AbstractMongoConfig {
	@Value("${employee}")
	private String database;

	@Override
	@Bean(name = "employeeTemplate")
	public MongoTemplate getMongoTemplate() {
		return new MongoTemplate(mongoDbFactory(database));
	}
}
