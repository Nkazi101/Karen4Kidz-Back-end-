package com.transport.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration

@EnableJpaRepositories(basePackages= "com.transport.repository")

@EntityScan(basePackages="com.transport.entity")

public class ApplicationConfig {
    
}
