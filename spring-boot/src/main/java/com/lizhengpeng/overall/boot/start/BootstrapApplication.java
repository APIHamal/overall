package com.lizhengpeng.overall.boot.start;

import com.lizhengpeng.overall.boot.autoconfig.EnableHttpInfoLogger;
import com.lizhengpeng.overall.boot.autoconfig.EnableHttpInfoLoggerWithSPI;
import com.lizhengpeng.overall.boot.autoconfig.HttpInfoAutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注意:SpringBoot中多个webMvcConfigurer的Bean可以共存
 * 并且Springboot会依次调用相关的方法
 */
@SpringBootApplication
@RestController
@EnableHttpInfoLogger
public class BootstrapApplication {

    @GetMapping("/health")
    public String healthCheck(){
        return "STATUS:[UP]";
    }

    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }

}
