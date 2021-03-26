package com.stackroute.userservice;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.userservice.jwtfilter.JwtFilter;

@SpringBootApplication
public class UserServiceApplication {

	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
		filter.addUrlPatterns("/api/v1/user");
		filter.setFilter(new JwtFilter());
		return filter;

	}
	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
}
