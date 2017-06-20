<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'goodsUpdate.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <h1>编辑商品</h1>
    <form action="updateGoods.action">
    	商品编号：<input type="text" name="id" value="${goods.id }"/><br/><br/>
    	商品名称：<input type="text" name="gname" value="${goods.gname }"/><br/><br/>
    	商品价格：<input type="text" name="price" value="${goods.price }"/><br/><br/>
    	<input type="submit"/>
    </form>
  </body>
</html>
