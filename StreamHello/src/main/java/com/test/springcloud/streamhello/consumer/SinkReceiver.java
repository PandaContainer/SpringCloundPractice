package com.test.springcloud.streamhello.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.handler.annotation.SendTo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.springcloud.streamhello.dto.User;

@EnableBinding({Processor.class})
public class SinkReceiver {
	
	private static final Logger log = LoggerFactory.getLogger(SinkReceiver.class);
	
	/**
	 * SpringCloud使用@StreamListener注解监听消息,内置了一系列消息转换功能,如json->对象,
	 * 需要发送方设置spring.cloud.stream.bindings.input.content-type=application/json,
	 * 使用@SendTo注解返回结果到绑定通道<br>
	 * 也可以使用SpringIntegration原生注解@ServiceActivator监听消息,使用outputChannel属性返回结果,用@Transformer注解进行消息转换
	 * @param payload
	 */
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
//	@ServiceActivator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public String receive(User user) {
		log.info("Received from input: {}", user);
		return "From input channel return";
	}
	
	/**
	 * 统一绑定通道定义多个监听器,会根据方法参数轮训匹配,不匹配打印异常,继续调用下一个监听器,不建议定义多个
	 * @param user
	 */
//	@StreamListener(Sink.INPUT)
//	@ServiceActivator(inputChannel = Sink.INPUT)
//	public void receive(Object payload) {
//		synchronized (this) {
//			log.info("Received: {}", payload);
//			this.notifyAll();
//		}
//	}
	
	@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.INPUT)
	public User transform(String message) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(message, User.class);
		return user;
	}
}
