package com.lizhengpeng.overall.distribute.mongo;

/**
 * HTTPSession操作封装
 * @author idealist
 */
public interface MongoService {

    /**
     * 将Session对象保存到mongodb中
     * @param sessionDocument
     */
    void saveSession(SessionDocument sessionDocument);

    /**
     * 移除当前Session对象
     * @param sessionId
     */
    void removeSession(String sessionId);

    /**
     * 加载session对象
     * @param sessionDocument
     */
    boolean loadSession(SessionDocument sessionDocument);

}
