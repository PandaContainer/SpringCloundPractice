package com.test.springcloud.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;

@Service
public class HelloService {

	private final Logger logger = LoggerFactory.getLogger(HelloService.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	// cacheKeyMethod属性优先级大于@CacheKey注解
	@CacheResult(cacheKeyMethod = "getHystrixCacheKey")
	// ignoreExceptions会忽略指定异常，不会触发服务降级
	@HystrixCommand(commandKey = "helloKey", fallbackMethod = "helloFallback")
	public String helloService(@CacheKey("name") String name) {
		long start = System.currentTimeMillis();
		String result = restTemplate.getForEntity("http://hello-service/hello", String.class).getBody();
		long end = System.currentTimeMillis();
		
		logger.info("Spend time : {}", end - start);
		
		return result;
	}
	
	//此处可能是另一个网络请求来获取，所以也可能失败
	@HystrixCommand(fallbackMethod = "helloFallbackSec")
	public String helloFallback(String name) {
		logger.info("helloFallback execute");
		
		throw new RuntimeException("模拟降级方法异常");
	}
	
	public String helloFallbackSec(String name) {
		logger.info("helloFallbackSec execute");
		
		return "error2";
	}
	
	private String getHystrixCacheKey(String name) {
		logger.info("getHystrixCacheKey execute, name={}", name);
		
		return name;
	}
	
	@CacheRemove(commandKey = "helloKey", cacheKeyMethod = "getHystrixCacheKey")
	public void update(String name) {
		
	}
	
}
