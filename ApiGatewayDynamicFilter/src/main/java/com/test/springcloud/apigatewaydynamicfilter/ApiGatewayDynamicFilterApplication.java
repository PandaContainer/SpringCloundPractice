package com.test.springcloud.apigatewaydynamicfilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import com.test.springcloud.apigatewaydynamicfilter.configuration.DynamicLoadFilterProperties;

/**
 * zuul使用Groovy脚本实现动态加载过滤器功能<br>
 * 
 * 目前还存在的问题:1.老版本删除Groovy脚本并不能从当前运行网关中移除该过滤器<br>
 * 2.动态过滤器无法直接注入Spring容器中加载的实例来使用,使用SrpingApplicationUtils应该可以用
 * 
 * @author xuhon
 *
 */
@EnableZuulProxy
@EnableConfigurationProperties({DynamicLoadFilterProperties.class})
@SpringBootApplication
public class ApiGatewayDynamicFilterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayDynamicFilterApplication.class, args);
	}

	@Bean
	public FilterLoader filterLoader(DynamicLoadFilterProperties filterProperties) {
		FilterLoader filterLoader = FilterLoader.getInstance();
		filterLoader.setCompiler(new GroovyCompiler());
		try {
			FilterFileManager.setFilenameFilter(new GroovyFileFilter());
			FilterFileManager.init(filterProperties.getInterval(), filterProperties.getRoot() + "/pre",
					filterProperties.getRoot() + "/route", filterProperties.getRoot() + "/post");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return filterLoader;
	}
}
