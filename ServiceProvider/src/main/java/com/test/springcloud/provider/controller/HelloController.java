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
	public String hello(@RequestParam(required = true,defaultValue = "请传递name参数") String name) {
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
	
	/**
	 * 当zuul配置为zuul.prefix=/api、zuul.strip-prefix=false、zuul.routes.api-a.strip-prefix不配置或配置为true
	 * 不移除公共路由前缀,只移除具体服务路由前缀，访问该接口返回成功
	 * @return
	 */
	@GetMapping("/api/hello4")
	public String hello() {
		// 访问zuul路由服务器地址：http://localhost:5555/api/api-a/hello4?accessToken=token
		return "访问地址/api/hello4,测试zuul.prefix=/api、zuul.strip-prefix=false、zuul.routes.api-a.strip-prefix没配置,不移除公共路由前缀,只移除具体服务路由前缀";
	}
}
