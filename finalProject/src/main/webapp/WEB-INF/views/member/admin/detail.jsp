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
	
		<h1>회원 정보 조회 (관리자용)</h1>
		<div>
	    <p>아이디</p>
	    ${vo.mmbId }
	    <p>이메일</p>
	    ${vo.mmbEmail }
	 	<p>전화번호</p>
	 	${vo.mmbPhone }
		<p>생년월일 </p>
		${vo.mmbBirthday }
		<p>선호지점 </p>
		${vo.brcName }점
		<p>탈퇴여부</p>
		${vo.mmbResignCheck }
		</div>
		<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>
		
</body>
</html>