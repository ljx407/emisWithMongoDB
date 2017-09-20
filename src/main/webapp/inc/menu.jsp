<%@ page language="java" pageEncoding="UTF-8"%>
<table width="100%" bgcolor="#EEEEEE">
	<tr>
		<td>
		<ul>
			<li><a href="welcome.jsp">首页面</a></li>
		</ul>
		</td>
	</tr>
	<tr>
		<td>
		<ul>
			<li><a href="javascript:void(0);">通讯工具</a></li>
		</ul>
		<ul class="ulnotab">
			<li><a href="address.do?method=list">通讯录管理</a></li>
			<li><a href="sms.do?method=list">短消息管理</a></li>
		</ul>
		</td>
	</tr>
	<tr>
		<td>
		<ul>
			<li><a href="javascript:void(0);">个人管理</a></li>
		</ul>
		<ul class="ulnotab">
			<li><a href="schedule.do?method=list">日程安排</a></li>
			<li><a href="worklog.do?method=list">工作记录</a></li>
		</ul>
		</td>
	</tr>
	<tr>
		<td>
		<ul>
			<li><a href="javascript:void(0);">企业管理</a></li>
		</ul>
		<ul class="ulnotab">
			<li><a href="notice.do?method=list">公司公告</a></li>
			<li><a href="meeting.do?method=list">工作会议</a></li>
		</ul>
		</td>
	</tr>
	<tr>
		<td>
		<ul>
			<li><a href="logout.do">退出</a></li>
		</ul>
		</td>
	</tr>
</table>