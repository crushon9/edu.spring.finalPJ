<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>Ticket Buy</title>

<style>
	#tkPeopleDiv {
		border: 1px solid gray;
		display: inline-block;
		width: auto;
		padding: 5px;
		margin-left: 20px;
	}
	
	#tkInfoDiv {
		border: 1px solid gray;
		display: inline-block;
		width: auto;
		padding: 5px;
		margin-bottom: 40px;
	}
	
	#screen {
		border: 1px solid gray;
		width: 50%;
		margin: auto;
		text-align: center;
	}
	
	#seatBtnsDiv {
		margin: auto;
		text-align: center;
	}
	
	.seatBtnUnselected {
		width: 40px;
		background-color: lightgray;
		margin: 3px;
	}
	
	.seatBtnSelected {
		width: 40px;
		background-color: red;
		color: white;
		margin: 3px;
	}
	
	.seatBtnBooked {
		width: 40px;
		margin: 3px;
	}
</style>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">

		<h1>티켓 예매</h1>
		<!-- ScheduleVO에서 넘어온 데이터 -->
		<input type="hidden" id="mvTitle" value="${vo.mvTitle }">
		<input type="hidden" id="brcName" value="${vo.brcName }">
		<input type="hidden" id="scdDate" value="${vo.scdDate }">
		<input type="hidden" id="scdTime" value="${vo.scdTime }">
		<input type="hidden" id="scdTheater" value="${vo.scdTheater }">
		<input type="hidden" id="scdSeatTotal" value="${vo.scdSeatTotal }">
		<input type="hidden" id="scdSeatBookedCnt" value="${vo.scdSeatBookedCnt }">
		<input type="hidden" id="scdPrice" value="${vo.scdPrice }">
		
		<!-- TicketVO로 넘길 데이터 -->
		<form action="buy" method="post">
			<input type="hidden" name="mmbId" value="<%=mmbIdSession %>">
			<input type="hidden" name="scdId" value="${vo.scdId }">
			<input type="hidden" name="mvId" value="${vo.mvId }">
			<div id="tkPeopleDiv" >
				일반&emsp;
				<input type="radio" name="adult" value="0" checked>0
				<input type="radio" name="adult" value="1">1
				<input type="radio" name="adult" value="2">2
				<input type="radio" name="adult" value="3">3
				<input type="radio" name="adult" value="4">4
				<input type="radio" name="adult" value="5">5
				<input type="radio" name="adult" value="6">6
				<input type="radio" name="adult" value="7">7
				<input type="radio" name="adult" value="8">8
				<br>
				청소년
				<input type="radio" name="adolescent" value="0" checked>0
				<input type="radio" name="adolescent" value="1">1
				<input type="radio" name="adolescent" value="2">2
				<input type="radio" name="adolescent" value="3">3
				<input type="radio" name="adolescent" value="4">4
				<input type="radio" name="adolescent" value="5">5
				<input type="radio" name="adolescent" value="6">6
				<input type="radio" name="adolescent" value="7">7
				<input type="radio" name="adolescent" value="8">8
				<br>
				<input type="hidden" name="tkPeopleList" readonly>
				좌석&emsp;
				<input type="text" name="tkSeatList" readonly>
				<br>
				합계&emsp;
				<input type="text" name="tkPriceTotal" readonly>
				<input id="submit" type="submit" value="예매하기" disabled>
			</div>
			<div id="tkInfoDiv" >
				${vo.mvTitle } <br>
				${vo.brcName } ${vo.scdTheater }관 <br>
				${vo.scdDate } | <div id="scdTimeOutput" style="display: inline-block;"></div> <br>
				남은좌석 ${vo.scdSeatTotal - vo.scdSeatBookedCnt } / ${vo.scdSeatTotal }
			</div>
		</form>
			<div id="screen" >SCREEN</div>
			<div id="seatBtnsDiv"></div>
			
		<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>
	
	<script>
	 $(document).ready(function() {
		 scdTimePrint();
		 seatBtnPrint();
		 $('#seatBtnsDiv').children('button').click(function (){
			 alert('인원을 선택해주세요');
		 });
		 $('input[name=adult],input[name=adolescent]').change(function() {
			 setTkPeopleList();
			 setTkPriceTotal();
			 // 인원 변경되면 좌석버튼 초기화
			 seatBtnPrint();
			 $('#seatBtnsDiv').children('button').click(function (){
				 setTkSeatList(this);
			 });
		 });
	 });
	 
	 // 상영 시간 인덱스를 시간 문자열로 변환
	 function scdTimePrint() {
		 var timeArray = ["00:00", "00:30", "01:00", "01:30", "02:00", "07:00", "07:30", "08:00",
			 	"08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
			 	"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30",	"17:00",
			 	"17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30",
			 	"22:00", "22:30", "23:00", "23:30"];
		 var scdTime = $('#scdTime').val();
		 var realTime = timeArray[scdTime];
		 $('#scdTimeOutput').html(realTime);
	 }
	 
	 // 좌석 버튼 반복문 출력
	 function seatBtnPrint() {
		 console.log('seatBtnPrint() 호출');
		 var scdSeatTotal = $('#scdSeatTotal').val();
		 var seatAlphabetRowArray = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
					 			  'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
					 			  'U', 'V', 'W', 'X', 'Y', 'Z'];
		 // 최대좌석수에 따라 반복출력 패턴 조정
		 // 기본 좌석 열 10, 행은 총좌석을 열로 나눈 몫 + 1
		 var seatNumberColMax = 10;
		 var seatAlphabetRowMax = (scdSeatTotal / 10) + 1;
		 // 만약 25열을 넘으면 최대 좌석 열 25로 제한
		 if (scdSeatTotal / 10 > 25) {
			 seatNumberColMax = 25;
			 seatAlphabetRowMax = (scdSeatTotal / 25) + 1;
		 }
		 // 반복으로 좌석버튼생성
		 var seatBtns = '<br>';
		 var count = 0;
		 for (var row = 0; row < seatAlphabetRowMax; row++) { 
			 for (var col = 1; col <= seatNumberColMax; col++) {
				 count++;
				 // 행이 아래와 같을때 칸 띄우기
				 if (col == 4 || col == 11 || col == 17 || col == 23) {
					 seatBtns += '&emsp;';
				 }
				 // count가 총 좌석보다 적을때까지만 좌석버튼 출력
				 if (count <= scdSeatTotal) {
				 	var seatBtnId = seatAlphabetRowArray[row] + col;
				 	seatBtns += '<button class="seatBtnUnselected" id="' + seatBtnId + '">' + seatBtnId + '</button>';
				 }
			 }
			 seatBtns += '<br>';
		 }
		 $('#seatBtnsDiv').html(seatBtns);
		 $('input[name=tkSeatList]').val('');
		 getBookedList();
	 }
	 
	 
	 // DB에서 ajax로 예매된 좌석 불러오기
	 function getBookedList() {
		console.log('getBookedList() 호출');
		var scdId = $('input[name=scdId]').val();
		var url = '/project/ticket/listScdId/' + scdId;
		$.getJSON(			
				url,
			function(data) {// 서버에서 온 data가 저장되어있음
				$(data).each(function() {
					var temp = this.tkSeatList;
					var tempSplit = temp.split('&');
					for (var i = 0; i < tempSplit.length; i++) {
						$('#seatBtnsDiv').children('#' + tempSplit[i]).prop('class', 'seatBtnBooked');
						$('#seatBtnsDiv').children('#' + tempSplit[i]).prop('disabled', true);
					}
				}); // end data.each
			}
		); // end getJSON
	 } // end getBookedList
	 
	 // 선택한 인원정보 String 정보로 변환
	 function setTkPeopleList() {
		 var theNumOfAdt = $('input[type=radio][name=adult]:checked').val();
		 var theNumOfAdsc = $('input[type=radio][name=adolescent]:checked').val();
		 $('input[name=tkPeopleList]').val('adult='+ theNumOfAdt + '&adolescent='+ theNumOfAdsc);
	 }
	 
	 // 선택한 인원정보에 맞추어  tkPriceTotal 계산
	 function setTkPriceTotal() {
		 var theNumOfAdt = $('input[type=radio][name=adult]:checked').val();
		 var theNumOfAdsc = $('input[type=radio][name=adolescent]:checked').val();
		 var scdPrice = $('#scdPrice').val();
		 var tkPriceTotal = scdPrice * theNumOfAdt + (scdPrice - 3000) * theNumOfAdsc;
		 $('input[name=tkPriceTotal]').val(tkPriceTotal);
	 }
	 
	 // 선택한 좌석을 String 정보로 변환
	 function setTkSeatList(btn) {
		 console.log('setTkSeatList(btn) 호출');
		 var isSelected = $(btn).prop('class');
		 $(btn).prop('class', isSelected == 'seatBtnSelected' ? 'seatBtnUnselected' : 'seatBtnSelected');
		 // 선택인원 합계
		 var theNumOfAdt = $('input[type=radio][name=adult]:checked').val();
		 var theNumOfAdsc = $('input[type=radio][name=adolescent]:checked').val();
		 var peopleTotal = Number(theNumOfAdt) + Number(theNumOfAdsc);
		 // 선택좌석 합계
		 var seatBtnSelected =  $('#seatBtnsDiv').children('.seatBtnSelected');
		 var theNumOfSelected = seatBtnSelected.length;
		 // 선택인원과 좌석 비교
		 console.log('선택버튼수=' + theNumOfSelected + '인원수=' + peopleTotal);
		 if (theNumOfSelected <= peopleTotal) { // 선택버튼이 총인원보다 작거나 같을때
			 // 선택한 좌석을 String 정보로 변환
			 var tkSeatList = '';
			 for (var i = 0; i < theNumOfSelected; i++) {
			 	tkSeatList += seatBtnSelected.eq(i).text();
			 	if (i != theNumOfSelected - 1) {
			 		tkSeatList += '&';
			 	}
			 }
			 console.log('tkSeatList : ' + tkSeatList);
			 $('input[name=tkSeatList]').val(tkSeatList);
		 } else { // 선택버튼이 총인원보다 클때
			 $(btn).prop('class', 'seatBtnUnselected');
			 alert('선택 인원을 초과하였습니다');
		 }
		 // 선택버튼과 총인원이 동일할때만 결제하기 버튼 활성화
		 if (theNumOfSelected == peopleTotal) {
			$('#submit').prop('disabled', false);
		 } else if (theNumOfSelected < peopleTotal) {
			$('#submit').prop('disabled', true);
		 }
	 }
	</script>

</body>
</html>