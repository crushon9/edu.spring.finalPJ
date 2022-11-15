<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>mypage</title>
</head>
<body>
	<h1>mypage</h1>
	<h2>${memberVO.mmbId }님 정보임</h2>
	
	<h2>d</h2>
	<a href="../">d</a>
	<h3>d</h3>
	<a href="">신청 d</a>
	
	<h3>d</h3>
	<a href="">d d</a>
	
	<h3>비밀번호 변경</h3>
	<a href="changePW">d 변경</a>
	<div>
	<a href="../auth/logout">로그 아웃</a>
	</div>
	
	<c:forEach var = "memberVO" items="${memberVO }" varStatus="status">
		<h3> phone : ${memberVO.mmbPhone }</h3>
	<hr>
	</c:forEach>
	
	
	
</body>
</html>