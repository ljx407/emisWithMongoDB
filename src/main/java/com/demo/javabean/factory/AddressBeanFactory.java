package com.demo.javabean.factory;

import com.demo.constant.EmisConstant;
import com.demo.javabean.AddressDAO;
import com.demo.javabean.mongoDBImpl.AddressBeanForMongo;
import com.demo.javabean.mysqlDBImpl.AddressBean;

import java.security.InvalidParameterException;

/**
 * Created by jasonliu on 2017/2/14.
 */
public class AddressBeanFactory {
    private AddressBeanFactory(){}

    private static AddressDAO addressDAO = null;

    public static AddressDAO newInstance(String dbtype) {
        if(addressDAO == null) {
            if (EmisConstant.DB_TYPE_MONGODB.equals(dbtype)) {
                addressDAO = new AddressBeanForMongo();
            } else if(EmisConstant.DB_TYPW_MYSQL.equals(dbtype)) {
                addressDAO = new AddressBean();
            } else {
                throw new InvalidParameterException();
            }
        }
        return addressDAO ;
    }
}
