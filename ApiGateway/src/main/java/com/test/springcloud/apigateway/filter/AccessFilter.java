package com.test.springcloud.apigateway.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

//也可以在配置类中使用@Bean配置自定义过滤器
//@Component
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
			response.setContentType("application/json;charset=utf-8");
			// 查看route类型过滤器源码可知,设置ctx.setSendZuulResponse(false)
			// 只是RibbonRoutingFilter和SimpleHostRoutingFilter过滤器不会执行,SendForwardFilter仍会执行
			ctx.setSendZuulResponse(false);
			// SendResponseFilter只要不存异常且存在响应头、响应体或响应输出流就会执行并返回信息
			ctx.setResponseBody("{\"returnCode\":2, \"returnMsg\":\"没有访问权限，accessToken参数不能为空\"}");
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
