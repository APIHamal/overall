package com.lizhengpeng.overall.server.provider;

import com.lizhengpeng.overall.distribute.mvc.EnableDistributeSession;
import com.lizhengpeng.overall.server.provider.model.UserInfo;
import com.lizhengpeng.overall.server.provider.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.InvalidParameterException;

/**
 * 服务提供者实现
 * @author idealist
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@RestController
@EnableDistributeSession
public class ServerProvider7001 {

    private static final Logger logger = LoggerFactory.getLogger(ServerProvider7001.class);

    @Autowired
    private Service service;

    @GetMapping("/plus_text")
    public String concatText(@RequestParam("text") String text, HttpServletRequest servletRequest){
        HttpSession httpSession = servletRequest.getSession();
        logger.info("sessionId--->"+httpSession.getId());
        if(httpSession.getAttribute("name") != null){
            logger.info("当前调用方名称--->"+httpSession.getAttribute("name"));
        }
        return "hi,plus_text:"+text;
    }

    @PostMapping("/print_by_json")
    public String printUserInfoByJson(@RequestBody UserInfo userInfo){
        if(userInfo == null){
            throw new InvalidParameterException("用户信息不能为空");
        }
        logger.info("用户编号:"+userInfo.getId()+"->用户年龄:"+userInfo.getAge()+"->用户姓名:"+userInfo.getName());
        return "用户信息输出完成";
    }

    @GetMapping("/print_by_query_param")
    public String printUserInfoByQueryParam(UserInfo userInfo){
        if(userInfo == null){
            throw new InvalidParameterException("用户信息不能为空");
        }
        logger.info("用户编号:"+userInfo.getId()+"->用户年龄:"+userInfo.getAge()+"->用户姓名:"+userInfo.getName());
        return "用户信息输出完成";
    }

    @PostMapping("/print_by_param_body")
    public String printUserInfoByParamBody(UserInfo userInfo){
        if(userInfo == null){
            throw new InvalidParameterException("用户信息不能为空");
        }
        logger.info("用户编号:"+userInfo.getId()+"->用户年龄:"+userInfo.getAge()+"->用户姓名:"+userInfo.getName());
        return "用户信息输出完成";
    }

    @GetMapping("/print_path_variable/{path_variable}")
    public String printPathVariable(@PathVariable("path_variable")String pathVariable){
        logger.info("输出路径信息:->"+pathVariable);
        return "输出路径信息成功";
    }

    @GetMapping("/print_by_hystrix")
    public String printExceptionByHystrix(@RequestParam("text") String text){
        return service.printText(text);
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerProvider7001.class,args);
    }
}
