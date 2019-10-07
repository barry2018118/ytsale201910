<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button aria-hidden="true" class="close" data-dismiss="modal" type="button">&times;</button>
            <h4 class="modal-title">公告</h4>
        </div>
        <div class="modal-body">
            <div class="gg">
                <div class="title">${notice.title}</div>
                <div class="time">${notice.ctreateTimeStr}</div>
                <p>${notice.content}</p>
            </div>
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" data-dismiss="modal" type="button" style="width:80px;">关 闭</button>
        </div>
    </div>
</div>

<!-- 
<div class="modal-dialog">
    <div class="modal-content">
        <form id="testSubmitForm" method="post" class="form-horizontal">
	        <div class="modal-header">
	            <button aria-hidden="true" class="close" data-dismiss="modal" type="button">&times;</button>
	            <h4 class="modal-title">公告</h4>
	        </div>
	        <div class="modal-body">
	            <div class="gg">
	                <div class="title">${notice.title}</div>
	                <div class="time">${notice.ctreateTimeStr}</div>
	                <p>${notice.content}</p>
	            </div>
	            <div class="gg">
	               <input class="form-control" placeholder="请输入商品类别名称" type="text" id="testName" name="testName" maxlength="10" />
                </div>
	        </div>
	        <div class="modal-footer">
	            <button class="btn btn-primary" type="submit" style="width:80px;" id="noticeSave">保存</button>
	            <button class="btn btn-primary" data-dismiss="modal" type="button" style="display:none" id="noticeClose">关 闭</button>
	        </div>
        </form>
    </div>
</div>
-->


<script type="text/javascript">
    var iscommiting = false;
    $(function() {
        $("#testSubmitForm").validate({
            rules: {
                testName: {required: true}
            },
            messages: {
                testName: {required: "不能为空！"}
            },
            submitHandler: function(form) {
                if(iscommiting){
                    return false;
                }
                iscommiting = true;
                
                jQuery(form).ajaxSubmit({
                    type: "post",
                    url: "${ctx}/chiefly/shop/testSubmit",
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                    timeout: 120000,
                    success: function(data) {
                        if(data.flag) {
                            art.dialog({
                                title: '消息',
                                width: 200, 
                                height: 80,
                                time: 2,
                                icon: 'succeed',
                                content: data.message,
                                close:function() {
                                    $("#noticeClose").click();
                                }
                            }) ;
                        } else {
                            iscommiting = false ;
                            art.dialog({
                                title: '消息',
                                width: 200, 
                                height: 80,
                                time: 2,
                                icon: 'warning',
                                content: data.message,
                                close:function() {
                                    $("#noticeClose").click();
                                }
                            });
                        }
                    },
                    error:function(e) {
                        iscommiting = false ;
                    }
                }) ;
            }
        }) ;
    }) ;

</script>