<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp" %>

<link rel="stylesheet" href="${jsPath}/jquery-validation/1.10.0/validate.css"/>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>

<div class="page-title">
    <!--列表上方索引 S-->
    <h1 class="fl">
        <span>公告管理</span>&nbsp;&gt;&nbsp;<span class="dq">新建</span>
    </h1>
    <!--列表上方索引 E-->
    <!--返回按钮 S-->
    <button class="btn btn-primary fr btn-top" type="button" onclick="toNoticeList()"><i
            class="icon-rotate-left"></i>返回
    </button>
    <!--返回按钮 E-->
</div>


<form id="noticeAdd"  class="form-horizontal">
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height">
                <div class="heading">
                    <div class="pull-left">公告管理</div>
                </div>
                <div class="widget-content padded">
                    <div class="form-group">
                        <label class="control-label col-md-2">公告类型：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <select class="form-control" id="noticeType" name="noticeType">
                                    <option value="" selected="selected">请选择</option>
                                    <option value="1">系统公告</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            *<span hidden="true" for="noticeType" class="error"></span>
                        </div>

                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">公告标题：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <div class="rel">
                                    <input class="form-control" placeholder="请输入公告标题" type="text" id="title"
                                           name="title" maxlength="30"/>

                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            *<span hidden="true" for="title" class="error"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-2">公告内容：</label>
                        <div class="col-md-7">
                            <div class="rel">
                                <textarea maxlength="100000" id="content" name="content"
                                          placeholder="请输入公告内容" class="form-control" rows="5"></textarea>
                            </div>
                        </div>
                        <div class="col-md-3 red-tag tag">
                            *<span hidden="true" for="content" class="error"></span>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="row" style="min-height:60px;">
        <div class="form-group btn-form">
            <div class="col-md-11 text-center">
                <button class="btn btn-footer btn-cancel" onclick="toNoticeList()">取消</button>
                <button class="btn btn-footer btn-primary" type="submit" id="next">保存</button>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    var iscommiting = false;

    $(function () {
        $("#noticeAdd").validate({
            rules: {
                noticeType: {
                    required: true
                },
                title: {
                    required: true
                },

                content: {
                    required: true
                },

            },
            messages: {
                noticeType: {
                    required: "标题类型不能为空！"
                },
                title: {
                    required: "标题不能为空！"
                },
                content: {
                    required: "公告内容不能为空！"
                },

            },
            submitHandler: function (form) {
                if (iscommiting) {
                    return false;
                }
                iscommiting = true;

                jQuery(form).ajaxSubmit({
                    type: "post",
                    url: "${ctx}/info/notice/add",
                    beforeSubmit: function (formData, jqForm, options) {
                    },
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                    timeout: 120000,
                    success: function (data) {
                        if (data.flag) {
                            art.dialog({
                                title: '消息',
                                width: 220,
                                height: 80,
                                time: 3,
                                icon: 'succeed',
                                content: data.message,
                                close: function () {
                                    toNoticeList();
                                }
                            });
                        } else {
                            art.dialog({
                                title: '消息',
                                width: 200,
                                height: 80,
                                time: 3,
                                icon: 'warning',
                                content: '新建公告失败！',
                                close: function () {
                                    toNoticeList();
                                }
                            });
                            iscommiting = false;
                        }
                    },
                    error: function (e) {
                        iscommiting = false;
                    }
                });
            }
        });

    });

    function toNoticeList() {
        var requestUrl = "${ctx}/info/notice/main";
        var requestData = {};
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv');
    }

</script>