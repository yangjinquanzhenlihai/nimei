<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<!DOCTYPE html>
<html>
<head>
<base href="${base}/" />
<meta charset="utf-8" />
<title>用户登录_嗨购商城</title>
<link rel="stylesheet" type="text/css" href="css/index.css" />
<%
	Cookie[] cookies = request.getCookies();
	String name = "";

	if (cookies != null) {
		for (int i = 0; i < cookies.length; i++) {

			Cookie cookie = cookies[i];
			if (cookie.getName().equals("name")) {

				name = cookie.getValue();

				name = URLDecoder.decode(name, "UTF-8");
			}
		}
	}
%>
</head>
<script type="text/javascript" src="jquery-3.1.1.min.js"></script>

<body class="second">
	<div class="brand_list container_2">
		<jsp:include page="header.jsp"></jsp:include>

		<div class="wrapper clearfix">
			<div class="wrap_box">
				<p class="tips">修改后请记住您的新密码</p>
				<div class="box login_box clearfix">
					<form action='<c:url value='UserServlet'/>' method="post">
						<input type="hidden" name="opr" value="changePwd" />
						<table width="515" class="form_table f_l">
							<col width="120px" />
							<col />
							<tr>
								<th>旧密码：</th>
								<td><input class="gray" type="password" name="oldpwd"
									value="" id="oldpwd"
									placeholder="请输入旧密码" /><span id="oldpwdmsg">${errorMsg }</span></td>
							</tr>
							<tr>
								<th>新密码：</th>
								<td><input class="gray" type="password" id="newpwd"
									value="" name="newpwd"
									placeholder="请输入新密码" /><span id="newpwdmsg"></span></td>
							</tr>
							<tr>
								<th>再次输入新密码：</th>
								<td><input class="gray" type="password" id="renewpwd"
									value="" name="renewpwd"
									placeholder="请再次输入新密码" /><span id="renewpwdmsg">${errorMsg2 }</span></td>
							</tr>
							<tr>
								<td></td>
								<td><input
									style="background:url(images/submit_change.png);width: 101.6px;height: 40px"
									type="submit" value="" /></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
