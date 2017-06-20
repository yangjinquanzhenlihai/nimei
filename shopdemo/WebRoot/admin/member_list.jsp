<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html>
<head>
<base href="${base}/" />
<title>后台管理</title>
<meta charset="utf-8" />
<link rel="stylesheet" href="css/admin.css" />
<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>

<script type="text/javascript">
	function goPage(p) {
		if (p<1 || p>${pb.totalPage}) {
			return;
		}
		$("#page").val(p);
		$("#form1").submit();
	}
	
	function del(id) {
		alert(id);
		if (confirm("您确认删除该记录吗?")) {
			
			location.href = "${pageContext.request.contextPath}/UserServlet?opr=del&id="
					+ id;
			
		}
	}
</script>
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
			<div class="headbar">
				<div class="field">
					<table class="list_table">
						<colgroup>
							<col width="30px">
							<col width="150px">
							<col width="150px">
							<col width="80px">
							<col width="135px">
							<col width="100px">
						</colgroup>
						<thead>
							<tr>
								<th>序号</th>
								<th>用户名</th>
								<th>Email</th>
								<th>余额</th>
								<th>注册日期</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<form method="post" name="member_list">
				<div class="content">
					<table id="list_table" class="list_table">
						<colgroup>
							<col width="30px">
							<col width="150px">
							<col width="150px">
							<col width="80px">
							<col width="135px">
							<col width="100px">
						</colgroup>
						<tbody>
							<c:forEach items="${pb.beanList}" var="user" varStatus="s">
								<tr>
									<td>${s.count}</td>
									<td title="">${user.name}</td>
									<td title="">${user.email}</td>
									<td title="95092.00">${user.balance}</td>
									<td title="2015-05-02 22:17:13">${user.regTime}</td>
									<td><a href="javascript:void(0)"><img class="operator"
											src="images/admin/icon_edit.gif" alt="修改" /></a>
					<a href="javascript:void(0)" onclick="javaScript:del('${user.id}');">
	     <img class="operator" src="images/admin/icon_del.gif" alt="删除" /></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</form>
			<div class='pages_bar'>
				<a href="<c:url value='/UserServlet?${pb.url }&pageCode=1'/>"
					id="first">首页</a>
				<c:choose>
					<c:when test="${pb.pageCode>1 }">
						<a
							href="<c:url value='/UserServlet?${pb.url }&pageCode=${pb.pageCode-1 }'/>">上一页</a>
					</c:when>
					<c:otherwise>
						<a>上一页</a>
					</c:otherwise>
				</c:choose>

				<c:forEach begin="1" end="${pb.totalPage}" var="p">
					<a href="<c:url value='/UserServlet?${pb.url }&pageCode=${p }'/>">${p}</a>
				</c:forEach>

				<c:choose>
					<c:when test="${pb.pageCode<pb.totalPage }">
						<a
							href="<c:url value='/UserServlet?${pb.url }&pageCode=${pb.pageCode+1 }'/>">下一页</a>
					</c:when>
					<c:otherwise>
						<a>下一页</a>
					</c:otherwise>
				</c:choose>
				<a
					href="<c:url value='/UserServlet?${pb.url }&pageCode=${pb.totalPage }'/>"
					id="last">尾页</a><span>当前第${pb.pageCode}页/共${pb.totalPage}页</span>
			</div>
		</div>

	</div>
	<div id="separator"></div>
	</div>

	<div
		style="display: none; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; cursor: move; opacity: 0; background: rgb(255, 255, 255);"></div>
</body>
</html>