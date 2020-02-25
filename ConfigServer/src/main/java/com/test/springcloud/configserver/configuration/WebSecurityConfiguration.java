package com.test.springcloud.configserver.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 通过浏览器访问/encrypt端点必须禁用跨源请求,springboot2.0以上只能通过提供实现WebSecurityConfigurer类对象来配置,建议使用curl工具访问
		http.csrf().disable();
		super.configure(http);
	}
}
