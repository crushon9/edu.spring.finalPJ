<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>Ticket Pay</title>
</head>
<body>

	<h2>티켓 예매</h2>
	<form action="pay" method="post">
		<input type="text" name="mmbId" value="${vo.mmbId }" readonly>
		<input type="number" name="scdId" value="${vo.scdId }" readonly>
		<input type="number" name="mvId" value="${vo.mvId }" readonly>
		<input type="text" name="tkPeopleList" value="${vo.tkPeopleList }" readonly>
		<input type="text" name="tkSeatList" value="${vo.tkSeatList }" readonly>
		<input type="number" name="tkPriceTotal" value="${vo.tkPriceTotal }" readonly>
		<input type="text" name="mvTitle" value="${vo.mvTitle }" readonly>
		<input type="text" name="brcName" value="${vo.brcName }" readonly>
		<input type="text" name="scdDate" value="${vo.scdDate }" readonly>
		<input type="number" name="scdTime" value="${vo.scdTime }" readonly>
		<input type="number" name="scdTheater" value="${vo.scdTheater }" readonly>
		<input type="number" name="scdSeatTotal" value="${vo.scdSeatTotal }" readonly>
		<input type="number" name="scdSeatBookedCnt" value="${vo.scdSeatBookedCnt }" readonly>
		<input type="number" name="scdPrice" value="${vo.scdPrice }" readonly>
	</form>
	<hr>
	<div style="border :1px solid gray; width :250px;">
		일반
		0<input type="radio" name="adult" value="0">
		1<input type="radio" name="adult" value="1">
		2<input type="radio" name="adult" value="2">
		3<input type="radio" name="adult" value="3">
		4<input type="radio" name="adult" value="4">
		<br>
		청소년
		0<input type="radio" name="adolescent" value="0">
		1<input type="radio" name="adolescent" value="1">
		2<input type="radio" name="adolescent" value="2">
		3<input type="radio" name="adolescent" value="3">
		4<input type="radio" name="adolescent" value="4">
	</div><br>
	
	<c:forTokens var="itemA" items="A,B,C,D,E,F,G,H,I,J,K" delims=",">
		<c:forTokens var="itemB" items="1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20" delims=",">
			<button style="width :40px">${itemA}${itemB}</button>
		</c:forTokens>
		<br>
	</c:forTokens>

</body>
</html>