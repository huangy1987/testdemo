//charset=UTF-8

/*var _menus = {
 "menus":[
 {
 "menuid": "1",
 "icon": "icon-sys",
 "menuname": "人力资源部",
 "menus": [
 {
 "menuid": "1",
 "menuname": "账户管理",
 "icon": "icon-nav",
 "url": "2.htm"
 },
 {
 "menuid": "2",
 "menuname": "档案维护",
 "icon": "icon-nav",
 "url": "2.htm"
 }
 ]
 }
 ]
 };*/

$(function() {
	$('#w').window('close');
	/*
	 * $('#editpass').click(function() { $('#w').window('open'); }); //密码更正
	 * $('#btnEp').click(function() { updatePw(); }) //
	 * $('#btnCancel').click(function(){closePwd();})
	 */
	// 退出系统
	$('#loginOut').click(function() {
		$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
			if (r) {
				parent.location.href = sybp() + '/account/logout';
			}
		});
	});
	var url = sybp() + '/role/findRoleModule';
	// 根据角色获取菜单所有功能模块
	var result = quickAjaxPost(url, false, null);
	result = jQuery.parseJSON(result);// 由JSON字符串转换为JSON对象
	//console.info(result);
	var menus = [];// 主菜单数组
	var menus1 = [];// 子菜单数组
	if (result != null) {
		// 部门
		for ( var n = 0; n < result.length; n++) {
			if (result[n].moduleParentID === 0) {
				menus.push({
					"menuid" : result[n].id,
					"icon" : result[n].moduleIcon,
					"menuname" : result[n].moduleName,
					"menus" : menus1,
					"PID" : result[n].moduleParentID
				});
//				alert(result[n].moduleName);
			}
		}
		// 功能模块
		for ( var n = 0; n < result.length; n++) {
			if (result[n].moduleParentID > 0) {//子菜单
				var PID = result[n].moduleParentID;
				// 将每个子菜单组装到主菜单
				for ( var m = 0; m < menus.length; m++) {//遍历主菜单
					if (menus[m].menuid === result[n].moduleParentID) {
						menus[m].menus.push({
							"menuid" : result[n].id,
							"icon" : result[n].moduleIcon,
							"menuname" : result[n].moduleName,
							"url" : result[n].modulePath,
							"PID" : result[n].moduleParentID
						});
						//alert('功能:'+result[n].moduleName);
					}
				}
			}

		}
	}
	_menus = {
		"menus" : menus
	};
	InitLeftMenu();
	tabClose();
	tabCloseEven();
});

// 初始化左侧
function InitLeftMenu() {
	$("#nav").accordion({
		animate : false
	});// 为id为nav的div增加手风琴效果，并去除动态滑动效果
	$.each(_menus.menus, function(i, n) {// $.each 遍历_menu中的元素
		var menulist = '';
		menulist += '<ul>';
		if (n.PID == 0) {
			$.each(n.menus, function(j, o) {
				if (o.PID == n.menuid) {
					menulist += '<li><div style=\'height: 20px;\'><a ref="' + o.menuid
							+ '" href="#" rel="' +  o.url
							+ '" ><span class="icon ' + o.icon
							+ '" >&nbsp;</span><span class="nav">' + o.menuname
							+ '</span></a></div></li> ';
				}
			});
			menulist += '</ul>';//console.info(menulist);
			$('#nav').accordion('add', {
				title : n.menuname,
				content : menulist,
				iconCls : 'icon ' + n.icon
			});
		}
	});

	$('.easyui-accordion li a').click(function() {// 当单击菜单某个选项时，在右边出现对用的内容
		var tabTitle = $(this).children('.nav').text();// 获取超链里span中的内容作为新打开tab的标题
		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");// 获取超链接属性中ref中的内容
		var icon = getIcon(menuid, icon);
//		alert(sybp() + url);
		addTab(tabTitle,sybp() + url, icon);// 增加tab
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
	}).hover(function() {
		$(this).parent().addClass("hover");
	}, function() {
		$(this).parent().removeClass("hover");
	});

	// 选中第一个
	var panels = $('#nav').accordion('panels');
	var t = panels[0].panel('options').title;
	$('#nav').accordion('select', t);
}
// 获取左侧导航的图标
function getIcon(menuid) {
	var icon = 'icon ';
	$.each(_menus.menus, function(i, n) {
		$.each(n.menus, function(j, o) {
			if (o.menuid == menuid) {
				icon += o.icon;
			}
		});
	});

	return icon;
}

function addTab(subtitle, url, icon) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title : subtitle,
			content : createFrame(url),
			closable : true,
			icon : icon
		});
	} else {
		$('#tabs').tabs('select', subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}

function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="' + url
			+ '" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	});
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});

		var subtitle = $(this).children(".tabs-closable").text();

		$('#mm').data("currtab", subtitle);
		$('#tabs').tabs('select', subtitle);
		return false;
	});
}
// 绑定右键菜单事件
function tabCloseEven() {
	// 刷新
	$('#mm-tabupdate').click(function() {
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update', {
			tab : currTab,
			options : {
				content : createFrame(url)
			}
		});
	});
	// 关闭当前
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close', currtab_title);
	});
	// 全部关闭
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			$('#tabs').tabs('close', t);
		});
	});
	// 关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function() {
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	// 关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			// msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#tabs').tabs('close', t);
		});
		return false;
	});
	// 关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 0) {
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#tabs').tabs('close', t);
		});
		return false;
	});

	// 退出
	$("#mm-exit").click(function() {
		$('#mm').menu('hide');
	});
}

// 弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
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
	var res = "";
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
