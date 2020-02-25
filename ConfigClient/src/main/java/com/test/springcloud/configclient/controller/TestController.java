package com.test.springcloud.configclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.springcloud.configclient.configproperties.FromConfigProperties;

/**
  *  对需要获取的属性使用@Value注解,如果对类使用@RefreshScope注解还将支持动态刷新功能,
  *  通过/refresh端点将内外部配置刷新到内存之后,访问@RefreshScope标注的Bean会重新创建实例,并为占位符更新属性值<br>
  *  调用/refresh端口刷新配置以后,使用@RefreshScope注解的@Value等占位符、Environment对象和@ConfigurationProperties注解的对象属性值都会更新
 * @author xuhon
 *
 */
@RefreshScope
@RestController
public class TestController {

	@Value("${name}")
	private String name;
	
	@Value("${from}")
	private String from;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private FromConfigProperties fromConfigProperties;
	
	@RequestMapping("/from")
	public String from() {
		StringBuilder sb = new StringBuilder();
		sb.append("1.使用@Value获取配置文件属性值：from=").append(this.from).append("<br>")
				.append("2.使用Environment对象获取配置文件属性值：from=").append(env.getProperty("from", "undefined")).append("<br>")
				.append("3.使用@ConfigurationProperties获取配置文件属性值：from=").append(fromConfigProperties.getFrom()).append("<br>")
				.append("4.使用overrides属性配置的解密后的属性值：name=").append(name).append("<br>")
				.append("5.使用配置文件中解密后的属性值：password=").append(env.getProperty("password", "undefined"));
		return sb.toString();
	}	
	
}
