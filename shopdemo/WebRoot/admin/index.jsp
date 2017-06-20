<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html>
<head>
<base href="${base}/" />
<title>后台管理</title>
<meta charset="utf-8" />
<link rel="stylesheet" href="css/admin.css" />
</head>
<body>
	<div class="container">
		<div id="header">
			<jsp:include page="header.jsp"></jsp:include>
		</div>
		<div id="admin_left">
			<ul class="submenu">
				<jsp:include page="left.jsp"></jsp:include>
			</ul>
			<div id="copyright"></div>
		</div>

		<div id="admin_right">
			<div class="content_box" style="border:none">
				<div class="content">
					<table width="98%" cellspacing="0" cellpadding="0"
						class="border_table_org" style="float:left">
						<thead>
							<tr>
								<th>最新10条订单</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="padding:5px 0">
									<table class="list_table3" width="100%">
										<thead>
											<th>订单号</th>
											<th>收货人</th>
											<th>支付状态</th>
											<th>金额</th>
											<th>下单时间</th>
											<th>操作</th>
										</thead>
										<tbody>
											<c:forEach items="${orders}" var="order">
												<tr>
													<td>${order.id}</td>
													<td><b>${order.address.accept}</b></td>
													<td>${order.status}</td>
													<td><b class="red3">￥${order.totalMoney}</b></td>
													<td>${order.orderTime}</td>
													<td><a href=""><img class="operator"
															src="images/admin/icon_check.gif" title="查看" /></a> <a
														href=""><img class="operator"
															src="images/admin/icon_edit.gif" title="编辑" /></a> <a
														href="javascript:void(0)" onclick=""><img
															class="operator" src="images/admin/icon_del.gif"
															title="删除" /></a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div id="separator"></div>
	</div>
</body>
</html>
