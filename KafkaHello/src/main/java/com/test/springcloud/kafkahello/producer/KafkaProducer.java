package com.test.springcloud.kafkahello.producer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send() {
		String msg = "Hello " + new Date();
		System.out.println("Sender : " + msg);
		kafkaTemplate.send("hello", msg);
	}
}
