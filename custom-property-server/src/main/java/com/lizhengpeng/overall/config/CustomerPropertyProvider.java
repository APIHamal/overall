package com.lizhengpeng.overall.config;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Collections;

/**
 * 自定义一个属性的配置类(该类在bootstrap[bootstrap->application]上下文启动时被加载)
 * spring-cloud-context/(org.springframework.cloud.bootstrap.BootstrapConfiguration)
 * 键值对的类列表会在bootstrap启动时被自动的加载
 * @author idealist
 */
public class CustomerPropertyProvider implements PropertySourceLocator {

    /**
     * 自定义环境变量配置该配置会覆盖本地(application|bootstrap)[yml|properties]中配置
     * @param environment
     * @return
     */
    @Override
    public PropertySource<?> locate(Environment environment) {
        /**
         * 设置自定义的环境配置
         */
        return new MapPropertySource("customProperty",
                Collections.<String, Object>singletonMap("server.port", "9999"));
    }

}
