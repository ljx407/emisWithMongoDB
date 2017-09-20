package com.demo.taglib;

import java.io.IOException;
import javax.servlet.jsp.tagext.TagSupport;

public class PagerTag extends TagSupport {

	private String action = null;
	// 取得分页参数
	private int pageSize = 25;//页大小
	private int pageNo = 1;//页码
	private int rowCount = 0;//行数
	private int pageCount = 1;//页数
	private int pageFirstNo = 1;//首页
	private int pagePreNo = 1;//前一页
	private int pageNextNo = 1;//后一页
	private int pageLastNo = 1;//尾页

	private static final long serialVersionUID = 1L;

	public int doStartTag() {

		StringBuffer buffer = new StringBuffer();

		// 输出表单开头
		buffer.append("\r\n<form name=\"form1\" action=\"").append(action)
				.append("\" method=\"post\">");
		buffer
				.append("\r\n<TABLE border=\"0\" width=\"100%\" class=\"pager\">");
		buffer.append("\r\n	<TR>");
		buffer.append("\r\n		<TD align=\"left\">每页记录数：");

		// 输出每页记录数下拉列表
		buffer
				.append("\r\n		<select name=\"pageSize\" onchange=\"document.all.pageNo.value='1';document.all.form1.submit();\">");
		int[] pageSizeIndexs = { 10, 25, 50, 100, 200, 300, 500 };
		for (int i = 0; i < pageSizeIndexs.length; i++) {
			int curvalue = pageSizeIndexs[i];
			// 没有被选中
			if (curvalue != pageSize) {
				buffer.append("\r\n			<option value=\"").append(curvalue)
						.append("\">").append(curvalue).append("</option>");
			} else {
				// 被选中，输出selected
				buffer.append("\r\n			<option value=\"").append(curvalue)
						.append("\" selected=\"selected\">").append(curvalue)
						.append("</option>");
			}
		}
		buffer.append("\r\n		</select></TD>");
		buffer.append("\r\n		<TD align=\"center\">总记录数：").append(rowCount)
				.append("</TD>");

		// 输出分页下拉列表
		buffer.append("\r\n		<TD align=\"right\">");
		buffer.append("\r\n		<a href=\"javascript:document.all.pageNo.value='")
				.append(pageFirstNo).append(
						"';document.all.form1.submit();\">首页</a>");
		buffer.append("\r\n		<a href=\"javascript:document.all.pageNo.value='")
				.append(pagePreNo).append(
						"';document.all.form1.submit();\">前一页</a>");
		buffer.append("\r\n		<a href=\"javascript:document.all.pageNo.value='")
				.append(pageNextNo).append(
						"';document.all.form1.submit();\">后一页</a>");
		buffer.append("\r\n		<a href=\"javascript:document.all.pageNo.value='")
				.append(pageLastNo).append(
						"';document.all.form1.submit();\">尾页</a>");
		buffer
				.append("\r\n		<select name=\"pageNo\" onchange=\"this.form.submit();\">");
		for (int i = 1; i <= pageCount; i++) {
			// 没有被选中
			if (i != pageNo) {
				buffer.append("\r\n			<option value=\"").append(i)
						.append("\">").append(i).append("</option>");
			} else {
				// 被选中，输出selected
				buffer.append("\r\n			<option value=\"").append(i).append(
						"\" selected=\"selected\">").append(i).append(
						"</option>");
			}
		}

		buffer.append("\r\n		</select>");
		buffer.append("\r\n		</TD>");
		buffer.append("\r\n		<TD width=\"20\"></TD>");
		buffer.append("\r\n	</TR>");
		buffer.append("\r\n</TABLE>");
		buffer.append("\r\n</form>");

		// 输出HTML结果
		try {
			pageContext.getOut().print(buffer.toString());
		} catch (IOException ioe) {
			System.out.println("Error in PagerTag: " + ioe);
		}

		// 必须要赋空值，否则会被缓存
		action = null;
		return (SKIP_BODY);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageFirstNo() {
		return pageFirstNo;
	}

	public void setPageFirstNo(int pageFirstNo) {
		this.pageFirstNo = pageFirstNo;
	}

	public int getPagePreNo() {
		return pagePreNo;
	}

	public void setPagePreNo(int pagePreNo) {
		this.pagePreNo = pagePreNo;
	}

	public int getPageNextNo() {
		return pageNextNo;
	}

	public void setPageNextNo(int pageNextNo) {
		this.pageNextNo = pageNextNo;
	}

	public int getPageLastNo() {
		return pageLastNo;
	}

	public void setPageLastNo(int pageLastNo) {
		this.pageLastNo = pageLastNo;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}
