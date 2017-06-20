<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"></meta>
<base href="${base}/" />
<title>订单详情_${site}</title>
<jsp:include page="../base.jsp" />
</head>
<body class="index">
	<div class="ucenter container">
		<jsp:include page="../header.jsp"></jsp:include>
		<jsp:include page="../navbar.jsp"></jsp:include>
		<jsp:include page="../search.jsp"></jsp:include>
		<div class="wrapper clearfix">
			<jsp:include page="left.jsp" />
			<div class="main f_r">
				<div class="uc_title m_10">
					<label class="current"><span>订单详情</span></label>
				</div>
				<div class="prompt_2 m_10">
					<div class="t_part">
						<c:if
							test="${order.status eq 1 or order.status eq 2 or order.status eq 3 or order.status eq 4 or order.status eq 5}">
							<p>
								<fmt:formatDate value="${order.orderTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
								&nbsp;&nbsp;<span class="black">订单创建</span>
							</p>
						</c:if>
						<c:if
							test="${order.status eq 2 or order.status eq 3 or order.status eq 4 or order.status eq 5}">
							<p>
								<fmt:formatDate value="${order.payTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
								&nbsp;&nbsp;<span class="black">订单付款 ${order.totalMoney }</span>
							</p>
						</c:if>
						<c:if test="${order.status eq 4 or order.status eq 5}">
							<p>
								<fmt:formatDate value="${order.sendTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
								&nbsp;&nbsp;<span class="black">订单发货完成</span>
							</p>
						</c:if>
						<c:if test="${order.status eq 5}">
							<p>
								<fmt:formatDate value="${order.completionTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
								&nbsp;&nbsp;<span class="black">订单完成</span>
							</p>
						</c:if>
					</div>
					<p>
						<b>订单号：</b>${order.id} <b>下单日期：</b>
						<fmt:formatDate value="${order.orderTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />
						<b>状态：</b> <span class="red2"> <b class="green"> <c:if
									test="${order.status eq 1}">未付款</c:if> <c:if
									test="${order.status eq 2}">已付款</c:if> <c:if
									test="${order.status eq 3}">未发货</c:if> <c:if
									test="${order.status eq 4}">已发货</c:if> <c:if
									test="${order.status eq 5}">已完成</c:if>
						</b>
						</span>
					</p>

					<form action='' method='post'>
						<p>
							<input type="hidden" name="order.id" value="${order.id}" />
							<c:if test="${order.status eq 1}">
								<label class="btn_orange"> <input type="submit"
									value="取消订单" />
								</label>
								<label class="btn_green"><input type="button"
									value="立即付款" /></label>
							</c:if>
							<c:if test="${order.status eq 5}">
							<label class="btn_orange"><input type="button" value="申请退款" onclick="" /> </label>
							</c:if>
						</p>
					</form>

				</div>
				<div class="box m_10">
					<div class="title">
						<h2>
							<span class="orange">收件人信息</span>
						</h2>
					</div>

					<!--收获信息展示-->
					<div class="cont clearfix" id="acceptShow">
						<table class="dotted_table f_l" width="100%" cellpadding="0"
							cellspacing="0">
							<col width="130px" />
							<col />
							<tr>
								<th>收货人：</th>
								<td>${order.address.accept}</td>
							</tr>
							<tr>
								<th>地址：</th>
								<td>${order.address.province}${order.address.city}
									${order.address.area} ${order.address.address}</td>
							</tr>
							<tr>
								<th>邮编：</th>
								<td>${order.address.zip}</td>
							</tr>
							<tr>
								<th>固定电话：</th>
								<td>${order.address.phoneNum}</td>
							</tr>
							<tr>
								<th>手机号码：</th>
								<td>${order.address.mobile}</td>
							</tr>
						</table>
					</div>
				</div>

				<!--支付和配送-->
				<div class="box m_10">
					<div class="title">
						<h2>
							<span class="orange">支付及配送方式</span>
						</h2>
					</div>
					<div class="cont clearfix">
						<table class="dotted_table f_l" width="100%" cellpadding="0"
							cellspacing="0">
							<col width="130px" />
							<col />
							<tr>
								<th>支付方式：</th>
								<td>${order.payType}</td>
							</tr>
						</table>
					</div>
				</div>

				<!--物品清单-->
				<div class="box m_10">
					<div class="title">
						<h2>
							<span class="orange">商品清单</span>
						</h2>
					</div>
					<div class="cont clearfix">
						<table class="list_table f_l" width="100%" cellpadding="0"
							cellspacing="0">
							<tbody>
								<tr>
									<th>图片</th>
									<th>商品名称</th>
									<th>商品价格</th>
									<th>商品数量</th>
									<th>小计</th>
								</tr>
								<c:forEach items="${order.orderDetails}" var="orderDetails">
									<tr>
										<td><img class="pro_pic"
											src="${orderDetails.goods.thumbnail}" width="50px"
											height="50px" /></td>
										<td class="t_l"><a class="blue"
											href="goods_view?goods.id=${orderDetails.goods.id}"
											target='_blank'>${orderDetails.goods.name}</a></td>
										<td class="red2">￥${orderDetails.goods.price2}</td>
										<td>x ${orderDetails.nums}</td>
										<td class="red2 bold">￥${orderDetails.goods.price2 * orderDetails.nums}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

				<div class="gray_box">
					<p>
						订单支付金额：<span class="red2">￥<label id="order_amount">${order.totalMoney}</label></span>
					</p>
				</div>
			</div>
		</div>
		<jsp:include page="../help.jsp" />
		<jsp:include page="../footer.jsp" />
	</div>

</body>
</html>
