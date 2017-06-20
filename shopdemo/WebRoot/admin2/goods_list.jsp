<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>后台管理</title>
<base href="${base}/" />
<link rel="stylesheet" href="css/admin.css" />
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
			<script type="text/javascript" charset="UTF-8"
				src="/iwebshop/runtime/_systemjs/artTemplate/artTemplate.js"></script>
			<script type="text/javascript" charset="UTF-8"
				src="/iwebshop/runtime/_systemjs/artTemplate/artTemplate-plugin.js"></script>
			<script type="text/javascript" charset="UTF-8"
				src="/iwebshop/runtime/_systemjs/my97date/wdatepicker.js"></script>
			<div class="headbar">
				<div class="position">
					<span>商品</span><span>></span><span>商品管理</span><span>></span><span>商品列表</span>
				</div>
				<div class="operating">
					<a href="javascript:void(0);"><button class="operating_btn"
							type="button"
							onclick="window.location.href='/iWebShop/index.php?controller=goods&action=goods_edit'">
							<span class="addition">添加商品</span>
						</button></a> <a href="javascript:void(0);"><button class="operating_btn"
							type="button" onclick="selectAll('id[]')">
							<span class="sel_all">全选</span>
						</button></a> <a href="javascript:void(0);"><button class="operating_btn"
							type="button" onclick="goods_del()">
							<span class="delete">删除</span>
						</button></a> <a href="javascript:void(0);"><button class="operating_btn"
							type="button" onclick="goods_stats('up')">
							<span class="import">上架</span>
						</button></a> <a href="javascript:void(0);"><button class="operating_btn"
							type="button" onclick="goods_stats('down')">
							<span class="export">下架</span>
						</button></a> <a href="javascript:void(0);"><button class="operating_btn"
							type="button" onclick="goodsCommend();">
							<span class="grade">商品推荐</span>
						</button></a>
				</div>

				<div class="searchbar">
					<form action="/iWebShop/index.php" method="get"
						name="searchListForm">
						<input type='hidden' name='controller' value='goods' /> <input
							type='hidden' name='action' value='goods_list' /> <select
							class="auto" name="search[seller_id]">
							<option value="">选择商品所属</option>
							<option value="=0">平台商品</option>
							<option value="!=0">商户商品</option>
						</select> <select class="auto" name="search[is_del]">
							<option value="">选择上下架</option>
							<option value="0">上架</option>
							<option value="2">下架</option>
							<option value="3">待审</option>
						</select> <select class="auto" name="search[store_nums]">
							<option value="">选择库存</option>
							<option value="go.store_nums < 1">无货</option>
							<option value="go.store_nums >= 1 and go.store_nums < 10">低于10</option>
							<option value="go.store_nums <= 100 and go.store_nums >= 10">10-100</option>
							<option value="go.store_nums >= 100">100以上</option>
						</select> <select class="auto" name="search[commend_id]">
							<option value="">选择商品标签</option>
							<option value="1">最新商品</option>
							<option value="2">特价商品</option>
							<option value="3">热卖商品</option>
							<option value="4">推荐商品</option>
						</select> <select class="auto" name="search[name]">
							<option value="go.name">商品名</option>
							<option value="go.goods_no">商品货号</option>
							<option value="seller.true_name">商户真实名称</option>
						</select> <input class="small" name="search[keywords]" type="text" value="" />

						<!--分类数据显示-->
						<span id="__categoryBox" style="margin-bottom:8px"></span>
						<button class="btn" type="button" name="_goodsCategoryButton">
							<span class="add">设置分类</span>
						</button>

						<button class="btn" type="button" onclick='initSearchbar(1)'>
							<span class="add">更 多</span>
						</button>
						<button class="btn" type="submit" onclick='changeAction(false)'>
							<span class="sel">筛 选</span>
						</button>
						<button class="btn" onclick='changeAction(true)'>
							<span class="sel">导出Excel</span>
						</button>
						<input type="hidden" name="search[adv_search]" value="" /> <input
							type="hidden" name="search[brand_id]" value="" /> <input
							type="hidden" name="search[sell_price]" value="" /> <input
							type="hidden" name="search[create_time]" value="" />
					</form>
				</div>
				<div class="searchbar" id="adv_searchbar" style="display:none;">
					<select class="auto" name="brand_id" id="brand_id">
						<option value="">选择商品品牌</option>
						<option value="1">哥弟</option>
						<option value="2">adidas</option>
						<option value="3">阿迪达斯</option>
						<option value="4">翔龙</option>
						<option value="5">美特斯匡威</option>
						<option value="6">匡威</option>
						<option value="7">美特斯邦威</option>
						<option value="8">邦威</option>
					</select> 销售价格：<input type="text" class="tiny" name="sell_price_start"
						id="sell_price_start" pattern="float" value="" />- <input
						type="text" class="tiny" name="sell_price_end" id="sell_price_end"
						pattern="float" value="" /> 创建时间：<input class="small" type="text"
						name="create_time_start" id="create_time_start"
						onfocus="WdatePicker()" value="" />- <input class="small"
						type="text" name="create_time_end" id="create_time_end"
						onfocus="WdatePicker()" value="" />
				</div>
			</div>

			<form action="" method="post" name="orderForm">
				<div class="content">
					<table class="list_table">
						<colgroup>
							<col width="40px" />
							<col width="360px" />
							<col width="180px" />
							<col width="90px" />
							<col width="70px" />
							<col width="80px" />
							<col width="70px" />
							<col width="110px" />
						</colgroup>

						<thead>
							<tr>
								<th>选择</th>
								<th>商品名称</th>
								<th>分类</th>
								<th>销售价</th>
								<th>库存</th>
								<th>状态</th>
								<th>排序</th>
								<th>操作</th>
							</tr>
						</thead>

						<tbody>
							<tr>
								<td><input name="id[]" type="checkbox" value="209" /></td>
								<td><img
									src='/iWebShop/index.php?controller=pic&action=thumb&img=upload@_@2016@_@01@_@22@_@55ac9689Ncc876cf1.jpg@_@w@_@100@_@h@_@100'
									class='ico' /><a class="orange"
									href="javascript:jumpUrl('0','/iWebShop/index.php?controller=site&action=products&id=209')"
									title="海外直采 意大利进口 卡维留里 蓝布鲁斯科 甜桃红 低泡葡萄酒 750ml">海外直采 意大利进口
										卡维留里 蓝布鲁斯科 甜桃红 低泡葡萄酒 750ml</a></td>
								<td>[食品饮料] [酒品] [红酒和白酒]</td>
								<td><a href="javascript:quickEdit(209,'price');"
									class="orange" title="点击更新价格" id="priceText209">86.00</a></td>
								<td><a href="javascript:quickEdit(209,'store');"
									class="orange" title="点击更新库存" id="storeText209">100</a></td>
								<td><select onchange="changeIsDel(209,this)">
										<option value="up" selected>上架</option>
										<option value="down">下架</option>
										<option value="check">待审</option>
								</select></td>
								<td><input type="text" class="tiny" value=""
									onchange="changeSort(209,this);" /></td>
								<td><a
									href="/iWebShop/index.php?controller=goods&action=goods_edit&id=209"><img
										class="operator"
										src="/iWebShop/views/sysdefault/skin/default/images/admin/icon_edit.gif"
										alt="编辑" /></a> <a href="javascript:void(0)"
									onclick="delModel({link:'/iWebShop/index.php?controller=goods&action=goods_del&id=209'})"><img
										class="operator"
										src="/iWebShop/views/sysdefault/skin/default/images/admin/icon_del.gif"
										alt="删除" /></a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>

			<div class='pages_bar'>
				<a href=''>首页</a><a class='current_page'
					href='/iWebShop/index.php?controller=goods&amp;action=goods_list&amp;page=1'>1</a><a
					href='/iWebShop/index.php?controller=goods&amp;action=goods_list&amp;page=2'>2</a><a
					href='/iWebShop/index.php?controller=goods&amp;action=goods_list&amp;page=3'>3</a><a
					href='/iWebShop/index.php?controller=goods&amp;action=goods_list&amp;page=4'>4</a><a
					href='/iWebShop/index.php?controller=goods&amp;action=goods_list&amp;page=5'>5</a><a
					href='/iWebShop/index.php?controller=goods&amp;action=goods_list&amp;page=9'>尾页</a><span>当前第1页/共9页</span>
			</div>
			<!--库存更新模板-->

		</div>
	</div>

</body>