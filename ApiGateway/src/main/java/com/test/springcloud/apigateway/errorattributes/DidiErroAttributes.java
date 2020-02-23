package com.test.springcloud.apigateway.errorattributes;

import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 * 自定义错误属性搜集器,为BasicErrorController类默认/error接口返回自定义错误信息
 * 
 * @author xuhon
 *
 */
public class DidiErroAttributes extends DefaultErrorAttributes {

	public DidiErroAttributes() {
		super(true);
	}
	
	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		Map<String, Object> result = super.getErrorAttributes(webRequest, includeStackTrace);
		result.put("other", "custom attribute");
		return result;
	}
}
