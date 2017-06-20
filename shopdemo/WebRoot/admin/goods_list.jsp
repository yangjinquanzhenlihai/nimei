<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html >
<html>

<body>
	<div class="container">
		<div id="header">
			<head>
<base href="${base}/">
<title>后台管理</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/admin.css">
<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function goPage(p) {
		if (p<1 || p>${pb.totalPage}) {
			return;
		}

		$("#page").val(p);
		//$("#form1").submit();
		if (${opr eq 'findAll'}) {
			location.href = "${pageContext.request.contextPath }/GoodsServlet?opr=findAll&pageCode="
					+ p;
		} else {
			location.href = "${pageContext.request.contextPath }/GoodsServlet?opr=query&pageCode="
					+ p
					+ "&categoryId=${sessionScope.category.id}&name=${sessionScope.queryGoods.name}&queryGoods.spprice1=${sessionScope.queryGoods.spprice1}&queryGoods.spprice2=${sessionScope.queryGoods.spprice2}";
		}
	}
	$(function() {
		$("#category").val("${categoryId}");
	});

	/*function del(id){
	alert(11);
		if(confirm("您确认删除该记录吗?")){
			location.href="${pageContext.request.contextPath}/GoodsServlet?opr=dle&goods.id="+id;
		}
	}*/

	$(function() {
		$(".del")
				.on(
						"click",
						function() {

							if (confirm("您确认删除该记录吗")) {
								location.href = "${pageContext.request.contextPath}/GoodsServlet?opr=del&id="
										+ $(this).attr("data-id");
							}
						});
	});

	//function go(p){
	//	$("#p").val(p);
	//	$("#form1").submit();
	//location.href="goods?opr=list&p="+p+"&categoryId="+$("#category").val()+"&name="+$("#name").val();
	//}
</script>
			</head><jsp:include page="header.jsp"></jsp:include>
		</div>
		<div id="admin_left">
			<ul class="submenu">
				<jsp:include page="left.jsp"></jsp:include>
			</ul>
			<div id="copyright"></div>
		</div>

		<div id="admin_right">
			<div class="headbar">
				<div class="searchbar">
					<form action="<c:url value='GoodsServlet'/>" method="get"
						id="form1">
						<input type="hidden" name="opr" value="query" /> <input
							type="hidden" name="p" id="page" value="1" /> <select
							class="auto" name="categoryId" id="category">
							<option value="">选择分类</option>
							<c:forEach items="${categories}" var="category">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select> 商品名： <input class="small" name="name" id="name" type="text"
							value="${name}"> 销售价:<input class="small"
							name="queryGoods.spprice1" id="name" type="text" value="">至<input
							class="small" name="queryGoods.spprice2" id="name" type="text"
							value="">
						<button class="btn" type="submit">
							<span class="sel">筛 选</span>
						</button>
					</form>
				</div>

				<div class="field">
					<table class="list_table">
						<col width="40px" />
						<col width="400px" />
						<col width="120px" />
						<col width="70px" />
						<col width="70px" />
						<col width="70px" />
						<thead>
							<tr>
								<th>选择</th>
								<th>商品名称</th>
								<th>分类</th>
								<th>销售价</th>
								<th>库存</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<form action="" method="post" name="orderForm">
				<div class="content">
					<table class="list_table">
						<col width="40px" />
						<col width="400px" />
						<col width="120px" />
						<col width="70px" />
						<col width="70px" />
						<col width="70px" />
						<tbody>
							<c:forEach items="${pb.beanList}" var="goods">
								<tr>
									<td><input name="" type="checkbox" value="1" /></td>
									<td><a href="" target="_blank" title="${goods.name}">${goods.name}</a></td>
									<td><c:forEach items="${categories}" var="category">
											<c:if test="${category.id eq goods.cid}">
												<span>${category.name }</span>
											</c:if>
										</c:forEach></td>
									<td>${goods.spprice}</td>
									<td>${goods.inventory}</td>
									<td><a href="goods_initUpdate?goods.id=${goods.id}"><img
											class="operator" src="images/admin/icon_edit.gif" alt="编辑" /></a>
										<a href="javascript:void(0)" class="del"
										data-id="${goods.id }"><img
											src="images/admin/icon_del.gif" alt="删除" /></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</form>
			<div class='pages_bar'>
				<a href='javascript:goPage(1)' id="first">首页</a> <a
					href='javascript:goPage(${pb.pageCode-1})' id="first">上一页</a>
				<c:forEach begin="1" end="${pb.totalPage}" var="p">
					<a href="javascript:goPage('${p}')">${p}</a>
				</c:forEach>
				<a href='javascript:goPage(${pb.pageCode+1})' id="first">下一页</a> <a
					href='javascript:goPage(${pb.totalPage})' id="last">尾页</a> <span>当前第${pb.pageCode}页/共${pb.totalPage}页</span>
			</div>
		</div>
		<div id="separator"></div>
	</div>
</body>
</html>