package com.demo.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jasonliu on 2017/2/19.
 */
public class PageUtil {
    public static void calPage(HttpServletRequest request,int rowCount, int pageSize, int pageNo) {

        request.setAttribute("rowCount", rowCount);
        //计算总页数并保存
        int pageCount = rowCount % pageSize == 0 ? rowCount / pageSize : rowCount / pageSize + 1;
        request.setAttribute("pageCount", pageCount);

        //计算跳页参数并保存
        int pageFirstNo = 1;// 首页
        int pageLastNo = pageCount;//尾页
        int pagePreNo = pageNo > 1 ? pageNo - 1 : 1;// 前一页
        int pageNextNo = pageNo < pageCount ? pageNo + 1 : pageCount;// 后一页
        request.setAttribute("pageFirstNo", pageFirstNo);
        request.setAttribute("pageLastNo", pageLastNo);
        request.setAttribute("pagePreNo", pagePreNo);
        request.setAttribute("pageNextNo", pageNextNo);
    }
}
