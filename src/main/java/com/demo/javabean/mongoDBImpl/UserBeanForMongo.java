package com.demo.javabean.mongoDBImpl;

import com.demo.constant.EmisConstant;
import com.demo.javabean.UserDAO;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * Created by jasonliu on 2017/1/10.
 */
public class UserBeanForMongo implements UserDAO{

    private final String USERS_COLLECTION = "users";

    @Override
    public boolean valid(String username, String password) {
        MongoClient mongoClient = new MongoClient("localhost",27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(EmisConstant.DB_DATABASE_EMIS);
        MongoCollection users = mongoDatabase.getCollection(USERS_COLLECTION);
        long count = users.count(and(eq("username",username),eq("password",password)));
        return count > 0 ;
    }

    @Override
    public boolean isExist(String username) {
        MongoClient mongoClient = new MongoClient("localhost",27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(EmisConstant.DB_DATABASE_EMIS);
        MongoCollection<Document> users = mongoDatabase.getCollection(USERS_COLLECTION);
        long count = users.count(eq("username", username));

        return count > 0;
    }

    @Override
    public void add(String username, String password, String email) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase(EmisConstant.DB_DATABASE_EMIS);
        MongoCollection<Document> users = database.getCollection(USERS_COLLECTION);

//        Document document = new Document();
//        document.put("username",username);
//        document.put("password",password);
//        document.put("email",email);

        Document documentNew = new Document()
                .append("username",username)
                .append("password",password)
                .append("email",email);

        users.insertOne(documentNew);
    }
}
