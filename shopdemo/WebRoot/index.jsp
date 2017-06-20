<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8"></meta>
<base href="${base}/" />
<title>首页_${site}</title>
<jsp:include page="base.jsp" />
</head>

<body class="index">
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<jsp:include page="navbar.jsp"></jsp:include>
		<jsp:include page="search.jsp"></jsp:include>

		<div class="wrapper clearfix">
			<div class="sidebar f_r">
				<div class="box m_10">
					<div class="title">
						<h2>嗨购资讯</h2>
						<a class="more"
							href="">更多...</a>
					</div>
					<div class="cont">
						<ul class="list">
						</ul>
					</div>
				</div>
				<!--热卖商品-->
				<div class="hot box m_10">
					<div class="title">
						<h2>热卖商品</h2>
					</div>
					<div class="cont clearfix">
						<ul class="prolist">
							<c:forEach items="${sessionScope.hotlist}" var="goods" begin="0" end="7">
								<li><a href=""><img src="${pageContext.request.contextPath }/${goods.gAvatar }"
									
										width="85" height="85" alt="" /></a>
									<p class="pro_title">
										<a href="">${goods.name }</a>
									</p>
									<p class="brown">
										<b>${goods.price }</b>
									</p></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<!--热卖商品-->
				<div class="book box m_10">
					<div class="title">
						<h2>电子订阅</h2>
					</div>
					<div class="cont">
						<p>我们会将最新的资讯发到您的Email</p>
						<input type="text" class="gray_m light_gray f_l" name='orderinfo'
							value="输入您的电子邮箱地址" /> <label class="btn_orange"><input
							type="button" onclick="orderinfo();" value="订阅" /></label>
					</div>
				</div>
				<!--电子订阅-->

			</div>
			<div class="main f_l">
				<ul class="bxslider">
					<li title="新设计，新内核，新起点"><a href="" target="_blank"><img
							src="http://localhost/iWebShop/upload/2011/06/07/20110607105300491.png"
							width="750px" title="新设计，新内核，新起点" /></a></li>
				</ul>
			</div>

			<div class="main f_l">
				<!--最新商品-->
				<div class="box yellow m_10">
					<div class="title title3">
						<h2>
							<img src="images/front/new_product.gif" alt="最新商品" width="160"
								height="36" />
						</h2>
					</div>
					<div class="cont clearfix">
						<ul class="prolist">
							<c:forEach items="${sessionScope.newlist}" var="goods" begin="0" end="7">
								<li style="overflow:hidden"><a href="<c:url value = '/GoodsServlet?opr=goodsview&id=${goods.id }'/>" target="_blank"><img
										src="${pageContext.request.contextPath }/${goods.gAvatar }" width="175" height="175" alt="${goods.name }" /></a>
									<p class="pro_title">
										<a title="${goods.name }" href="<c:url value = '/GoodsServlet?opr=goodsview&id=${goods.id }'/>">${goods.name }</a>
									</p>
									<p class="brown">
										惊喜价：<b>￥${goods.spprice }</b>
									</p>
									<p class="light_gray">
										市场价：<s>￥${goods.price }</s>
									</p></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<!--最新商品-->
				<c:forEach items="${categories}" var="category">
					<div class="box m_10 green" name="showGoods">
						<div class="title title3">
							<h2>
								<a href=""><strong>${category.name}</strong></a>
							</h2>
							<a class="more" href="">更多商品...</a>
						</div>

						<div class="cont clearfix">
							<ul class="prolist">
								<c:forEach items="${sessionScope.newgoodslist}" var="goods" begin="8" end="15">
									<li style="overflow:hidden"><a href=""><img
											src="${pageContext.request.contextPath }/${goods.gAvatar}" width="175" height="175" alt=""
											title=""></a>
										<p class="pro_title">
											<a title="" href="">${goods.name}</a>
										</p>
										<p class="brown">
											惊喜价：<b>￥${goods.spprice}</b>
										</p>
										<p class="light_gray">
											市场价：<s>￥${goods.price}</s>
										</p></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</c:forEach>
				<!--最新评论-->
				<div class="comment box m_10">
					<div class="title title3">
						<h2>
							<img src="images/front/comment.gif" alt="最新评论" width="155"
								height="36" />
						</h2>
					</div>
					<div class="cont clearfix">
						<c:forEach begin="1" end="4">
							<dl class="no_bg">
								<dt>
									<a href=""><img src="images/goods/apple.jpg" width="66"
										height="66"></a>
								</dt>
								<dd>
									<a href="goods_view.jsp">苹果（Apple）iPhone 6 (A1586) 64GB</a>
								</dd>
								<dd>
									<span class="grade"><i style="width:42px"></i></span>
								</dd>
								<dd class="com_c">还不错</dd>
							</dl>
						</c:forEach>
					</div>
				</div>
				<!--最新评论-->
			</div>
		</div>
		<jsp:include page="help.jsp"></jsp:include>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>