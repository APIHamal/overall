package com.lizhengpeng.overall.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * eurekaServer服务端环境搭建
 * @author idealist
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer7770 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer7770.class, args);
    }
}
