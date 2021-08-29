package com.lizhengpeng.overall.distribute.mvc;

import com.lizhengpeng.overall.distribute.mongo.MongoClientAutoConfig;
import com.lizhengpeng.overall.distribute.mongo.MongoService;
import com.lizhengpeng.overall.distribute.mongo.MongoServiceImpl;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 配置session存储依赖的mongodb的环境
 * @author idealist
 */
@Configuration
@Import(MongoClientAutoConfig.class)
public class DistributeSessionAutoConfig {

    @Bean
    @ConditionalOnBean(MongoClient.class)
    public MongoService mongoService(MongoClient mongoClient){
        MongoService mongoService = new MongoServiceImpl(mongoClient);
        return mongoService;
    }

    @Bean
    public FilterRegistrationBean init(MongoService mongoService){
        FilterRegistrationBean<DistributeSessionCreateFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new DistributeSessionCreateFilter(mongoService));
        registration.setName("DistributeSessionCreateFilter");
        registration.setUrlPatterns(Arrays.asList("/*"));
        registration.setOrder(-1); /** 优先级越低约先被执行 **/
        return registration;
    }

}
