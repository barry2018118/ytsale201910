/****************************************************
 *	cserp公共JS 
 **/

var _ctx = $("#ctx").val() ;

function cserpLoadPage(requestUrl, requestData, requestMethod, showArea) {
	if(requestUrl==null || requestUrl=="") {
		return ;
	}
	if(typeof(_ctx)=="undefined"){ 
		_ctx="" ;
	}
	requestUrl = requestUrl + "?reqTime=" + new Date().getTime() ;
	
	$.ajax({
		url: requestUrl ,
		data: requestData, 
		type: requestMethod,
		dataType:"text",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		timeout:120000,
		beforeSend: function() {
			$("#"+showArea).html("") ;
			$("#"+showArea).html('<div id="ajaxStartHtml" style=" width:100%; height:400px; text-align:center;"><img style="padding-top:100px;" src="'+_ctx+'/static/images/loading.gif"/></div>') ;
		},
		success: function(data) {
			if(data=="refresh"){
				location.href=requestUrl;
				return;
			}
			$("#"+showArea).html("") ;
			$("#"+showArea).html(data) ;
		},
		error:function(e) {
			$("#"+showArea).html("") ;
			$("#"+showArea).html("加载页面失败，请重新加载！") ;
		}
	})
} ;

function showEmptyPage(showArea) {
	$("#"+showArea).html("此功能即将上线！") ;
}

/**
 * 获取当前的日期函数
 * 传入一个时间戳,如果不传则为当前时间
 * @type String timestamp 要转换的时间戳格式 1469504554276
 * @returns {String} 日期格式: 2016-07-26 10:55
 */
// function timestampFormat(timestamp=false, isDateOnly=false) {
function timestampFormat(timestamp, isDateOnly) {
	timestamp = false ;
	isDateOnly = false ;
	
	if(timestamp) {
		if((""+timestamp).length==10) {
			timestamp=timestamp*1000;
		}
		var date = new Date(timestamp);
	}else{
		//var date = new Date();
		return ;
	}
	var Y = date.getFullYear() ;
	var m = date.getMonth()+1 ;
	var d = date.getDate() ;
	var H = date.getHours() ;
	var i = date.getMinutes() ;
	if(m<10){
		m = '0'+m;
	}
	if(d<10){
		d = '0'+d;
	}
	if(H<10){
		H = '0'+H;
	}
	if(i<10){
		i = '0'+i;
	}
	var t;
	if(isDateOnly){
		t = Y+'-'+m+'-'+d;
	}else{
		t = Y+'-'+m+'-'+d+' '+H+':'+i;
	}
	return t;
}

/**
* 日期格式化
* @param date 日期对象 new Date()
* @param fmt format 输出日期格式 yyyy-MM-dd hh:mm:ss
*/
function _formatDate(date, fmt) {
    if (/(y+)/i.test(fmt)) {
      fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
    }

    var obj = {
      'M+': date.getMonth() + 1,
      'd+': date.getDate(),
      'h+': date.getHours(),
      'm+': date.getMinutes(),
      's+': date.getSeconds()
    }

    for (var key in obj) {
      if (new RegExp('('+ key +')').test(fmt)) {
        var str = obj[key] + '';
        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : _formatNumber(str));
      }
    }
    return fmt;
};
function _formatNumber(n) {
	n = n.toString();
	return n[1] ? n : '0' + n;
};

//js日期比较(yyyy-mm-dd)
function _compareDate(a, b) {
    var arr = a.split("-");
    var starttime = new Date(arr[0], arr[1], arr[2]);
    var starttimes = starttime.getTime();

    var arrs = b.split("-");
    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
    var lktimes = lktime.getTime();

    if (starttimes >= lktimes) {
        return false;
    }
    else
        return true;

}
