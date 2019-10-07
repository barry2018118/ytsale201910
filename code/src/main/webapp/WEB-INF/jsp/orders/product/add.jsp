<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>
<style>
	#productAddForm input.checkbox {
		vertical-align: top;
		margin-right: 5px;
	}
</style>
<link rel="stylesheet" href="${cssPath }/bootstrap/datepicker.css" type="text/css"></link>
<script type="text/javascript" src="${jsPath }/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${jsPath }/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="${jsPath}/jquery-validation/1.10.0/validate.css" />
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>

<form id="productAddForm" method="post" action="" class="form-horizontal" enctype="multipart/form-data">
	<h4>预定信息</h4>
	<input type="hidden" id="productRealname" name="productRealname" value="${product.isRealname}" />
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">商品名称：</label>
		<div class="col-md-8">
			<div class="rel">
				<input type="hidden" id="sproductId" name="sproductId" value="${product.id}">
				<label class="control-label">${product.name}</label>
			</div>
		</div>
		<div class="col-md-3 red-tag tag">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">游玩日期：</label>
		<div class="col-md-3">
			<div class="input-group date datepicker fr">
				<input class="form-control" data-date-autoclose="true" data-date-format="dd-mm-yyyy" placeholder="请选择游玩日期" type="text" id="consumeTime" name="consumeTime"><span class="input-group-addon"><i class="icon-calendar"></i></span>
			</div>
		</div>
		<div class="col-md-3 red-tag tag">
			*<span hidden="true" for="consumeTime" class="error"></span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">购买数量：</label>
		<div class="col-md-3">
			<div class="rel">
				<input class="form-control" placeholder="请输入购买数量" type="number" id="num" name="num" maxlength="4" value="1">
			</div>
		</div>
		<div class="col-md-3 red-tag tag">
			*<span hidden="true" for="num" class="error"></span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">商品单价：</label>
		<div class="col-md-3">
			<div class="rel">
				<input type="hidden" id="unitPrice" name="unitPrice" value="0">
				<label class="control-label" id="money">--</label>
			</div>
		</div>
		<div class="col-md-3 red-tag tag">
			<span hidden="true" for="unitPrice" class="error"></span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">订单合计：</label>
		<div class="col-md-3">
			<div class="rel">
				<input type="hidden" id="price" name="price" value="1000">
				<label class="control-label" id="toprise">--</label>
			</div>
		</div>
		<div class="col-md-3 red-tag tag">
			<span hidden="true" for="price" class="error"></span>
		</div>
	</div>

	<div id="showRealConsumerInfoDiv"></div>
	
	<h4>取票人信息</h4>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">真实姓名：</label>
		<div class="col-md-3">
			<div class="rel">
					<input class="form-control" placeholder="请输入真实姓名" type="text" id="customerName" name="customerName" maxlength="20">
			</div>
		</div>
		<div class="col-md-3 red-tag tag">
			*<span hidden="true" for="customerName" class="error"></span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">手机号码：</label>
		<div class="col-md-3">
			<div class="rel">
				<input class="form-control" placeholder="请输入手机号" type="text" id="tel" name="tel" maxlength="11">
			</div>
		</div>
		<div class="col-md-3 red-tag tag">
			*<span hidden="true" for="tel" class="error"></span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">身份证号：</label>
		<div class="col-md-3">
			<div class="rel">
				<input class="form-control" placeholder="请输入身份证号码" type="text" id="idcard" name="idcard" maxlength="18">
			</div>
		</div>
		<div class="col-md-3 red-tag tag">
            <c:choose>
                <c:when test="${product.isMustCard == 1}">
                    *<span hidden="true" for="idcard" class="error"></span>
                </c:when>
                <c:otherwise>
                    <span hidden="true" for="idcard" class="error"></span>
                </c:otherwise>
            </c:choose>
		</div>
	</div>
	<h4>备注信息</h4>
	<div class="form-group">
		<label class="control-label col-md-1"></label>
		<label class="control-label col-md-2">备注:</label>
		<div class="col-md-8">
			<textarea maxlength="100" id="notes" name="notes" placeholder="请输入备注" class="form-control" rows="5"></textarea>
		</div>
	</div>

	<div class="row" style="min-height:60px;">
		<div class="form-group btn-form">
			<div class="col-md-11 text-center">
				<button aria-hidden="true" class="btn btn-footer btn-cancel" data-dismiss="modal" type="button" id="no">取消</button>
				<button class="btn btn-footer btn-primary" type="submit" id="next">保存</button>
			</div>
		</div>
	</div>
</form>

<script type="text/javascript">
	var cardflag = true;
    jQuery.validator.addMethod("userCardCheck", function(value, element, params) {
        var reg= /^[0-9][0-9]{17}$/ ;
        return this.optional(element) ||(reg.test(value)) ;
    }, "身份证号格式不正确！") ;

    jQuery.validator.addMethod("userphoneCheck", function(value, element, params) {
        var reg= /^[1][0-9]{10}$/ ;
        return this.optional(element) ||(reg.test(value)) ;
    }, "取票人手机号格式不正确！") ;

    jQuery.validator.addMethod("userCardCheckset", function(value, element, params) {
        if(cardflag){
            return true ;
		}else{
            return false ;
		}
    }, "身份证信息不能一致!") ;

     var iscommiting = false;
     var productRealname = 0 ;
     $(function() {
         if ($.validator) {
             $.validator.prototype.elements = function () {
                 var validator = this,
                     rulesCache = {};

                 // select all valid inputs inside the form (no submit or reset buttons)
                 return $(this.currentForm)
                     .find("input, select, textarea")
                     .not(":submit, :reset, :image, [disabled]")
                     .not(this.settings.ignore)
                     .filter(function () {
                         if (!this.name && validator.settings.debug && window.console) {
                             console.error("%o has no name assigned", this);
                         }
                         //注释这行代码
                         // select only the first element for each name, and only those with rules specified
                         //if ( this.name in rulesCache || !validator.objectLength($(this).rules()) ) {
                         //    return false;
                         //}
                         rulesCache[this.name] = true;
                         return true;
                     });
             }
         }
         $(".datepicker").datepicker({
             language: "zh-CN",
             autoclose: true, //选中之后自动隐藏日期选择框
             clearBtn: true, //清除按钮
             todayBtn: true, //今日按钮
             format: "yyyy-mm-dd" //日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
         });
         
         productRealname = $("#productRealname").val() ;

         if(productRealname == 1) {
             var requestUrl = "${ctx}/orders/product/showRealConsumerInfo" ;
             var requestData = {"buyNum": 1} ;
             cserpLoadPage(requestUrl, requestData, 'GET', 'showRealConsumerInfoDiv') ;
         }

         $("#productAddForm").validate({
             rules: {
                 consumeTime: {
                     required: true,
                 },
                 num: {
                     required: true,
                     digits:true,
                 },
                 customerName: {
                     required: true,
                 },
                 consumerName: {
                     required: true,
                 },
                 consumerCard: {
                     required: true,
                     userCardCheck:true,
                     userCardCheckset:true,
                 },
                 tel: {
                     required: true,
                     userphoneCheck:true,
                 }
             },
             messages: {
                 consumeTime: {
                     required: "请选择游玩时间！",
                 },
                 num: {
                     required: "输入商品购买数量！",
                     digits:"请输入整数!",
                 },
                 customerName: {
                     required: "请输入取票人姓名！",
                 },
                 consumerName: {
                     required:  "请输入姓名！",
                 },
                 consumerCard: {
                     required:  "请输入身份证号！",
                 },
                 tel: {
                     required: "请输入取票人手机号！",
                 }
             },
             submitHandler: function(form) {
                 if( $("#unitPrice").val() == 0 ){
                     if(!iscommiting) {
                         return true;
                     }
                     return false;
                 }
                 if(num<1){
                     art.dialog({
                         title: '消息',
                         width: 200,
                         height: 80,
                         time: 3,
                         icon: 'warning',
                         content: "购买数量不能小于1!",
                         close: function() {}
                     });
                     if(!iscommiting) {
                         return true;
                     }
                     return false;
                 }
                 if(iscommiting) {
                     return false;
                 }
                 iscommiting = true;
                 jQuery(form).ajaxSubmit({
                     type: "post",
                     url: "${ctx}/orders/product/add",
                     beforeSubmit: function(formData, jqForm, options) {},
                     dataType: "json",
                     contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                     timeout: 120000,
                     success: function(data) {
                         if(data.flag) {
                             if(data.type == 0) {
								 var msgPhone = $('#tel').val();
								 var msgNum = $('#num').val();
								 sendOrderMessageNo(msgPhone,"${product.name}",msgNum,data.code);
							 }
                             if(data.type == 1) {
                                 var msgPhone = $('#tel').val();
                                 for(var item in data.codeList) {
                                     sendOrderMessageis(msgPhone,data.codeList[item].name,data.codeList[item].card,"${product.name}",data.codeList[item].code);
                                 }
                             }
                             art.dialog({
                                 title: '消息',
                                 width: 200,
                                 height: 80,
                                 time: 3,
                                 icon: 'succeed',
                                 content: data.message,
                                 close: function() {
                                     $("#no").click();
                                 }
                             });
                         } else {
                             iscommiting = false;
                             art.dialog({
                                 title: '消息',
                                 width: 200,
                                 height: 80,
                                 time: 3,
                                 icon: 'warning',
                                 content: data.message,
                                 close: function() {}
                             });
                             iscommiting = false;
                         }
                     },
                     error: function(e) {
                         iscommiting = false;
                     }
                 });
             }
         });
         $("[name=consumerName]").each(function(){
             $(this).rules("add", {
                 required: true,
                 messages: {
                     required: "请输入实名制姓名！"
                 }
             });
         });
         $("#consumeTime").change(function() {
             var time = $(this).val() ;
             var _url = "${ctx}/orders/product/${product.id}/"+time+"/getPrice" ;
             var num = "";

             $.ajax({
                 url: _url ,
                 type: "get",
                 dataType:"json",
                 contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                 async: true,
                 beforeSend: function() {
                 },
                 success: function(data) {
                     if(data.message != "") {
                         num =  data.message;
                         $("#unitPrice").val(num);
                         $("#money").text(num);

                         $("#price").val(num);
                         $("#toprise").text(num);
                     }else{
                         num ="<font color='red'>无法获取商品价格!</font>";
                         $("#unitPrice").val(0);
                         $("#money").html(num);
					 }
                 },
                 error:function(e) {
                     num ="<font color='red'>无法获取商品价格!</font>";
                     $("#unitPrice").val(0);
                     $("#money").html(num);
                 }
             });
         }) ;

         $("#num").on('input',function() {
             var num = $(this).val() ;
             var money = $("#unitPrice").val() ;
             var rateMoney = num * money;
             $("#price").val(rateMoney);
             window.document.getElementById("toprise").innerText = rateMoney;

             if(productRealname == 1) {
                var buyNum = $("#num").val() ;
                var requestUrl = "${ctx}/orders/product/showRealConsumerInfo" ;
                var requestData = {"buyNum": buyNum} ;
                cserpLoadPage(requestUrl, requestData, 'GET', 'showRealConsumerInfoDiv') ;
             }
         }) ;

     });


     function  sendOrderMessageNo(phone,name,num,code) {
         var _url = "${ctx}/orders/product/"+phone+"/"+name+"/"+num+"/"+code+"/sendOrderMessageNo" ;
         $.ajax({
             url: _url ,
             type: "get",
             dataType:"json",
             contentType: "application/x-www-form-urlencoded;charset=UTF-8",
             async: true,
             beforeSend: function() {
             },
             success: function(data) {
             },
             error:function(e) {
             }
         });
     }


    function  sendOrderMessageis(phone,name,card,productname,code) {
        var _url = "${ctx}/orders/product/"+phone+"/"+name+"/"+card+"/"+productname+"/"+code+"/sendOrderMessageis" ;
        $.ajax({
            url: _url ,
            type: "get",
            dataType:"json",
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            async: true,
            beforeSend: function() {
            },
            success: function(data) {
            },
            error:function(e) {
            }
        });
    }

    function checkName(data){
        //校验联系信息不能一致
        var values="";
        $(".t").each(function(i,item){
            var value=$(this).val();
            values+=value; //获取所有的名称
        });
        var val=$(data).val(); //获得当前输入框的值
        var newValue=values.replace(val,""); //去除当前输入框的值
        if(newValue==""){
            return false;
        }else{
            if(newValue.indexOf(val)>-1){  //当前值和newValue作比较
                $(data).focus();
                cardflag = false;
            }else{
                cardflag = true;
			}
        }
    }
</script>