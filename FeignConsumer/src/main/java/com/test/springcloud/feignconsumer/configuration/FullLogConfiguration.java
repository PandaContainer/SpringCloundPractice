package com.test.springcloud.feignconsumer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class FullLogConfiguration {

	@Bean
	Logger.Level feignLoggerLevel() {
		// 配置Feign客户端日志调用级别,@FeignClient使用
		return Logger.Level.FULL;
	}
}
