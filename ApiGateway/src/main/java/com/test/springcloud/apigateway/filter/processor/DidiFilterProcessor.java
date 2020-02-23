package com.test.springcloud.apigateway.filter.processor;

import com.netflix.zuul.FilterProcessor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 在zuul老版本中SendErrorFilter属于POST类型过滤器,
 * 如果当POST类型过滤器抛出异常时,异常信息是无法返回给客户端的,
 * 所以需要自定义过滤器处理器保存异常过滤器的类型,为ErrorExtFilter过滤器是否处理提供判断依据,
 * 还需要在应用主类中调用FilterProcessor.setProcessor(new DidiFilterProcessor());启用自定义核心处理器
 * 
 * @author xuhon
 */
public class DidiFilterProcessor extends FilterProcessor {

	@Override
	public Object processZuulFilter(ZuulFilter filter) throws ZuulException {
		try {
			return super.processZuulFilter(filter);
		} catch (ZuulException e) {
			// 保存完抛出异常的过滤器,仍然抛出异常,不改变老逻辑,可能会影响性能
			RequestContext ctx = RequestContext.getCurrentContext();
			ctx.set("failed.filter", filter);
			throw e;
		}
	}
}
