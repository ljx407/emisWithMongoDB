package com.demo.javabean;

/**
 * Created by jasonliu on 2017/1/10.
 */
public interface UserDAO {
    boolean valid(String username, String password);
    boolean isExist(String username);
    void add(String username, String password, String email);
}
