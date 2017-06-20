<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<base href="${base}" />
<meta charset="UTF-8" />
<title>快速注册 - 人才系统</title>
<link rel="shortcut icon" href="//favicon.ico" />
<link href="styles/user.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	var a=false,b=false,c=false,d=false;
	$(document).ready(function() {
		$("#username").blur(function() {
			$.ajax({
				type : "POST",
				url : "checkUserName.action",
				data : "account=" + $("#username").val(),
				success : function(result) {
					$("#usermsg").css({
						"color" : "red"
					});
					$("#usermsg").html(result);
						if (result==="该用户名可以用") {
								return a=true;
								}
				}
			});
		});
		$("#email").blur(function(){
          var email=$("#email").val();
          $("#emailmsg").css({"color":"red"});
          var reg=new RegExp("\^[A-Za-z0-9]+\@[A-Za-z0-9]+\.[A-Za-z]{2,4}");
          if (reg.test(email)) {
			$("#emailmsg").html("OK");
			return b=true;
		}
		else{
			$("#emailmsg").html("邮箱格式不对");
		}
          });
          $("#password2").blur(function(){
          	var password = $("#password").val();
          	var password2 = $("#password2").val();
          	if(password===password2){
          		$("#passwordmsg").html("ok");
          		return c=true;
          	}else{
          		$("#passwordmsg").html("两次输入的密码不一致");
          	}
          });
		$("#reg").click(function(){
		if(document.getElementById("agreement").checked == true
		){
			if(a&&b&&c){
				$("#Form1").submit();
			}else{
				alert("请输入正确信息");
			}
			}else{
				alert("请仔细阅读注册协议并同意协议中条款！");
			}
		});
	});
</script>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="reg_box">
		<div class="reg_box_tit link_lan">
			<strong>个人会员注册</strong><span>切换至 <a href="users/reg_comp.jsp">企业会员</a>
				注册
			</span>
		</div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			style="margin-bottom:50px; margin-top:30px;">
			<tr>
				<td width="600" style=" border-right:1px #DDDDDD solid">
					<form id="Form1" name="Form1" method="post"
						action="person_register.action">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="130" height="40" align="right">用户名：</td>
								<td><input name="account" type="text" class="reg_input"
									id="username" maxlength="25" required="required"/> <span id="usermsg"></span></td>
							</tr>
							<tr>
								<td height="40" align="right">常用电子邮箱：</td>
								<td>
									<input name="email" type="text" class="reg_input"
									id="email" maxlength="60" required="required"/>
									<span id="emailmsg"></span>
								</td>
							</tr>
							<tr>
								<td height="40" align="right">设置登录密码：</td>
								<td><input name="password" type="password"
									class="reg_input" id="password" maxlength="18" required="required"/></td>
							</tr>
							<tr>
								<td height="40" align="right">确认登录密码：</td>
								<td><input name="password2" type="password"
									class="reg_input" id="password2" maxlength="18" required="required"/>
									<span id="passwordmsg"></span>
									</td>
							</tr>
							<tr>

								<td><input name="member_type" type="hidden"
									id="member_type" value="2" /></td>
								<td class="link_lan"><input name="agreement" id="agreement"
									type="checkbox" value="1" checked="checked" /> 我已阅读并同意 <a
									href="" target="_blank">[注册协议]</a></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td height="50">
									<div style="position:relative;">
										<input name="reg" type="button"  id="reg" class="but100"
											value="立即注册" /> <input id="waiting" type="button"
											value="正在注册" style="display:none;" class="but100"
											disabled="disabled" />
									</div>
								</td>
							</tr>
						</table>
					</form>
				</td>
				<td valign="top"><table width="220" border="0" align="center"
						cellpadding="0" cellspacing="10" style="line-height:180%">
						<tr>
							<td style="font-size:14px;"><strong>已经有帐号？</strong></td>
						</tr>
						<tr>
							<td><input name="reg2" type="submit" class="but100" id=""
								value="立即登录" onclick="window.location='users/login.jsp'" /></td>
						</tr>
						<tr>
							<td><strong style="color: #009900">企业会员</strong> <br />
								发布招聘信息，查看人才简历，搜索简历，在线邀请面试...</td>
						</tr>
						<tr>
							<td><strong style="color:#009900"> 个人会员 </strong> <br />
								填写个人简历，在线投递简历，在线申请职位，搜索职位...</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
