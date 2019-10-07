<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="res" value="${pageContext.request.contextPath}/static" />
<c:set var="jsPath" value="${pageContext.request.contextPath}/static/js" />
<c:set var="cssPath" value="${pageContext.request.contextPath}/static/css" />
<c:set var="imagesPath" value="${pageContext.request.contextPath}/static/images" />
<%-- <c:set var="picCtx" value="https://invest.coolshow.net" /> --%>
<script type="text/javascript">var picCtx = '${picCtx}', ctx = '${ctx}',ctxStatic='${res}';</script>

<c:set var="showResourcePath" value="http://49.4.6.218:8889" />

<!-- 
	<%--@ page import="com.ds.learn.util.Statics">
	<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
	<c:set var="up_prefix" value="<%=Statics.UPLOAD_PATH_PREFIX %>"/>
	<c:set var="res_prefix" value="<%=Statics.UPLOAD_PATH --%>"/>
-->