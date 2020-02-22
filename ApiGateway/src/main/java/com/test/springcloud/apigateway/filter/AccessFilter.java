package com.test.springcloud.apigateway.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
		String accessToken = request.getParameter("accessToken");
		if(StringUtils.isEmpty(accessToken)) {
			logger.warn("access token is empty");
			HttpServletResponse response = ctx.getResponse();
			response.setContentType("text/html;charset=utf-8");
			ctx.setSendZuulResponse(false);
			ctx.setResponseBody("accessToken参数不能为空，没有访问权限!");
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
