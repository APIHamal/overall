package com.lizhengpeng.overall.server.provider.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 模块服务方法
 * @author idealist
 */
@Component
public class Service {

    /**
     * 日志信息的输出
     */
    private static final Logger logger = LoggerFactory.getLogger(Service.class);

    /**
     * 供Hystrix调用拦截方法
     * @param text
     * @return
     */
    @HystrixCommand(commandKey = "Service_printText",fallbackMethod = "exceptionHandler")
    public String printText(String text){
        if(text == null){
            throw new RuntimeException("方法参数为空");
        }
        return "Service类调用返回:"+text;
    }

    /**
     * Hystrix调用异常默认处理方法
     * @param text
     * @return
     */
    public String exceptionHandler(String text){
        if(text == null){
            logger.info("Fallback检查参数为空");
        }else{
            logger.info("Fallback检查参数:参数为->"+text);
        }
        return "Fallback监测到异常返回默认参数";
    }

}
