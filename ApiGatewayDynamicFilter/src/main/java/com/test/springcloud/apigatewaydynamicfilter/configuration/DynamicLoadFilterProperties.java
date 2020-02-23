package com.test.springcloud.apigatewaydynamicfilter.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("zuul.filter")
public class DynamicLoadFilterProperties {

	private String root;
	
	private Integer interval;

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}
}
