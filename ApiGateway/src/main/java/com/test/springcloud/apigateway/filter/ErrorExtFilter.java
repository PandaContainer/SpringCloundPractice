package com.test.springcloud.apigateway.filter;

import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 在zuul老版本中SendErrorFilter属于POST类型过滤器,如果当POST类型过滤器抛出异常时,异常信息是无法返回给客户端的,
 * 所以除了需要使用try-catch或自定义ERROR类型ErrorFilter过滤器组织error.*参数保存到请求上下文中之外,
 * 还需要创建ERROR类型过滤器实现SendErrorFilter过滤器功能,来返回POST类型过滤器抛出的异常信息
 * @author xuhon
 *
 */
//@Component
public class ErrorExtFilter extends SendErrorFilter {

	@Override
	public String filterType() {
		return "error";
	}
	
	@Override
	public int filterOrder() {
		// 大于自定义过滤器ErrorFilter的值10
		return 30;
	}
	
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		ZuulFilter failedFilter = (ZuulFilter) ctx.get("failed.filter");
		if (null != failedFilter && "post".equals(failedFilter.filterType())) {
			return true;
		}
		return false;
	}
}
