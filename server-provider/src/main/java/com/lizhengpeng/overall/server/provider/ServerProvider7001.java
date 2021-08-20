package com.lizhengpeng.overall.server.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 服务提供者实现
 * @author idealist
 */
@SpringBootApplication
@EnableEurekaClient
public class ServerProvider7001 {
    public static void main(String[] args) {
        SpringApplication.run(ServerProvider7001.class,args);
    }
}
