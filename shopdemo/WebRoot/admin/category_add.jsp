<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<base href="${base}/" />
<title>后台管理</title>
<meta charset="utf-8" />
<link rel="stylesheet" href="css/admin.css" />
<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
<script type="text/javascript">
	if("${msg}"!=""){
		alert("${msg}");
	}
</script>

<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    var na=false;
	$(document).ready(function(){
	
		$("#name").blur(function(){
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/CategoryServlet",
				 data:"opr=findByName&name="+$("#name").val(),
				 success:function(result){
				 	$("#msg").html(result);
				 	if(result=="OK"){
						na=true;					 	
				 	}
				 }
			});
		
		});
		
		$("#dosubmit").click(function(){
					if(na){
					$("#form1").submit();
						}
					});
	});
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
			<div class="content_box">
				
				<div class="content form_content">
					<span id="msg">${msg }</span>
					<form action="<c:url value='/CategoryServlet'/>" method="post" id="form1">
						<input type="hidden" name="opr" value="add" />
						<table class="form_table" cellpadding="0" cellspacing="0">
							<col width="150px" />
							<col />
							<tr>
								<th>分类名称：</th>
								<td><input id="name" class="normal" name="name" type="text"
									value="" required="required"><label>* 必选项</label></td>
							</tr>
							<tr>
								<td></td>
								<td><button id="dosubmit" class="submit" type="button">
										<span>确 定</span>
									</button></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div id="separator"></div>
	</div>
</body>
</html>
