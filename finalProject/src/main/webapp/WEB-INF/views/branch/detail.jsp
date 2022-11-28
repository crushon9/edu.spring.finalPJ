<%@page import="edu.spring.project.domain.MovieVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>${vo.brcName}점 정보입니다.</title>
<style type="text/css">
.detail {
	display : inline-flex;
	width : 200px;
	height : 200px;
	border : 1px solid grey;
}
.mvContent {
	padding: 100px 200px 100px 200px;
	margin-right: 200px;
	margin-left: 200px;
}
.mvImage {
	margin-right: 150px;
	width: 300px;
	height: 450px;
}
</style>

</head>
<body>

	<h2>branch detail</h2>
	<h2>${vo.brcId}</h2>
	<p>${vo.brcName}</p>
	<p>${vo.brcTheaterNumbers}</p>
	<p>${vo.brcTheaterSeats}</p>
	<p>${vo.brcInfo}</p>
	<hr>

	<!-- reply 입력 -->
	<% String mmbId = (String) session.getAttribute("mmbIdSession"); %>
	
	<hr>

</body>
</html>