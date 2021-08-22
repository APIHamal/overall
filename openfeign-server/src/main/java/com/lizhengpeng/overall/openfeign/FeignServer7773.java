package com.lizhengpeng.overall.openfeign;

import com.lizhengpeng.overall.openfeign.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

/**
 * OpenFeign功能支持
 * @author idealist
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RestController
public class FeignServer7773 {

    @Autowired
    private ServerProviderClient serverProviderClient;

    /**
     * 拼接字符串信息
     * @param text
     * @return
     */
    @GetMapping("/plus_text")
    public String concatText(@RequestParam("text") String text){
        return serverProviderClient.plusText(text);
    }

    /**
     * 拼接用户信息
     * @return
     */
    @GetMapping("/print_by_json")
    public String printUserInfoByJson(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(100);
        userInfo.setName("李正鹏");
        userInfo.setAge(27);
        return serverProviderClient.printUserInfoByJson(userInfo);
    }

    /**
     * 拼接用户信息
     * @return
     */
    @GetMapping("/print_by_query_param")
    public String printUserInfoByQueryParam(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(100);
        userInfo.setName("李正鹏");
        userInfo.setAge(27);
        return serverProviderClient.printUserInfoByQueryParam(userInfo);
    }

    /**
     * 拼接用户信息
     * @return
     */
    @GetMapping("/print_by_param_body")
    public String printUserInfoByParamBody(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(100);
        userInfo.setName("李正鹏");
        userInfo.setAge(27);
        return serverProviderClient.printUserInfoByParamBody(userInfo);
    }

    /**
     * 输出路径信息
     * @param pathVariable
     * @return
     */
    @GetMapping("/print_path_variable/{path_variable}")
    public String printPathVariable(@PathVariable("path_variable")String pathVariable){
        return serverProviderClient.printPathVariable(pathVariable);
    }

    /**
     * 输出路径信息通过hystrix包装方法
     * @param text
     * @return
     */
    @GetMapping("/print_by_hystrix")
    public String printExceptionByHystrix(@RequestParam("text") String text){
        return serverProviderClient.printExceptionByHystrix(text);
    }

    public static void main(String[] args) {
        SpringApplication.run(FeignServer7773.class,args);
    }
}
