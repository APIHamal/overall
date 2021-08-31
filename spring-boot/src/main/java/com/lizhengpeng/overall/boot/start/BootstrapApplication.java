package com.lizhengpeng.overall.boot.start;

import com.lizhengpeng.overall.boot.autoconfig.EnableHttpInfoLogger;
import com.lizhengpeng.overall.boot.autoconfig.EnableHttpInfoLoggerWithSPI;
import com.lizhengpeng.overall.boot.autoconfig.HttpInfoAutoConfig;
import com.lizhengpeng.overall.boot.redis.RedisCustomConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注意:SpringBoot中多个webMvcConfigurer的Bean可以共存
 * 并且Springboot会依次调用相关的方法
 */
@SpringBootApplication
@RestController
@EnableHttpInfoLogger
@Import(RedisCustomConfiguration.class)
public class BootstrapApplication {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/health")
    public String healthCheck(){
        return "STATUS:[UP]";
    }

    @GetMapping("/addText")
    public String addText(String text){
        redisTemplate.opsForValue().set("hello_redis",text);
        return "添加数据成功";
    }

    @GetMapping("/getText")
    public String getText(String text){
        String content = (String) redisTemplate.opsForValue().get(text);
        if(content == null){
            content = "数据不存在";
        }
        return content;
    }

    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }

}
