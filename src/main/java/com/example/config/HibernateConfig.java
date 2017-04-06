package com.example.config;

import java.util.Properties;

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
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource(value="classpath:hibernate.properties")
@EnableJpaRepositories(basePackages = "com.example.dao")
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
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		Properties properties =  new Properties();
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
		properties.put("hibernate.generate_statistic", environment.getProperty("hibernate.generate_statistic"));
		
		//glowny obiekt fabryki beanow
		LocalContainerEntityManagerFactoryBean factorybean = new LocalContainerEntityManagerFactoryBean();
		factorybean.setPackagesToScan("com.example.model");
		factorybean.setJpaVendorAdapter(vendorAdapter);
		factorybean.setJpaProperties(properties);
		factorybean.setDataSource(dataSource());
		
		//produkcja beanow encyjnych byla mozliwa dopiero gdy zostanie wykonana cala konfiguracja
		factorybean.afterPropertiesSet();
		
		return factorybean.getObject();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(entityManagerFactory());
		
		return manager;
	}
}
