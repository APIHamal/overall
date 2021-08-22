package com.lizhengpeng.overall.openfeign;

import com.lizhengpeng.overall.openfeign.model.UserInfo;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ServerProviderClient远程调用服务回退类
 * @author idealist
 */
@Component
public class ServerProviderClientFallbackFactory implements FallbackFactory<ServerProviderClient> {

    private static final Logger logger = LoggerFactory.getLogger(ServerProviderClientFallbackFactory.class);

    @Override
    public ServerProviderClient create(Throwable throwable) {
        logger.info("ServerProviderClient调用失败",throwable);
        return new ServerProviderClient() {
            @Override
            public String plusText(String textContent) {
                return "OpenFeign调用Fallback返回数据";
            }

            @Override
            public String printUserInfoByJson(UserInfo userInfo) {
                return "OpenFeign调用Fallback返回数据";
            }

            @Override
            public String printUserInfoByQueryParam(UserInfo userInfo) {
                return "OpenFeign调用Fallback返回数据";
            }

            @Override
            public String printUserInfoByParamBody(UserInfo userInfo) {
                return "OpenFeign调用Fallback返回数据";
            }

            @Override
            public String printPathVariable(String pathVariable) {
                return "OpenFeign调用Fallback返回数据";
            }

            @Override
            public String printExceptionByHystrix(String text) {
                return "OpenFeign调用Fallback返回数据";
            }
        };
    }
}
