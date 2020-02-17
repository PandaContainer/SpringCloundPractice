package com.test.springcloud.consumer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.test.springcloud.consumer.filter.HystrixRequestContextServletFilter;

@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
//@SpringCloudApplication
public class RibbonConsumerApplication {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RibbonConsumerApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<HystrixRequestContextServletFilter> indexFilterRegistration() {
	    FilterRegistrationBean<HystrixRequestContextServletFilter> registration = new FilterRegistrationBean<HystrixRequestContextServletFilter>(new HystrixRequestContextServletFilter());
	    registration.addUrlPatterns("/*");
	    return registration;
	}
}
