<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<ul class="BbsInBox_right">
	<c:forEach items="${lstNotice}" var="notice" varStatus="s">
		<li>
            <span>${notice.ctreateTimeStr}</span>
            <em>${s.index+1}、</em>
            <a data-toggle="modal" href="#noticeModal" onclick="toViewNotice(${notice.id})">${notice.title}</a>
            <i></i>
        </li>
	</c:forEach>
</ul>

<script type="text/javascript">
	$(document).ready(function() {}) ;

     function toViewNotice(id) {
        // 获取公告信息
        var requestUrl = "${ctx}/chiefly/shop/getNotice/" + id ;
        var requestData = {} ;
        cserpLoadPage(requestUrl, requestData, 'GET', 'noticeModal') ;
    }
    
</script>