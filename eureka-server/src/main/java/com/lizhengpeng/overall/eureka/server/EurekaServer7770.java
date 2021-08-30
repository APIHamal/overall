package com.lizhengpeng.overall.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * eurekaServer服务端环境搭建
 * @author idealist
 */
@SpringBootApplication
@EnableEurekaServer
@RestController
public class EurekaServer7770 {

    /**
     * 输出文本的内容
     * @param text
     * @return
     */
    @GetMapping("/print")
    public String printText(String text){
        return "hystrix:"+text;
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer7770.class, args);
    }
}
