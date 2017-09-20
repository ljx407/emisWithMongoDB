package com.demo.servlet;

import com.demo.constant.EmisConstant;
import com.demo.javabean.UserDAO;
import com.demo.javabean.factory.UserBeanFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

	public final static long serialVersionUID = 0;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 取得参数
		String username = request.getParameter("username");
		String password1 = request.getParameter("password1");
		String email = request.getParameter("email");

		String dbsource = getServletContext().getInitParameter("dbsource");
		dbsource = dbsource == null ? EmisConstant.DB_TYPE_MONGODB : dbsource;

		// 注册用户
		UserDAO userBean = UserBeanFactory.newInstance(dbsource);
		boolean isExist = userBean.isExist(username);
		if(!isExist) {
			userBean.add(username, password1, email);
			response.sendRedirect("login.jsp");
		} else {
			response.sendRedirect("register.jsp");
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
