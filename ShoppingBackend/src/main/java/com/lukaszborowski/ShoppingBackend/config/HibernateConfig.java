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

@Configuration
@ComponentScan(basePackages= {"com.lukaszborowski.ShoppingBackend.dto"})
@EnableTransactionManagement
public class HibernateConfig {

	private static final String DATABASE_URL = "jdbc:h2:tcp://localhost/~/ShopProject";
	private static final String DATABASE_DRIVER = "org.h2.Driver";
	private static final String DATABASE_DIALECT = "org.hibernate.dialect.H2Dialect";
	private static final String DATABASE_USERNAME = "sa";
	private static final String DATABASE_PASSWORD = "";

	//dataSource bean will be available
	@Bean
	public DataSource getDataSource() {
		
		BasicDataSource dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);
		return dataSource;
	}
	//sessionFactory bean
	@Bean 
	public SessionFactory getSessionFactory(DataSource dataSource) {
		
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.lukaszborowski.ShoppingBackend.dto");
		return builder.buildSessionFactory();
		
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		
		properties.put("hibernate.dialect", DATABASE_DIALECT);
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		
		properties.put("hibernate.hbm2ddl.auto", "create");
		
		
		return properties;
	}
	//transakcionManager Bean
	@Bean 
	public HibernateTransactionManager getTransakcionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transakcionManager = new HibernateTransactionManager(sessionFactory);
		return transakcionManager;
	}
	


}
