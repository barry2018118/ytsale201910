<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>分销系统</title>
</head>
<div class="logo">
    <img src="${res}/images/login/logo.png">
</div>

<div id="sys_module" class="pull-left left-menu">
    <c:if test="${empty lstUserModule}">
        <p style="width:800px; text-align:center; font-size:20px; line-height:66px; margin:0; letter-spacing: 1px;">管理员还未给您分配功能</p>
    </c:if>
    <c:forEach items="${lstUserModule}" var="userModule" varStatus="s">
        <div >
            <a href="javascript:;" url="${ctx}${userModule.moduleUrl}" moduleId="${userModule.moduleId}">
                <img src="${imagesPath}/sys/${userModule.moduleIcon}-active.png" alt="${userModule.moduleName}" />
                <span icon="${userModule.moduleIcon}">${userModule.moduleName}</span>
            </a>
        </div>
        </li>
    </c:forEach>
</div>

<div class="pull-right  hidden-sm">
    <ul class="nav navbar-nav pull-right">
        <li class="dropdown user hidden-xs"><a data-toggle="dropdown" class="dropdown-toggle" href="#">
            <img width="24" height="24" src="${res}/images/iconfont-denglu.png" />${user.name}<b class="caret"></b></a>
            <ul class="dropdown-menu" id="my">
                <li>
                    <a href="javascript:;" onclick="editInfo()">
                        <i class="icon-gear"></i>个人资料
                    </a>
                </li>
                <li>
                    <a href="javascript:;" onclick="editPassword()">
                        <i class="icon-gear"></i>修改密码
                    </a>
                </li>
                <li>
                    <a href="${ctx}/logout">
                        <i class="icon-signout"></i>退出
                    </a>
                </li>
            </ul>
        </li>
    </ul>
</div>
        
        
<script type="text/javascript">
    $(document).ready(function() {
    
        $("#sys_module > div > a").click(function() {
            var moduleUrl = $(this).attr("url") ;
            var moduleId = $(this).attr("moduleId") ;
            if(moduleUrl=="") {
                art.dialog({
                    title: '消息',
                    id: "showModuleFunctions" ,
                    width: 200, 
                    height: 100,
                    time: 5 ,
                    icon: 'warning' ,
                    content: '此模块下尚未设置功能！' ,
                        close:function() {
                        }
                    }) ;
                return ;    
            } else {
                // 清除图标点击效果
                setIconHover() ;
                
                // 增加图标点击效果
                var img = $(this).children("img") ;
                $(img).remove() ;
                var span = $(this).children("span") ;
                var iconImage = $(span).attr("icon") ;
                var iconText = $(span).text() ;
                $(span).before("<img src='${imagesPath}/sys/"+iconImage+"-hover.png' alt='"+iconText+"' />") ;
                $(span).css("color","#00aae9") ;
                
                // 加载模块下的菜单
                var requestUrl = moduleUrl ;
                var requestData = {"moduleId": moduleId} ;
                cserpLoadPage(requestUrl, requestData, 'GET', 'main_left') ;
            }
        }) ;
        
        $("#sys_module > div > a").hover(function() {
        }) ;
        
        $("#sys_module > div > a").get(0).click() ;
    });
    
    function setIconHover() {
        $("#sys_module > div > a").each(function(index) {
            var img = $(this).children("img") ;
            $(img).remove() ;
            
            var span = $(this).children("span") ;
            var iconImage = $(span).attr("icon") ;
            var iconText = $(span).text() ;
            $(span).before("<img src='${imagesPath}/sys/"+iconImage+"-active.png' alt='"+iconText+"' />") ;
            $(span).css("color","#333") ;
        }) ;
    }
    
    function editPassword(){
        var requestUrl = "${ctx}/manage/user/editpassword" ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
    
    function editInfo(){
        var requestUrl = "${ctx}/my/user/${user.username}/toEditUsername";
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'mainContentDiv') ;
    }
    
</script>