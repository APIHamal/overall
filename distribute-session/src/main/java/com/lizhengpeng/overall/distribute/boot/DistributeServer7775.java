package com.lizhengpeng.overall.distribute.boot;

import com.lizhengpeng.overall.distribute.mvc.EnableDistributeSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 分布式Session服务
 * @author idealist
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDistributeSession/** 自定义注解加载分布式session的自动配置类 **/
@RestController
public class DistributeServer7775 {

    @GetMapping("/inspect")
    public String distributeSessionInspect(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        return "session属性->"+httpSession.getId();
    }

    @GetMapping("/session_store")
    public String testSessionStore(HttpSession session){
        System.out.println("sessionId--->"+session.getId());
        session.setAttribute("date",String.valueOf(new Date()));
        return "session store";
    }

    public static void main(String[] args) {
        SpringApplication.run(DistributeServer7775.class,args);
    }
}
