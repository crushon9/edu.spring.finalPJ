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
	
	<h2>로그인</h2>
	<a href="../member/login">로그인</a>
	<h3>마이페이지</h3>
	<a href="/member/mypage">마이페이지</a>		
	<h3>비밀번호 변경</h3>
	<a href="changeMmbPassword">변경</a>
	<div>
	<a href="../member/logout">로그아웃</a>
	</div>
	
<!--	<c:forEach var = "memberVO" items="${memberVO }" varStatus="status">
		<h3> phone : ${memberVO.mmbPhone }</h3>
	<hr>
	</c:forEach>
	 
	 -->
	
</body>
</html>