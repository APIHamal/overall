package com.lizhengpeng.overall.distribute.mvc;

import com.lizhengpeng.overall.distribute.mongo.MongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * MVC拦截器对象在此处重新实现HttpServletRequest对象
 * @author idealist
 */
public class DistributeSessionCreateFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(DistributeSessionCreateFilter.class);

    /**
     * MongoSessionDocument管理对象
     */
    private MongoService mongoService;

    /**
     * 默认的构造函数
     * @param mongoService
     */
    public DistributeSessionCreateFilter(MongoService mongoService){
        this.mongoService = mongoService;
    }

    /**
     * 创建MongoSessionRequest对象
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(!(servletRequest instanceof DistributeSessionServletRequestWrapper)){
            servletRequest = new DistributeSessionServletRequestWrapper((HttpServletRequest) servletRequest,(HttpServletResponse)servletResponse,mongoService);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
