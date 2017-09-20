package com.demo.servlet;

import com.demo.javabean.mysqlDBImpl.DBAccess;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitServlet extends HttpServlet {

	public final static long serialVersionUID = 0;

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		String drv = conf.getInitParameter("drv");
		String url = conf.getInitParameter("url");
		String usr = conf.getInitParameter("usr");
		String pwd = conf.getInitParameter("pwd");
		DBAccess.drv = drv;
		DBAccess.url = url;
		DBAccess.usr = usr;
		DBAccess.pwd = pwd;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		doPost(request, response);
	}

}
