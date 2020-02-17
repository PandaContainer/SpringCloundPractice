package com.test.springcloud.hystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class HystrixDashboardApplication {

	/**
	 * hystrix仪表盘地址：http://localhost:2001/hystrix
	 * 
	 * Cluster via Turbine (default cluster): https://turbine-hostname:port/turbine.stream 
	 * Cluster via Turbine (custom cluster): https://turbine-hostname:port/turbine.stream?cluster=[clusterName]
	 * Single Hystrix App: https://hystrix-app:port/actuator/hystrix.stream
	 */	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardApplication.class, args);
	}

}
