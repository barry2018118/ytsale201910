<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<div class="form-horizontal">
    <div class="row">
            <div class="form-group">
                <label class="control-label col-md-2">订单号：</label>
                <div class="col-md-3">
                    <div class="rel">
                        <label class="control-label">${order.orderno}</label>
                    </div>
                </div>
                <div class="col-md-3 red-tag tag">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-2">所属景区：</label>
                <div class="col-md-3">
                    <div class="rel">
                        <label class="control-label">${scenic.name}</label>
                    </div>
                </div>
                <div class="col-md-3 red-tag tag">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-2">商品名称：</label>
                <div class="col-md-3">
                    <div class="rel">
                        <label class="control-label">${product.name}</label>
                    </div>
                </div>
                <div class="col-md-3 red-tag tag">
                </div>
            </div>

            <h4>取票信息</h4>
            <div class="form-group">
                <label class="control-label col-md-2">姓名：</label>
                <div class="col-md-5">
                    <div class="rel">
                        <label class="control-label">${order.customerName}</label>
                    </div>
                </div>
                <div class="col-md-3 red-tag tag">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-2">手机号：</label>
                <div class="col-md-5">
                    <div class="rel">
                        <label class="control-label">${order.tel}</label>
                    </div>
                </div>
                <div class="col-md-3 red-tag tag">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-2">身份证：</label>
                <div class="col-md-5">
                    <div class="rel">
                        <label class="control-label">${order.idcard}</label>
                    </div>
                </div>
                <div class="col-md-3 red-tag tag">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2">购买数量：</label>
                <div class="col-md-3">
                    <div class="rel">
                        <label class="control-label">${order.num}</label>
                    </div>
                </div>
                <div class="col-md-3 red-tag tag">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2">当前可核销数量：</label>
                <div class="col-md-3">
                    <div class="rel">
                        <label class="control-label">${order.num-order.printnum-order.locknum-order.clocknum}</label>
                    </div>
                </div>
                <div class="col-md-3 red-tag tag">
                </div>
            </div>
            
            <h4>码信息</h4>
            <c:forEach items="${realNames}" var="li">
                <div class="form-group">
                    <label class="control-label col-md-2" >
                            &nbsp;
                    </label>
                    <div class="col-md-6">
                        <label class="checkbox">
                            <c:set var="unused_num" value="${li.num-li.printnum-li.locknum-li.clocknum}" />
                            <c:if test="${unused_num>0}">
                                 <input  name="code" type="checkbox" value="${li.cardPwd}"  <c:if test="${fn:length(realNames)==1}"  >checked</c:if>/>
                            </c:if>
                        <span>${li.cardPwd}&nbsp;&nbsp;<c:if test="${not empty li.consumerName}">( ${li.consumerName} - ${li.consumerCard} )</c:if></span>
                    </label>
                    </div>
                </div>
            </c:forEach>
            <div class="form-group">
                <label class="control-label col-md-2">核销数量：</label>
                <div class="col-md-3">
                    <div class="rel">
                        <input id="consume_num" class="form-control" name="consume_num" type="number" value="1" />
                    </div>
                </div>
                <div class="col-md-3 red-tag tag">
                </div>
            </div>
    </div>
    <div class="row" style="min-height:60px;">
        <div class="form-group btn-form">
            <div class="col-md-11 text-center">
                <button aria-hidden="true" class="btn btn-footer btn-cancel" data-dismiss="modal" type="button" id="quxiao">取消</button>
                <button class="btn btn-footer btn-primary" type="button" onclick="consume_submit();">核销</button>
            </div>
        </div>
    </div>

<script type="text/javascript">
var maxNum='${order.num}';
var iscommiting = false;
function consume_submit(){
    var conusme_num=$("#consume_num").val();
    if(!isIntNum(conusme_num)||conusme_num<=0||conusme_num>maxNum){
        art.dialog({
                    title : '消息',
                    width : 200,
                    height : 80,
                    time : 3,
                    icon : 'error',
                    content : '请输入有效数量！'
                });
        return ;
    }

    var inputObjs=$("input[name='code'][type='checkbox']");
    if(inputObjs&&inputObjs.size()>1){
        if(conusme_num>1){
            art.dialog({
                    title : '消息',
                    width : 200,
                    height : 80,
                    time : 3,
                    icon : 'error',
                    content : '有效数量应为1！'
                });
            return;    
        }
    }

    var inputCheckedObjs=$("input[name='code'][type='checkbox']:checked");
    if(!inputCheckedObjs||inputCheckedObjs.size()<1){
        art.dialog({
                title : '消息',
                width : 200,
                height : 80,
                time : 3,
                icon : 'error',
                content : '请选择需要核销的码！'
            });
        return;    
    }

    var checkedArray = new Array();
    inputCheckedObjs.each(function () {  
        checkedArray.push($(this).val());
    });  
    var _codes=checkedArray.join(",");

    $.ajax({
        type : "post",
        url : "${ctx}/orders/consume/check/code",
        data:{'orderId':'${order.id}','codes':_codes,'num':conusme_num},
        dataType : "text",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
        timeout : 60000,
        success : function(data) {
            if (data=="ok") {
                art.dialog({
                    title : '消息',
                    width : 220,
                    height : 80,
                    time : 3,
                    icon : 'succeed',
                    content :"核销成功！" ,
                    close : function() {
                        $("#quxiao").click();
                        toStock();
                    }
                });
            } else {
                var errMsg="";
                if(data!="err"){
                    try{
                        var jsonObj=JSON.parse(data);
                        errMsg=jsonObj.msg;
                    }catch(e){
                        console.log(e);
                    }
                }
                art.dialog({
                    title : '消息',
                    width : 360,
                    height : 80,
                    time : 3,
                    icon : 'error',
                    content : '核销失败！'+errMsg
                });
            }
            iscommiting = false;
        },
        error : function(e) {
            console.log(e);
            art.dialog({
                    title : '消息',
                    width : 200,
                    height : 80,
                    time : 3,
                    icon : 'error',
                    content : '核销失败！请稍后再试'
            });
            iscommiting = false;
        }
    });

}  

function isIntNum(val){
    var regPos = /^\d+$/; // 非负整数
    if(regPos.test(val)){
        return true;
    }else{
        return false;
    }
}  
</script>
