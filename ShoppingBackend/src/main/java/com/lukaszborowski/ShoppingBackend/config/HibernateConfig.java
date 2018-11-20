package com.lukaszborowski.ShoppingBackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Database connection properties
 * 
 * @author Łukasz Borowski
 *
 */

@Configuration
@ComponentScan(basePackages= {"com.lukaszborowski.ShoppingBackend.dto"})
@EnableTransactionManagement
public class HibernateConfig {

	private static final String DATABASE_URL = "jdbc:h2:tcp://localhost/~/ShopProject";
	private static final String DATABASE_DRIVER = "org.h2.Driver";
	private static final String DATABASE_DIALECT = "org.hibernate.dialect.H2Dialect";
	private static final String DATABASE_USERNAME = "sa";
	private static final String DATABASE_PASSWORD = "";

	
	/**
	 * dataSource bean will be available
	 * @return dataSource set
	 */
	@Bean("dataSource")
	public DataSource getDataSource() {
		
		BasicDataSource dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);
		return dataSource;
	}
	/**
	 * Method that builds session factory 
	 * @param dataSource is a parameter witch all database variables had set
	 * @return Session Factory
	 */
	@Bean 
	public SessionFactory getSessionFactory(DataSource dataSource) {
		
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.lukaszborowski.ShoppingBackend.dto");
		return builder.buildSessionFactory();
		
	}

	/**
	 * Method that set hibernate properties 
	 * @return hibernate properties
	 */
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		
		properties.put("hibernate.dialect", DATABASE_DIALECT);
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		
		properties.put("hibernate.hbm2ddl.auto", "update");
		
		
		return properties;
	}
	/**
	 * Transaction manager bean 
	 * @param sessionFactory is current session factory 
	 * @return new hibrnate transakcion manager
	 */
	@Bean 
	public HibernateTransactionManager getTransakcionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transakcionManager = new HibernateTransactionManager(sessionFactory);
		return transakcionManager;
	}
	


}
