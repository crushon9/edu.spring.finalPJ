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
	
	<input type="hidden" id="memberRegisterResult" value="${memberRegisterResult}"/>
	<input type="hidden" id="memberLoginResult" value="${memberLoginResult}"/>	
	
	<script type="text/javascript">
		$(document).ready(function() {
			responseAlert();
		});
		
		// 결과 값에 대한 알러트
		function responseAlert() {
			var memberRegisterResult = $('#memberRegisterResult').val();
			if (memberRegisterResult == 'success'){
				alert("회원가입에 성공하였습니다. 멤버가 되신 것을 축하합니다.");
			} 
			var memberLoginResult = $('#memberLoginResult').val();
			if (memberLoginResult == 'fail'){
				alert("로그인이 실패하였습니다. 정보를 다시 확인해주세요");
			}
			
		}
		
	</script>
</body>
</html>