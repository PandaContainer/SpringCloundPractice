package com.test.springcloud.apigatewaydynamicroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * zuul使用SpringCloudConfig动态配置实现动态路由功能
 * 
 * @author xuhon
 */
@EnableDiscoveryClient
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
		// 默认使用@ConfigurationProperties标注的ZuulProperties不支持刷新配置,此处刷新后重新创建ZuulProperties实例并更新属性
		// 使用@RefreshScope使zuul配置内容支持动态刷新,其它配置即使修改也不一定会生效
		return new ZuulProperties();
	}
	
}
