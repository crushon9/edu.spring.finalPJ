<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 포맷형태 바꾸는 taglib 이걸로 서버에서 받아온 날짜포맷변경할거-->
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<style type="text/css">
table, th, td {
	border-style: solid;
	border-width: 1px;
	text-align: center;
}
ul {
	list-style-type: none;
}
li {
	display: inline-block;
}
</style>

<meta charset="UTF-8">
<title>movie chart</title>
</head>

<body>
	<h1>movie chart</h1>
	<a href="detail"><input type="button" value="상세보기"></a>	
	
	<img class="image" src="display?fileName=/${vo.mvImage}"/>
	
	
	
	
</body>
</html>