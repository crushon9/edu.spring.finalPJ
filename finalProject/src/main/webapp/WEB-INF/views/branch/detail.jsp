<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>branch detail</title>
	
	<style type="text/css">
	.detail {
		display : inline-flex;
		width : 200px;
		height : 200px;
		border : 1px solid grey;
	}
	.mvContent {
		padding: 100px 200px 100px 200px;
		margin-right: 200px;
		margin-left: 200px;
	}
	.mvImage {
		margin-right: 150px;
		width: 300px;
		height: 450px;
	}
	</style>

</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">

		<h1>지점 정보</h1>
		<p>지점이름</p>${vo.brcName}
		<p>지점번호</p>${vo.brcId}
		<p>극장수</p> ${vo.brcTheaterNumbers}
		<p>좌석수 </p>${vo.brcTheaterSeats}
		<p>지점정보</p> ${vo.brcInfo}
		
		<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>
	
</body>
</html>