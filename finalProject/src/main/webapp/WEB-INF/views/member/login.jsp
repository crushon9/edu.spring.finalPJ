<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>Member Login</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
			<h1>Member Login</h1>
			<form action="login" method="post">
				<input type="text" name="mmbId" placeholder="아이디" required autofocus>
				<br>
				<input type="password" name="mmbPassword" placeholder="비밀번호" required>
				<br><br>
				<input type="submit" value="로그인">
			</form>
			<a href="register"><input type="button" value="회원가입"></a>
		
		<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>
	
</body>
</html>