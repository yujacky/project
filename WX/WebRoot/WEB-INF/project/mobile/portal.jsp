<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>游乐聚落部</title>
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Shortcut Icon" href="/WX/image/trip.jpg" type="image/x-icon">
<script type="text/javascript" src="/WX/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="/WX/js/amazeui.min.js"></script>
<!-- 	
	<script type="text/javascript" src="/WX/js/handlebars.min.js"></script>
	<script type="text/javascript" src="/WX/js/amazeui.widgets.helper.min.js"></script> 
-->
<link rel="stylesheet" href="/WX/css/amazeui.min.css">
<style type="text/css">
.jk-cm {
	margin: 0px;
}
.jk-cp {
	padding:0px !important;
}
.jk-text-show{
	overflow:hidden; 
	text-overflow:ellipsis;
	display:-webkit-box; 
	-webkit-box-orient:vertical;
	-webkit-line-clamp:2; 
}
.jk-img-fixed-w-d{
	width:400px;
	height:210px;
}
 a:visited {color:gray;} 
</style>
</head>

<body>
	<!-- 图片轮播 -->
	<div data-am-widget="slider" class="am-slider am-slider-c2"
		data-am-slider='{"directionNav":false}'>
		<ul class="am-slides">
			<li><img src="/WX/image/WxPortal/1.jpg" class="jk-img-fixed-w-d">
				<div class="am-slider-desc">远方 有一个地方 那里种有我们的梦想</div></li>
			<li><img src="/WX/image/WxPortal/2.jpg" class="jk-img-fixed-w-d">
				<div class="am-slider-desc">某天 也许会相遇 相遇在这个好地方</div></li>
			<li><img src="/WX/image/WxPortal/3.jpg" class="jk-img-fixed-w-d">
				<div class="am-slider-desc">不要太担心 只因为我相信 终会走过这条遥远的道路</div></li>
			<li><img src="/WX/image/WxPortal/4.jpeg" class="jk-img-fixed-w-d">
				<div class="am-slider-desc">OH PARA PARADISE 是否那么重要 你是否那么地遥远</div></li>
		</ul>
	</div>

	<!-- 主体内容 -->
	<div data-am-widget="tabs" class="am-tabs am-tabs-default jk-cm">
	<!-- 选项栏 -->
		<ul class="am-tabs-nav am-cf">
			<li class="am-active"><a href="[data-tab-panel-0]">推介</a></li>
			<li class=""><a href="[data-tab-panel-1]">本地</a></li>
			<li class=""><a href="[data-tab-panel-2]">异地</a></li>
		</ul>
	<!-- 列表 -->
		<div class="am-tabs-bd">
			<div data-tab-panel-0 class="am-tab-panel am-active jk-cp">
				<!-- 列表 -->
				<ul class="am-list am-list-border">
					<li class="am-g"><a href="http://192.168.0.101:8080/WX/mobile/gotoPage.do?pageId=123"
						class="am-list-item-hd ">test</a>
					</li>
					<li class="am-g"><a href="http://www.douban.com/online/11614662/"
						class="am-list-item-hd ">我很囧，你保重....晒晒旅行中的那些囧！</a>
					</li>
					<li class="am-g"><a
						href="http://www.douban.com/online/11624755/"
						class="am-list-item-hd ">我最喜欢的一张画</a>
					</li>
					<li class="am-g"><a
						href="http://www.douban.com/online/11645411/"
						class="am-list-item-hd " >“你的旅行，是什么颜色？” 晒照片，换北欧梦幻极光之旅！</a>
					</li>
					<li class="am-g"><a
						href="http://www.douban.com/online/11614662/"
						class="am-list-item-hd ">我很囧，你保重....晒晒旅行中的那些囧！</a>
					</li>
					<li class="am-g"><a
						href="http://www.douban.com/online/11624755/"
						class="am-list-item-hd ">我最喜欢的一张画</a>
					</li>
					<li class="am-g"><a
						href="http://www.douban.com/online/11645411/"
						class="am-list-item-hd ">“你的旅行，是什么颜色？” 晒照片，换北欧梦幻极光之旅！</a>
					</li>
				</ul>
			</div>
			<div data-tab-panel-1 class="am-tab-panel  jk-cp">
				<!-- 列表 -->
				<ul class="am-list am-list-border">
					<li class="am-g"><a href="http://www.douban.com/online/11614662/"
						class="am-list-item-hd ">我很囧，你保重....晒晒旅行中的那些囧！</a>
					</li>
					<li class="am-g"><a
						href="http://www.douban.com/online/11624755/"
						class="am-list-item-hd ">我最喜欢的一张画</a>
					</li>
					<li class="am-g"><a
						href="http://www.douban.com/online/11645411/"
						class="am-list-item-hd " >“你的旅行，是什么颜色？” 晒照片，换北欧梦幻极光之旅！</a>
					</li>
					<li class="am-g"><a
						href="http://www.douban.com/online/11614662/"
						class="am-list-item-hd ">我很囧，你保重....晒晒旅行中的那些囧！</a>
					</li>
					<li class="am-g"><a
						href="http://www.douban.com/online/11624755/"
						class="am-list-item-hd ">我最喜欢的一张画</a>
					</li>
					<li class="am-g"><a
						href="http://www.douban.com/online/11645411/"
						class="am-list-item-hd ">“你的旅行，是什么颜色？” 晒照片，换北欧梦幻极光之旅！</a>
					</li>
				</ul>
			</div>
			<div data-tab-panel-2 class="am-tab-panel  jk-cp">
				<!-- 列表 -->
				<ul class="am-list am-list-border">
					<li class="am-g"><a href="http://www.douban.com/online/11614662/"
						class="am-list-item-hd ">我很囧，你保重....晒晒旅行中的那些囧！</a>
					</li>
					<li class="am-g"><a
						href="http://www.douban.com/online/11624755/"
						class="am-list-item-hd ">我最喜欢的一张画</a>
					</li>
					<li class="am-g"><a
						href="http://www.douban.com/online/11645411/"
						class="am-list-item-hd " >“你的旅行，是什么颜色？” 晒照片，换北欧梦幻极光之旅！</a>
					</li>
					<li class="am-g"><a
						href="http://www.douban.com/online/11614662/"
						class="am-list-item-hd ">我很囧，你保重....晒晒旅行中的那些囧！</a>
					</li>
					<li class="am-g"><a
						href="http://www.douban.com/online/11624755/"
						class="am-list-item-hd ">我最喜欢的一张画</a>
					</li>
					<li class="am-g"><a
						href="http://www.douban.com/online/11645411/"
						class="am-list-item-hd ">“你的旅行，是什么颜色？” 晒照片，换北欧梦幻极光之旅！</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
