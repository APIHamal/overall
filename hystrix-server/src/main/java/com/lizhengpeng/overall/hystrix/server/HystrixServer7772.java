package com.lizhengpeng.overall.hystrix.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hystrix服务实例
 * @author idealist
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@RestController
/** 开启hystrix图形界面功能 **/
@EnableHystrixDashboard
public class HystrixServer7772 {

    @GetMapping("/health")
    public String healthInspect(){
        return "hello,hystrix server";
    }

    public static void main(String[] args) {
        SpringApplication.run(HystrixServer7772.class,args);
    }
}
