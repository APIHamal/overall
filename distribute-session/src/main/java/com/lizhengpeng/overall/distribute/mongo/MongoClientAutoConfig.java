package com.lizhengpeng.overall.distribute.mongo;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * MongoDB客户端自动配置对象
 */
@Configuration
public class MongoClientAutoConfig {

    /**
     * 该配置类主要用于提供MongodbClient对象
     * @param mongoProperties
     * @return
     */
    @Bean
    public MongoClient mongoClient(MongoProperties mongoProperties) {
        MongoClientSettings.Builder builder = MongoClientSettings.builder();
        List<ServerAddress> serverAddressList = new ArrayList<>();
        String[] hostArray = mongoProperties.getHost().split(",");
        for (String host : hostArray) {
            serverAddressList.add(new ServerAddress(host, mongoProperties.getPort()));
        }
        builder.applyToClusterSettings(settingBuilder -> settingBuilder.hosts(serverAddressList));
        MongoCredential credential = MongoCredential.createCredential(mongoProperties.getUsername(), mongoProperties.getDatabase(), mongoProperties.getPassword());
        builder.credential(credential);
        builder.applyToConnectionPoolSettings(poolBuilder -> {
            poolBuilder.minSize(32);
            poolBuilder.maxSize(64);
            poolBuilder.maxConnectionIdleTime(60, TimeUnit.SECONDS);
            poolBuilder.maxConnectionLifeTime(10, TimeUnit.MINUTES);
        });
        builder.applyToSocketSettings(socketBuilder -> {
            socketBuilder.connectTimeout(5, TimeUnit.SECONDS);
            socketBuilder.readTimeout(10, TimeUnit.SECONDS);
        });
        builder.writeConcern(WriteConcern.MAJORITY);
        builder.readPreference(ReadPreference.secondaryPreferred());
        MongoClient mongoClient = MongoClients.create(builder.build());
        return mongoClient;
    }

}
