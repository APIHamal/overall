package com.lizhengpeng.overall.zuul;

import com.lizhengpeng.overall.distribute.mongo.MongoService;
import com.lizhengpeng.overall.distribute.mvc.DistributeSessionServletRequestWrapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对Zuul使用的HttpServletRequest对象进行包装使其具备分布式session功能
 * @author idealist
 */
@Service
public class ZuulHttpRequestWrapperFilter extends ZuulFilter implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(ZuulHttpRequestWrapperFilter.class);

    /**
     * 获取当前的spring上下文环境
     */
    private ApplicationContext applicationContext;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 注意该对象初始化顺序必须在AuthZuulFilter对象之前
     * @return
     */
    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 对原始的HttpServletRequest对象进行包装使其具备分布式session功能
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        logger.info("开始检查分布式session工作环境....ok");
        MongoService mongoService = applicationContext.getBean(MongoService.class);
        if(mongoService != null){
            logger.info("mongoSession环境配置检查....ok");
            HttpServletRequest request = context.getRequest();
            HttpServletResponse response = context.getResponse();
            DistributeSessionServletRequestWrapper wrapper = new DistributeSessionServletRequestWrapper(request,response,mongoService);
            context.setRequest(wrapper);
        }else{
            logger.info("mongoSession环境配置检查....fail");
        }
        return AuthZuulFilter.NO_OPERATION;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
