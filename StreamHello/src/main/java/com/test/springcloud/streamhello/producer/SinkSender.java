package com.test.springcloud.streamhello.producer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.springcloud.streamhello.binding.channel.SinkOutput;
import com.test.springcloud.streamhello.dto.User;

@EnableBinding({SinkOutput.class, Processor.class})
public class SinkSender {

	private static final Logger log = LoggerFactory.getLogger(SinkSender.class);
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * SpringCloud使用依赖注入消息通道来发送消息<br>
	 * 也可以使用SpringIntegration原生注解@InboundChannelAdapter声明消息通道发送消息,使用@Transformer注解进行消息转换<br>
	 * poller参数设置轮训执行,每2s输出一次消息
	 * @return
	 */
	@Bean
	@InboundChannelAdapter(value = SinkOutput.OUTPUT, poller = @Poller(fixedDelay = "2000"))
	public MessageSource<User> timerMassageSource() {
		// 使用Java8 Lambda表达式实现 MessageSource接口匿名实现类
		// 当input通道消息内容类型content-type设置为application/json,自动调用POJO->JSON String类型转换器,但是每次创建的对象hascode不同,通过payload分区键表达式计算的值不同
		return () -> new GenericMessage<User>(new User("didi", 30));
//		return () -> new GenericMessage<String>("{\"name\":\"didi\",\"age\":30}");
//		return () -> new GenericMessage<>(new Date());
	}
	
	@StreamListener(Processor.OUTPUT)
//	@ServiceActivator(inputChannel = Processor.OUTPUT)
	public void receive(Object payload) {
		log.info("Received from output: {}", payload);
	}
	
	@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.INPUT)
	public Object transform(Date message) {
		return dateFormat.format(message);
	}
}
