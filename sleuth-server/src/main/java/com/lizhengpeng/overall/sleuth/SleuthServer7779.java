package com.lizhengpeng.overall.sleuth;

import com.lizhengpeng.overall.sleuth.feign.HystrixFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分布式链路追踪实现
 * @author idealist
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableFeignClients
public class SleuthServer7779 {

    @Autowired
    private HystrixFeign hystrixFeign;

    /**
     * 输出字符串的内容
     * @param text
     * @return
     */
    @GetMapping("/print_text")
    public String printText(String text){
        return "self->"+text;
    }

    @GetMapping("/call_self")
    public String callSelf(String text){
        if(text == null || text.trim().length() == 0)
            text = "hello,World";
        return hystrixFeign.printText(text);
    }


    public static void main(String[] args) {
        SpringApplication.run(SleuthServer7779.class, args);
    }
}
