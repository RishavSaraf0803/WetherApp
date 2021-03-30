package com.stackroute.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stackroute.paymentservice.jwtfilter.JwtFilter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import javax.servlet.Filter;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
public class PaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
//
//    @Bean
//    public FilterRegistrationBean<?> jwtFilter() {
//
//        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
//        filter.addUrlPatterns("/api/v1/*");
//        filter.setFilter(new JwtFilter());
//        return filter;
//    }

}
