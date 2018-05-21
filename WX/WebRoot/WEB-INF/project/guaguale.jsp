<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>在线刮刮乐</title>
	<script type="text/javascript" src="/WX/js/jquery-1.8.0.min.js"></script>

	<link rel="stylesheet" href="/WX/css/guaguale.css" type="text/css"></link>
	
</head>

<body>

	<img src="image/banner_ggl.png" class="banner1" />
	<br>
	<div class="ggl" id="top">
		<div class="info" id="prize">
			<span id="prompt"></span>
			<span class="btn" id="ok">领取奖品</span> 
			<span class="btn" id="no">再来一次</span>
		</div>
		<canvas id="c1" class="canvas"></canvas>
	</div>
	<div class="num" style="display:none">
		您还有<span class="num1" ></span>次刮卡机会
	</div>
	
<!-- 	<div class="again_div">
		<span ><button id="again" class="again_btn">再来一次</button></span>
	</div> -->
	
	<!--<img src="img/guize.png" class="guize" />-->

	<!-- 遮罩层1抽奖次数已经用完-->
	<div class="pop pop1">
		<img src="img/pop1.png" />
	</div>
	<div class="pop pop2">
		<img src="img/pop2.png" id="pop2" />
	</div>
</body>
<script type="text/javascript" src="js/ggl.js"></script>
</html>
