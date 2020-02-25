package com.test.springcloud.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * spring cloud config配置服务器<br>
 * 1.在ConfigServer文件系统中,每次客户端请求配置信息时,Config Server会从Git仓库获取最新配置到本地,然后从本地Git仓库中读取并返回,
  *  如果远程仓库无法获取时,直接将本地内容返回,起到了缓存作用,即使Git服务端无法访问,依然可以取缓存内容进行使用<br>
 * 2.访问配置信息的URL与配置文件的映射关系如下所示:<br>
 * /{application}/{profile}[/{label}]	<br>
 * /{application}-{profile}.yml	<br>
 * /{label}/{application}-{profile}.yml	<br>
 * /{application}-{profile}.properties	<br>
 * /{label}/{application}-{profile}.properties
 * 
 * @author xuhon
 *
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
