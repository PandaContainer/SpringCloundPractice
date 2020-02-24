package com.test.springcloud.turbineamqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * Turbine基于消息代理RabbitMQ搜集实现<br>
  *  使用新版本hystrix功能的服务消费者引入spring-cloud-netflix-hystrix-stream依赖?
 * @author xuhon
 *
 */
@EnableTurbineStream
@EnableDiscoveryClient
@SpringBootApplication
public class TurbineAmqpApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurbineAmqpApplication.class, args);
	}

}
