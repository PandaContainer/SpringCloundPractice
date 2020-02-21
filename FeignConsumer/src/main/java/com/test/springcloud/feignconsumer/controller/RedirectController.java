package com.test.springcloud.feignconsumer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RedirectController {

	@RequestMapping("/redirect-{toUrl}")
	public String redirect(HttpServletRequest request, HttpServletResponse response, @PathVariable("toUrl") String toUrl, @RequestParam String accessToken) throws Exception {
		//request.getRequestDispatcher("/feign-consumer").forward(request,response);
		//response.sendRedirect("/feign-consumer");
		//return "forward:/feign-consumer";
		StringBuilder sb = new StringBuilder();
		sb.append("redirect:/").append(toUrl);
		if(null != accessToken) {
			sb.append("?accessToken=").append(accessToken);
		}
		return sb.toString();
	}
}
