<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>系统登录</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="js/easyui/themes/default/easyui.css" type="text/css"></link>
     <link rel="stylesheet" href="js/easyui/themes/icon.css" type="text/css"></link>
     <link rel="stylesheet" type="text/css" href="js/module/poshytip/src/tip-violet/tip-violet.css" />
      <link rel="stylesheet" type="text/css" href="css/login.css" />
     
     <script type="text/javascript" src="js/easyui/jquery-1.7.2.min.js" charset="UTF-8"></script>
 	 <script type="text/javascript" src="js/easyui/jquery.easyui.min.js" charset="UTF-8"></script>
 	 <script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script>
 	 <script type="text/javascript" src="js/module/poshytip/src/jquery.poshytip.min.js"></script>
	<script type="text/javascript" src="js/common/tool.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
</head>
  
<body onkeydown="keyLogin();">  
<div id="main" style="margin-left: auto;margin-right: auto;">
	<div id="bgpic" >
		<img alt="soultion" src="images/bg1.jpg">
	</div>
</div>

<div id = "bg" style="width: 850px;height: 300px; border:1px solid gray;display:none; background-color: gray; left:15%; margin:0 0 0 0; position:absolute; top:1%">
	<div id="loginAndRegDialog" style="overflow: hidden;">
		<br/><br/><br/>
		<form id="loginInputForm" method="post" >
		<table>
			<tr>	
				<th align="right">&nbsp;&nbsp;&nbsp;工&nbsp;&nbsp;&nbsp;号</th>
				<td><input type="text" id="loginName" name="loginName" /></td>
			</tr>
			<tr>
				<th align="right">&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;码</th>
				<td><input id="passWord" name="passWord" type="password" />
			</tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>