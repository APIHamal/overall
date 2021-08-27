package com.lizhengpeng.overall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 自定义属性源服务
 * @author idealist
 */
@SpringBootApplication
@EnableEurekaClient
public class CustomPropertyServer7776 {
    public static void main(String[] args) {
        SpringApplication.run(CustomPropertyServer7776.class, args);
    }
}
