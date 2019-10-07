<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>


<link rel="stylesheet" type="text/css" href="${cssPath}/style.css" />
<link rel="stylesheet" type="text/css" href="${jsPath}/calendar/calendar-price-jquery.min.css" />
<script type="text/javascript" src="${jsPath}/calendar/calendar-price-jquery.js"></script>
<link rel="stylesheet" href="${cssPath }/bootstrap/datepicker.css" type="text/css"></link>
<script type="text/javascript" src="${jsPath }/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${jsPath }/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="${jsPath}/jquery-validation/1.10.0/validate.css" />
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>



<div class="page-title">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>商品</span>&nbsp;&gt;&nbsp;<span>设置分销</span>&nbsp;&gt;&nbsp;<span class="dq">设置【${saleGroup.name}】-【${supplierProduct.name}】利润</span>
    </h1>
    <!--列表上方索引 E-->
    <!--返回按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="backToProdutList()"><i class="icon-rotate-left"></i>返回</button>
    <!--返回按钮 E-->
</div>

<div class="form-horizontal">
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height">
                <div class="heading tabs">
                    <div class="pull-left">设置【${saleGroup.name}】-【${supplierProduct.name}】 利润 &nbsp;&nbsp;
                    <span class="text-muted">(有效期：<fmt:formatDate value="${supplierProduct.validStartDate}" pattern="yyyy年MM月dd日"/> 至 <fmt:formatDate value="${supplierProduct.validEndDate}" pattern="yyyy年MM月dd日"/>)</span>
                    </div>

                </div>
                <div class="row">
                    <div class="col-sm-6">
                       <!--日历-->
                       <div class="container"></div>
                    </div>
                    <div class="col-sm-6">

                        <div class="tab-content padded" id="my-tab-content" >
                            <div class="col-sm-1"></div>
                        <div class="row" style="margin-bottom: 20px;padding:15px 50px 0 0; ">
                            <ul class="nav nav-tabs" data-tabs="tabs" id="tabs" >
                                <li class="active" >
                                    <a data-toggle="tab" href="#tab1"><span>按时间范围设置</span></a>
                                </li>
                                <li class="" >
                                    <a data-toggle="tab" href="#tab2"><span>按星期设置</span></a>
                                </li>
                                <li class="">
                                    <a data-toggle="tab" href="#tab3"><span>关闭价格设置</span></a>
                                </li>
                            </ul>
                        </div>
                        <div class="form-group">

                            <div class="col-sm-4"></div>
                            <div style="font-size: 14px; margin-left:-80px; float:left; font-weight: 700;">
                                <div  style="margin-right: 16px;  display: inline-block;">
                                    门市销售价: <b class="red-tag">${supplierProduct.marketPrice}</b>元

                                </div><div  style="display: inline-block">
                                网络限售价: <b class="red-tag">${supplierProduct.limitPrice}</b>元

                            </div>
                            </div>


                        </div>
                        <div class="tab-pane active" id="tab1">
                            <form id="tab1Form" onsubmit="return false;">
                            <div class="row">
                                    <div class="form-group">
                                        <label class="control-label col-md-4">起止日期：</label>
                                        <div class="col-md-4 input-group  date datepicker " >
                                            <input class="form-control" data-date-autoclose="true" data-date-format="dd-mm-yyyy" id="startDate" name="startDate" placeholder="开始日期"  type="text" /><span class="input-group-addon"><i class="icon-calendar"></i></span>
                                        </div>
                                        <div class="col-md-3 red-tag tag">
                                            <span hidden="true" for="startDate" class="error"></span>
                                        </div>

                                    </div>
                                    <div class="form-group ">
                                        <div class="control-label col-md-4"></div>
                                        <div class="col-md-4 input-group  date datepicker ">
                                            <input class="form-control" data-date-autoclose="true" data-date-format="dd-mm-yyyy" id="endDate" name="endDate" placeholder="结束日期"  type="text" /><span class="input-group-addon"><i class="icon-calendar"></i></span>
                                        </div>
                                        <div class="col-md-3 red-tag tag">
                                            <span hidden="true" for="endDate" class="error"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">利润：</label>
                                        <div class="col-md-3">
                                            <input class="form-control"  type="text" name="profitPrice" id="profitPrice"/>
                                        </div>
                                         <div class="col-md-3 red-tag tag">
                                            <span hidden="true" for="profitPrice" class="error"></span>
                                        </div>

                                    </div>
                                    <div class="row text-center" style="margin-top:20px;">
                                        <button class="btn btn-primary" style="width:80px;" typex="3">设 置</button>
                                    </div>
                            </div>
                            </form>
                        </div>
                        <div class="tab-pane" id="tab2">
                            <form id="tab2Form" onsubmit="return false;">
                            <div class="row">
                                    <div class="form-group">
                                        <label class="control-label col-md-4">起止日期：</label>
                                        <div class="col-md-4  input-group  date datepicker ">
                                            <input class="form-control" data-date-autoclose="true"  data-date-format="dd-mm-yyyy" id="startDate" name="startDate" placeholder="开始日期"  type="text" /><span class="input-group-addon"><i class="icon-calendar"></i></span>
                                        </div>
                                        <div class="col-md-3 red-tag tag">
                                            <span hidden="true" for="startDate" class="error"></span>
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <div class="control-label col-md-4"></div>
                                        <div class="col-md-4  input-group  date datepicker">
                                            <input class="form-control" data-date-autoclose="true"    data-date-format="dd-mm-yyyy" id="endDate" name="endDate" placeholder="结束日期"  type="text" /><span class="input-group-addon"><i class="icon-calendar"></i></span>
                                        </div>
                                        <div class="col-md-3 red-tag tag">
                                            <span hidden="true" for="endDate" class="error"></span>
                                        </div>

                                    </div>
                                   <div class="form-group">
                                        <label class="control-label col-md-4">设置星期：</label>
                                        <div class="col-md-8">         
                                            <label class="checkbox-inline"><input name="setWeek" type="checkbox" value="2"><span>周一</span></label>
                                           <label class="checkbox-inline"><input name="setWeek" type="checkbox" value="3"><span>周二</span></label>
                                           <label class="checkbox-inline"><input name="setWeek" type="checkbox" value="4"><span>周三</span></label>
                                           <label class="checkbox-inline"><input name="setWeek" type="checkbox" value="5"><span>周四</span></label>
                                           <label class="checkbox-inline"><input name="setWeek" type="checkbox" value="6"><span>周五</span></label>
                                                      
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="control-label col-md-4"></div>
                                        <div class="col-md-4 checkbox">
                                            <label class="checkbox-inline"><input name="setWeek" type="checkbox" value="7"><span>周六</span></label>
                                           <label class="checkbox-inline"><input name="setWeek" type="checkbox" value="1"><span>周日</span></label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-4">利润：</label>
                                        <div class="col-md-3">
                                            <input class="form-control"  type="text" name="profitPrice" id="profitPrice">
                                        </div>
                                         <div class="col-md-3 red-tag tag">
                                            <span hidden="true" for="profitPrice" class="error"></span>
                                        </div>
                                    </div>
                                    <div class="row text-center" style="margin-top:20px;">
                                        <button class="btn btn-primary" style="width:80px;" typex="2">设 置</button>
                                    </div>
                            </div>
                            </form>
                        </div>
                        <div class="tab-pane" id="tab3">
                            <form id="tab3Form" onsubmit="return false;">
                            <div class="row">
                                    <div class="form-group">
                                        <label class="control-label col-md-4">起止日期：</label>
                                        <div class="col-md-4 input-group  date datepicker">
                                            <input class="form-control" data-date-autoclose="true" data-date-format="dd-mm-yyyy" id="startDate"  name="startDate" placeholder="开始日期"  type="text"  /><span class="input-group-addon"><i class="icon-calendar"></i></span>
                                        </div>
                                        <div class="col-md-3 red-tag tag">
                                            <span hidden="true" for="startDate" class="error"></span>
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <div class="control-label col-md-4"></div>
                                        <div class="col-md-4 input-group  date datepicker">
                                            <input class="form-control" data-date-autoclose="true" data-date-format="dd-mm-yyyy" id="endDate" name="endDate" placeholder="结束日期"  type="text"  /><span class="input-group-addon"><i class="icon-calendar"></i></span>
                                        </div>
                                        <div class="col-md-3 red-tag tag">
                                            <span hidden="true" for="endDate" class="error"></span>
                                        </div>
                                    </div>

                                    
                                    <div class="row text-center" style="margin-top:20px;">
                                        <button class="btn btn-primary" style="width:80px;" typex="1">设 置</button> 
                                    </div>
                            </div>
                            </form>
                        </div>



                    </div></div>
                </div>

            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
var iscommiting = false;
var _startDate='<fmt:formatDate value="${supplierProduct.validStartDate}" pattern="yyyy-MM-dd"/>';
var _endDate='<fmt:formatDate value="${supplierProduct.validEndDate}" pattern="yyyy-MM-dd"/>';
var zxCalendar;
$(document).ready(function() {
     var todayStr=_formatDate(new Date(),'yyyy-MM');
     var isAfterToday=_compareDate(_startDate,todayStr);
    
     $(".datepicker").datepicker({
        language: "zh-CN",
        autoclose: true, //选中之后自动隐藏日期选择框
        startDate:isAfterToday?todayStr:_startDate,
        endDate:_endDate,
        format: "yyyy-mm-dd" //日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
    });

    var priceData = loadProfitPrice('${supplierProduct.id}','${saleGroup.id}',todayStr);
    if(priceData&&priceData.length<=0){
        priceData=[];
    }
    //console.log(priceData,_startDate,_endDate);
    zxCalendar = $.CalendarPrice({
      el: '.container',
      startDate: _startDate,
      endDate: _endDate,
      data: priceData,
      // 配置需要设置的字段名称
      config: [
        {
          key: 'profitPrice',
          name: '利润:'
        }
      ],
      // 配置在日历中要显示的字段
      show: [
        {
          key: 'costPrice',
          name: '成：￥'
        },
        {
          key: 'profitPrice',
          name: '利：￥'
        }
      ],
      monthChange: function (month) {
        var changeData=loadProfitPrice('${supplierProduct.id}','${saleGroup.id}',month);
        zxCalendar.update(changeData);
      },
      // dateChange: function(setData){
      //   //console.log(setData);
      //   _setPrice=setData['profitPrice'];
      //   if(!checkAmount(_setPrice)){
      //       return false;
      //   }
      //   return settingProfitPriceDate('${supplierProduct.id}','${saleGroup.id}',setData.date,_setPrice);
      // },
      error: function (err) {
        console.error(err.msg);
      },
      // 自定义颜色
      style: {
        // 头部背景色
        headerBgColor: '#098cc2',
        // 头部文字颜色
        headerTextColor: '#fff',
        // 周一至周日背景色，及文字颜色
        weekBgColor: '#098cc2',
        weekTextColor: '#fff',
        // 周末背景色，及文字颜色
        weekendBgColor: '#098cc2',
        weekendTextColor: '#fff',
        // 有效日期颜色
        validDateTextColor: '#333',
        validDateBgColor: '#fff',
        validDateBorderColor: '#eee',
        // Hover
        validDateHoverBgColor: '#098cc2',
        validDateHoverTextColor: '#fff',
        // 无效日期颜色
        invalidDateTextColor: '#ccc',
        invalidDateBgColor: '#fff',
        invalidDateBorderColor: '#eee',
        // 底部背景颜色
        footerBgColor: '#fff',
      }
      // 点击有效的某一触发的回调函数
      // 注意：配置了此参数，设置窗口无效，即不能针对日期做参数设置
      // 返回每天的数据
       ,everyday: function (dayData) {
       }
      // 隐藏底部按钮（重置、确定、取消），前台使用该插件时，则需要隐藏底部按钮
       ,hideFooterButton: true
    });

     $("#tab1Form").validate({
        rules:{
            startDate:{required:true},
            endDate:{required:true },
            profitPrice:{
                 required: true,    //要求输入不能为空
                 isMoney: true   //调用自定义验证
            }
        },
        messages:{
            startDate:{required:'请选择开始日期！'},
            endDate:{required:'请选择结束日期！'},
            profitPrice:{
                required:'请输入金额！',
                isMoney:'请输入正确的金额！'
            },
        }, 
        submitHandler: function() {
           var _tabPane=$("#tab1");
           var _startDate=$("#startDate",_tabPane).val();
           var _endDate=$("#endDate",_tabPane).val();
           var _profitPrice=$("#profitPrice",_tabPane).val();
           //console.log(_startDate,_endDate,_profitPrice);
           settingProfitPriceSection('${supplierProduct.id}','${saleGroup.id}',_startDate,_endDate,'',_profitPrice,3);
        }
    });

     $("#tab2Form").validate({
        rules:{
            startDate:{required:true},
            endDate:{required:true},
            profitPrice:{
                 required: true,    //要求输入不能为空
                 isMoney: true   //调用自定义验证
            }
        },
        messages:{
            startDate:{required:'请选择开始日期！'},
            endDate:{required:'请选择结束日期！'},
            profitPrice:{
                required:'请输入金额！',
                isMoney:'请输入正确的金额！'
            },
        }, 
        submitHandler: function() {
           var _tabPane=$("#tab2");
           var _startDate=$("#startDate",_tabPane).val();
           var _endDate=$("#endDate",_tabPane).val();
           var _profitPrice=$("#profitPrice",_tabPane).val();
           var _week="";
           var _weekObj=$("input[name='setWeek']:checked");  
            if(_weekObj.length==0){
                return ;  
            }  
            var checkedArray = new Array();
            _weekObj.each(function () {  
                checkedArray.push($(this).val());
            });  
            _week=checkedArray.join(",");
           settingProfitPriceSection('${supplierProduct.id}','${saleGroup.id}',_startDate,_endDate,_week,_profitPrice,2);
        }
    });

    $("#tab3Form").validate({
        rules:{
            startDate:{required:true},
            endDate:{required:true}
        },
        messages:{
            startDate:{required:'请选择开始日期！'},
            endDate:{required:'请选择结束日期！'}
        }, 
        submitHandler: function() {
           var _tabPane=$("#tab3");
           var _startDate=$("#startDate",_tabPane).val();
           var _endDate=$("#endDate",_tabPane).val();
           settingProfitPriceSection('${supplierProduct.id}','${saleGroup.id}',_startDate,_endDate,'','',1);
        }
    });

});


function backToProdutList() {
    var requestUrl = "${ctx}/product/salegroup/product/${saleGroup.id}/main" ;
    var requestData = {} ;
    cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
};

function checkInput(jid,parentObj){
    var _obj=$("#"+jid,parentObj);
    //console.log(_obj);
    var _val=_obj.val();
     //console.log(_val);
    if(!_val||_val.length<=0){
        _obj.focus(function(){
            _obj.parent(".form-group").addClass(".has-error");
        });
        return false;
    }
    return true;
}

function loadProfitPrice(sproductId,groupId,month){
    var resultData={}
    var url = "${ctx}/product/price/load/profit/month" ;
    jQuery.ajax({
        type:"get",
        async: false,
        cache: false,
        url: url ,
        data:{'sproductId':sproductId,'groupId':groupId,'month':month},
        dataType:"text",
        success:function(data) {
            var jsonObj={};
            try{
                jsonObj=JSON.parse(data);
            }catch(e){
                console.log(e);
            }
            if(data&&jsonObj) {
                resultData=jsonObj.data;
            } else {
                art.dialog({
                    title : '消息',
                    id : "profitId",
                    time: 3,
                    icon : 'warning',
                    width : 200,
                    height : 80,
                     zIndex : 999999,
                    content : '加载数据失败，请稍后再试'
                }) ;
            }
        } ,
        error : function(e){
            console.log(e);
            art.dialog({
                    title : '消息',
                    id : "profitId",
                    time: 3,
                    icon : 'warning',
                    width : 200,
                    height : 80,
                     zIndex : 999999,
                    content : '加载数据失败，请求稍后再试！'
                }) ;
        }
    });
    return resultData;
}

function settingProfitPriceSection(sproductId,groupId,startDate,endDate,week,profitPrice,typex){
    if (iscommiting) {
        return false;
    }
    iscommiting = true;

    var url = "${ctx}/product/price/setting/profit/section" ;
    jQuery.ajax({
        type:"post",
        async: false,
        cache: false,
        url: url ,
        data:{ 'sproductId':sproductId,
                'groupId':groupId,
                'startDate':startDate,
                'endDate':endDate,
                'week':week,
                'profitPrice':profitPrice,
                'type':typex,
            },
        dataType:"text",
        success:function(data) {
            var jsonObj={};
            try{
                jsonObj=JSON.parse(data);
            }catch(e){
                console.log(e);
            }
            if(data&&jsonObj&&jsonObj.success==true) {
                 art.dialog({
                    title : '消息',
                    id : "section",
                    time: 3,
                    icon : 'succeed',
                    width : 200,
                    height : 80,
                     zIndex : 999999,
                    content : jsonObj.msg?jsonObj.msg:"设置成功！"
                 }) ;
                //表单清空
                var objForms=$("form");
                objForms.each(function(){
                    this.reset();
                });
                //更新日历数据
                var changeData=loadProfitPrice(sproductId,groupId,zxCalendar.getCurrMonthStr());
                zxCalendar.update(changeData);
            } else {
                art.dialog({
                    title : '消息',
                    id : "section",
                    time: 3,
                    icon : 'warning',
                    width : 200,
                    height : 80,
                     zIndex : 999999,
                    content : '设置失败'+(jsonObj.msg?('，'+jsonObj.msg):'')
                }) ;
            }
            iscommiting = false;
        } ,
        error : function(e){
            console.log(e);
            art.dialog({
                    title : '消息',
                    id : "section",
                    time: 3,
                    icon : 'warning',
                    width : 200,
                    height : 80,
                    zIndex : 9999,
                    content : '设置失败，请求稍后再试！'
                }) ;
            iscommiting = false;
        }
    });
}

function settingProfitPriceDate(sproductId,groupId,date,profitPrice){
    if (iscommiting) {
        return false;
    }
    iscommiting = true;

    var isSuccess=false;
    var url = "${ctx}/product/price/setting/profit/day" ;
    jQuery.ajax({
        type:"post",
        async: false,
        cache: false,
        url: url ,
        data:{ 'sproductId':sproductId,
                'groupId':groupId,
                'date':date,
                'profitPrice':profitPrice
            },
        dataType:"text",
        success:function(data) {
            var jsonObj={};
            try{
                jsonObj=JSON.parse(data);
            }catch(e){
                console.log(e);
            }
            if(data&&jsonObj&&jsonObj.success==true) {
                    isSuccess=true;
            } else {
                art.dialog({
                    title : '消息',
                    id : "dayId",
                    time: 3,
                    icon : 'warning',
                    width : 200,
                    height : 80,
                     zIndex : 999999,
                    content : '设置失败，请稍后再试'
                }) ;
            }
            iscommiting = false;
        } ,
        error : function(e){
            console.log(e);
            art.dialog({
                    title : '消息',
                    id : "dayId",
                    time: 3,
                    icon : 'warning',
                    width : 200,
                    height : 80,
                     zIndex : 999999,
                    content : '设置数据失败，请求稍后再试！'
                }) ;
            iscommiting = false;
        }
    });
    return isSuccess;
}

function checkAmount(strval){
    var fix_amountTest= /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
    if(fix_amountTest.test(strval)==false){
         art.dialog({
                title : '消息',
                id : "amountId",
                time: 3,
                icon : 'warning',
                width : 200,
                height : 80,
                zIndex : 999999,
                content : '请输入有效金额！'
            }) ;
        return false;
    }
    return true;
}
</script>