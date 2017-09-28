package com.demo.javabean.mongoDBImpl;

import com.demo.javabean.SmsDAO;
import com.demo.utils.ConvertUtil;
import com.demo.utils.PageUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

import static com.demo.constant.EmisConstant.*;

/**
 * Created by Jasonliu on 2017/9/26.
 */
public class SmsBeanForMongo implements SmsDAO {

    private final String SMS_COLLECTION = "sms";

    @Override
    public boolean list(HttpServletRequest request, String username, String strPageSize, String strPageNo) {
        MongoClient mongoClient = new MongoClient(DB_ADDRESS_DEFAULT, DB_PORT_DEFAULT);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(DB_DATABASE_EMIS);
        MongoCollection<Document> smsCollection = mongoDatabase.getCollection(this.SMS_COLLECTION);

        long count = smsCollection.count(Filters.eq("username", username));
        int rowCount = ConvertUtil.getInt(count);
        int pageSize = ConvertUtil.getInt(strPageSize);
        int pageNo = ConvertUtil.getInt(strPageNo);
        PageUtil.calPage(request,rowCount,pageSize,pageNo);

        int start = pageSize * (pageNo - 1) ;
        FindIterable<Document> smsList = smsCollection.find(Filters.eq("username", username))
                .skip(start)
                .limit(pageSize)
                .sort(Sorts.descending("sendtime"));

        MongoIterable<Map<String, String>> convertResult = smsList.map(this::convertDocToMap);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Consumer<Map<String,String>> consumer = list::add;
        convertResult.forEach(consumer);

        // 保存所有行数据列表传递给下一个页面
        request.setAttribute("list", list);

        return true;

    }

    private Map<String,String> convertDocToMap(Document doc) {
        Map<String,String> hash = new HashMap<>();
        hash.put("id", ConvertUtil.getString(doc.get("_id")));
        hash.put("username", ConvertUtil.getString(doc.get("username")));
        hash.put("sender", ConvertUtil.getString(doc.get("sender")));
        hash.put("message", ConvertUtil.getString(doc.get("message")));
        hash.put("sendtime", ConvertUtil.getString(doc.get("sendtime")));
        hash.put("isRead", ConvertUtil.getString(doc.get("isRead")));
        return hash ;

    }

    @Override
    public boolean delete(HttpServletRequest request, String username) {
        MongoClient mongoClient = new MongoClient(DB_ADDRESS_DEFAULT, DB_PORT_DEFAULT);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(DB_DATABASE_EMIS);
        MongoCollection<Document> smsCollection = mongoDatabase.getCollection(this.SMS_COLLECTION);

        // 根据id组成删除SQL，执行删除
        String id = request.getParameter("id");
        Document deleteParam = new Document()
                .append("_id",new ObjectId(id));
        smsCollection.deleteOne(deleteParam);

        return true;
    }

    @Override
    public boolean insert(HttpServletRequest request, String username) {
        MongoClient mongoClient = new MongoClient(DB_ADDRESS_DEFAULT, DB_PORT_DEFAULT);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(DB_DATABASE_EMIS);
        MongoCollection<Document> smsCollection = mongoDatabase.getCollection(this.SMS_COLLECTION);

        // 取得新增表单参数
        String to = request.getParameter("username");
        String message = request.getParameter("message");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sendtime = format.format(new Date());

        Document insertParam = new Document().append("username",to)
                .append("sender",username)
                .append("message",message)
                .append("sendtime",sendtime)
                .append("isRead","0");

        smsCollection.insertOne(insertParam);
        return true;
    }

    @Override
    public boolean read(HttpServletRequest request, String username) {
        MongoClient mongoClient = new MongoClient(DB_ADDRESS_DEFAULT,DB_PORT_DEFAULT);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(DB_DATABASE_EMIS);
        MongoCollection<Document> smsCollection = mongoDatabase.getCollection(this.SMS_COLLECTION);
        // 根据id组成删除SQL，执行更新
        String id = request.getParameter("id");

        Document conditionParam = new Document()
                .append("_id",new ObjectId(id));

        Document updateParam = new Document()
                .append("isRead","1");

        smsCollection.updateOne(conditionParam,new Document("$set",updateParam));

        return true;
    }

}
