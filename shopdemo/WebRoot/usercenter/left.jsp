<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="sidebar f_l">
	<img src="images/front/ucenter/ucenter.gif" width="180" height="40" />
	<div class="box">
		<div class="title">
			<h2>交易记录</h2>
		</div>
		<div class="cont">
			<ul class="list">
				<li><a href="usercenter/order_list.jsp">我的订单</a></li>
			</ul>
		</div>
	</div>
	<div class="box">
		<div class="title">
			<h2 class='bg4'>账户资金</h2>
		</div>
		<div class="cont">
			<ul class="list">
				<li><a href="usercenter/account_log.jsp">帐户余额</a></li>
				<li><a href="usercenter/online_recharge.jsp">在线充值</a></li>
			</ul>
		</div>
	</div>
	<div class="box">
		<div class="title">
			<h2 class='bg5'>个人设置</h2>
		</div>
		<div class="cont">
			<ul class="list">
				<li><a href="<c:url value='AddressServlet?opr=findAllByUid'/>">地址管理</a></li>
				<li><a href="change_pwd.jsp">修改密码</a></li>
				<li >
					<c:if test="${user.role eq '1' }">
						<a href="<c:url value='/admin/index.jsp'/>">后台管理</a>
					</c:if>
					<c:if test="${user.role eq '2' }">
						<a href="<c:url value='/admin2/index.jsp'/>">后台管理</a>
					</c:if>
				</li>
			</ul>
		</div>
	</div>
</div>
