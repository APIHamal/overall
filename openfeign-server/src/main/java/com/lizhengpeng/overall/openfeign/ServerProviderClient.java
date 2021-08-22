package com.lizhengpeng.overall.openfeign;

import com.lizhengpeng.overall.openfeign.model.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * ServerProvider模块远程调用
 * @author idealist
 */
@FeignClient(name = "server-provider",fallbackFactory = ServerProviderClientFallbackFactory.class)
public interface ServerProviderClient {

    /**
     * 远程返回拼接字符串
     * @param textContent
     * @return
     */
    @RequestMapping(value = "/plus_text",method = RequestMethod.GET)
    String plusText(@RequestParam("text") String textContent);

    /**
     * 输出用户信息
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/print_by_json",method = RequestMethod.POST)
    String printUserInfoByJson(UserInfo userInfo);

    /**
     * 输出用户信息
     * key=value&key1=value1的形式列出内容
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/print_by_query_param",method = RequestMethod.GET)
    String printUserInfoByQueryParam(@SpringQueryMap UserInfo userInfo);

    /**
     * 输出用户信息
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/print_by_param_body",method = RequestMethod.POST)
    String printUserInfoByParamBody(@RequestBody UserInfo userInfo);

    /**
     * 输出路径信息
     * @param pathVariable
     * @return
     */
    @RequestMapping(value = "/print_path_variable/{path}",method = RequestMethod.GET)
    String printPathVariable(@PathVariable("path")String pathVariable);

    /**
     * 通过hystrix返回调用备用信息
     * @param text
     * @return
     */
    @RequestMapping(value = "/print_by_hystrix",method = RequestMethod.GET)
    String printExceptionByHystrix(@RequestParam("text") String text);


}
