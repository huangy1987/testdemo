    //获取项目路径
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

   //部门下拉框改变引起职位的联动
    function setPosadd(value){
    	//alert(value);
        if(value == 0){
        	$('#positionIDadd').empty();
         	$('#positionIDadd').append('<option>请选择<\/option>');
        	$('#accountRoleIDadd').empty();
           	$('#accountRoleIDadd').append('<option>请选择<\/option>');
           	return;
        }
    	maparr= {'id':value};
    	var url = sybp()+'/admin/findAllPositonByDep';
    	//根据部门id获取所有职位
    	var result = quickAjaxPost(url, false, maparr);
    	result = jQuery.parseJSON(result);
    	var va ='';
    	var iva =0;
    	var aa =0;
    	var ops = '';
    	for(var d=0;d<result.length;d++){
        		va= result[0].positionName;
        		aa = result[d].id;
        		iva = result[0].id;
    		ops =ops + '<option value=\"' + aa +'\">'+result[d].positionName+'<\/option>';
       	}

    	//设置下拉框显示值
    	$('#positionIDadd').empty();
    	document.getElementById('positionIDadd').selectedIndex = iva - 1;//设置select显示值
    	$('#positionIDadd').append(ops);
		//根据选择部门显示相应存在的角色
    	  setRoleadd(value);  
    }
  //根据选择部门显示相应存在的角色
	function setRoleadd(value){
		maparr= {'id':value};
    	var url = sybp()+'/admin/findAllRoleByDep';
    	//根据部门id获取所有角色
    	var result = quickAjaxPost(url, false, maparr);
    	result = jQuery.parseJSON(result);
    	var va = '';
    	var iva = 0;
    	var aa = 0;
    	var ops = '';
    	for(var d=0;d<result.length;d++){
        		va= result[0].positionName;
        		aa = result[d].id;
        		iva = result[0].id;
    		ops =ops + '<option value=\"'+ aa+'\">'+result[d].roleName+'<\/option>';
       	}
    	 $('#accountRoleIDadd').empty();
    	 document.getElementById('accountRoleIDadd').selectedIndex = iva - 1;//设置select显示值
    	//设置下拉框显示值
    	//给select赋值选项
    	//  document.getElementById('accountRoleID').innerHTML = ops;
      	$('#accountRoleIDadd').append(ops);
	}
	//职位下拉框改变引起的联动
	function setByPosition(v){
		//根据职位id获取对应的部门id
		maparr= {'id':v};
    	var url = sybp()+'/admin/findAllDepByPos';//alert(url);
    	//根据部门id获取所有角色
    	var result = quickAjaxPost(url, false, maparr);
    	//result = jQuery.parseJSON(result);
    	var depID = parseInt(result);
    	//设置部门下拉框
    	document.getElementById('departmentIDadd').selectedIndex = depID;
    	setPos(depID);
	}
	//角色下拉框改变引起的联动
	function setByRole(v){
		//根据职位id获取对应的部门id
		maparr= {'id':v};
    	var url = sybp()+'/admin/findAllDepByRole';//alert(url);
    	//根据部门id获取所有角色
    	var result = quickAjaxPost(url, false, maparr);
    	//result = jQuery.parseJSON(result);
    	var depID = parseInt(result);
    	//设置部门下拉框
    	document.getElementById('departmentIDadd').selectedIndex = depID;
    	setPos(depID);
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
    /**
     * 验证提醒框
     * 
     * @param elementid
     * @param content
     * @return
     */
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
    		$('#' + elementid).poshytip('destroy');
    	}, 1400);

    }
    //工号重复验证
    function checkSame(loginName){
    	if(loginName == '' || loginName == null || loginName.replace(/^ +| +$/g, '') == ''){
    		return;
    	}
		maparr= {'loginName':loginName};
    	var url = sybp()+'/admin/findAllSameName';//alert(url);
    	//根据部门id获取所有角色
    	var result = quickAjaxPost(url, false, maparr);
    	if(result == 'true'){
    		chexkwarn("loginNameadd", '工号已存在！');
    		return false;
    	}
    	$('#loginNameadd').poshytip('destroy');
    	return true;
	}
    //添加账户前检查数据
    function checkAddAccount(){
    	
    	var flag2 = checkIsNull();
    	if(!flag2){
    		return false;
    	}
    	return true;
    }
    //键盘回车检查数据添加账户前检查数据
    function keycheck(){
    	if(event.keyCode == 13){
    		checkAddAccount();
    	}
    }
    
    //非空验证
    function checkIsNull(){
    	$('#loginNameadd').poshytip('destroy');// 销毁提醒框
    	if ($('#loginNameadd').val().replace(/^ +| +$/g, '') == '') {
    		chexkwarn('loginNameadd', '工号不能为空!');
    		return false;
    	}
    	var reg = /^[0-9]{3,4}$/;
    	if (!(reg.test($('#loginNameadd').val()))) {
    		chexkwarn('loginNameadd', '工号只能为3-4位数字!');
    		return false;
    	}
    	var loginName = $('#loginNameadd').val();//alert(loginName);
    	var flag1 = checkSame(loginName);
    	if(!flag1){
    		return false;
    	}
    	$('#realNameadd').poshytip('destroy');// 销毁提醒框
    	if ($('#realNameadd').val().replace(/^ +| +$/g, '') == '') {
    		chexkwarn('realNameadd', '姓名不能为空!');
    		return false;
    	}
    	
    	$('#passWordadd').poshytip('destroy');// 销毁提醒框
    	if ($('#passWordadd').val().replace(/^ +| +$/g, '') == '') {
    		chexkwarn('passWordadd', '密码不能为空!');
    		return false;
    	}
    	$('#departmentIDadd').poshytip('destroy');// 销毁提醒框
    	if ($('#departmentIDadd').val() == 0) {
    		chexkwarn("departmentIDadd", '部门不能为空!请添加部门信息！');
    		return false;
    	}
    	$('#positionIDadd').poshytip('destroy');// 销毁提醒框
    	if ($('#positionIDadd').val() == 0) {
    		chexkwarn("positionIDadd", '职位不能为空!请添加部门信息！');
    		return false;
    	}
    	$('#accountRoleIDadd').poshytip('destroy');// 销毁提醒框
    	if ($('#accountRoleIDadd').val() == 0) {
    		chexkwarn("accountRoleIDadd", '角色不能为空!请添加部门信息！');
    		return false;
    	}
    	return true;
    }
