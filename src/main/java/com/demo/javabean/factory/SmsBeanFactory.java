package com.demo.javabean.factory;

import com.demo.constant.EmisConstant;
import com.demo.javabean.SmsDAO;
import com.demo.javabean.mongoDBImpl.SmsBeanForMongo;
import com.demo.javabean.mysqlDBImpl.SmsBean;

import javax.servlet.ServletException;

/**
 * Created by Jasonliu on 2017/9/26.
 */
public class SmsBeanFactory {

    public static SmsDAO smsDao = null ;
    private SmsBeanFactory(){}

    public static SmsDAO newInstance(String dbtype) throws ServletException {
        if(dbtype == null) {
            throw new ServletException("输入参数有误！");
        }

        if(smsDao == null) {
            if (EmisConstant.DB_TYPW_MYSQL.equals(dbtype)) {
                smsDao = new SmsBean();
            } else if (EmisConstant.DB_TYPE_MONGODB.equals(dbtype)) {
                smsDao = new SmsBeanForMongo();
            } else {
                throw new ServletException("输入参数有误！");
            }
        }
        return smsDao ;
    }
}
