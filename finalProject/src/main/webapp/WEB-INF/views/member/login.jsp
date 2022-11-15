<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h1>로그인 페이지</h1><!-- contoroller 보낼 주소... -->
	<form action="login" method="POST">
		<input type="text" name="mmbId" placeholder="아이디" required autofocus>
		<br>
		<input type="password" name="mmbPassword" placeholder="비밀번호" required>
		<br>
		<input type="submit" value="로그인">
	</form>
</body>
</html>