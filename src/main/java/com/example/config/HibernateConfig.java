package com.example.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource(value="classpath:hibernate.properties")
@EnableJpaRepositories(basePackages = "com.ak.dao")
public class HibernateConfig {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.user.name"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.driver.class.name"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.url"));
		
		return dataSource;
	}
	
	//metoda z konfiguracja hinernatea w ktorej rowniez przakazujemy informacje w ktorym pakiecie znajduja sie encje
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		//HibernateJpaVendorAdapter vendorAdapter = new 
		
		return null;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(entityManagerFactory());
		
		return manager;
	}
