package com.indus.training.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages="com.indus.training.core")
@EnableWebMvc
public class StudentDaoConfig {

}
