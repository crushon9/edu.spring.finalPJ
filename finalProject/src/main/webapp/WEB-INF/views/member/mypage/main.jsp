<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mypage.css">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>mypage main</title>
</head>
<body>
<%String mmbIdSession = (String) session.getAttribute("mmbIdSession");%>
	<h1 id="mainhead">My Page</h1>
	<div class="grid">
		<div class="gridleft">
			<ul>
				<li><a href="/project/member/update?mmbId=<%=mmbIdSession %>">회원정보 수정</a></li>
				<li><a href="/project/ticket/list">예매티켓 조회</a></li>
				<li><a href="/project/review/list">작성리뷰 조회</a></li>
			</ul>
		</div>
		<div class="gridright">
			<%=mmbIdSession %>님 환영!
		</div>
	</div>
</body>
</html>