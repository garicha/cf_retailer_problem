package com.example.retailer.config;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.PersistenceUnit;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@ManagedResource
@EnableJpaRepositories(basePackages = {"com.example.retailer,repository"})
public class JpaConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(JpaConfig.class);

    @Autowired
    private Environment environment;

    @Bean
    @PersistenceUnit
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setPackagesToScan(new String[] { "com.example.retailer.entity" });
        entityManagerFactory.setJpaProperties(additionalProperties());
        return entityManagerFactory;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }

    @Bean
    public PoolDataSource dataSource() {
        PoolDataSource dataSource = PoolDataSourceFactory.getPoolDataSource();
        try{
            dataSource.setUser(environment.getRequiredProperty("spring.datasource.username"));
            dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
            dataSource.setConnectionFactoryClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));
            dataSource.setURL(environment.getRequiredProperty("spring.datasource.url"));
            dataSource.setInitialPoolSize(Integer.parseInt(environment.getRequiredProperty("spring.datasource.initialPoolSize")));
            dataSource.setMinPoolSize(Integer.parseInt(environment.getRequiredProperty("spring.datasource.minPoolSize")));
            dataSource.setMaxPoolSize(Integer.parseInt(environment.getRequiredProperty("spring.datasource.maxPoolSize")));
            dataSource.setConnectionWaitTimeout(Integer.parseInt(environment.getRequiredProperty("spring.datasource.connection.wait.timeout.secs")));
            dataSource.setSQLForValidateConnection("Select 1 from dual;");
            dataSource.setValidateConnectionOnBorrow(Boolean.TRUE);
        } catch (SQLException e) {
            LOGGER.error("Error in initializing data source: {}", e);
        }
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

}
