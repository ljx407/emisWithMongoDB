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

<title>企业信息管理系统 - 通讯录管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="企业,信息,管理">
<meta http-equiv="description" content="企业信息管理系统 - 通讯录管理">
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
				<TD>当前位置：通讯录管理</TD>
				<TD align="right"><a
					href="address.do?method=add&pageSize=${requestScope.pageSize}&pageNo=${requestScope.pageNo}">新增联系人</a></TD>
				<TD width="20"></TD>
			</TR>
		</TABLE>
		<b></b>
		<TABLE border="0" width="100%">
			<TR class="tableheader">
				<TD>姓名</TD>
				<TD>性别</TD>
				<TD>手机</TD>
				<TD>Email</TD>
				<TD>QQ号码</TD>
				<TD>工作单位</TD>
				<TD>地址</TD>
				<TD>邮编</TD>
				<TD>操作</TD>
			</TR>
			<c:forEach items="${requestScope.list}" var="hash">
				<TR>
					<TD>${hash.name}</TD>
					<TD>${hash.sex}</TD>
					<TD>${hash.mobile}</TD>
					<TD>${hash.email}</TD>
					<TD>${hash.qq}</TD>
					<TD>${hash.company}</TD>
					<TD>${hash.address}</TD>
					<TD>${hash.postcode}</TD>
					<TD><a
						href='address.do?method=edit&id=${hash.id}&pageSize=${requestScope.pageSize}&pageNo=${requestScope.pageNo}'>修改</a>
					<a
						href="address.do?method=delete&id=${hash.id}&pageSize=${requestScope.pageSize}&pageNo=${requestScope.pageNo}">删除</a></TD>
				</TR>
			</c:forEach>
		</TABLE>


		<emis:pager action="address.do?method=list"
			pageSize="${requestScope.pageSize}"
			pageNo="${requestScope.pageNo}"
			rowCount="${requestScope.rowCount}" 
			pageCount="${requestScope.pageCount}" 
			pageFirstNo="${requestScope.pageFirstNo}" 
			pagePreNo="${requestScope.pagePreNo}" 
			pageNextNo="${requestScope.pageNextNo}"
			pageLastNo="${requestScope.pageLastNo}"  />
<!-- 
		<form name="form1" action="address.do?method=list" method="post">
		<TABLE border="0" width="100%" class="pager">
			<TR>
				<TD align="left">每页记录数： <select name="pageSize"
					onchange="document.all.pageNo.value='1';document.all.form1.submit();">
					<option value="10"
						<c:if test="${requestScope.pageSize}==10">selected</c:if>>10</option>
					<option value="25"
						<c:if test="${requestScope.pageSize}==25">selected</c:if>>25</option>
					<option value="50"
						<c:if test="${requestScope.pageSize}==50">selected</c:if>>50</option>
					<option value="100"
						<c:if test="${requestScope.pageSize}==100">selected</c:if>>100</option>
					<option value="200"
						<c:if test="${requestScope.pageSize}==200">selected</c:if>>200</option>
					<option value="300"
						<c:if test="${requestScope.pageSize}==300">selected</c:if>>300</option>
					<option value="500"
						<c:if test="${requestScope.pageSize}==500">selected</c:if>>500</option>
				</select></TD>
				<TD align="center">总记录数：${requestScope.rowCount}</TD>
				<TD align="right"><a
					href="javascript:document.all.pageNo.value='${requestScope.pageFirstNo}';document.all.form1.submit();">首页</a>
				<a
					href="javascript:document.all.pageNo.value='${requestScope.pagePreNo}';document.all.form1.submit();">前一页</a>
				<a
					href="javascript:document.all.pageNo.value='${requestScope.pageNextNo}';document.all.form1.submit();">后一页</a>
				<a
					href="javascript:document.all.pageNo.value='${requestScope.pageLastNo}';document.all.form1.submit();">尾页</a>
				<select name="pageNo" onchange="document.all.form1.submit();">
					<c:forEach var="i" begin="1" end="${requestScope.pageCount}">
						<option value="${i}"
							<c:if test="${i}==${requestScope.pageNo}">selected</c:if>>${i}</option>
					</c:forEach>
				</select></TD>
				<TD width="20"></TD>
			</TR>
		</TABLE>
		</form> -->
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
