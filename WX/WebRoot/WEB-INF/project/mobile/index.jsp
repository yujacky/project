<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<title>Amaze UI Examples</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Set render engine for 360 browser -->
<meta name="renderer" content="webkit">
<!-- No Baidu Siteapp-->
<meta http-equiv="Cache-Control" content="no-siteapp" />

<script type="text/javascript" src="/WX/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="/WX/js/amazeui.min.js"></script>
<link rel="stylesheet" href="/WX/css/amazeui.min.css">
<!-- <link rel="stylesheet" href="assets/css/app.css"> -->
</head>
<body>

	<h1>aah1 标题1</h1>
	<h2>h2 标题2</h2>
	<h3>h3 标题3</h3>
	<h4>h4 标题4</h4>
	<h5>h5 标题5</h5>
	<h6>h6 标题6</h6>

	<div class="am-g">
		<div class="am-u-sm-4">4</div>
		<div class="am-u-sm-8">8</div>
	</div>

	<div class="am-g doc-am-g">
		<div class="am-u-sm-6 am-u-md-4 am-u-lg-3"
			style="background-color:red">sm-6 md-4 lg-3</div>
		<div class="am-u-sm-6 am-u-md-8 am-u-lg-9"
			style="background-color:yellow">sm-6 md-8 lg-9</div>
	</div>

	<div class="am-container" style="background-color:blue">I'm in
		the .am-container.</div>

	<div class="am-g">
		<div class="am-u-sm-3" style="background-color:blue">3</div>
		<div class="am-u-sm-3" style="background-color:orange">3</div>
		<div class="am-u-sm-3 am-u-end" style="background-color:green">3</div>
	</div>


	<ul class="am-avg-sm-3 boxes">
		<li class="box box-1">1</li>
		<li class="box box-2">2</li>
		<li class="box box-3">3</li>
		<li class="box box-4">4</li>
		<li class="box box-5">5</li>
		<li class="box box-6">6</li>
		<li class="box box-7">7</li>
		<li class="box box-8">8</li>
		<li class="box box-9">9</li>
	</ul>

	<div class="am-cf">
		<button class="am-btn am-fl am-btn-success">向左浮动</button>
		<button class="am-btn am-fr am-btn-success">向右浮动</button>
	</div>

	<div class="am-vertical-align"
		style="height: 150px;background-color:yellow">
		<div class="am-vertical-align-bottom">飘在半空中的 XX</div>
	</div>

	<p>...</p>
	<p class="am-text-primary">111</p>
	<p class="am-text-secondary">222</p>
	<p class="am-text-success">333</p>
	<p class="am-text-warning">444</p>
	<p class="am-text-danger am-text-xxxl">555</p>

	<button class="am-btn am-btn-default">
		<i class="am-icon-cog"></i> 设置
	</button>

	<a class="am-btn am-btn-warning" href="#"> <i
		class="am-icon-shopping-cart"></i> 结账 </a>

	<button class="am-btn am-btn-default">
		<i class="am-icon-spinner am-icon-spin"></i> 加载中
	</button>

	<button class="am-btn am-btn-primary">
		下载片片 <i class="am-icon-cloud-download"></i>
	</button>

	<div class="am-form-group am-form-file">
		<button type="button" class="am-btn am-btn-default am-btn-sm">
			<i class="am-icon-cloud-upload"></i>选择要上传的文件
		</button>
		<input type="file" multiple>
	</div>

	<hr />

	<div class="am-form-group am-form-file">
		<i class="am-icon-cloud-upload"></i> 选择要上传的文件 <input type="file"
			multiple>
	</div>

	<div class="am-form-group am-form-file">
		<button type="button" class="am-btn am-btn-danger am-btn-sm">
			<i class="am-icon-cloud-upload"></i> 选择要上传的文件
		</button>
		<input id="doc-form-file" type="file" multiple>
	</div>
	<div id="file-list"></div>
	<script>
		$(function() {
			$('#doc-form-file').on(
					'change',
					function() {
						var fileNames = '';
						$.each(this.files, function() {
							fileNames += '<span class="am-badge">' + this.name
									+ '</span> ';
						});
						$('#file-list').html(fileNames);
					});
		});
	</script>
	<hr>

	<form class="am-form">
		<fieldset>
			<legend>表单标题</legend>

			<div class="am-form-group">
				<label for="doc-ipt-email-1">邮件</label> <input type="email" class=""
					id="doc-ipt-email-1" placeholder="输入电子邮件">

			</div>

			<div class="am-form-group">
				<label for="doc-ipt-pwd-1">密码</label> <input type="password"
					class="" id="doc-ipt-pwd-1" placeholder="设置个密码吧">
			</div>

			<div class="am-form-group">
				<label for="doc-ipt-file-1">原生文件上传域</label> <input type="file"
					id="doc-ipt-file-1">
				<p class="am-form-help">请选择要上传的文件...</p>
			</div>

			<div class="am-form-group am-form-file">
				<label for="doc-ipt-file-2">Amaze UI 文件上传域</label>
				<div>
					<button type="button" class="am-btn am-btn-default am-btn-sm">
						<i class="am-icon-cloud-upload"></i> 选择要上传的文件
					</button>
				</div>
				<input type="file" id="doc-ipt-file-2">
			</div>

			<div class="am-checkbox">
				<label> <input type="checkbox"> 复选框，选我选我选我 </label>
			</div>

			<div class="am-radio-inline">
				<label> <input type="radio" name="doc-radio-1"
					value="option1" checked> 单选框 - 选项1 </label>
			</div>

			<div class="am-radio-inline">
				<label> <input type="radio" name="doc-radio-1"
					value="option2"> 单选框 - 选项2 </label>
			</div>

			<div class="am-form-group">
				<label class="am-checkbox-inline"> <input type="checkbox"
					value="option1"> 选我 </label> <label class="am-checkbox-inline">
					<input type="checkbox" value="option2"> 同时可以选我 </label> <label
					class="am-checkbox-inline"> <input type="checkbox"
					value="option3"> 还可以选我 </label>
			</div>

			<div class="am-form-group">
				<label class="am-radio-inline"> <input type="radio" value=""
					name="docInlineRadio"> 每一分 </label> <label class="am-radio-inline">
					<input type="radio" name="docInlineRadio"> 每一秒 </label> <label
					class="am-radio-inline"> <input type="radio"
					name="docInlineRadio"> 多好 </label>
			</div>

			<div class="am-form-group">
				<label for="doc-select-1">下拉多选框</label> <select id="doc-select-1">
					<option value="option1">选项一...</option>
					<option value="option2">选项二.....</option>
					<option value="option3">选项三........</option>
				</select> <span class="am-form-caret"></span>
			</div>

			<div class="am-form-group">
				<label for="doc-select-2">多选框</label> <select multiple class=""
					id="doc-select-2">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
				</select>
			</div>

			<div class="am-form-group">
				<label for="doc-ta-1">文本域</label>
				<textarea class="" rows="5" id="doc-ta-1"></textarea>
			</div>

			<p>
				<button type="submit" class="am-btn am-btn-default">提交</button>
			</p>
		</fieldset>
	</form>
	
	<form class="am-form am-form-horizontal">
	  <div class="am-form-group">
	    <label for="doc-ipt-3" class="am-u-sm-2 am-form-label">电子邮件</label>
	    <div class="am-u-sm-10">
	      <input type="email" id="doc-ipt-3" placeholder="输入你的电子邮件">
	    </div>
	  </div>
	
	  <div class="am-form-group">
	    <label for="doc-ipt-pwd-2" class="am-u-sm-2 am-form-label">密码</label>
	    <div class="am-u-sm-10">
	      <input type="password" id="doc-ipt-pwd-2" placeholder="设置一个密码吧">
	    </div>
	  </div>
	
	  <div class="am-form-group">
	    <div class="am-u-sm-offset-2 am-u-sm-10">
	      <div class="checkbox">
	        <label>
	          <input type="checkbox"> 记住十万年
	        </label>
	      </div>
	    </div>
	  </div>
	
	  <div class="am-form-group">
	    <div class="am-u-sm-10 am-u-sm-offset-2">
	      <button type="submit" class="am-btn am-btn-danger">提交登入</button>
	    </div>
	  </div>
	</form>
	
	<hr>
	<div class="am-container">
	<p><input type="text" class="am-form-field am-radius" placeholder="圆角表单域" /></p>
<p><input type="text" class="am-form-field am-round" placeholder="椭圆表单域" disabled/></p></div>

	<p>
	  <img class="am-radius" alt="140*140" src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg?imageView/1/w/1000/h/1000/q/80" width="140" height="140" />
	
	  <img class="am-round am-img-thumbnail" alt="140*140" src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg?imageView/1/w/1000/h/600/q/80" width="200" height="120"/>
	
	  <img class="am-circle" src="/WX/image/ggl.png" width="140" height="140"/>
	</p>
	
	<div class="am-container">
	<table class="am-table am-table-bordered am-table-striped am-table-hover">
		<thead>
			<tr class="am-warning">
				<td>网站名称</td>
				<td>网址</td>
			</tr>
		</thead>
		<tbody>
			<tr class="">
				<td>百度</td>
				<td>www.baidu.com</td>
			</tr>
						<tr class="">
				<td>百度</td>
				<td>www.baidu.com</td>
			</tr>
						<tr class="am-danger">
				<td>百度</td>
				<td><a class="am-badge am-badge-danger am-round am-text-xl">Danger</a> www.baidu.com</td>
			</tr>
		</tbody>
	</table>
	</div>
	
	<div class="am-container">
		<ol class="am-breadcrumb">
			<li><a>首页</a></li>
			<li><a>导航1</a></li>
			<li><a>导航2</a></li>
		</ol>
	</div>
	
	<div class="am-container">
		<div class="am-btn-group am-btn-justify">
			<a class="am-btn am-btn-danger am-round">1</a>
			<a class="am-btn am-btn-danger">1</a>
			<a class="am-btn am-btn-danger">1</a>
			
		</div>
	</div>
	
	<div class="am-btn-group am-btn-group-justify">
	  <a class="am-btn am-btn-primary" role="button">左手</a>
	  <a class="am-btn am-btn-primary" role="button">左手</a>
	  <a class="am-btn am-btn-primary" role="button">左手</a>
	  <a class="am-btn am-btn-primary" role="button">左手</a>
	</div>
	
	<div class="am-btn-toolbar">
		<div class="am-btn-group">
			<button class="am-btn am-btn-primary">1</button>
			<button class="am-btn am-btn-danger">1</button>
			<button class="am-btn am-btn-danger">1</button>
		</div>
	</div>
	<hr>
	<div class="am-btn-group-stacked">
		<button class="am-btn am-btn-primary">test</button>
		<button class="am-btn am-btn-primary">test</button>
		<button class="am-btn am-btn-primary">test</button>
		<button class="am-btn am-btn-primary">test</button>
	</div>
</body>
</html>