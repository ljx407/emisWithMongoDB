package com.demo.javabean;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jasonliu on 2017/9/26.
 */
public interface SmsDAO {

    /**
     * 获取列表
     * @param request
     * @param username
     * @param strPageSize
     * @param strPageNo
     * @return
     */
    boolean list(HttpServletRequest request, String username, String strPageSize,
                 String strPageNo);

    /**
     * 删除
     * @param request
     * @param username
     * @return
     */
    boolean delete(HttpServletRequest request, String username);

    /**
     * 插入
     * @param request
     * @param username
     * @return
     */
    boolean insert(HttpServletRequest request, String username);

    /**
     * (4)阅读函数
     * @param request
     * @param username
     * @return
     */
    boolean read(HttpServletRequest request, String username);
}