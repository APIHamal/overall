package com.lizhengpeng.overall.boot.autoconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 输出HTTP请求相关信息
 * @author idealist
 */
public class HttpInfoLogger implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(HttpInfoLogger.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("请求时间"+new Date());
        logger.info("HTTP请求路径["+request.getRequestURI()+"]");
        return true;
    }

}
