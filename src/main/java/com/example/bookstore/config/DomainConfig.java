package com.example.bookstore.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.example.bookstore")
@EnableJpaRepositories("com.example.bookstore")
@EnableTransactionManagement
public class DomainConfig {
}
