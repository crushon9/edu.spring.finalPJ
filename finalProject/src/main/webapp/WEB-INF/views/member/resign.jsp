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
			<h1>회원 탈퇴</h1>
			<p>탈퇴 이후에는 기존의 회원 서비스 이용이 제한됩니다. <br> 아래 '탈퇴하기' 버튼을 누르시면 탈퇴가 완료됩니다.</p>
	 		<form action="resign" method="POST">
				<input type="hidden" name="mmbId" value="<%=mmbIdSession %>">
				<input type="submit" value="탈퇴하기">
			</form>
		
		<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>

</body>
</html>