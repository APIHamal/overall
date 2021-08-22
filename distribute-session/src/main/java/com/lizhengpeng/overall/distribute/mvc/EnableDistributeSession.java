package com.lizhengpeng.overall.distribute.mvc;

import com.lizhengpeng.overall.distribute.mongo.MongoClientAutoConfig;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.*;

/**
 * 注解驱动用于开启基于mongodb的分布式Session组件
 * @author idealist
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Document
@Inherited
@Import({DistributeSessionAutoConfig.class})
public @interface EnableDistributeSession {
}
