<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html >
<html>
<head>
<base href="${base}/" />
<title>后台管理</title>
<meta charset="utf-8" />
<link rel="stylesheet" href="css/admin.css" />


<script type="text/javascript">
	/*$(function() {
		var ue = UE.getEditor('description', {
			toolbars : [ [ 'fullscreen', 'source', '|', 'undo', 'redo', '|',
					'bold', 'italic', 'underline', 'fontborder',
					'strikethrough', 'superscript', 'subscript',
					'removeformat', 'formatmatch', 'autotypeset', 'blockquote',
					'pasteplain', '|', 'forecolor', 'backcolor',
					'insertorderedlist', 'insertunorderedlist', 'selectall',
					'cleardoc', '|', 'rowspacingtop', 'rowspacingbottom' ] ],
			initialFrameWidth : 600 //初始化编辑器宽度,默认1000
			,
			initialFrameHeight : 300
		//初始化编辑器高度,默认320

		});

	});*/
</script>
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
		var conditon = false;
							
		$(document).ready(function(){		
			
		$("#name").blur(function(){					
		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/GoodsServlet",
			data:"opr=creatId&name="+$("#name").val(),
				success:function(result){		
				//$("#nametext1").html(arr[0]);
			    $("#goodsNum").val(result);
				}
		});
		});
			$("#description").blur(function(){
			$.ajax({
				type:"POST",
			    url:"${pageContext.request.contextPath}/GoodsServlet",
			    data:"opr=checkGoods&name="+$("#name").val()+"&info="+$("#description").val(),
			    success:function(result){
			    if (result=="OK") {
						conditon=true;
						
					}
					else
			    	$("#erorrtip").html(result);
			    	
			    }
			});	
			});
		$("#dosubmit").click(function(){
		
		
					if(conditon){
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
					<form action="<c:url value = '/GoodsAddServlet'/>" method="post" id="form1"
						enctype="multipart/form-data">
						
						<span id="erorrtip">${erorrMsg }</span>
						<div id="table_box_1">
							<table class="form_table">
								<colgroup>
									<col width="150px">
									<col>
								</colgroup>
								<tbody>
									<tr>
										<th>商品名称：</th>
										<td><input class="normal" name="name" type="text"
											id="name" autofocus="autofocus" placeholder="请输入商品名称"
											value="" required="required"><span id="nametext1"></span></td>
									</tr>
									<tr>
										<th>所属分类：</th>
										<td><select name="categoryId">
												<c:forEach items="${categories}" var="category">
													<option value="${category.id}">${category.name}</option>
												</c:forEach>

										</select></td>
									</tr>
									<tr>
										<th>基本数据：</th>
										<td>
											<div class="con">
												<table class="border_table">
													<thead id="goodsBaseHead">
														<tr>
															<th>商品货号</th>
															<th>库存</th>
															<th>市场价格</th>
															<th>销售价格</th>
														</tr>
													</thead>
													<tbody id="goodsBaseBody">
														<tr class="td_c">
															<td><input class="small" name="goodsNum" type="text"
													id="goodsNum" value="自动生成" readonly="readonly" /></td>
															<td><input class="tiny" name="inventory" type="text" required="required"></td>
															<td><input class="tiny" name="price" type="text" required="required"></td>
															<td><input class="tiny" name="spprice" type="text" required="required"></td>
														</tr>
													</tbody>
												</table>
											</div>
										</td>
									</tr>
									<tr>
										<th>缩略图：</th>
										<td><input type="file" name="thumbnail" /></td>
									</tr>
									<tr>
										<th style="color:red">特别注意：</th>
										<td style="color:red">开头请详细输入商品品牌，型号，用，隔开(中文逗号)。</td>
									</tr>
									<tr>
										<th>产品描述：</th>
										<td><textarea rows="5" cols="5" name="info"
												id="description" placeholder="开头请详细输入商品品牌，型号，用，隔开。" required="required"></textarea></td>
									</tr>
								</tbody>
							</table>
						</div>
						<table class="form_table">
							<colgroup>
								<col width="150px">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td></td>
									<td><button class="submit" type="button" id = "dosubmit">
											<span>发布商品</span>
										</button></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div id="separator"></div>
	</div>
</body>
</html>