package com.test.springcloud.provider.controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.springcloud.provider.entity.User;

@RestController
public class HelloController {

	private final Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private Registration registration; // 服务注册

	@GetMapping("/hello")
	public String index() throws Exception {
		
		//模拟超时，hystrix默认超时时间是1000ms
		int sleepTime = new Random().nextInt(3000);
		logger.info("sleepTime:{}", sleepTime);
		Thread.sleep(sleepTime);
		
		logger.info("/hello, host:" + registration.getHost() + ", service_id:" + registration.getServiceId() + ", port:"
				+ registration.getPort());
		return "Hello World";
	}
	
	@GetMapping("/hello1")
	public String hello(@RequestParam String name) {
		return "Hello " + name;
	}
	
	@GetMapping("/hello2")
	public User hello(@RequestHeader String name, @RequestHeader Integer age) {
		return new User(name, age);
	}
	
	@PostMapping("/hello3")
	public String hello(@RequestBody User user) {
		return "Hello " + user.getName() + ", " + user.getAge();
	}
}
