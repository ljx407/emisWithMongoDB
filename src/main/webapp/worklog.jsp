<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="emis" uri="emis"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>企业信息管理系统 - 工作记录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="企业,信息,管理">
<meta http-equiv="description" content="企业信息管理系统 - 工作记录">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="css/styles.css">

</head>
<body>
<div align="center">

<table height="100%" width="100%" border="0" cellspacing="0"
	cellpadding="0">
	<tr>
		<td colspan="2" height="108">
		<table height="108" background="images/banner.jpg" border="0"
			cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td width="160" bgcolor="#EEEEEE" valign="top" height="100%"><%@ include
			file="inc/menu.jsp"%></td>
		<td align="left" valign="top">
		<TABLE width="100%" class="position">
			<TR>
				<TD>当前位置：工作记录</TD>
				<TD align="right"><a href="worklog.do?method=add&pageSize=${requestScope.pageSize}&pageNo=${requestScope.pageNo}">新增工作记录</a></TD>
				<TD width="20"></TD>
			</TR>
		</TABLE>
		<b></b>
		<TABLE border="0" width="100%">
			<TR class="tableheader">
				<TD>年份</TD>
				<TD>月份</TD>
				<TD>日期</TD>
				<TD>日志标题</TD>
				<TD>日志内容</TD>
				<TD>记录时间</TD>
				<TD>操作</TD>
			</TR>
			<c:forEach items="${requestScope.list}" var="hash">
				<TR>
					<TD>${hash.year}</TD>
					<TD>${hash.month}</TD>
					<TD>${hash.day}</TD>
					<TD>${hash.title}</TD>
					<TD>${hash.description}</TD>
					<TD>${hash.logtime}</TD>
					<TD><a href='worklog.do?method=edit&id=${hash.id}&pageSize=${requestScope.pageSize}&pageNo=${requestScope.pageNo}'>修改</a>
					<a href="schedule.do?method=delete&id=${hash.id}&pageSize=${requestScope.pageSize}&pageNo=${requestScope.pageNo}">删除</a></TD>
				</TR>
			</c:forEach>
		</TABLE>

		<emis:pager action="worklog.do?method=list"
			pageSize="${requestScope.pageSize}"
			pageNo="${requestScope.pageNo}"
			rowCount="${requestScope.rowCount}" 
			pageCount="${requestScope.pageCount}" 
			pageFirstNo="${requestScope.pageFirstNo}" 
			pagePreNo="${requestScope.pagePreNo}" 
			pageNextNo="${requestScope.pageNextNo}"
			pageLastNo="${requestScope.pageLastNo}"  />
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center"><%@ include file="inc/foot.jsp"%>
		</td>
	</tr>
</table>
</div>
</body>
</html>
