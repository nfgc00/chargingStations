package com.hb.charging.stations.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Database configuration class 
 * 
 * @author fernando
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages= {"com.hb.charging.stations.repository"})
public class Database {
	
	@Value("${db.jdbc.driverClassName}")
	private String driverClassName;
	
	@Value("${db.jdbc.url}")
	private String dbJdbcUrl;
	
	@Value("${db.jdbc.user}")
	private String dbUser;
	
	@Value("${db.jdbc.password}")
	private String dbPassword;
	
	@Value("${db.hibernate.dialect}")
	private String hibernateDialect;
	
	@Value("${db.hibernate.hbm2ddl.auto}")
	private String hibernateHbm2ddlAuto;
	
	@Value("${db.hibernate.show_sql}")
	private String hibernateShowSql;

	/**
	 * Returns a {@code DataSource} object configured with the properties read from the application configuration file.
	 * 
	 * @return {@code DataSource}
	 */
	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(dbJdbcUrl);
		dataSource.setUsername(dbUser);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}
	
	/**
	 * Returns a {@code LocalContainerEntityManagerFactoryBean} object to set up a EntityMaganerFactory 
	 * configured to use Hibernate JPA implementation.
	 * 
	 * @return {@code LocalContainerEntityManagerFactoryBean}
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan("com.hb.charging.stations.domain");
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", hibernateDialect);
		jpaProperties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
		jpaProperties.put("hibernate.show_sql", hibernateShowSql);
		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		return entityManagerFactoryBean;
	}
	
	/**
	 * Returns a {@code PlatformTransactionManager} object to implement JPA Transaction Management.
	 *  
	 * @return {@code PlatformTransactionManager}
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
}
