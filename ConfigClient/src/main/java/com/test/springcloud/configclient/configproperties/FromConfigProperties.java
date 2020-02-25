package com.test.springcloud.configclient.configproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "")
@Component
public class FromConfigProperties {

	private String from;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
}
