package com.demo.javabean.mongoDBImpl;

import com.demo.constant.EmisConstant;
import com.demo.javabean.AddressDAO;
import com.demo.utils.ConvertUtil;
import com.demo.utils.PageUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;

/**
 * Created by jasonliu on 2017/2/14.
 */
public class AddressBeanForMongo implements AddressDAO {

    private final String ADDRESS_COLLECTION = "address";

    public boolean list(HttpServletRequest request, String username, String strPageSize, String strPageNo) {

        MongoClient mongoClient = new MongoClient(EmisConstant.DB_ADDRESS_DEFAULT, EmisConstant.DB_PORT_DEFAULT);
        MongoDatabase database = mongoClient.getDatabase(EmisConstant.DB_DATABASE_EMIS);
        MongoCollection<Document> addresses = database.getCollection(ADDRESS_COLLECTION);

        int pageSize = Integer.parseInt(strPageSize);
        int pageNo = Integer.parseInt(strPageNo);
        int start = pageSize * (pageNo - 1);

        long addressSize = addresses.count(eq("username", username));
        int rowCount = ConvertUtil.getInt(addressSize);

        PageUtil.calPage(request,rowCount,pageSize,pageNo);

        FindIterable<Document> result = addresses.find(eq("username", username)).skip(start).limit(pageSize).sort(ascending("name"));
        MongoCursor<Document> iterator = result.iterator();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        while (iterator.hasNext()) {
            Document next = iterator.next();

            // 查询每行数据的各个字段数据
            Map<String, String> hash = new HashMap<String, String>();
            hash.put("id", String.valueOf(next.get("_id")));
            hash.put("username", String.valueOf(next.get("username")));
            hash.put("name", String.valueOf(next.get("name")));
            hash.put("sex", String.valueOf(next.get("sex")));
            hash.put("mobile", String.valueOf(next.get("mobile")));
            hash.put("email", String.valueOf(next.get("email")));
            hash.put("qq", String.valueOf(next.get("qq")));
            hash.put("company", String.valueOf(next.get("company")));
            hash.put("address", String.valueOf(next.get("address")));
            hash.put("postcode", String.valueOf(next.get("postcode")));
            // 保存当前行
            list.add(hash);
        }
        // 保存所有行数据列表传递给下一个页面
        request.setAttribute("list", list);

        return false;
    }

    public boolean delete(HttpServletRequest request, String username) {

        MongoClient mongoClient = new MongoClient(EmisConstant.DB_ADDRESS_DEFAULT, EmisConstant.DB_PORT_DEFAULT);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(EmisConstant.DB_DATABASE_EMIS);
        MongoCollection<Document> addressCollection = mongoDatabase.getCollection(this.ADDRESS_COLLECTION);

        // 根据id组成删除SQL，执行删除
        String id = request.getParameter("id");
        DeleteResult deleteResult = addressCollection.deleteOne(eq("_id", new ObjectId(id)));
        return deleteResult.getDeletedCount() == 1;
    }

    public boolean insert(HttpServletRequest request, String username) {

        // 连接数据库
        MongoClient mongoClient = new MongoClient(EmisConstant.DB_ADDRESS_DEFAULT, EmisConstant.DB_PORT_DEFAULT);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(EmisConstant.DB_DATABASE_EMIS);
        MongoCollection<Document> addressCollections = mongoDatabase.getCollection(this.ADDRESS_COLLECTION);

        // 从前台获取得新增表单参数
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String qq = request.getParameter("qq");
        String company = request.getParameter("company");
        String address = request.getParameter("address");
        String postcode = request.getParameter("postcode");

        // 拼装sql
        Document addressDoc = new Document();
        addressDoc.put("name",name);
        addressDoc.put("username",username);
        addressDoc.put("sex",sex);
        addressDoc.put("mobile",mobile);
        addressDoc.put("email",email);
        addressDoc.put("qq",qq);
        addressDoc.put("company",company);
        addressDoc.put("address",address);
        addressDoc.put("postcode",postcode);

        // 执行脚本
        addressCollections.insertOne(addressDoc);

        return true;
    }

    public boolean select(HttpServletRequest request, String username) {
        // 连接数据库
        MongoClient mongoClient = new MongoClient(EmisConstant.DB_ADDRESS_DEFAULT, EmisConstant.DB_PORT_DEFAULT);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(EmisConstant.DB_DATABASE_EMIS);
        MongoCollection<Document> addressCollections = mongoDatabase.getCollection(this.ADDRESS_COLLECTION);

        // 获取查询数据id
        String id = request.getParameter("id");
        // 拼接sql并执行
        FindIterable<Document> addressResult = addressCollections.find(eq("_id", new ObjectId(id)));
        Document first = addressResult.first();

        // 取得各个字段的值并保存
        request.setAttribute("id", first.get("_id"));
        request.setAttribute("username", first.get("username"));
        request.setAttribute("name", first.get("name"));
        request.setAttribute("sex", first.get("sex"));
        request.setAttribute("mobile", first.get("mobile"));
        request.setAttribute("email", first.get("email"));
        request.setAttribute("qq", first.get("qq"));
        request.setAttribute("company", first.get("company"));
        request.setAttribute("address", first.get("address"));
        request.setAttribute("postcode", first.get("postcode"));
        return true;
    }

    public boolean update(HttpServletRequest request, String username) {
        // 连接数据库
        MongoClient mongoClinet = new MongoClient(EmisConstant.DB_ADDRESS_DEFAULT,EmisConstant.DB_PORT_DEFAULT);
        MongoDatabase mongoDatabase = mongoClinet.getDatabase(EmisConstant.DB_DATABASE_EMIS);
        MongoCollection<Document> addressCollections = mongoDatabase.getCollection(this.ADDRESS_COLLECTION);

        // 获取前台传入表单更新数据
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String qq = request.getParameter("qq");
        String company = request.getParameter("company");
        String address = request.getParameter("address");
        String postcode = request.getParameter("postcode");
        // 拼接sql并执行
        Document filter = new Document();
        filter.put("_id",new ObjectId(id));

        Document addressDoc = new Document();
        addressDoc.append("name",name)
                  .append("sex",sex)
                  .append("mobile",mobile)
                  .append("email",email)
                  .append("qq",qq)
                  .append("company",company)
                  .append("address",address)
                  .append("postcode",postcode);

        addressCollections.updateOne(filter, new Document("$set",addressDoc));
        return true;
    }
}
