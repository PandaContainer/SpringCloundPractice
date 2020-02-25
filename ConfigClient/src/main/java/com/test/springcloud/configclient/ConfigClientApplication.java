package com.test.springcloud.configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * spring cloud config配置客户端<br>
 *  1.通过引入config客户端依赖,可以加载ConfigServer配置信息,通过请求/refresh端点可以从ConfigServer拉取最新的配置文件<br>
 *  2.使用/refresh必须添加actuator依赖,并且配置management.endpoints.web.exposure.include=refresh暴露/refresh端口<br>
 *  3.调用/refresh端口刷新配置以后,使用@RefreshScope注解的@Value等占位符、Environment对象和@ConfigurationProperties注解的对象属性值都会更新
 * 
 * @author xuhon
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

}
