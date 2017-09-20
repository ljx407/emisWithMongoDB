package com.demo.javabean.factory;

import com.demo.constant.EmisConstant;
import com.demo.javabean.UserDAO;
import com.demo.javabean.mongoDBImpl.UserBeanForMongo;
import com.demo.javabean.mysqlDBImpl.UserBean;

import javax.servlet.ServletException;

/**
 * Created by jasonliu on 2017/1/10.
 */
public class UserBeanFactory {

    public static UserDAO userDao = null ;
    private UserBeanFactory(){}

    public static UserDAO newInstance(String dbtype) throws ServletException {
        if(dbtype == null) {
            throw new ServletException("输入参数有误！");
        }

        if(userDao == null) {
            if (EmisConstant.DB_TYPW_MYSQL.equals(dbtype)) {
                userDao = new UserBean();
            } else if (EmisConstant.DB_TYPE_MONGODB.equals(dbtype)) {
                userDao = new UserBeanForMongo();
            } else {
                throw new ServletException("输入参数有误！");
            }
        }
        return userDao ;
    }
}
