package com.test.springcloud.streamhello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.web.WebAppConfiguration;

import com.test.springcloud.streamhello.binding.channel.SinkOutput;
import com.test.springcloud.streamhello.consumer.SinkReceiver;

@SpringBootTest
@WebAppConfiguration
class StreamHelloApplicationTests {

	/**
	 * 注入绑定接口:测试发现不能发送消息
	 */
	@Autowired
	SinkOutput sinkOutput;
	
	/**
	 * 注入消息通道,可以使用@Qualifier或@Resource指定通道名称
	 */
	@Autowired
	@Qualifier("input")
	MessageChannel input;
	
	@Autowired
	SinkReceiver sinkReceiver;
	
	@Test
	public void send() throws InterruptedException {
		sinkOutput.output().send(MessageBuilder.withPayload("From SinkSender").build());
		input.send(MessageBuilder.withPayload("From SinkSender MessageChannel").build());
		synchronized (sinkReceiver) {
			sinkReceiver.wait(3000);
		}
	}

}
