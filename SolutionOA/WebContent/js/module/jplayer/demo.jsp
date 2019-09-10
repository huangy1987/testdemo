<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>jPlayer</title>

<link rel="stylesheet" href="css/not.the.skin.css">
<link rel="stylesheet" href="circle.skin/circle.player.css">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.transform2d.js"></script>
<script type="text/javascript" src="js/jquery.grab.js"></script>
<script type="text/javascript" src="js/jquery.jplayer.js"></script>
<script type="text/javascript" src="js/mod.csstransforms.min.js"></script>
<script type="text/javascript" src="js/circle.player.js"></script>

<style type="text/css">
body{
	margin: 0;
  	padding:0;
	height: 100%;
}
#defaultPlayer{
	margin-top: 30px;
}
#jquery_jplayer_1, .prototype-wrapper{
	display: none;
}
#closeBtn{
	margin: 20px 0 0 120px;
}
.prototype-wrapper{
	float: left;
}
#voiceWords1{
	float: left;
	margin-top: 10px;
	padding: 5px;
	font-size: 14px;
	height: 50px;
	width: 300px;
	overflow-y: auto;
	line-height: 24px;
	border: 1px dotted #bbb;
	border-radius: 5px;
}
#voiceWords2{
	float: left;
	margin-top: 35px;
	padding: 5px;
	font-size: 14px;
	height: 120px;
	width: 130px;
	overflow-y: auto;
	line-height: 24px;
	border: 1px dotted #bbb;
	border-radius: 5px;
	display: none;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	var fileUrl = $('#basePath').val() + $(window.parent.document).find('#fileUrl').val();
	fileUrl = fileUrl.replace(/\\/g,'/');
	//IE浏览器
	if($.browser.msie){
		//语音内容
		$('#voiceWords1').show().html($(window.parent.document).find('#soundWords').val());
		$('#defaultPlayerDiv').append('<embed id="defaultPlayer" src=' + fileUrl + ' width="300px" height="45px" autostart="true"></embed><br/><input type="button" id="closeBtn" value="关 闭" />');
		//手动关闭弹出框
		$('#closeBtn').click(function(){
			$('#playVoiceIframe', parent.document).attr('src', '');
			parent.artDialog({id: 'onlineListen'}).close();
		})
	}else{	//其他浏览器
		$('#defaultPlayerDiv').hide();
		$('.prototype-wrapper').show();
		//语音内容
		$('#voiceWords2').show().html($(window.parent.document).find('#soundWords').val());
		//播放器
		$('#jquery_jplayer_1').jPlayer({
			ready: function(){
				$(this).jPlayer("volume", 1);
			},
			ended: function(){
				$('#playVoiceIframe', parent.document).attr('src', '');
				parent.artDialog({id: 'onlineListen'}).close();
			},
			supplied: 'wav'
		});
		var myCirclePlayer = new CirclePlayer('#jquery_jplayer_1',{
			wav: fileUrl	//'sounds/rtyedt.wav'
		}, {
			cssSelectorAncestor: '#cp_container_1',
			autoplay: true
		})
	}
});
</script>
</head>
<body>

	<input type="hidden" id="basePath" value="<%=basePath %>" />
	<div id="defaultPlayerDiv">
		<div id="voiceWords1"></div>
	</div>

	<div id="jquery_jplayer_1" class="cp-jplayer"></div>
	<div class="prototype-wrapper">
		<div id="cp_container_1" class="cp-container">
			<div class="cp-buffer-holder">
				<div class="cp-buffer-1"></div>
				<div class="cp-buffer-2"></div>
			</div>
			<div class="cp-progress-holder">
				<div class="cp-progress-1"></div>
				<div class="cp-progress-2"></div>
			</div>
			<div class="cp-circle-control"></div>
			<ul class="cp-controls">
				<li><a class="cp-play" tabindex="1">播放</a></li>
				<li><a class="cp-pause" style="display:none" tabindex="1">暂停</a></li>
			</ul>
		</div>
	</div>
	<div id="voiceWords2"></div>
</body>
</html>