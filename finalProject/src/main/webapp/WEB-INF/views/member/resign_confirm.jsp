<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>Resign</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
		<h1>회원 탈퇴 확인</h1>
		<p>개인정보 보호를 위하여 비밀번호를 다시 한번 입력해 주세요</p>
		<form action="resign_confirm" method="POST">
			<p>아이디&emsp;&emsp;<input type="text" name="mmbId" value="<%=mmbIdSession %>" readonly></p>
			<p>비밀번호&emsp;<input type="password" name="mmbPassword" placeholder="비밀번호" required>&nbsp;<input type="submit" value="확인"></p>
		</form>
		
		<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>

</body>
</html>