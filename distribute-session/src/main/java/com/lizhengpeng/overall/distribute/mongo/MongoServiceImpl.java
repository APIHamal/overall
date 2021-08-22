package com.lizhengpeng.overall.distribute.mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.model.InsertOneOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MongoServiceImpl implements MongoService{

    /**
     * 使用的Mongodb数据库名称
     */
    private static final String DATA_BASE = "app_db";

    /**
     * 使用的集合对象
     */
    private static final String COLLECTION_NAME = "distribute_session";

    /**
     * 使用的Mongodb数据库对象
     */
    private MongoDatabase mongoDatabase;

    /**
     * 默认的构造函数
     * @param mongoClient
     */
    public MongoServiceImpl(MongoClient mongoClient){
        mongoDatabase = mongoClient.getDatabase(DATA_BASE);
    }

    /**
     * 获取指定的集合对象
     * @return
     */
    public MongoCollection getMongoCollection(){
        MongoCollection mongoCollection = mongoDatabase.getCollection(COLLECTION_NAME);
        return mongoCollection;
    }

    @Override
    public void saveSession(SessionDocument sessionDocument) {
        MongoCollection collection = getMongoCollection();
        if(sessionDocument.getSessionId() != null){
            List<Bson> bsonList = new ArrayList<>();
            Map<String,Object> originMap = sessionDocument.getAttribute();
            originMap.forEach((key,value)->{
                bsonList.add(Updates.set(key,value));
            });
            collection.updateOne(Filters.eq("_id",new ObjectId(sessionDocument.getSessionId())),Updates.combine(bsonList));
        }else{
            Document document = new Document();
            Map<String,Object> originMap = sessionDocument.getAttribute();
            originMap.forEach((key,value)->{
                document.append(key,value);
            });
            collection.insertOne(document);
            sessionDocument.setSessionId(document.getObjectId("_id").toHexString());
        }
    }

    @Override
    public void removeSession(String sessionId) {
        MongoCollection collection = getMongoCollection();
        collection.deleteMany(Filters.eq("_id",new ObjectId(sessionId)));
    }

    @Override
    public boolean loadSession(SessionDocument sessionDocument) {
        MongoCollection collection = getMongoCollection();
        try{
            FindIterable<Document> iterable = collection.find(Filters.eq("_id",new ObjectId(sessionDocument.getSessionId())));
            boolean findSession = false;
            for(Document document : iterable){
                findSession = true;
                sessionDocument.setSessionId(document.getObjectId("_id").toHexString());
                document.forEach((key,value)->{
                    sessionDocument.getAttribute().put(key,value);
                });
            }
            return findSession;
        }catch (Exception e){
            /** 可能加载一个非ObjectId类型的Cookie导致主键异常因此重新初始化Session **/
            return false;
        }
    }

}
