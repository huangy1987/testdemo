/**
 * 弹出框
 * 
 * @return
 */
function popquestion(elementid, titles) {
	//$('.addAccount').display('block');
	var a = art.dialog( {
		id : elementid,
		fixed : true,
		padding : 0,
		lock : true,
		opacity : 0.3,
		title : titles,
		content : document.getElementById(elementid)
	});
}

function add(){
	//alert('马上进入添加角色界面');
	//alert('正在加载角色权限');
	//location.href = "../page/admin/role_add.jsp";
	//同步加载模型树
	loadModule($("#ztreeRoleadd"));//alert('加载完毕');
	popquestion('add', '添加角色');
}

/**
 * 页面加载
 */
function loadModule(loadtree){
	var url=sybp() + "/admin/findAllMenus"; //alert(url);
	var result = quickAjaxPost(url, false, null);console.info(result);
	var list_role = jQuery.parseJSON(result);
	// 循环问题，赋值节点属性
	if (list_role != null) {
		var zNodes = [];
		for ( var i = 0; i < list_role.length; i++) {
			zNodes.push( {
				"id" : list_role[i].id,
				"pId" : list_role[i].pid,
				"name" : list_role[i].name,
				"open" : false
			});
		}
		// 初始化树状结构
		zTreeObj = $.fn.zTree.init(loadtree, setting, zNodes);
	}
};

// 设置树结构属性
var zTreeObj, zNodes, setting = {
	check : {
		enable : true,
		chkStyle : "checkbox",
		chkboxType : {
			"Y" : "ps",
			"N" : "ps"
		}
	},
	view : {
		selectedMulti : true
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : ztreeClick
	// 点击节点调用方法
	}
};


/**
 * 获取添加角色表单并设置提交参数值
 * @return
 */
function checkAddFrom(){
	//获取树状结构对象
	var treeObj = $.fn.zTree.getZTreeObj("ztreeRoleadd");
	//获取选中的值
	var nodes = treeObj.getCheckedNodes(true);
	//id叠加
	var idvalue = "";
	for ( var i = 0; i < nodes.length; i++) {
		//if (!nodes[i].getCheckStatus().half && !nodes[i].isParent)
		//if (!nodes[i].getCheckStatus().half){
			//if (nodes[i].id!=0) {
		if((nodes[i].id).indexOf('p-') != -1){
			nodes[i].id = nodes[i].id.substring(2);
		}
				idvalue += nodes[i].id + ",";
			//}
		//}
	}
	alert(idvalue);
	//赋值选择的功能id
	$("#moduleListadd").val(idvalue.replace(/^ +| +$/g, ''));
}

/**
 * 点击节点调用的方法
 * 
 * @param event
 *            js event标准对象
 * @param treeId
 *            节点tid
 * @param treeNode
 *            节点对象
 * @return
 */
function ztreeClick(event, treeId, treeNode) {

	/*var map;
	// 判断点击的是根节点还是子节点
	if (treeNode.pId == null || treeNode.pId.split('-')[1] == 0) {
		KindEditor.remove('#editor_id');
		editor = KindEditor.create('#editor_id', options);
		$("#updatequestionName").val("");
		// 赋值答案空
		editor.html("");
		// 赋值id为空
		$("#updateTableid").val("");
		// 更改按钮属性
		$("#ktablebutton").attr( {
			"onclick" : "addKtable('2')",
			"value" : "添加"
		});
		showTypeorTable();
	} else {
		if(editor!=undefined){
			// 移除编辑器
			KindEditor.remove('#editor_id');
		}
		// 创建编辑器
		editor = KindEditor.create('#editor_id', options);
		map = {
			"ID" : treeNode.ID
		};
		// 查询问题详细信息
		var ktable = jQuery.parseJSON(quickAjaxPost(
				"/b/KnowledgeScriptAction?a=know_queryidTable", false, map));
		if (ktable != null) {
			// 赋值问题
			$("#updatequestionName").val(ktable.ScriptName);
			// 赋值初始问题名称用于判断
			$("#initialTableName").val(ktable.ScriptName);
			// 赋值问题id
			$("#updateTableid").val(ktable.ID);
			// 赋值答案
			editor.html(ktable.ScriptAnswer);
			// 更换问题表单提交按钮属性
			$("#ktablebutton").attr( {
				"onclick" : "updateKtypeOrtable('2')",
				"value" : "修改"
			});
		}
		showTypeorTable();
	}*/
}

//添加角色验证表单非空
function checkIsNull(){//alert('checkIsNull');
	$('#departmentNameadd').poshytip('destroy');// 销毁提醒框
	if ($('#departmentNameadd').val() == 0) {
		chexkwarn('departmentNameadd', '部门名称不能为空!');
		return false;
	}
	$('#realNameadd').poshytip('destroy');// 销毁提醒框
	if ($('#realNameadd').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('realNameadd', '角色名称不能为空!');
		return false;
	}
	
	//获取添加角色表单并设置提交参数值
	checkAddFrom();
	return true;
}

function chexkwarn(elementid, content) {
	$('#' + elementid).poshytip( {
		content : content,
		className : 'tip-violet',
		alignTo : 'target',
		alignX : 'inner-left',
		alignY : 'bottom',
		offsetX : 0,
		offsetY : 5
	});
	// $("#" + elementid).focus();
	$('#' + elementid).poshytip('show');
	setTimeout(function() {
		$('#' + elementid).poshytip('hide');
	}, 1700);

}
function quickAjaxPost(url, async, maparr) {
	var res;
	$.ajax( {
		async : async,
		type : 'post',
		url : url,
		data : maparr,
		success : function(result) {
			res = result;
		},
		error : function(result) {
			res = "timeout";
		}
	});
	return res;
}
//进入角色更改界面
function updateRole(id){//alert(id);
	var maparr = {'id':id};
	var url = sybp() + "/admin/findAllroleByID";
	//查找对象
	var result = quickAjaxPost(url, false, maparr);//alert('相应角色对象'+result);
	var role = jQuery.parseJSON(result);
	//给页面表单赋值
	 var t1 = document.getElementById("departmentNameupdate");   
     for(i=0;i<t1.length;i++){//给select赋值  
         if(role.departmentID == t1.options[i].value){  
             t1.options[i].selected=true;
         }  
     }  
//	document.getElementById('departmentNameupdate').selectedIndex = role.departmentID - 1;
	$('#roleNameupdate').val(role.roleName);
	$('#remarkupdate').val(role.remark);
	//角色id赋值
	$('#roleIDupdate').val(role.id);
	//加载模型树 
	var modList = role.moduleList; 
	var str = modList.split(',');
	var url2=sybp() + "/admin/findAllMenus"; 
	var result2 = quickAjaxPost(url2, false, null);
	var list_role = jQuery.parseJSON(result2);
	//alert('模型长度:'+str.length);
	// 循环问题，赋值节点属性
	if (list_role != null) {
		var zNodes = [];
		var flag = true;
		//加载节点
		for ( var i = 0; i < list_role.length; i++) {
			flag = true;
			if(str != '' || str.length > 0 || str != null){
				for(var j=0;j<str.length -1;j++){
					if((list_role[i].id).indexOf('p-') != -1 && list_role[i].id.substring(2) == str[j]){//菜单
						zNodes.push({
							"id" : list_role[i].id,
							"pId" : list_role[i].pid,
							"name" : list_role[i].name,
							"checked" : true,
							"open" : true
						});
						flag = false;
						break;
					}
					if(str[j] == list_role[i].id){
//						alert(':'+list_role[i].name);
						zNodes.push({
							"id" : list_role[i].id,
							"pId" : list_role[i].pid,
							"name" : list_role[i].name,
							"checked" : true,
							"open" : true
						});
						flag = false;
					}
					
				}
			}
			if(flag){
				//alert('模型遍历次数'+i);
				zNodes.push( {
					"id" : list_role[i].id,
					"pId" : list_role[i].pid,
					"name" : list_role[i].name,
					"checked":false,
					"open" : false
				});
			}
		}
		// 初始化树状结构
		zTreeObj = $.fn.zTree.init($("#ztreeRoleupdate"), setting, zNodes);
	}
	//location.href='${pageContext.request.contextPath}/admin/updaterole?type=2&id=<%=kt.getId()%>';
	popquestion('update', '修改角色');
}
//角色更改功能模型赋值
function appendModuleTree(){
	//获取树状结构对象
	var treeObj = $.fn.zTree.getZTreeObj("ztreeRoleupdate");
	//获取选中的值
	var nodes = treeObj.getCheckedNodes(true);
	var idvalue = "";
	for ( var i = 0; i < nodes.length; i++) {
		if((nodes[i].id).indexOf('p-') != -1){
			nodes[i].id = nodes[i].id.substring(2);
		}
		idvalue += nodes[i].id + ",";
	}
	//alert("<"+idvalue+">");
	//赋值选择的功能id
	$("#ztreeRoleupdatevalue").val(idvalue.replace(/^ +| +$/g, ''));
}
//更改角色提交界面非空验证
function checkIsNullUpdate(){
	
	$('#roleNameupdate').poshytip('destroy');// 销毁提醒框
	if ($('#roleNameupdate').val().replace(/^ +| +$/g, '') == '') {
		chexkwarn('roleNameupdate', '角色名称不能为空!');
		return false;
	}
	
	if(!confirm('你确定要修改该角色吗？')){
		return false;
	}
	//时间赋值不需要
	//角色更改功能模型赋值
	appendModuleTree();
	return true;
}
