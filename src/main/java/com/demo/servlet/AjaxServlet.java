package com.demo.servlet;

import com.demo.constant.EmisConstant;
import com.demo.javabean.UserDAO;
import com.demo.javabean.factory.UserBeanFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxServlet extends HttpServlet {

	public final static long serialVersionUID = 0;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		response.setContentType("text/xml");
		response.setHeader("Cache-Control","no-cache");

		String dbsource = String.valueOf(getServletContext().getInitParameter("dbsource"));
		dbsource = dbsource == null ? EmisConstant.DB_TYPE_MONGODB : dbsource ;

		String username = request.getParameter("username");

		UserDAO userBean = UserBeanFactory.newInstance(dbsource);
		boolean isExist = userBean.isExist(username);
		
		PrintWriter out = response.getWriter();
		if(isExist) {
			out.print("<content>failure</content>");
		}else{
			out.print("<content>ok</content>");
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
