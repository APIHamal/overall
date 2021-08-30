package com.lizhengpeng.overall.sleuth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 指向自身HTTP调用的Feign对象
 * @author idealist
 */
@FeignClient(name = "sleuth-server", fallbackFactory = HystrixFeignFallbackFactory.class)
public interface HystrixFeign {

    /**
     * 输出字符串的内容
     * @param text
     * @return
     */
    @RequestMapping(value = "/print_text", method = RequestMethod.GET)
    String printText(@RequestParam("text") String text);

}
