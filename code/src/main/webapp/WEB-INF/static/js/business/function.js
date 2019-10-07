var _ctx = $("#ctx").val() ;

/***********************************
 * 功能模块
 */
function toModuleAdd() {
	var requestUrl = _ctx+"/manage/function/module/add" ;
	var requestData = {} ;
	cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
}
		
function toModuleEdit(moduleId) {
	var requestUrl = _ctx+"/manage/function/module/"+moduleId+"/edit" ;
	var requestData = {} ;
	cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
}
		
function toModuleDelete(moduleId) {
	$.dialog({
		id : "askToDeleteModule",
		title : '删除模块',
		width: 160, 
		height: 80,
		lock : true,
		background : '#ff0000', // 背景色
		opacity : 0, // 透明度
		content : '您确定要删除吗？',
		icon : 'warning',
		zIndex : 91,
		ok : function() {
			this.hide();
			moduleDelete(moduleId) ;
		},
		cancel : true
	});
}
		
function moduleDelete(moduleId) {
	var _url = _ctx+"/manage/function/module/"+moduleId+"/delete" ;
	$.ajax({
		url: _url ,
		type: "post",
		dataType:"text",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		timeout:120000,
		beforeSend: function() {
			// $("#mainContentDiv").html('<div id="ajaxStartHtml" style=" width:100%; height:400px; text-align:center;"><img style="padding-top:100px;" src="'+_ctx+'/static/images/loading.gif"/></div>') ;
		},
		success: function(data) {
			if(data=="0") {
				$.dialog({
					id: "deleteModule1" ,
					title: '删除模块',
					width: 160, 
					height: 80,
				    time: 3 ,
				    icon: 'succeed' ,
				    zIndex : 911,
				    content: '删除成功' ,
				    close:function() {
				    	var requestUrl = _ctx+"/manage/function/view" ;
				    	var requestData = {} ;
				    	cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
				    }
				}) ;
			} else {
				$.dialog({
					id: "deleteModule2" ,
					title: '删除模块',
					width: 160, 
					height: 80,
				    time: 3 ,
				    icon: 'warning' ,
				    zIndex : 912,
				    content: '已分配，不能删除！' ,
				    close:function() {
				    }
				}) ;
			}
		},
		error:function(e) {
			$("#mainContentDiv").html(error) ;
		}
	})
}

/***********************************
 * 功能菜单
 */
function toMenuAdd(moduleId) {
	var requestUrl = _ctx+"/manage/function/menu/add" ;
	var requestData = {"moduleId": moduleId} ;
	cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
}
	
function toMenuEdit(menuId) {
	var requestUrl = _ctx+"/manage/function/menu/"+menuId+"/edit" ;
	var requestData = {"menuId": menuId} ;
	cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
}
	
function toMenuDelete(menuId) {
	$.dialog({
		id : "askToDeleteMenu",
		title : '删除菜单',
		width: 160, 
		height: 80,
		lock : true,
		background : '#ff0000', // 背景色
		opacity : 0, // 透明度
		content : '您确定要删除吗？',
		icon : 'warning',
		zIndex : 93,
		ok : function() {
			this.hide();
			menuDelete(menuId) ;
		},
		cancel : true
	});
}
		
function menuDelete(menuId) {
	var _url = _ctx+"/manage/function/menu/"+menuId+"/delete" ;
	$.ajax({
		url: _url ,
		type: "post",
		dataType:"text",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		timeout:120000,
		beforeSend: function() {
			// $("#mainContentDiv").html('<div id="ajaxStartHtml" style=" width:100%; height:400px; text-align:center;"><img style="padding-top:100px;" src="'+_ctx+'/static/images/loading.gif"/></div>') ;
		},
		success: function(data) {
			if(data=="0") {
				$.dialog({
					id: "deleteMenu1" ,
					title: '删除菜单',
					width: 160, 
					height: 80,
				    time: 3 ,
				    icon: 'succeed' ,
				    zIndex : 921,
				    content: '删除成功' ,
				    close:function() {
				    	var requestUrl = _ctx+"/manage/function/view" ;
				    	var requestData = {} ;
				    	cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
				    }
				}) ;
			} else {
				$.dialog({
					id: "deleteMenu2" ,
					title: '删除菜单',
					width: 160, 
					height: 80,
				    time: 3 ,
				    icon: 'warning' ,
				    zIndex : 922,
				    content: '已分配，不能删除！' ,
				    close:function() {
				    }
				}) ;
			}
		},
		error:function(e) {
			$("#mainContentDiv").html(error) ;
		}
	})
}
	
/***********************************
 * 功能动作
 */
function toActionAdd(menuId) {
	var requestUrl = _ctx+"/manage/function/action/add" ;
	var requestData = {"menuId": menuId} ;
	cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
}
	
function toActionEdit(actionId) {
	var requestUrl = _ctx+"/manage/function/action/"+actionId+"/edit" ;
	var requestData = {"actionId": actionId} ;
	cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
}
	
function toActionDelete(actionId) {
	$.dialog({
		id : "askToDeleteAction",
		title : '删除功能',
		width: 160, 
		height: 80,
		lock : true,
		background : '#ff0000', // 背景色
		opacity : 0, // 透明度
		content : '您确定要删除吗？',
		icon : 'warning',
		zIndex : 95,
		ok : function() {
			this.hide();
			actionDelete(actionId) ;
		},
		cancel : true
	});
}
		
function actionDelete(actionId) {
	var _url = _ctx+"/manage/function/action/"+actionId+"/delete" ;
	$.ajax({
		url: _url ,
		type: "post",
		dataType:"text",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		timeout:120000,
		beforeSend: function() {
			// $("#mainContentDiv").html('<div id="ajaxStartHtml" style=" width:100%; height:400px; text-align:center;"><img style="padding-top:100px;" src="'+_ctx+'/static/images/loading.gif"/></div>') ;
		},
		success: function(data) {
			if(data=="0") {
				$.dialog({
					id: "deleteAction1" ,
					title: '删除功能',
					width: 160, 
					height: 80,
				    time: 3 ,
				    icon: 'succeed' ,
				    zIndex : 931,
				    content: '删除成功' ,
				    close:function() {
				    	var requestUrl = _ctx+"/manage/function/view" ;
				    	var requestData = {} ;
				    	cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
				    }
				}) ;
			} else {
				$.dialog({
					id: "deleteAction2" ,
					title: '删除功能',
					width: 160, 
					height: 80,
				    time: 3 ,
				    icon: 'warning' ,
				    zIndex : 932,
				    content: '已分配，不能删除！' ,
				    close:function() {
				    }
				}) ;
			}
		},
		error:function(e) {
			$("#mainContentDiv").html(error) ;
		}
	})
}
	
/***********************************
 * 功能菜单
 */
function toButtonList(actionId) {
	var requestUrl = _ctx+"/manage/function/button/list" ;
	var requestData = {"actionId": actionId} ;
	cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
}

function toButtonAdd(actionId) {
	var requestUrl = _ctx+"/manage/function/button/add" ;
	var requestData = {"actionId": actionId} ;
	cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
}

function toButtonEdit(buttonId) {
	var requestUrl = _ctx+"/manage/function/button/"+buttonId+"/edit" ;
	var requestData = {"buttonId": buttonId} ;
	cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
}
	
function toButtonDelete(actionId, buttonId) {
	$.dialog({
		id : "askToDeleteButton",
		title : '删除子功能',
		width: 160, 
		height: 80,
		lock : true,
		background : '#ff0000', // 背景色
		opacity : 0, // 透明度
		content : '您确定要删除吗？',
		icon : 'warning',
		zIndex : 97,
		ok : function() {
			this.hide();
			buttonDelete(actionId, buttonId) ;
		},
		cancel : true
	});
}
		
function buttonDelete(actionId, buttonId) {
	var _url = _ctx+"/manage/function/button/"+buttonId+"/delete" ;
	$.ajax({
		url: _url ,
		type: "post",
		dataType:"text",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		timeout:120000,
		beforeSend: function() {
			// $("#mainContentDiv").html('<div id="ajaxStartHtml" style=" width:100%; height:400px; text-align:center;"><img style="padding-top:100px;" src="'+_ctx+'/static/images/loading.gif"/></div>') ;
		},
		success: function(data) {
			if(data=="0") {
				$.dialog({
					id: "deleteButton1" ,
					title: '删除子功能',
					width: 160, 
					height: 80,
				    time: 3 ,
				    icon: 'succeed' ,
				    zIndex : 941,
				    content: '删除成功' ,
				    close:function() {
				    	toButtonList(actionId) ;
				    }
				}) ;
			} else {
				$.dialog({
					id: "deleteButton2" ,
					title: '删除子功能',
					width: 160, 
					height: 80,
				    time: 3 ,
				    icon: 'warning' ,
				    zIndex : 942,
				    content: '已分配，不能删除！' ,
				    close:function() {
				    }
				}) ;
			}
		},
		error:function(e) {
			$("#mainContentDiv").html(error) ;
		}
	})
}
