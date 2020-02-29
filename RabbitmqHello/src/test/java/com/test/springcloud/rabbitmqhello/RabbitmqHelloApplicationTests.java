package com.test.springcloud.rabbitmqhello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.springcloud.rabbitmqhello.consumer.Receiver;
import com.test.springcloud.rabbitmqhello.producer.Sender;

@SpringBootTest
class RabbitmqHelloApplicationTests {

	@Autowired
	Sender sender;
	
	@Autowired
	Receiver receiver;
	
	@Test
	public void hello() throws InterruptedException {
		sender.send();
		synchronized (receiver) {
			receiver.wait(3000);
		}
	}
}
