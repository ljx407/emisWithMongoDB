package com.demo.utils;

import java.security.InvalidParameterException;

/**
 * Created by jasonliu on 2017/2/19.
 */
public class ConvertUtil {
    public static String getString(Object obj) {
        if(obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static int getInt(Object obj) {
        if(obj == null) {
            throw new NumberFormatException();
        }
        return Integer.valueOf(getString(obj));
    }
}
