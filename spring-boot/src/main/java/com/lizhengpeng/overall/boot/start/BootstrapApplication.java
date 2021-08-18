package com.lizhengpeng.overall.boot.start;

import com.lizhengpeng.overall.boot.autoconfig.EnableHttpInfoLogger;
import com.lizhengpeng.overall.boot.autoconfig.EnableHttpInfoLoggerWithSPI;
import com.lizhengpeng.overall.boot.autoconfig.HttpInfoAutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableHttpInfoLoggerWithSPI
public class BootstrapApplication {

    @GetMapping("/health")
    public String healthCheck(){
        return "STATUS:[UP]";
    }

    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }

}
