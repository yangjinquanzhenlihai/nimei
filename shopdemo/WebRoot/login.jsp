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
		Cookie[] cookies=request.getCookies();
		String name="";
		
		if(cookies!=null){
		for(int i=0;i<cookies.length;i++){
		
		Cookie cookie=cookies[i];
			if(cookie.getName().equals("name")){
			
			name=cookie.getValue();
			
			name=URLDecoder.decode(name, "UTF-8");
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
				<h3 class="notice">已注册用户，请登录</h3>
				<p class="tips">欢迎来到我们的网站，如果您已是本站会员请登录</p>
				<div class="box login_box clearfix">
				<p stye="color:red,font-size:12px;" >${errorMsg }</p>
					<form action='<c:url value='UserServlet'/>' method="post">
					<input type="hidden" name="opr" value="login" />
						<table width="515" class="form_table f_l">
							<col width="120px" />
							<col />
							<tr>
								<th>用户名：</th>
								<td><input class="gray" type="text" name="account" value="${cookie.name.value }"
									id="login_info" placeholder="请输入用户名" /><span id="namemsg"></span></td>
							</tr>
							<tr>
								<th>密码：</th>
								<td><input class="gray" type="password" id="password" value="${cookie.password.value }"
									name="password" placeholder="请输入6-20位长度的密码" /><span
									id="pwdmsg"></span></td>
									
							</tr>
							<tr>
								<td></td>
								<td><input class="submit_login" type="submit" value="登录" /></td>
							</tr>
						</table>
					</form>

					<!--正常登录时-->
					<table width="360px" class="form_table prompt_3 f_l">
						<col width="75px" />
						<col />
						<tr>
							<th></th>
							<td>
								<p class="mt_10">
									<strong class="f14">您还不是<span class="orange">嗨购商城</span>用户
									</strong>
								</p>
								<p>
									现在免费注册成为嗨购商城用户，便能立即享受便宜又放心的购物乐趣。<a class="blue" href="">网站首页>></a>
								</p>
								<p class="mt_10">
									<a class="reg_btn" href="register.jsp">注册新用户</a>
								</p>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
