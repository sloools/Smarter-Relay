package com.boot.smartrelay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

//@Configuration
//public class DataSourceConfig {
//
//    @Bean
//    @ConfigurationProperties("spring.datasource")
//    DataSource mysqlDataSource(){
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
//}
@Configuration
@PropertySource("classpath:/application.properties")
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mysqlEntityManager", transactionManagerRef = "mysqlTransactionManager", basePackages = "com.boot.smartrelay.mysql")
public class DataSourceConfig {

    @Autowired
    Environment env;

    @Autowired
    DbConfigProperties dbConfigProperties;

    @Primary
    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbConfigProperties.getDriverClass());
        dataSource.setUrl(dbConfigProperties.getUrl());
        dataSource.setUsername(dbConfigProperties.getUsername());
        dataSource.setPassword(dbConfigProperties.getPassword());
        return dataSource;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean mysqlEntityManager() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        HashMap<String, Object> properties = new HashMap<>();
        localContainerEntityManagerFactoryBean.setDataSource(mysqlDataSource());
        localContainerEntityManagerFactoryBean.setPackagesToScan(new String[] { "com.boot.smartrelay.mysql.entity" });
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.main.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.main.hibernate.dialect"));
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(properties);
        return localContainerEntityManagerFactoryBean;
    }

    @Primary
    @Bean
    public PlatformTransactionManager mysqlTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mysqlEntityManager().getObject());
        return transactionManager;
    }
}