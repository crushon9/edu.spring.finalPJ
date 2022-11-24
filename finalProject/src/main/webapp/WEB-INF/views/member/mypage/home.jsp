<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>mypage home</title>
</head>
<body>
	<%String mmbIdSession = (String) session.getAttribute("mmbIdSession");%>
	<%=mmbIdSession %>님 환영!
	<br>
	<br>
	<a href="update?mmbId=<%=mmbIdSession %>"><input type="button" value="회원정보수정하기"></a>
	<a href="/project/ticket/list"><input type="button" value="예매티켓 조회"></a>
	
</body>
</html>