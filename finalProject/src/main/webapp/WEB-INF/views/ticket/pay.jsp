<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>Ticket Pay</title>

<style>
#tkPeople {
	border: 1px solid gray;
	display: inline-block;
	width: 300px;
	margin: 5px;
	padding: 5px;
}

#tkInfo {
	border: 1px solid gray;
	display: inline-block;
	width: 400px;
	margin: 5px;
	padding: 5px;
}

#screen {
	border: 1px solid gray;
	width: 600px;
	margin-left: 120px;
	text-align: center;
}
</style>

</head>
<body>

	<h2>티켓 예매</h2>
	<form action="pay" method="post">
		mmbId<input type="text" name="mmbId" value="${vo.mmbId }" readonly>
		scdId<input type="number" name="scdId" value="${vo.scdId }" readonly>
		mvId<input type="number" name="mvId" value="${vo.mvId }" readonly>
		mvTitle<input type="text" name="mvTitle" value="${vo.mvTitle }" readonly>
		brcName<input type="text" name="brcName" value="${vo.brcName }" readonly>
		scdDate<input type="text" name="scdDate" value="${vo.scdDate }" readonly>
		scdTime<input type="number" name="scdTime" value="${vo.scdTime }" readonly>
		scdTheater<input type="number" name="scdTheater" value="${vo.scdTheater }" readonly>
		scdSeatTotal<input type="number" name="scdSeatTotal" value="${vo.scdSeatTotal }" readonly>
		scdSeatBookedCnt<input type="number" name="scdSeatBookedCnt" value="${vo.scdSeatBookedCnt }" readonly>
		scdPrice<input type="number" name="scdPrice" value="${vo.scdPrice }" readonly>
	</form>
	<hr>
	<div id="tkPeople" >
		일반&emsp;
		<input type="radio" name="adult" value="0" checked>0
		<input type="radio" name="adult" value="1">1
		<input type="radio" name="adult" value="2">2
		<input type="radio" name="adult" value="3">3
		<input type="radio" name="adult" value="4">4
		<input type="radio" name="adult" value="5">5
		<input type="radio" name="adult" value="6">6
		<br>
		청소년
		<input type="radio" name="adolescent" value="0" checked>0
		<input type="radio" name="adolescent" value="1">1
		<input type="radio" name="adolescent" value="2">2
		<input type="radio" name="adolescent" value="3">3
		<input type="radio" name="adolescent" value="4">4
		<input type="radio" name="adolescent" value="5">5
		<input type="radio" name="adolescent" value="6">6
		<input type="text" name="tkPeopleList" readonly>
		<br>
		좌석&emsp;
		<input type="text" name="tkSeatList" readonly>
		<br>
		합계&emsp;
		<input type="text" name="tkPriceTotal" readonly>
		
	</div>
	<div id="tkInfo" >
	${vo.mvTitle } <br>
	${vo.brcName } ${vo.scdTheater }관 <br>
	${vo.scdDate } | <div id="scdTimeOutput" style="display: inline-block;"></div> <br>
	남은좌석 ${vo.scdSeatTotal - vo.scdSeatBookedCnt } / ${vo.scdSeatTotal }
	</div>
	<br><br>
	
	<div id="screen" >SCREEN</div>
	<div id="seatBtn"></div>
	
	<script>
	 $(document).ready(function() {
		 scdTimePrint();
		 seatBtnPrint();
		 $('input[name=adult],input[name=adolescent]').change(function() {
			 setTkPeopleList();
		 });
	 });
	 
	 function scdTimePrint() {
		 var timeArray = ["00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30",
			 	"04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00",
			 	"08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
			 	"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30",	"17:00",
			 	"17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30",
			 	"22:00", "22:30", "23:00", "23:30"];
		 var scdTime = $('input[name=scdTime]').val();
		 var realTime = timeArray[scdTime];
		 $('#scdTimeOutput').html(realTime);
	 }
	 
	 function seatBtnPrint() {
		 var scdSeatTotal = $('input[name=scdSeatTotal]').val();
		 
		 var seatBtnItems = '';
		 for (var i = 1; i <= scdSeatTotal/10; i++) {
			 seatBtnItems += i;
			 if (i != scdSeatTotal/10) {
				 seatBtnItems += ','
			 }
		 }
		 
		 var seatBtn = '<br>'
		 + '<c:forTokens items="A,B,C,D,E,F,G,H,I,J" delims="," var="itemO">'
		 + '<c:forTokens items="' + seatBtnItems + '" delims="," var="itemI">'
		 + '<button class="seatBtn" style="width :40px">${itemO}${itemI}</button>'
		 + '</c:forTokens>'
		 + '<br>'
		 + '</c:forTokens>';
		 console.log(seatBtn);
		 $('#seatBtn').html(seatBtn);
	 }
	 
	 function setTkPeopleList() {
		 var adult = $('input[type=radio][name=adult]:checked').val();
		 var adolescent = $('input[type=radio][name=adolescent]:checked').val();
		 $('input[name=tkPeopleList]').val('adult='+ adult + '&adolescent='+ adolescent);
	 }
	 
	</script>

</body>
</html>