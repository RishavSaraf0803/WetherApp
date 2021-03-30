package com.stackroute.userservice;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.userservice.jwtfilter.JwtFilter;
import com.stackroute.userservice.model.UserModel;
import com.stackroute.userservice.repositroy.UserRepository;

@SpringBootApplication
public class UserServiceApplication {
	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@PostConstruct
//	public void initUsers() {
//		List<UserModel> users = Stream.of(
//				new UserModel(101, "John", "John123", "johndoe@gmail.com"),
//				new UserModel(102, "Jane", "Jane678", "janedoe@gmail.com"),
//				new UserModel(103, "Jai", "jaivrat", "jaivrat@gmail.com"),
//				new UserModel(104, "test", "test", "test@gmail.com")
//				).collect(Collectors.toList());
//		userRepository.saveAll(users);
//	}

//	@Bean
//	public FilterRegistrationBean jwtFilter() {
//		FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
//		filter.addUrlPatterns("/api/v1/user");
//		filter.setFilter(new JwtFilter());
//		return filter;
//
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
}
