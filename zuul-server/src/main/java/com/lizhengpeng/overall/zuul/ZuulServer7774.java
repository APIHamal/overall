package com.lizhengpeng.overall.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 路由转发与身份认证服务
 * @author idealist
 */
@SpringBootApplication
@EnableZuulProxy /** 启用zuul的代理功能 **/
public class ZuulServer7774 {
    public static void main(String[] args) {
        SpringApplication.run(ZuulServer7774.class, args);
    }
}
