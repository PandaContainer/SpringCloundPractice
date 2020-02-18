package com.test.springcloud.feignconsumer.service.impl;

import org.springframework.stereotype.Component;

import com.test.springcloud.feignconsumer.entity.User;
import com.test.springcloud.feignconsumer.service.HelloService;

@Component
public class HelloServiceFallback implements HelloService {

	@Override
	public String hello() {
		return "error";
	}

	@Override
	public String hello(String name) {
		return "error";
	}

	@Override
	public User hello(String name, Integer age) {
		return new User("未知", 0);
	}

	@Override
	public String hello(User user) {
		return "error";
	}

}
