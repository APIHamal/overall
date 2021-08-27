package com.lizhengpeng.overall.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 配置服务器
 * 注意:仓库中[application|application-profile](yml|properties)文件被所有实例共享
 * 因此例如
 * 1)eureka的服务配置
 * 2)springSession中的配置
 * 3)mongodb中的配置
 * 等以上共同的配置均应该放在application(yml|properties)文件
 * 不需要为每个不同的实例进行配置
 * @author idealist
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ConfigServer7776 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServer7776.class, args);
    }
}
