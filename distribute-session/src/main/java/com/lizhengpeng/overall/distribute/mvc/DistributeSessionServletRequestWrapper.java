package com.lizhengpeng.overall.distribute.mvc;

import com.lizhengpeng.overall.distribute.mongo.MongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.*;

/**
 * 重写HttpServletRequest对象的Session获取逻辑
 * 将获取的Session对象改为
 * @author idealist
 */
public class DistributeSessionServletRequestWrapper extends HttpServletRequestWrapper {

    private static final Logger logger = LoggerFactory.getLogger(DistributeSessionServletRequestWrapper.class);

    private MongoService mongoService;
    private HttpSession httpSession;
    private HttpServletResponse response;

    /**
     * 默认的构造方法
     * @param request
     */
    public DistributeSessionServletRequestWrapper(HttpServletRequest request,HttpServletResponse response, MongoService mongoService) {
        super(request);
        this.response = response;
        this.mongoService = mongoService;
    }

    /**
     * 创建HttpSession对象
     * @param create
     * @return
     */
    @Override
    public HttpSession getSession(boolean create) {
        if(httpSession == null){
            if(create){
                if(mongoService == null){
                    logger.warn("[MongoSession]环境配置异常{请检查Mongodb环境|MongoClient配置}");
                    return httpSession = super.getSession(create);
                }else{
                    /**
                     * 加载Session时根据cookie值去mongo加载一次如果加载失败
                     * 1)session已经失效被mongo删除
                     * 2)一个无效的cookie值需要重新生成session
                     */
                    String sessionId = null;
                    Cookie[] cookies = ((HttpServletRequest)getRequest()).getCookies();
                    if(cookies != null){
                        for(Cookie cookie : cookies){
                            if (cookie.getName().equals(MongoSession.SESSION_NAME)){
                                sessionId = cookie.getValue();
                                break;
                            }
                        }
                    }
                    MongoSession mongoSession = new MongoSession(mongoService);
                    boolean loadComplete = false;
                    if(sessionId != null){
                        loadComplete = mongoSession.loadSession(sessionId);
                    }
                    if(!loadComplete || sessionId == null){
                        mongoSession.resetSession();
                        response.addCookie(new Cookie(MongoSession.SESSION_NAME,mongoSession.getId()));
                    }
                    httpSession = mongoSession;
                }
            }
        }
        return httpSession;
    }

    /**
     * 创建HttpSession对象
     * @return
     */
    @Override
    public HttpSession getSession() {
        return getSession(true);
    }

}
