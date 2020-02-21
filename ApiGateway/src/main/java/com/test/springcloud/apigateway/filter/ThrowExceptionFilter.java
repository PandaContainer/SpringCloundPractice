package com.test.springcloud.apigateway.filter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ThrowExceptionFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(ThrowExceptionFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		logger.info("This is a pre filter, it will throw a RuntimeException");
		
		// 查看RibbonRoutingFilter过滤器代码,新版本不用组装异常信息,创建异常对象信息然后直接抛出即可
		doSomething();
		
		// 老版本可使用try-catch处理异常,搜集异常信息,交给后续SendErrorFilter过滤器返回错误信息
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
		return PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
