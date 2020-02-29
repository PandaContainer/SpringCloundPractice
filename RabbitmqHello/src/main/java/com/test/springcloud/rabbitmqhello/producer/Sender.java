package com.test.springcloud.rabbitmqhello.producer;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send() {
		String msg = "Hello " + new Date();
		System.out.println("Sender : " + msg);
		amqpTemplate.convertAndSend("hello", msg);
	}
}
