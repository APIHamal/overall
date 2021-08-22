package com.lizhengpeng.overall.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 开启Zuul的认证授权过滤器
 * @author idealist
 */
@Service
public class AuthZuulFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthZuulFilter.class);
    public static Object NO_OPERATION;

    @Override
    public String filterType() {
        /**
         * 定义ZUUL过滤器的类型
         * 1)pre   在请求被转发到指定的微服务之前触发
         * 2)route 请求被转发到路由时执行(本质上pre与route无很大区别都是在转发到指定微服务前触发)
         * 3)post  请求从微服务返回时触发(可以修改HTTP响应内容[或者说可以读取到响应内容])
         * 4)error zuul处理过程中发生异常时都会被触发
         */
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        /**
         * 定义ZUUL过滤器的执行顺序
         * 默认情况下该值越小则过滤器越先被执行(越小越先执行)
         */
        return Integer.valueOf(0);
    }

    @Override
    public boolean shouldFilter() {
        /**
         * 表示该过滤器是否应该被执行
         * !!!!此处可以进行动态的判断!!!!
         * 例如根据HTTP头部信息等
         */
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        /**
         * 获取当前的HTTP请求上下文对象
         * 该对象对于每个不同的请求都不相同(shouFilter中也可以获取到该对象进行动态的拦截判断)
         */
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        logger.info("请求的URL->"+request.getRequestURI());
        return NO_OPERATION;
    }
}
