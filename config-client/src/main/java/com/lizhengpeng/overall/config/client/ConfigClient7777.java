package com.lizhengpeng.overall.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * config客户端实例
 * @author idealist
 */
@EnableEurekaClient
@SpringBootApplication
@RestController
public class ConfigClient7777 {

    @Value("${hello.world}")
    private String hello;

    @Value("${prod}")
    private String prod;

    @Value("${application}")
    private String application;

    @GetMapping("/text")
    public String getText(){
        return hello;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClient7777.class, args);
    }
}
