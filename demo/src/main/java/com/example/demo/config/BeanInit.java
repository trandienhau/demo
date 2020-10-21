package com.example.demo.config;

import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Configuration
public class BeanInit {
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer addCustomBigDecimalDeserialization() {
	return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
	.serializerByType(ObjectId.class, new ToStringSerializer())
	.featuresToEnable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES).failOnUnknownProperties(false);
	}
}
