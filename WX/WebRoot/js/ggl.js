//控制刮卡次数
var t = 1;
// 初始化所有数据并且随机产生奖品
var initialize = function() {
	// 剩余刮卡次数
	$('.num1').html(4 - t);
	// 随机数
	function getRandomNum(lbound, ubound) {
		return (Math.floor(Math.random() * (ubound - lbound)) + lbound);
	}
	var r = getRandomNum(1, 100);
	var btn = document.getElementsByClassName("btn");
	for ( var i = 0; i < btn.length; i++) {
		btn[i].style.zIndex = '1';
	}
	document.getElementById("no").style.display = "none";
	document.getElementById("ok").style.display = "none";

	// 初始化涂抹面积
	isOk = 0;

	if (r < t * 33) {
		document.getElementById("prompt").innerHTML = "恭喜您，中奖了！"
		var ok = document.getElementById("ok");
		ok.style.display = "block";
		// 点击领取奖品
		ok.onclick = function() {
			// window.location.href="prize.html"
			alert("一天到晚想着一夜发财，好好努力工作吧！ --By junxian");
		};
	} else {
		document.getElementById("prompt").innerHTML = "很遗憾，未中奖！"
		document.getElementById("no").style.display = "block";
	}
};

var c1; // 画布
var ctx; // 画笔
var ismousedown; // 标志用户是否按下鼠标或开始触摸
var isOk = 0; // 标志用户是否已经刮开了一半以上
var fontem = parseInt(window.getComputedStyle(document.documentElement, null)["font-size"]);// 这是为了不同分辨率上配合@media自动调节刮的宽度

/* 页面加载后开始初始化画布 */
window.onload = function() {

	initialize();
	c1 = document.getElementById("c1");

	// 这里很关键，canvas自带两个属性width、height,我理解为画布的分辨率，跟style中的width、height意义不同。
	// 最好设置成跟画布在页面中的实际大小一样
	// 不然canvas中的坐标跟鼠标的坐标无法匹配
	c1.width = c1.clientWidth;
	c1.height = c1.clientHeight;
	ctx = c1.getContext("2d");

	// PC端的处理
	c1.addEventListener("mousemove", eventMove, false);
	c1.addEventListener("mousedown", eventDown, false);
	c1.addEventListener("mouseup", eventUp, false);

	// 移动端的处理
	c1.addEventListener('touchstart', eventDown, false);
	c1.addEventListener('touchend', eventUp, false);
	c1.addEventListener('touchmove', eventMove, false);

	// 初始化
	initCanvas();
}

// 初始化画布，画灰色的矩形铺满
function initCanvas() {
	// 网上的做法是给canvas设置一张背景图片，我这里的做法是直接在canvas下面另外放了个div。
	// c1.style.backgroundImage="url(中奖图片.jpg)";
	ctx.globalCompositeOperation = "source-over";
	ctx.fillStyle = '#aaaaaa';
	ctx.fillRect(0, 0, c1.clientWidth, c1.clientHeight);
	ctx.fill();

	ctx.font = "Bold 30px Arial";
	ctx.textAlign = "center";
	ctx.fillStyle = "#999999";
	ctx.fillText("刮刮乐", c1.width / 2, 50);

	// 把这个属性设为这个就可以做出圆形橡皮擦的效果
	// 有些老的手机自带浏览器不支持destination-out,下面的代码中有修复的方法
	ctx.globalCompositeOperation = 'destination-out';
}

// 鼠标按下 和 触摸开始
function eventDown(e) {
	e.preventDefault();
	ismousedown = true;
}

// 鼠标抬起 和 触摸结束
function eventUp(e) {
	e.preventDefault();

	// 得到canvas的全部数据
	var a = ctx.getImageData(0, 0, c1.width, c1.height);
/*	console.log(a.data);
	console.log(a.data.length);*/
	var j = 0;
	for ( var i = 3; i < a.data.length; i += 4) {
		if (a.data[i] == 0)
			j++;
	}

	// 当被刮开的区域等于一半时，则可以开始处理结果
	if (j >= a.data.length / 8) {
		isOk = 1;
	}
	ismousedown = false;
}

// 鼠标移动 和 触摸移动
function eventMove(e) {
	e.preventDefault();
	if (ismousedown) {
		if (e.changedTouches) {
			e = e.changedTouches[e.changedTouches.length - 1];
		}
		var topY = document.getElementById("top").offsetTop;
		var oX = c1.offsetLeft, oY = c1.offsetTop + topY;

		var x = (e.clientX + document.body.scrollLeft || e.pageX) - oX || 0, y = (e.clientY
				+ document.body.scrollTop || e.pageY)
				- oY || 0;

		// 画360度的弧线，就是一个圆，因为设置了ctx.globalCompositeOperation = 'destination-out';
		// 画出来是透明的
		ctx.beginPath();
		ctx.arc(x, y, fontem * 1.2, 0, Math.PI * 2, true);

		// 下面3行代码是为了修复部分手机浏览器不支持destination-out
		// 我也不是很清楚这样做的原理是什么
		c1.style.display = 'none';
		c1.offsetHeight;
		c1.style.display = 'inherit';

		ctx.fill();
	}

	if (isOk) {
		var btn = document.getElementsByClassName("btn");
		for ( var i = 0; i < btn.length; i++) {
			btn[i].style.zIndex = '3';
		}
		document.getElementsByClassName("btn")[0].style.zIndex = "3";
	}
}

// 没有中奖再来一次
$("#no").click(function() {
	if (t > 3) {
		// 因该弹出遮罩层提示您的次数已经用完了
		$('.pop1').show();
		$('.pop1 img').click(function() {
			$('.pop1').hide();
		})
	} else {
		t++;
		// 初始化按钮
		document.getElementById("no").style.display = "none";
		document.getElementById("ok").style.display = "none";
		window.onload();
		initCanvas();

	}
});
