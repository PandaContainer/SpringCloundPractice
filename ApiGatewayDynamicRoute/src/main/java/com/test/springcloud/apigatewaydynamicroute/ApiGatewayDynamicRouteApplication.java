package com.test.springcloud.apigatewaydynamicroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * zuul动态路由
 * 
 * @author xuhon
 *
 */
@EnableZuulProxy
@SpringBootApplication
public class ApiGatewayDynamicRouteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayDynamicRouteApplication.class, args);
	}

	@Bean
	@Primary
	@RefreshScope
	@ConfigurationProperties("zuul")
	public ZuulProperties zuulProperties() {
		// 使用@RefreshScope来将zuul的配置内容支持动态刷新
		return new ZuulProperties();
	}
	
}
