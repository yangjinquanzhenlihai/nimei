<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<base href="${base}" />
<meta charset="UTF-8" />
<title>企业会员中心 - 人才系统</title>
<link rel="shortcut icon" href="favicon.ico" />
<link href="styles/user.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<!--导航 -->
<div class="floatnav">
 <jsp:include page="../nav.jsp"></jsp:include>
</div>
<!--导航end -->
<div class="page_location link_bk">

当前位置：<a href="//">首页</a>&nbsp;>>&nbsp;<a href="company_index.php">会员中心</a>&nbsp;>>&nbsp;公司信息&nbsp;>>&nbsp;标注电子地图
</div>
<table width="985" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px;" >
  <tr>
    <td width="173" valign="top" class="link_bk">
			<jsp:include page="left.jsp"></jsp:include>
	</td>
    <td valign="top">
		<div class="user_right_box">
		<div class="user_right_top_tit_bg">		
		  <h1>设置电子地图</h1>
		</div>
		<table width="100%" border="0" cellpadding="4" cellspacing="0" bgcolor="#FFFFFF">
   <tr>
          <td height="30" bgcolor="#FFFFFF" class="link_lan"  style="padding-left:20px; color: #009900;">请在地图上找见您的具体位置，然后点击地图右上角“标记我的位置”，标记位置完成后点击“保存我的位置”。
		  <form action="?act=company_map_set_save" method="post"  style="padding:0px; margin:0px;" id="form1" name="form1">
  <input name="x" type="hidden" id="x"><input name="y" type="hidden" id="y"><input name="zoom" type="hidden" id="zoom">
</form>
		  </td>
</tr>
<tr>
<td  height="500" valign="top"  bgcolor="#FFFFFF">
<div style="width:750px;height:450px;border:3px solid  #CCCCCC;  margin:0 auto; cursor:" id="container"></div>
<script type="text/javascript">
var map = new BMap.Map("container");
//假如已经设置了标注
var opts = {   
  width : 250,     // 信息窗口宽度   
  height: 80,     // 信息窗口高度   
  title : "guoxinan"  // 信息窗口标题   
}
var infoWindow = new BMap.InfoWindow("3123124234234", opts);  // 创建信息窗口对象 
//假如有设置的，显示参数
var point = new BMap.Point(112.524623, 37.622039);
map.centerAndZoom(point, 18); 
var qs_marker = new BMap.Marker(point);        // 创建标注   
map.addOverlay(qs_marker);  
map.openInfoWindow(infoWindow,point);
map.setCenter(point);
document.getElementById("x").value=112.524623; 
document.getElementById("y").value= 37.622039; 
document.getElementById("zoom").value=  18; 
map.addControl(new BMap.NavigationControl());//添加鱼骨
map.enableScrollWheelZoom();//启用滚轮放大缩小，默认禁用。
var myPushpin = new BMap.PushpinTool(map,{
cursor : "crosshair"  
});   // PushpinTool创建标注工具实例
myPushpin.addEventListener("markend", function(e){//添加题地图监控
// 打开信息窗口
map.openInfoWindow(infoWindow,e.marker.getPoint());   
document.getElementById("x").value=e.marker.getPoint().lng; 
document.getElementById("y").value= e.marker.getPoint().lat; 
document.getElementById("zoom").value= map.getZoom(); 
//alert(map.getZoom());//地图缩放级别
//map.zoomIn();地图放大一个级别
//alert("您标注的位置：" + e.marker.getPoint().lng + ", " + e.marker.getPoint().lat);
//打开提示层
});
//myPushpin.setIcon(icon:Icon);
// 定义一个控件类，即function   
function ZoomControl(){   
  // 设置默认停靠位置和偏移量   
  this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;   
  this.defaultOffset = new BMap.Size(108, 10);   
}
function SaveMap(){   
  // 设置默认停靠位置和偏移量   
  this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;   
  this.defaultOffset = new BMap.Size(10, 10);   
}  
// 通过JavaScript的prototype属性继承于BMap.Control   
ZoomControl.prototype = new BMap.Control();
SaveMap.prototype = new BMap.Control();   
  
// 自定义控件必须实现自己的initialize方法，并且将控件的DOM元素返回   
// 在本方法中创建个div元素作为控件的容器，并将其添加到地图容器中   
ZoomControl.prototype.initialize = function(map){   
  // 创建一个DOM元素   
  var div = document.createElement("div");   
  // 添加文字说明   
  div.appendChild(document.createTextNode("标记我的位置"));   
  // 设置样式  
  div.style.cursor = "pointer";   
  div.style.padding = "6px"; 
  div.style.color = "#FFFFFF"; 
  div.style.border = "1px solid #003300";   
  div.style.backgroundColor = "#009900";   
  div.onclick = function(e){ 
  map.clearOverlays();//清楚地图上所有覆盖物
  myPushpin.open();//打开标注工具
  }   
  // 添加DOM元素到地图中   
  map.getContainer().appendChild(div);   
  // 将DOM元素返回   
  return div;   
}
SaveMap.prototype.initialize = function(map){   
  // 创建一个DOM元素   
  var div = document.createElement("div");   
  // 添加文字说明   
  div.appendChild(document.createTextNode("保存我的位置"));   
  // 设置样式 
  div.style.cursor = "pointer";   
  div.style.padding = "6px"; 
  div.style.color = "#FFFFFF"; 
  div.style.border = "1px solid #990000";   
  div.style.backgroundColor = "#FF6600";   
  div.onclick = function(e){ 
  chk_form();//验证
  }   
  // 添加DOM元素到地图中   
  map.getContainer().appendChild(div);   
  // 将DOM元素返回   
  return div;   
}   
  
// 创建控件   
var myZoomCtrl = new ZoomControl(); 
var SaveMapsub = new SaveMap(); 
// 添加到地图当中   
map.addControl(myZoomCtrl);
map.addControl(SaveMapsub);
//验证表单
function chk_form()
{
if (document.form1.x.value=="" || document.form1.y.value=="")
	{
	alert("请先在地图上标记您的位置")
	return (false);
	}
	else
	{
	document.form1.submit();
	}	
}
</script></td>
        </tr>
	  </table>
		</div>
    </td>
  </tr>
</table>
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
