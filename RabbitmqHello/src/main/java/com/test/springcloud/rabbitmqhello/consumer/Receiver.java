package com.test.springcloud.rabbitmqhello.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class Receiver {
	
	/**
	 * 处理不包含content_type属性的消息
	 * @param msg
	 */
	@RabbitHandler
	public void process(byte[] msg) {
		synchronized (this) {
			System.out.println("Receiver : " + msg);
			this.notifyAll();
		}
	}
	
	/**
	 * 处理content_type属性为text的消息
	 * @param msg
	 */
	@RabbitHandler
	public void process(String msg) {
		synchronized (this) {
			System.out.println("Receiver : " + msg);
			this.notifyAll();
		}
	}
}
