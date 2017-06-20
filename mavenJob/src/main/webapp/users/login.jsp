<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>会员登录 - 人才系统</title>
<base href="${base}" />
<link rel="shortcut icon" href="favicon.ico" />
<link href="styles/user.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript">
	<%
	Cookie[] cookies = request.getCookies();
	String account = "";
	if (cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals("account")) {
				account = cookie.getValue();
				/* account=URLDecoder.decode(account, "UTF-8"); */
			}
		}
	}
%>
	
</script>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<div class="login_bg">
	<div class="login_box">
			  <div class="login_boxin">
				<form id="Formlogin" action="login.action" name="Formlogin" method="post">
					<div class="tit">会员登录&nbsp;&nbsp;<span style="color: red">${msg}</span></div>
					<div class="login_err"></div>
					<table width="100%" border="0" align="center" cellpadding="5"
						cellspacing="0">

						<tr>
							<td width="55" align="right" id="account">帐号：</td>
							<td><input name="account" type="text" class="login_input"
								id="username" maxlength="30" value="${cookie.account.value}"
								style="color:#999999" required="required"/></td>
						</tr>
						<tr>
							<td align="right">密码：</td>
							<td><input name="password" type="password"
								class="login_input" id="password" maxlength="20" required="required"
								value="${cookie.password.value }"/></td>
						</tr>
						<tr>
							<td align="right">&nbsp;</td>
							<td><div style="position:relative;">
									<label><input type="checkbox" name="expire" id="expire"
										value="7" /> 一周内自动登录</label>
								</div></td>
						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="5"
						cellspacing="0">
						<tr>
							<td align="right" width="50">&nbsp;</td>
							<td height="40" valign="top">
								<div style="position:relative;">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="100"><input type="submit" name="submitlogin"
												id="login" class="but80" value="登录" /></td>
											<td class="link_bk"><a href="getpass.jsp">忘记密码？</a></td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td align="right" style="border-top:1px  #E8E8E8 solid">&nbsp;</td>
							<td height="20" class="link_lan"
								style="border-top:1px  #E8E8E8 solid">还没有账号？<a
								href="users/register.jsp">免费注册</a></td>
						</tr>
					</table>
				</form>
			</div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
