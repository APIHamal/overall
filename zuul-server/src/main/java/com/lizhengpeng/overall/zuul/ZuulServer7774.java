package com.lizhengpeng.overall.zuul;

import com.lizhengpeng.overall.distribute.mvc.DistributeSessionServletRequestWrapper;
import com.lizhengpeng.overall.distribute.mvc.EnableDistributeSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 路由转发与身份认证服务
 * @author idealist
 */
@SpringBootApplication
@EnableZuulProxy /** 启用zuul的代理功能 **/
@EnableDistributeSession
@RestController
public class ZuulServer7774 {

    @GetMapping("/inspect")
    public String zuulInspect(HttpServletRequest request){
        System.out.println("请求类型-->"+(request instanceof DistributeSessionServletRequestWrapper));
        return "zuul,Inspect method return";
    }

    public static void main(String[] args) {
        SpringApplication.run(ZuulServer7774.class, args);
    }
}
