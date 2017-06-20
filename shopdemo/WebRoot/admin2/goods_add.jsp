<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>后台管理</title>
<base href="${base}/" />
<link rel="stylesheet" href="css/admin.css" />
<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
<script type="text/javascript">
function select_tab(curr_tab)
{
	$("form[name='goodsForm'] > div").hide();
	$("#table_box_"+curr_tab).show();
	$("ul[name=menu1] > li").removeClass('selected');
	$('#li_'+curr_tab).addClass('selected');
}

</script>
</head>
<body>
	<div class="container">
		<div id="header">
			<jsp:include page="top.jsp"></jsp:include>
		</div>
		<div id="admin_left">
			<jsp:include page="left.jsp"></jsp:include>
		</div>

		<div id="admin_right">
			<div class="headbar clearfix">
				<div class="position">
					<span>商品</span><span>></span><span>商品管理</span><span>></span><span>商品编辑</span>
				</div>
				<ul class="tab" name="menu1">
					<li id="li_1" class="selected"><a href="javascript:void(0)"
						hidefocus="true" onclick="select_tab('1')">商品信息</a></li>
					<li id="li_2"><a href="javascript:void(0)" hidefocus="true"
						onclick="select_tab('2')">描述</a></li>
				</ul>
			</div>

			<div class="content_box">
				<div class="content form_content">
					<form action="" name="goodsForm" method="post">
						<input type="hidden" name="id" value="" /> <input type='hidden'
							name="img" value="" /> <input type='hidden' name="_imgList"
							value="" /> <input type='hidden' name="callback" value="" />

						<div id="table_box_1">
							<table class="form_table">
								<col width="150px" />
								<col />
								<tr>
									<th>商品名称：</th>
									<td><input class="normal" name="name" type="text" value=""
										pattern="required" alt="商品名称不能为空" /><label>*</label></td>
								</tr>
								<tr>
									<th>所属分类：</th>
									<td><script type="text/javascript">
										
									</script> <a href='' class='orange'>请点击添加分类</a></td>
								</tr>
								<tr>
									<th>是否上架：</th>
									<td><label class='attr'><input type="radio"
											name="is_del" value="0" checked> 是</label> <label
										class='attr'><input type="radio" name="is_del"
											value="2"> 否</label> <label>只有上架的商品才会在前台显示出来，客户是无法看到下架商品</label>
									</td>
								</tr>
								<tr>
									<th>附属数据：</th>
									<td>
										<div class="con">
											<table class="border_table">
												<thead>
													<tr>
														<th>购买成功增加积分</th>
														<th>排序</th>
														<th>计量单位显示</th>
														<th>购买成功增加经验值</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td><input class="small" name="point" type="text"
															pattern="int" value="0" /></td>
														<td><input class="small" name="sort" type="text"
															pattern="int" value="99" /></td>
														<td><input class="small" name="unit" type="text"
															value="千克" /></td>
														<td><input class="small" name="exp" type="text"
															pattern="int" value="0" /></td>
													</tr>
												</tbody>
											</table>
										</div>
									</td>
								</tr>
								<tr>
									<th>基本数据：</th>
									<td>
										<div class="con">
											<table class="border_table">
												<thead id="goodsBaseHead"></thead>

												<!--商品标题模板-->
												<script id="goodsHeadTemplate" type='text/html'>
									<tr>
										
									</tr>
									</script>

												<tbody id="goodsBaseBody"></tbody>

												<!--商品内容模板-->
												<script id="goodsRowTemplate" type="text/html">
									
									</script>
											</table>
										</div>
									</td>
								</tr>
								<tr>
									<th>商品推荐类型：</th>
									<td><label class="attr"><input
											name="_goods_commend[]" type="checkbox" value="1" />最新商品</label> <label
										class="attr"><input name="_goods_commend[]"
											type="checkbox" value="2" />特价商品</label> <label class="attr"><input
											name="_goods_commend[]" type="checkbox" value="3" />热卖商品</label> <label
										class="attr"><input name="_goods_commend[]"
											type="checkbox" value="4" />推荐商品</label></td>
								</tr>
								<tr>
									<th>产品相册：</th>
									<td><input class="middle" type="text" disabled />
										<div class="upload_btn">
											<span id="uploadButton"></span>
										</div> <label>可以上传多张图片，分辨率3000px以下，大小不得超过2M</label></td>
								</tr>
								<tr>
									<td></td>
									<td id="divFileProgressContainer"></td>
								</tr>
								<tr>
									<td></td>
									<td id="thumbnails"></td>
								</tr>
							</table>
						</div>

						<div id="table_box_2" cellpadding="0" cellspacing="0"
							style="display:none">
							<table class="form_table">
								<colgroup>
									<col width="150px" />
									<col />
								</colgroup>
								<tr>
									<th>产品描述：</th>
									<td><textarea id="content" name="content"
											style="width:700px;height:400px;">
												此处使用UEditor
											</textarea></td>
								</tr>
							</table>
						</div>

						<table class="form_table">
							<col width="150px" />
							<col />
							<tr>
								<td></td>
								<td><button class="submit" type="submit"
										onclick="return checkForm()">
										<span>发布商品</span>
									</button></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
</body>