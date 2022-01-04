package com.boot.smartrelay.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import javax.sql.DataSource;

/**
 *
 * docker exec -i -t mongo_boot bash
 */
@Configuration
public class MongoConfig{
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb")
    DataSource mongoDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
