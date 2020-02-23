package com.test.springcloud.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

import com.test.springcloud.apigateway.errorattributes.DidiErroAttributes;
import com.test.springcloud.apigateway.filter.AccessFilter;

@EnableZuulProxy
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		// 老版本zuul启用自定义核心处理器,保存异常过滤器信息,为ERROR类型ErrorExtFilter过滤器提供是否执行判断依据
		// FilterProcessor.setProcessor(new DidiFilterProcessor());
		
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public AccessFilter accessFilter() {
		// 自定义权限过滤器
		return new AccessFilter();
	}
	
	@Bean
	public PatternServiceRouteMapper serviceRouteMapper() {
		// 自定义路由映射规则:第一个参数用来匹配服务名称是否符合正则表达式,第二个参数则是定义根据服务名转换出来的路径规则,如果没有匹配使用默认路由规则
		// 比如user-service-v1服务转换成路径规则为v1/user-service
		return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)", "${version>/${name}") ;
	}
	
	@Bean
	public DefaultErrorAttributes errorAttributes() {
		// 自定义错误属性搜集器
		return new DidiErroAttributes();
	}
}
