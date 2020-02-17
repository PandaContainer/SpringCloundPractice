package com.test.springcloud.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.springcloud.consumer.service.HelloService;

@RestController
public class ConsumerController {

	@Autowired
	HelloService helloService;
	
	@GetMapping("/ribbon-consumer")
	public String helloConsumer() {
		return helloService.helloService("你好");
	}
}
