<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="menu">
	<li><a href="<c:url value='/GoodsServlet?opr=findAll&'/>">商品列表</a></li>
	<li><a href="<c:url value='/CategoryServlet?opr=findAllToAdd'/>">商品添加</a></li>
	<li><a href="<c:url value='/CategoryServlet?opr=findAll'/>">分类列表</a></li>
	<li><a href="admin/category_add.jsp">添加分类</a></li>
	<li><a href="<>">订单列表</a></li>
	<li><a href="UserServlet?opr=findAll&">用户列表</a></li>
</ul>