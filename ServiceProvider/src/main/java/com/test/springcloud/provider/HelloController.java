package com.test.springcloud.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private final Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private Registration registration; // 服务注册

	@GetMapping("/hello")
	public String index() {
		logger.info("/hello, host:" + registration.getHost() + ", service_id:" + registration.getServiceId() + ", port:"
				+ registration.getPort());
		return "Hello World";
	}
}
