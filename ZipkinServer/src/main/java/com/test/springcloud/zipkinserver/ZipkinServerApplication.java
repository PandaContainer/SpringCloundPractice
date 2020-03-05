package com.test.springcloud.zipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

import zipkin.server.EnableZipkinServer;


/**
 * 1.spring-boot2.0以上官方不推荐自定义zipkin-server并提供了可执行jar,选择exec.jar结尾的jar下载即可,
 * 执行java -jar zipkin-server-2.12.9-exec.jar启动服务器,启动参数查看jar包里zipkin-server-shared.yml文件配置,
 * 比如带上--RABBIT_ADDRESSES=localhost:5672 --RABBIT_USER=guest --RABBIT_PASSWORD=guest --RABBIT_VIRTUAL_HOST=/参数使用rabbitmq通信<br>
 * 2.springboot老版本可以使用@EnableZipkinServer注解默认使用http进行通信,@EnableZipkinStreamServer采用stream方式启动zipkinServer,
 * 也支持http通信,包含了@EnableZipkinServer,同时创建了rabbitmq消息队列监听器
 * 
 * @author xuhon
 *
 */
//@EnableZipkinServer
@EnableZipkinStreamServer
@SpringBootApplication
public class ZipkinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinServerApplication.class, args);
	}

}
