<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" 
contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.solution.entity.Account"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head id="Head1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>四川塞尔努型办公系统</title>
    <link href="css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="themes/default/easyui.css" />
    <script type="text/javascript" src="js/jquery-1.4.4.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/index.js"> </script>
    <script type="text/javascript" src="js/common/tool.js"></script>
    </head>
    <body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<noscript>
    <div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;"> <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' /> </div>
    </noscript>
    
<div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
         <span style="float:right; padding-right:20px;" class="head"></span>
          <span style="padding-left:10px; font-size: 16px; ">
 			<!-- 判断用户是否登录 -->
			  <c:if test="${empty sessionScope.user}">
			  	<!-- 让父容器页面跳转 -->	
			  	<script type="text/javascript" charset="utf-8">window.parent.location.href='login.jsp'</script>
			  </c:if>
 		 ${user.loginName }&nbsp; 您好！欢迎访问四川塞尔努型办公管理系统
          </span>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;角色: ${role.roleName}
          <a id="loginOut" ><span style="padding-left:30px; font-size: 14px;color:#ccc; ">&lt; 注销 &gt;</span></a>
        <%  
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = "9999-12-31 00:00:00";//默认 
			Account acc =(Account) request.getAttribute("user");
			if(acc != null){
			 String dtime = sdf.format(acc.getEndTime())  ;
			if(!dtime.equals(time)){
		%>
				<script type="text/javascript" charset="utf-8">window.parent.location.href='login.jsp'</script>
		<%	}}
		%>  
</div>
       
<div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
      <div class="footer">开发企业：四川塞尔努型信息技术有限公司重庆分公司</div>
    </div>
<div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
      <div id="nav" class="easyui-accordion" fit="true" border="false"> 
      <!--  导航内容 --> 
    
    </div>
</div>
<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
      <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
    <div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; " >
          <h1 style="font-size:24px;">欢迎使用塞尔努型办公管理系统</h1>
          四川塞尔努型信息技术有限公司是一家集系统集成、应用软件开发、信息化建设、网络通信、无线通信等为一体的高新技术企业。<br/>
          公司面向广大移动手机用户和中小型企业，提供专业的无线增值数据服务以及联通、电信、移动、数据通讯等领域的信息化咨询、方案设计、<br/>
          技术支持等服务。公司注册资金1000万，2010年经工信部批准，获得全网icp增值电信业务许可。在同行业内，公司具有强有力的核心<br/>
          竞争力！希望你的加入能让公司绽放更加耀眼的光芒！公司地址：重庆北部新区洪湖西路22号上丁企业商务楼20-10<br/>
        </div>
  </div>
    </div>
<div region="east" title="其他" split="true" style="width:180px;overflow:hidden;">
      <div class="easyui-calendar"></div>
    </div>

<!--修改密码窗口-->
<div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;" >
      <div class="easyui-layout" fit="true">
    <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
          <table cellpadding=3>
        <tr>
              <td>新密码：</td>
              <td><input id="txtNewPass" type="password" class="txt01" /></td>
            </tr>
        <tr>
              <td>确认密码：</td>
              <td><input id="txtRePass" type="password" class="txt01" /></td>
            </tr>
      </table>
        </div>
    <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;"> <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" > 确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a> </div>
  </div>
    </div>
<div id="mm" class="easyui-menu" style="width:150px;">
      <div id="mm-tabupdate">刷新</div>
      <div class="menu-sep"></div>
      <div id="mm-tabclose">关闭</div>
      <div id="mm-tabcloseall">全部关闭</div>
      <div id="mm-tabcloseother">除此之外全部关闭</div>
      <div class="menu-sep"></div>
      <div id="mm-tabcloseright">当前页右侧全部关闭</div>
      <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
      <div class="menu-sep"></div>
      <div id="mm-exit">退出</div>
    </div>
</body>
</html>