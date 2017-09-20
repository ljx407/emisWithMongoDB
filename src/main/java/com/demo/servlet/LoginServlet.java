package com.demo.servlet;

import com.demo.constant.EmisConstant;
import com.demo.javabean.UserDAO;
import com.demo.javabean.factory.UserBeanFactory;
import com.demo.javabean.mysqlDBImpl.UserBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    public final static long serialVersionUID = 0;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String dbsource = getServletContext().getInitParameter("dbsource");
        dbsource = dbsource == null ? EmisConstant.DB_TYPE_MONGODB : dbsource ;

        // 取得参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 验证登录
        UserDAO userBean = UserBeanFactory.newInstance(dbsource);
        boolean isValid = userBean.valid(username, password);

        if (isValid) {
            session.setAttribute("username", username);
            response.sendRedirect("welcome.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    // 访问mysql数据库
    private boolean accessWithMysql(String username, String password) {
        // 验证登录
        UserBean userBean = new UserBean();
        return userBean.valid(username, password);
    }

}
