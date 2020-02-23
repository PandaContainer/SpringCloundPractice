package com.test.springcloud.apigateway.filter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 1、老版本zuul没有error类型过滤器,新老版本在执行pre/route/post类型过滤器时都存在异常处理逻辑,详见ZuulServlet类源码分析<br>
 * 2、查看ZuulServlet和SendErrorFilter源码可知,新版本zuul中SendErrorFilter属于error类型过滤器,
 * 所有类型过滤器抛出的异常都将被封装成ZuulException类型异常,并调用RequestContext.getCurrentContext().setThrowable(e)方法,
 * 可以直接由ERROR类型SendErrorFilter过滤器返回异常信息<br>
 * 3、老版本zuul中SendErrorFilter属于post类型过滤器,自定义过滤器需要使用try-catch处理异常或自定义ERROR类型过滤器,
 * 设置error.*异常参数,才能由POST类型SendErrorFilter过滤器返回异常信息
 * 
 * @author xuhon
 *
 */
// 也可以在配置类中使用@Bean配置自定义过滤器
//@Component
public class ThrowExceptionFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(ThrowExceptionFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		logger.info("This is a {} filter, it will throw a RuntimeException", this.filterType());

		/* 老版本zuul没有error类型过滤器,新老版本在执行pre/route/post类型过滤器时都存在异常处理逻辑,详见ZuulServlet类源码分析 */
		
		// 1.查看ZuulServlet和SendErrorFilter源码可知,新版本zuul中SendErrorFilter属于error类型过滤器,
		// 所有类型过滤器抛出的异常都将被封装成ZuulException类型异常,并调用RequestContext.getCurrentContext().setThrowable(e)方法,
		// 可以直接由ERROR类型SendErrorFilter过滤器返回异常信息
		doSomething();
		
		// 2.老版本zuul中SendErrorFilter属于post类型过滤器,自定义过滤器需要使用try-catch处理异常或自定义ERROR类型过滤器,
		// 设置error.*异常参数,才能由POST类型SendErrorFilter过滤器返回异常信息
		RequestContext ctx = RequestContext.getCurrentContext();
		try {
			doSomething();
		} catch (Exception e) {
			ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			ctx.set("error.exception", e);
			// 如果不设置error.message属性,则取error.exception异常中的message信息
			//ctx.set("error.message", "Some erros...");
		}
		return null;
	}

	private void doSomething() {
		throw new RuntimeException("Exist some errors...");
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
