<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"></meta>
<base href="${base}/" />
<title>地址管理_嗨购商城</title>
<jsp:include page="../base.jsp" />
<script type="text/javascript" src="js/adress.js"></script>
<script type="text/javascript">
	$(function() {
		setSelectedClass("usercenter/address_list.jsp");
	});
</script>
<script type="text/javascript">

			var province = [
				["四川", "成都", "绵阳"],
				["贵州", "贵阳", "遵义"],
				["云南", "昆明", "曲靖"]
			];
			var city = [
				["成都", "高新区", "武侯区"],
				["绵阳", "涪城区", "安州区"],
				["贵阳", "南明区", "云岩区"],
				["遵义", "红花岗区", "汇川区"],
				["昆明", "五华区", "盘龙区"],
				["曲靖", "麒麟区", "沾益区"]
			];

			
			

			function fun1(val) {
			var s1 = document.getElementById("city");
				s1.options.length = 1;
				for(var i = 0; i < province.length; i++) {
					if(province[i][0] == val) {
						for(var j = 1; j < province[i].length; j++) {
							var newoption = document.createElement("option");
							var te = document.createTextNode(province[i][j]);
							newoption.value = province[i][j];
							newoption.appendChild(te);
							s1.appendChild(newoption);

						}
					}
				}

			}

			function fun2(val) {
			var s2 = document.getElementById("area");
				s2.options.length = 1;
				for(var i = 0; i < city.length; i++) {
					if(city[i][0] == val) {
						for(var j = 1; j < city[i].length; j++) {
							var newoption = document.createElement("option");
							var te = document.createTextNode(city[i][j]);
							newoption.value = city[i][j];
							newoption.appendChild(te);
							s2.appendChild(newoption);

						}
					}
				}

			}

		</script>
</head>
<body class="index">
	<div class="ucenter container">
		<jsp:include page="../header.jsp"></jsp:include>
		<jsp:include page="../navbar.jsp"></jsp:include>
		<jsp:include page="../search.jsp"></jsp:include>
		<div class="wrapper clearfix">
			<jsp:include page="left.jsp"></jsp:include>
			<div class="main f_r">
				<div class='tabs'>
					<div class="uc_title m_10 tabs_menu">
						<label class="current node"><span>地址管理</span></label>
					</div>
					<div class='tabs_content'>
						<div id="address_list" class="form_content m_10 node">
							<div class="uc_title2 m_10">
								<strong>已保存的有效地址</strong>
							</div>
							<table class="list_table" width="100%" cellpadding="0"
								cellspacing="0">
								<col width="120px" />
								<col width="120px" />
								<col width="120px" />
								<col width="120px" />
								<col width="120px" />
								<col />
								<thead>
									<tr>
										<th>收货人</th>
										<th>所在地区</th>
										<th>街道地址</th>
										<th>电话/手机</th>
										<th>邮编</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${userAddressList}" var="userAddress">

									<tr>
										<td>${userAddress.name }</td>
										<td>${userAddress.area }</td>
										<td>${userAddress.street }</td> 
										<td>${userAddress.cellphone }</td>
										<td>${userAddress.zipcode }</td>
										<td><a class="blue" href="<c:url value='/AddressServlet?opr=alterAddress&id=${userAddress.id }'/>" >修改</a>|
											<a class="blue" href="<c:url value='/AddressServlet?opr=deleteAddress&id=${userAddress.id }'/>" >删除</a>|
											<c:choose>
												<c:when test="${userAddress.selected eq '1' }">
												<a class="red2" href="<c:url value='/AddressServlet?opr=cancelSelected&uid=${userAddress.uid }'/>">取消默认</a>
												</c:when>
												<c:otherwise>
												<a class="red2" href="<c:url value='/AddressServlet?opr=setSelected&id=${userAddress.id }'/>">设置默认</a>
												</c:otherwise>
											</c:choose>
											
									</tr>
									
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<!--表单修改-->
				<div class="orange_box" id='address_form'>
					<form  id="adsForm" action="<c:url value='/AddressServlet?opr=add&id=${address.id }'/>" method='post' name='form'>
						<table class="form_table" width="100%" cellpadding="0"
							cellspacing="0">
							<col width="120px" />
							<col />
							<caption>收货地址</caption>
							<tr>
								<th><span class="red">*</span> 收货人姓名：</th>
								<td><input  id="inpName" name='address.accept' class="normal" type="text" required="required" value="${address.name }"/><label>收货人真实姓名，方便快递公司联系。</label></td>
							</tr>
							<tr>
								<th><span class="red">*</span> 所在地区：</th>
								
								<td>
								<select name="address.province" id="province" onclick="fun1(this.value)">
									<option value="" >--省--</option>
									<option value="四川">四川</option>
									<option value="云南">云南</option>
									<option value="贵州">贵州</option>
								</select>
								
								<select name="address.city" id="city" onclick="fun2(this.value)">
									<option value="" >--市--</option>
			
								</select>
								<select name="address.area" id="area">
									<option value="" >--区/县--</option>
								</select>
								</td>
							</tr>
							<tr>
								<th><span class="red">*</span> 街道地区：</th>
								<td><input id="inpStreet" name='address.address' class="normal" type="text" required="required" value="${address.street }" /><label>真实详细收货地址，方便快递公司联系。</label></td>
							</tr>
							<tr>
								<th>邮政编码：</th>
								<td><input name='address.zip' class="normal" type="text" value="${address.zipcode }"/><label>邮政编码,如250000。</label></td>
							</tr>
							<tr>
								<th>电话号码：</th>
								<td><input name='address.phoneNum' class="normal" type="text" value="${address.telephone }" /><label>电话号码,如010-12345688。</label></td>
							</tr>
							<tr>
								<th>手机号码：</th>
								<td><input name='address.mobile' class="normal" type="text" value="${address.cellphone }" /><label>手机号码，如：13588888888</label></td>
							</tr>
							<tr>
								<th>设为默认：</th>
								<td><label><input name='address.isdefault' type='checkbox' 
										value='1'></label></td>
							</tr>
							<tr>
								<th></th>
								<td><label class="btn"><input type="submit" 
										value="保存" /></label> <label class="btn"><input type="reset"
										value="取消" /></label></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="../help.jsp"></jsp:include>
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>
</body>
</html>
