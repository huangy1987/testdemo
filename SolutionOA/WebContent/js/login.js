var loginAndRegDialog;
// alert(sybp());
loginAndRegDialog = $(function() {
	$.messager.progress();
	$.messager.progress('close');
	$('#loginAndRegDialog').dialog({
		title : 'Solution账号登录',
		cache : false,
		width : 270,
		collapsible : false,// 不可折叠按钮
		height : 210,
		closable : false,// 右上角关闭按钮不显示
		modal : true,// 模式化
		// 数组用中括号代表，大括号表示一个对象
		buttons : [ {
			text : '登录',
			handler : function() {
				testData();
			}
		}
//		, {
//			text : '关闭',
//			handler : function() {
//				loginAndRegDialog.dialog('close');
//				alert(5);
//			}
//		} 
		]
	});
	$('#loginInputForm input[name=loginName]').focus();
});

/*$(function(){
	$("body").attr("onkeydown",function(){
		keyLogin();
	})
});*/

// 回车键控制登录
function keyLogin() {
	if (event.keyCode == 13) { // 回车键的键值为13
		testData();
	}
}
// 提交数据非空验证
function testLogin() {
	var name = $('#loginInputForm input[name=loginName]').val();
	var pw = $('#loginInputForm input[name=passWord]').val();
	if (name == '' || name == null) {
		chexkwarn('loginName', '请输入登录号!');
		$('#loginInputForm input[name=loginName]').focus();
		return false;
	}
	if (pw == '' || pw == null) {
		chexkwarn('passWord', '请输入登录密码!');
		$('#loginInputForm input[name=passWord]').focus();
		return false;
	}
}
// 提交数据验证
function testData() {
	var url = sybp() + '/account/login';
//	alert(url);
	// 验证登录提交数据
	if (testLogin() == false) {
		return;
	}
//	var maparr = {"loginName":$('#loginInputForm input[name=loginName]').val(),"passWord":$('#loginInputForm input[name=passWord]').val()};
//	var result = quickAjaxPost(url, false, maparr);
//	alert(result);
//	if(result != null){
//		var r = result[0].loginState;
//		alert(r);
//	}
//	alert(typeof(result));
//	alert(typeof(result.loginState));
	// ajax验证登录提交数据
	$.ajax({
		url : url,
		data : $('#loginInputForm').serialize(),// 序列号表单内容提交maparr,//
		cache : false,
		dataType : 'json', // xmls 返回类型
		success : function(r) {// r为返回json对象
			//alert('登录成功');// 登录成功才执行，要不然后面的语句除了console等都不会执行,并且ie浏览器不支持console语句
			var acc = r.account;
			if(r && r.loginState){// 一定会执行放在最后
				$('#loginAndRegDialog').dialog('close');// 隐藏登陆框
				$('#main').hide();
				$.messager.show({
					title:'友情提示：', 
					msg:'亲爱的用户：'+ acc.loginName + ', 恭喜你,登录成功！'
				});
				setTimeout(function() {
					window.location.href='index.jsp';
				}, 1000);
			}else{// 登录失败
				alert('工号或密码错误！');
				//$.messager.alert('登录失败','工号或密码错误!','error');
				$('#loginInputForm input[name=loginName]').val('');
				$('#loginInputForm input[name=passWord]').val('');
				$('#loginInputForm input[name=loginName]').focus();
			}
		}
	});
}

function countDown(secs){
	  if(--secs>0){
	   setTimeout("countDown("+secs+")",1000);
	  }
}
/**
 * 验证提醒框poshytip
 * 
 * @param elementid
 * @param content
 * @return
 */
function chexkwarn(elementid, content) {
	$("#" + elementid).poshytip({
		content : content,
		className : 'tip-violet',
		alignTo : 'target',
		alignX : 'inner-left',
		alignY : 'bottom',
		offsetX : 3,	
		offsetY : 5
	});
	$("#" + elementid).poshytip('show');
	setTimeout(function() {
		$("#" + elementid).poshytip('destroy');
	}, 1000);
	// $("#" + elementid).poshytip('hide');// 销毁提醒框
}
/**
 * 快速ajax
 * 
 * @param url
 *            路径
 * @param async
 *            是否同步
 * @param amparr
 *            参数数组键值对
 * @return
 */
function quickAjaxPost(url, async, maparr) {
	var res;
	$.ajax({
		async : async,
		type : 'post',
		url : url,
		data : maparr,// json格式数据
		success : function(result) {
			res = result;

		},
		error : function(result) {
			res = "timeout";
		}
	});
	return res;
}