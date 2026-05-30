package com.eatm.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.eatm")
public class MyConfig {
	
	@Bean
	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("emp").createEntityManager();
	}
	
	@Bean
	public EntityTransaction getEntityTransaction() {
		return getEntityManager().getTransaction();
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
