package com.demo.javabean.mysqlDBImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ScheduleBean {
	/**
	 * (1)列表函数
	 * @param request
	 * @param username
	 * @param strPageSize
	 * @param strPageNo
	 * @return
	 */
	public boolean list(HttpServletRequest request, String username, String strPageSize,
			String strPageNo) {
		// 创建数据库连接
		DBAccess db = new DBAccess();
		if(db.createConn()) {

			// 查询总的记录数，计算跳页参数
			int pageSize = Integer.parseInt(strPageSize);
			int pageNo = Integer.parseInt(strPageNo);
			int start = pageSize * (pageNo - 1);
			// 总记录数查询SQL
			String sql1 = "select count(*) as countall from schedule where username='"
				+ username + "'";
			db.query(sql1);
			if (db.next()) {
				//计算总行数并保存
				String countall = db.getValue("countall");
				int rowCount = Integer.parseInt(countall);
				request.setAttribute("rowCount", rowCount);

				//计算总页数并保存
				int pageCount = rowCount % pageSize == 0 ? rowCount / pageSize
						: rowCount / pageSize + 1;
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
			db.closeRs();

			// 取得当前页数据SQL
			String sql2 = "select * from schedule where username='" + username
			+ "' order by year desc, month desc, day desc limit "
			+ start + "," + pageSize;
			List<Hashtable<String, String>> list = new ArrayList<Hashtable<String, String>>();
			db.query(sql2);
			while (db.next()) {
				// 查询每行数据的各个字段数据
				Hashtable<String, String> hash = new Hashtable<String, String>();				
				hash.put("id", db.getValue("id"));
				hash.put("username", db.getValue("username"));
				hash.put("year", db.getValue("year"));
				hash.put("month", db.getValue("month"));
				hash.put("day", db.getValue("day"));
				hash.put("plan", db.getValue("plan"));
				
				// 保存当前行
				list.add(hash);
			}
			// 保存所有行数据列表传递给下一个页面
			request.setAttribute("list", list);
			db.closeRs();
			db.closeStm();
			db.closeConn();
		}
		return true;
	}
	
	/**
	 * (2)删除函数
	 * @param request
	 * @param username
	 * @return
	 */
	public boolean delete(HttpServletRequest request, String username) {
		// 创建数据库连接
		DBAccess db = new DBAccess();
		if(db.createConn()) {

			// 根据id组成删除SQL，执行删除
			String id = request.getParameter("id");
			String sql = "delete from schedule where id=" + id;
			db.update(sql);
			db.closeStm();
			db.closeConn();
		}
		return true;
	}
	
	/**
	 * (3)新增函数
	 * @param request
	 * @param username
	 * @return
	 */
	public boolean insert(HttpServletRequest request, String username) {
		// 创建数据库连接
		DBAccess db = new DBAccess();
		if(db.createConn()) {

			// 取得新增表单参数
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String plan = request.getParameter("plan");

			// 组合新增SQL
			String sql = "insert into schedule (username, year, month, day, plan) ";
			sql += " values('" + username + "'," + year + "," + month + ","
					+ day + ",'" + plan + "')";

			// 转换参数编码
//			try {
//				sql = new String(sql.getBytes("ISO8859-1"), "UTF-8");
//			} catch (Exception e) {
//				return false;
//			}

			// 执行插入
			db.update(sql);
			db.closeStm();
			db.closeConn();
		}
		return true;
	}
	
	/**
	 * (4)查询函数
	 * @param request
	 * @param username
	 * @return
	 */
	public boolean select(HttpServletRequest request, String username) {
		// 创建数据库连接
		DBAccess db = new DBAccess();
		if(db.createConn()) {

			// 根据id编号查询当前行记录
			String id = request.getParameter("id");
			String sql = "select * from schedule where id=" + id;
			db.query(sql);
			if (db.next()) {
				// 取得各个字段的值并保存
				request.setAttribute("id", db.getValue("id"));
				request.setAttribute("username", db.getValue("username"));
				request.setAttribute("year", db.getValue("year"));
				request.setAttribute("month", db.getValue("month"));
				request.setAttribute("day", db.getValue("day"));
				request.setAttribute("plan", db.getValue("plan"));
			}
			db.closeRs();
			db.closeStm();
			db.closeConn();
		}
		return true;
		
	}
	
	/**
	 * (5)更新函数
	 * @param request
	 * @param username
	 * @return
	 */
	public boolean update(HttpServletRequest request, String username) {
		// 创建数据库连接
		DBAccess db = new DBAccess();
		if(db.createConn()) {

			// 取得修改页表单参数
			String id = request.getParameter("id");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String plan = request.getParameter("plan");

			// 组合更新SQL
			String sql = "update schedule set year=" + year + ", month="
					+ month + ", day=" + day + ", plan='" + plan
					+ "' where id=" + id;

			// 转换参数编码
//			try {
//				sql = new String(sql.getBytes("ISO8859-1"), "UTF-8");
//			} catch (Exception e) {
//				return false;
//			}
			
			// 执行更新
			db.update(sql);
			db.closeStm();
			db.closeConn();
		}
		return true;
		
	}
}
