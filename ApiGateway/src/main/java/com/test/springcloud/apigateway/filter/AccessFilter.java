package com.test.springcloud.apigateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class AccessFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		logger.info("send {} request to {}", request.getMethod(), request.getRequestURI().toString());
		Object accessToken = request.getParameter("accessToken");
		if(null == accessToken) {
			logger.warn("access token is empty");
			ctx.setSendZuulResponse(false);
			ctx.setResponseBody("缺少accessToken参数，没有访问权限!");
			ctx.setResponseStatusCode(401);
			return null;
		}
		logger.info("access token ok");
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
