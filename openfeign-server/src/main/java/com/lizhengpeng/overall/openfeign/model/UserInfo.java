package com.lizhengpeng.overall.openfeign.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 实体类信息
 * @author idealist
 */
@Setter
@Getter
public class UserInfo {

    private Integer id;  //用户编号
    private String name; //用户姓名
    private Integer age; //用户年龄

}
