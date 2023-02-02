package com.lms.userservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
	
	@Bean
	public static ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public static RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
