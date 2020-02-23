package com.test.springcloud.apigateway.filter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 在zuul老版本中SendErrorFilter属于post类型过滤器,自定义过滤器如果未使用try-catch异常处理机制,
 * 也可以通过创建error类型过滤器组织error.*参数保存到请求上下文,再由POST类型SendErrorFilter过滤器返回异常信息
 * @author xuhon
 *
 */
//@Component
public class ErrorFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(ErrorFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		Throwable t = ctx.getThrowable();
		logger.error("this is a ErrorFilter : {}", t.getCause().getMessage());
		ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		ctx.set("error.exception", t.getCause());
		return null;
	}

	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		return 10;
	}

}
