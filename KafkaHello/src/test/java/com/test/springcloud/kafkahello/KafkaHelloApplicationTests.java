package com.test.springcloud.kafkahello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.springcloud.kafkahello.consumer.KafkaConsumer;
import com.test.springcloud.kafkahello.producer.KafkaProducer;

@SpringBootTest
class KafkaHelloApplicationTests {

	@Autowired
	KafkaProducer producer;
	
	@Autowired
	KafkaConsumer consumer;
	
	@Test
	public void hello() throws InterruptedException {
		producer.send();
		synchronized (consumer) {
			consumer.wait(3000);
		}
	}

}
