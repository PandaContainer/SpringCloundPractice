package com.test.springcloud.kafkahello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot Kafka使用举例,测试类KafkaHelloApplicationTests<br>
 * 需要手动创建Topic,然后重启本地Kafka服务器
 * @author xuhon
 *
 */
@SpringBootApplication
public class KafkaHelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaHelloApplication.class, args);
	}

}
