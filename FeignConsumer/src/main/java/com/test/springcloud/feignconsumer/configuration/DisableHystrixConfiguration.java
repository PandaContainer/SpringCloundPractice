package com.test.springcloud.feignconsumer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Feign;

@Configuration
public class DisableHystrixConfiguration {

	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder() {
		// 如果只想针对某个服务关闭hystrix功能,可以在@FeignClient注解中使用该配置,为指定的客户端生成Feign.Builder实例
		return Feign.builder();
	}
}
