package com.lizhengpeng.overall.distribute.mvc;

import com.lizhengpeng.overall.distribute.mongo.MongoService;
import com.lizhengpeng.overall.distribute.mongo.SessionDocument;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Mongodb封装实现的HttpSession对象
 * @author idealist
 */
public class MongoSession extends BaseMongoSession {

    public static final String SESSION_NAME = "JSESSIONID";
    private MongoService mongoService;
    private SessionDocument sessionDocument;
    private Map<String,Object> attribute = new HashMap<>();


    public MongoSession(MongoService mongoService){
        this.mongoService = mongoService;
        sessionDocument = new SessionDocument();
    }

    public boolean loadSession(String sessionId){
        sessionDocument.setSessionId(sessionId);
        return mongoService.loadSession(sessionDocument);
    }

    public void resetSession(){
        sessionDocument.setSessionId(null);
        setAttribute("_reset_date",new Date());
    }

    @Override
    public String getId() {
        return sessionDocument.getSessionId();
    }

    @Override
    public Object getAttribute(String attr) {
        return attribute.get(attr);
    }

    @Override
    public Object getValue(String s) {
        return getAttribute(s);
    }

    @Override
    public void setAttribute(String attr, Object value) {
        attribute.put(attr,value);
        mongoService.saveSession(sessionDocument);
    }

    @Override
    public void removeAttribute(String attr) {
        attribute.remove(attr);
        mongoService.saveSession(sessionDocument);
    }

    @Override
    public void invalidate() {
        attribute.clear();
        if(sessionDocument.getSessionId() != null){
            mongoService.removeSession(sessionDocument.getSessionId());
        }
    }

}
