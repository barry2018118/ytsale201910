<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/layouts/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ViewUrl</title>
    
    <link rel="stylesheet" href="${cssPath}/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" href="${cssPath}/bootstrap/font.css"/>
    <link rel="stylesheet" href="${cssPath}/bootstrap/font-awesome.css"/>
    <link rel="stylesheet" href="${cssPath}/bootstrap/se7en-font.css"/>
    <link rel="stylesheet" href="${cssPath}/style.css"/>
    
    <script src="${res}/js/jquery.min.js"></script>
	<script src="${res}/js/bootstrap.min.js"></script>
</head>

<body>
    <h1>系统路径说明</h1>
	<ul>
		<li>
			官方宣传网站，产品介绍：&nbsp;&nbsp;&nbsp;&nbsp;http://www.cserp.com
		</li>
		<li>
			系统管理后台：&nbsp;&nbsp;&nbsp;&nbsp;http://admin.cserp.com
		</li>
		<li>
			客户企业后台1：&nbsp;&nbsp;&nbsp;&nbsp;http://xy.cserp.com
		</li>
		<li>
			客户企业后台2：&nbsp;&nbsp;&nbsp;&nbsp;http://zx.cserp.com
		</li>
	</ul>
	<br/><br/>
	<ul>
		<li>
			管理后台--机构管理：&nbsp;&nbsp;&nbsp;&nbsp;/manage/enterprise
		</li>
		<li>
			管理后台--部门管理：&nbsp;&nbsp;&nbsp;&nbsp;/manage/department
		</li>
		<li>
			管理后台--员工管理：&nbsp;&nbsp;&nbsp;&nbsp;/manage/employee
		</li>
		<li>
			管理后台--功能管理：&nbsp;&nbsp;&nbsp;&nbsp;/manage/function
		</li>
		<li>
			管理后台--权限管理：&nbsp;&nbsp;&nbsp;&nbsp;/manage/role
		</li>
	</ul>	
</body>
</html>