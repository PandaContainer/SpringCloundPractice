package com.test.springcloud.kafkahello.consumer;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = {"hello"})
public class KafkaConsumer {

	/**
	 * 处理不包含content_type属性的消息
	 * @param msg
	 */
	@KafkaHandler
	public void handleMessage(byte[] msg) {
		synchronized (this) {
			System.out.println("Receiver : " + msg);
			this.notifyAll();
		}
	}
	
	/**
	 * 处理content_type属性为text的消息
	 * @param msg
	 */
	@KafkaHandler
	public void handleMessage2(String msg) {
		synchronized (this) {
			System.out.println("Receiver : " + msg);
			this.notifyAll();
		}
	}
}
