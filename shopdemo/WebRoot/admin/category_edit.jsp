<%@ page language="java" pageEncoding="UTF-8"%>
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
					<form action="category" method="post">
						<input type="hidden" name="opr" value="edit" />
						<input type="hidden" name="id" value="${category.id}" />
						<table class="form_table" cellpadding="0" cellspacing="0">
							<col width="150px" />
							<col />
							<tr>
								<th>分类名称：</th>
								<td><input class="normal" name="name" type="text"
									value="${category.name}"><label>* 必选项</label></td>
							</tr>
							<!-- <tr>
								<th>商品数量：</th>
								<td><input class="normal" name="goodsNums" type="text"
									value="${category.goodsNums}" readonly="readonly"><label>* 必选项</label></td>
							</tr> -->
							<tr>
								<td></td>
								<td><button class="submit" type="submit">
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
