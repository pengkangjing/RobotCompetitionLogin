<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link rel="shortcut icon" type="image/x-icon" href="image/robot32.ico" media="screen" />
    <base href="<%=basePath%>">  
    <title>北京工业大学机器人竞赛报名平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
<body style="text-align:center">
<img src="image/top.jpg"  />
<div style="background-color:#39C; margin:0px auto; width:733px; height:1000px; ">
<%@ include file="publicMenu.jsp" %>
<style>
	tr { height: 30px; }

</style>
<br/>

	<div> 
	<span style="font-size:20px; font-family:'微软雅黑';">科目信息 </span>
	<table style="padding-left: 40px;">
	<tr >
		<td style="width: 350px; font-family:'微软雅黑'; height: 40px;">&nbsp;&nbsp;&nbsp;&nbsp;竞赛科目名称</td> <td style="width: 150px; height: 40px; font-family:'微软雅黑'; ">允许报名最大人数</td>
	</tr>
		<c:forEach var="sub" items="${listSubject}" >
	<tr>
		<td style="width: 350px; font-family:'微软雅黑';"><a href="doPublicSelectOneSubject?subId=${sub.subjectId}">[${sub.subjectName}]</a></td><td style="width: 150px; font-family:'微软雅黑'; text-align: center; ">${sub.peopleNumber}人</td>
	
	</tr>
		</c:forEach>
	</table>

	<br/>
	</div>

</div>
</body>
</html>
