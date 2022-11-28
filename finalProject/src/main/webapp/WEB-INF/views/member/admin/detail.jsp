<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>Member Admin detail page</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
	
		<h1>Member Admin detail page</h1>
		<div>
	    <p>아이디 : ${vo.mmbId }</p>
	    <p>패스워드 : ${vo.mmbPassword }</p>
	    <p>이메일 : ${vo.mmbEmail }</p>
	 	<p>전화번호 : ${vo.mmbPhone }</p>
		<p>생년월일 : ${vo.mmbBirthday }</p>
		<p>선호지점 : ${vo.brcName }</p>
		</div>
		<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>
		
</body>
</html>