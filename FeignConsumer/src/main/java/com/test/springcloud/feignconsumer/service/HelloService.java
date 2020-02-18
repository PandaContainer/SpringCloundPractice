package com.test.springcloud.feignconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.springcloud.feignconsumer.configuration.FullLogConfiguration;
import com.test.springcloud.feignconsumer.entity.User;
import com.test.springcloud.feignconsumer.service.impl.HelloServiceFallback;

@FeignClient(name="hello-service", fallback = HelloServiceFallback.class, configuration = FullLogConfiguration.class)
public interface HelloService {

	@RequestMapping("/hello")
	String hello();
	
	@GetMapping("/hello1")
	public String hello(@RequestParam("name") String name);
	
	@GetMapping("/hello2")
	public User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);
	
	@PostMapping("/hello3")
	public String hello(@RequestBody User user);
	
}
