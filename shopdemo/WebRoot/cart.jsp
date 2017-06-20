<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"></meta>
<base href="${base}/" />
<title>购物车_${site}</title>
<jsp:include page="base.jsp" />
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		
		
		
		 $("#add").on("click",function(){
		  if($("#buyNums").val()<$("#add").attr("value")){
		       $("#buyNums").val(parseInt($("#buyNums").val())+1);
		  }
		  else alert("没有那么多！抢手货！");
			  
		  });
		  $(".reduce").on("click",function(){
			  if($("#buyNums").val()==1){
				  return;
			  }
			  $("#buyNums").val(parseInt($("#buyNums").val())-1);
		  });
		  
		  
		  
	});
	function finish() {
		$("#form1").submit();
	}
		
		
		
</script>
<script type="text/plain" id="goods">
	<tr>
						<td><input type="hidden" name="orderDetails[{{index}}].goods.id" value="{{id}}"/>
						<img src="{{thumbnail}}" width="66px"
							height="66px" alt="{{name}}"
							title="{{name}}" /></td>
						<td class="t_l"><a href="" class="blue">{{name}}</a></td>
						<td>￥<b>{{price2}}</b></td>
						<td>
							<div class="num">
								<a class="reduce" href="javascript:void(0)" onclick=''>-</a> <input
									name="orderDetails[{{index}}].nums" class="tiny" value="{{goodsNum}}" onblur='' type="text" id="goods_count_3">
								<a class="add" href="javascript:void(0)" onclick=''>+</a>
							</div>
						</td>
						<td>￥<b class="red2" id="goods_sum_3">{{goodsNum*price2}}</b></td>
						<td><a href="">删除</a></td>
					</tr>
</script>
</head>
<body class="second">
	<div class="brand_list container_2">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="wrapper clearfix">
			<div class="position mt_10">
				<span>您当前的位置：</span> <a href=""> 首页</a> » 购物车
			</div>
			<div class="myshopping m_10">
				<ul class="order_step">
					<li class="current"><span class="first">1、查看购物车</span></li>
					<li><span>2、填写核对订单信息</span></li>
					<li class="last"><span>3、成功提交订单</span></li>
				</ul>
			</div>
			<form action="order_add" id="form1">
				<table width="100%" class="cart_table m_10">
					<col width="115px" />
					<col width="400px" />
					<col width="80px" />
					<col width="80px" />
					<col width="80px" />
					<caption>查看购物车</caption>
					<thead>
						<tr>
							<th>图片</th>
							<th>商品名称</th>
							<th>单价</th>
							<th>数量</th>
							<th>小计</th>
							<th class="last">操作</th>
						</tr>
						
					</thead>
					<tbody id="goodsList">
					<c:forEach items="${scarList}" var="car">
						<tr>
							<td><img width="20px" height="20px" 
					src="${pageContext.request.contextPath }/${car.goods.gAvatar }" /></td>
					<td>${car.goods.name }
					</td>
					<td>${car.goods.spprice }</td>
					<td>${car.quantity }	
					</td>
					<td>${car.subtotal }</td>
					<td class="last"><a><img width="20px" height="20px" src="images/admin/delete.jpg" alt="删除" /></a></td>
						</tr>
					</c:forEach>
						<tr class="stats">
							<td colspan="8">金额总计（不含运费）：${totalPay }￥<b class="orange" id='sum_price'></b></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2" class="t_l"></td>
							<td colspan="6" class="t_r"><a class="btn_continue" href="">继续购物</a>
								<a class="btn_pay" href="javascript:finish();">去结算</a></td>
						</tr>
					</tfoot>
				</table>
			</form>
			<div class="box">
				<div class="title">热门商品推荐</div>
			</div>
			<ul id="scrollpic" class="prolist">
				<c:forEach begin="1" end="5">
					<li><a href=""> <img width="98px" height="106px"
							src="images/goods/apple.jpg" alt="苹果（Apple）iPhone 6 (A1586) 64GB"
							title="苹果（Apple）iPhone 6 (A1586) 64GB">
					</a>
						<p class="pro_title">
							<a href="">苹果（Apple）iPhone 6 (A1586) 64GB</a>
						</p>
						<p class="brown">
							<b>￥5800.00</b>
						</p> <label class="btn_orange2"><input type="submit"
							onclick="" value="加入购物车"></label></li>
				</c:forEach>
			</ul>
		</div>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>
