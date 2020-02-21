package com.test.springcloud.feignconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.springcloud.feignconsumer.entity.User;
import com.test.springcloud.feignconsumer.service.HelloService;
import com.test.springcloud.feignconsumer.service.RefactorHelloService;

@RestController
public class ConsumerController {

	@Autowired
	HelloService helloService;
	
	@Autowired
	RefactorHelloService refactorHelloService;
	
	@GetMapping("/feign-consumer")
	public String helloConsumer() {
		return helloService.hello();
	}
	
	
	@GetMapping("/feign-consumer2")
	public String helloConsumer2() {
		StringBuilder sb = new StringBuilder();
		sb.append(helloService.hello()).append("<br>");
		sb.append(helloService.hello("DIDI")).append("<br>");
		sb.append(helloService.hello("DIDI", 30)).append("<br>");
		sb.append(helloService.hello(new User("DIDI", 30))).append("<br>");
		return sb.toString();
	}
	
	@GetMapping("/feign-consumer3")
	public String helloConsumer3() {
		StringBuilder sb = new StringBuilder();
		sb.append(refactorHelloService.hello("MINI")).append("<br>");
		sb.append(refactorHelloService.hello("MINI", 20)).append("<br>");
		sb.append(refactorHelloService.hello(new com.test.springcloud.helloserviceapi.dto.User("MINI", 20))).append("<br>");
		return sb.toString();
	}

}
