package com.test.springcloud.feignconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.test.springcloud.helloserviceapi.service.HelloService;

@FeignClient("HELLO-SERVICE")
public interface RefactorHelloService extends HelloService {

}
