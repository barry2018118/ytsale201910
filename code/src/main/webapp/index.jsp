<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>云端行分销平台</title>
		<script type="text/javascript" src="${jsPath}/jquery.min.js"></script>
	</head>
	<body>
		<input type="hidden" id="currentUser" value="${sessionScope.session_user}" />
	</body>
</html>

<script type="text/javascript">
	$(document).ready(function() {
		window.location.href = "${ctx}/index" ;
	}) ;
</script>