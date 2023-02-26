package com.bancoserfinanza.bookbank;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "com.bancoserfinanza.models.bookbank" })
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
public class TestConfig {
}
