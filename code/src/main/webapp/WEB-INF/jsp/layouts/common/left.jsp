<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>分销系统</title>
</head>
    
<ul>
    <c:forEach items="${lstUserMenu}" var="userMenu" varStatus="f">
        <li>
            <div class="title" url="${ctx}${userMenu.menuUrl}">
                <i class="nav-icon-chanpin"></i>
                ${userMenu.menuName} <span class="fr-icon icon-angle-down"></span>
            </div>
            <ul class="expecial">
                <c:forEach items="${userMenu.userAction}" var="userMenu" varStatus="s">
                    <li url="${ctx}${userMenu.actionUrl}">
                        <a href="javascript:;">${userMenu.actionName}</a>
                    </li>
                </c:forEach>
            </ul>
        </li>
    </c:forEach>
</ul>


<script type="text/javascript">
    $(document).ready(function() {
        $(".left-nav ul li").click(function() {
            $(this).addClass("on").siblings("li").removeClass("on");
            $(this).addClass("on").siblings("li").find(".title").removeClass("on").next("ul").hide();
            $(this).addClass("on").siblings("li").find("li").removeClass("on");
           /*  var $href= $(this).addClass("on").children("a").attr("href");
            $(".J_iframe").attr("src",$href);
            $(".J_iframe").attr("data-id",$href); */
        }) ;

        $(".left-nav li .title").click(function() {
            $(this).next("ul").toggle();
            $(this).toggleClass("on");
            $(this).addClass("on").parents("li").siblings("li").find(".title").removeClass("on").next("ul").hide();
            $(this).find(".fr-icon").toggleClass("icon-angle-up");
            $(this).parents("li").siblings("li").find(".title .fr-icon").removeClass("icon-angle-up");
            
            // 增加“点击菜单”触发请求功能
            var menuUrl = $(this).attr("url") ;
            if(menuUrl != "") {
                 var requestUrl = menuUrl ;
                var requestData = {} ;
                cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
            }
        });
        
        /**
         * 进入右侧具体功能页面
         */
        if($(".left-nav .expecial li").length) {
            $(".left-nav .expecial li").click(function() {
                var moduleUrl = $(this).attr("url") ;
	            
	            if(moduleUrl=="/camp") {
	                showEmptyPage('mainContentDiv') ;
	                var requestUrl = "${ctx}/manage/empty" ;
	                // cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
	            } else {
	                var requestUrl = moduleUrl ;
	                var requestData = {} ;
	                cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
	            }
	        }) ;
	        
	        $(".left-nav > ul > li .title").get(0).click() ;
	        $(".left-nav .expecial li").get(0).click() ;
        } else {
        }
    });
</script>