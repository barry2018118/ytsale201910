<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>分销系统管理后台</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    
    <link rel="stylesheet" href="${cssPath}/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" href="${cssPath}/bootstrap/font.css"/>
    <link rel="stylesheet" href="${cssPath}/bootstrap/font-awesome.css"/>
    <link rel="stylesheet" href="${cssPath}/bootstrap/se7en-font.css"/>
    <link rel="stylesheet" href="${cssPath}/bootstrap/datatables.css"/>
    <link rel="stylesheet" href="${cssPath}/style.css"/>
    <link rel="stylesheet" href="${jsPath}/jquery-validation/1.10.0/validate.css" />
    <sitemesh:head/>
</head>

<body>
<input type="hidden" id="ctx" value="${ctx}" />
<div class="modal-shiftfix">
    <div id="main_top" class="top">
        <%--@ include file="/WEB-INF/jsp/layouts/common/header.jsp"--%>
    </div>
    <div id="main_left" class="left-nav">
        <%--@ include file="/WEB-INF/jsp/layouts/common/left.jsp"--%>
    </div>
    <div id="mainContentDiv" class="container-fluid main-content" style="">
        <div style="width:100%; height:100%; position:relative; background:#f7f7f7;">
            <img src="${imagesPath}/wel_img.png" class="welcome-img" />
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript" src="${jsPath}/jquery.min.js"></script>
<script type="text/javascript" src="${jsPath}/bootstrap.min.js"></script>
<script type="text/javascript" src="${jsPath}/nav.js"></script>
<script type="text/javascript" src="${jsPath}/common.js"></script>
<script type="text/javascript" src="${jsPath}/modernizr.custom.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.validate.js" ></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/messages_bs_zh.js"></script>
<script type="text/javascript" src="${jsPath}/jquery-validation/1.10.0/jquery.form.js"></script>
<script type="text/javascript" src="${jsPath}/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="${jsPath}/artDialog/plugins/iframeTools.source.js?skin=blue"></script>
<script type="text/javascript" src="${jsPath}/highChart/highChart.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        var requestUrl = "${ctx}/manage/userFunction/getModule" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'main_top') ;
    
        
        $(".left-nav ul li").click(function(){
            var $href= $(this).addClass("on").children("a").attr("href");
            // 点击链接不发生跳转
            var href = window.location.href;
            window.location.href = href.substr(0, href.indexOf('#')) + '#' + $href;
            return false;
        });
        $("#my li").each(function(){
            $(this).click(function(){
                var $href= $(this).children("a").attr("href");
                $(".J_iframe").attr("src", $href);
            });
        });
       
    });
</script>