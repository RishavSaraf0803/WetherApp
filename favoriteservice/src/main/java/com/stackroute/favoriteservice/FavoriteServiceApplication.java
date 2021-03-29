package com.stackroute.favoriteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.favoriteservice.jwtfilter.JwtFilter;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
public class FavoriteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FavoriteServiceApplication.class, args);
    }

    
//    @Bean
//	public FilterRegistrationBean<JwtFilter> jwtFilter() {
//		FilterRegistrationBean<JwtFilter> filterRegistrationBean=new FilterRegistrationBean<>();
//		filterRegistrationBean.setFilter(new JwtFilter());
//		filterRegistrationBean.addUrlPatterns("/api/v1/*");
//		return filterRegistrationBean;
//	}
}
