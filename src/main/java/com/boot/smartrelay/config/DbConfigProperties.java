package com.boot.smartrelay.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Getter
@Setter
public class DbConfigProperties {
    String driverClassName;
    String url;
    String username;
    String password;
}
