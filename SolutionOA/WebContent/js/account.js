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
//进入添加账户界面
function add(){
	//console.info('进入添加账户界面');
	//获取职位角色 
	//location.href = "../page/admin/account_add.jsp";
	popquestion('add', '添加账户');
}
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

/**
 *获得项目根路径
 *使用方法:sy.bp();
*/							
function sybp(){
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPath = curWwwPath.substring(0,pos);
	var projectName = pathName.substring(0,pathName.substr(1).indexOf('/') + 1);
	//返回值:/http://ip地址端口/项目名
	//alert(localhostPath + projectName);
	return localhostPath + projectName;
}
//停用账户
function stopTime(id,nowtime){
	//更改数据库结束时间
	var result = quickAjaxPost(sybp() + '/admin/updateaccount?type=3&id='+id+'&time='+nowtime, false, null);
	//返回页面或者刷新页面时间
	window.location.reload();
}
//启用账户
function startTime(id,time){
	//更改数据库结束时间
	var result = quickAjaxPost(sybp() + '/admin/updateaccount?type=4&id='+id +'&time='+time, false, null);
	//返回页面或者刷新页面时间
	window.location.reload();
}