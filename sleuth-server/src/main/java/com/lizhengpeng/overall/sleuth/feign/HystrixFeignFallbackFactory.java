package com.lizhengpeng.overall.sleuth.feign;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置HystrixFeign降级处理
 * @author idealist
 */
public class HystrixFeignFallbackFactory implements FallbackFactory<HystrixFeign> {

    private static final Logger logger = LoggerFactory.getLogger(HystrixFeignFallbackFactory.class);

    @Override
    public HystrixFeign create(Throwable throwable) {
        logger.error("hystrixFeign异常", throwable);
        return new HystrixFeign() {
            @Override
            public String printText(String text) {
                return "{code:'error',msg:feign调用失败'}";
            }
        };
    }
}
