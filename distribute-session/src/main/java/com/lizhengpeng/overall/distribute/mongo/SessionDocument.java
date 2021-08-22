package com.lizhengpeng.overall.distribute.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * HttpSession对应的mongo文档对象模型
 * @author idealist
 */
@Setter
@Getter
public class SessionDocument {

    /**
     * 文档对象的Id标识
     */
    private String sessionId;

    /**
     * Session中保存属性的对象
     */
    private Map<String,Object> attribute;

}
