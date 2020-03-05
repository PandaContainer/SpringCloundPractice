package com.test.springcloud.trace1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 分布式服务跟踪SpringCloudSleuth使用举例,浏览器->Trace1->Trace2,生成跟踪日志<br>
 * 1.Sleuth跟踪支持通过RabbitMQ/Kafka(以及其它Stream绑定器实现的消息中间件)传递的请求、Zuul代理传递的请求和RestTemplate发起的请求,最新版本还支持Feign/Redis/
 * 每个请求发送之前,Sleuth会在请求Header中增加X-B3-TraceId、X-B3-SpanId、X-B3-ParentSpanId、X-B3-Sampled是否抽样输出标志等实现跟踪重要信息<br>
 * 2.输出INFO [trace-1,701cf23a48a2bd2a,701cf23a48a2bd2a,false]日志信息,第一个值记录了应用名称,
 * 第二个值Sleuth生成的TraceID标识一条请求链路,第三个值Sleuth生成的SpanID表示一个基本工作单元,
 * 第四个值表示是否要将该信息输出到Zipkin等日志分析平台中来收集和展示<b4>
 * 3.整合日志分析系统ELK平台、Zipkin系统等对服务跟踪信息做进一步分析
 *  
 * @author xuhon
 *
 */
@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class Trace1Application {
	
	private static final Logger log = LoggerFactory.getLogger(Trace1Application.class);

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 配置始终采样策略
	 * @return
	 */
//	@Bean
//	Sampler defaultSampler() {
//		return Sampler.ALWAYS_SAMPLE;
//	}
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@GetMapping("/trace-1")
	public String trace() {
		log.info("===call trace-1===");
		return restTemplate.getForEntity("http://sleuth-trace-2/trace-2", String.class).getBody();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Trace1Application.class, args);
	}

}
