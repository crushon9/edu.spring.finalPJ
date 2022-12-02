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
	
/* 좌석 선택여부와 예매여부를 3개의 클래스로 구분 */
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
/* --------------------------- */
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
		 // 상영 시간 인덱스를 시간 문자열로 변환출력
		 $('#scdTimeOutput').html(publicScdTimeArray[$('#scdTime').val()]);
		 // 초기 좌석 버튼 반복문 출력
		 seatBtnPrint();
		 // 초기 인원 미선택시 알람 
		 $('#seatBtnsDiv').children('button').click(function (){
			 alert('인원을 선택해주세요');
		 });
		 // 인원 변경시 인원정보, 가격정보, 좌석버튼 초기화
		 $('input[name=adult],input[name=adolescent]').change(function() {
			 setTkPeopleList();
			 setTkPriceTotal();
			 seatBtnPrint();
			 // 좌석 버튼을 클릭할때마다 클릭한 좌석의 정보에 따라 인원,좌석정보 변경 이벤트 발생
			 $('#seatBtnsDiv').children('button').click(function (){
				 setSeatBtnEvent(this);
			 });
		 });
	 });
	 
	 // 좌석 버튼 반복문 기본틀 출력
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
				 	// seatId : 영문(row) + 숫자(col)
				 	var seatId = seatAlphabetRowArray[row] + col;
				 	// 추후 데이터 접근을 위해 좌석ID를 해당 좌석 버튼에 아이디로 지정
				 	seatBtns += '<button class="seatBtnUnselected" id="' + seatId + '">' + seatId + '</button>';
				 }
			 }
			 seatBtns += '<br>';
		 }
		 $('#seatBtnsDiv').html(seatBtns);
		 $('input[name=tkSeatList]').val('');
		 // 기본틀 출력 후 DB에서 예매된 좌석 정보 가져오기
		 getBookedList();
	 }
	 
	 // DB에서 ajax로 예매된 좌석 불러오기
	 function getBookedList() {
		console.log('getBookedList() 호출');
		var scdId = $('input[name=scdId]').val();
		var url = '/project/ticket/listScdId/' + scdId;
		$.getJSON(
				url,
			function(data) {
				// data : scdId에 해당하는 좌석정보들을 담은 리스트
				$(data).each(function() {
					// seatSplitedArray : 하나의 구매내역에 해당하는 좌석 String을 구분자 &로 나눈 배열
					var seatSplitedArray = this.tkSeatList.split('&');
					for (var i = 0; i < seatSplitedArray.length; i++) {
						// seatId로 접근하여 예매된 좌석은 seatBtnBooked 클래스 적용 (디자인 효과 부여 및 비활성화)
						$('#seatBtnsDiv').children('#' + seatSplitedArray[i]).prop('class', 'seatBtnBooked');
						$('#seatBtnsDiv').children('#' + seatSplitedArray[i]).prop('disabled', true);
					}
				}); // end data.each
			}
		); // end getJSON
	 }
	 
	 // 선택한 좌석을 String 정보로 변환
	 function setSeatBtnEvent(btn) {
		 console.log('setSeatBtnEvent(btn) 호출');
		 var btnClass = $(btn).prop('class');
		 // 클릭한 좌석버튼의 클래스가 선택이면 미선택으로, 아니면 선택으로 (toggle)
		 $(btn).prop('class', btnClass == 'seatBtnSelected' ? 'seatBtnUnselected' : 'seatBtnSelected');
		 // peopleTotal : 선택인원 합계
		 var adultNumber = $('input[type=radio][name=adult]:checked').val();
		 var adolescentNumber = $('input[type=radio][name=adolescent]:checked').val();
		 var peopleTotal = Number(adultNumber) + Number(adolescentNumber);
		 // seatBtnSelectedTotal : 선택좌석 합계
		 var seatBtnSelectedArray =  $('#seatBtnsDiv').children('.seatBtnSelected');
		 var seatBtnSelectedTotal = seatBtnSelectedArray.length;
		 // 선택인원과 현재 선택된 좌석 수 비교
		 console.log('현재선택버튼수=' + seatBtnSelectedTotal + '인원수=' + peopleTotal);
		// 선택된 좌석 버튼수가 총인원보다 작거나 같을때
		 if (seatBtnSelectedTotal <= peopleTotal) { 
			 // 선택한 좌석을 String 정보로 변환
			 var tkSeatList = '';
			 for (var index = 0; index < seatBtnSelectedArray.length; index++) {
				 // seatBtnSelectedArray의 요소를 eq(index)로 접근하여 seatId를  String 이어붙이기
			 	tkSeatList += seatBtnSelectedArray.eq(index).text();
			 	// 마지막 요소만 제외하고 좌석간 구분자 &
			 	if (index != seatBtnSelectedArray.length - 1) { 
			 		tkSeatList += '&';
			 	}
			 }
			 $('input[name=tkSeatList]').val(tkSeatList);
		 // 선택버튼이 총인원보다 클때	 
		 } else {
			 // 현재 누른 버튼을 seatBtnUnselected로 클래스 변경한 뒤 알람
			 $(btn).prop('class', 'seatBtnUnselected');
			 alert('선택 인원을 초과하였습니다');
		 }
		 // 선택버튼과 총인원이 동일할때만 결제하기 버튼 활성화
		 if (seatBtnSelectedTotal == peopleTotal) {
			$('#submit').prop('disabled', false);
		 } else if (seatBtnSelectedTotal < peopleTotal) {
			$('#submit').prop('disabled', true);
		 }
	 }
	 
	 // 선택한 인원정보 String 정보로 변환하여 input박스에 출력
	 function setTkPeopleList() {
		 var theNumOfAdt = $('input[type=radio][name=adult]:checked').val();
		 var theNumOfAdsc = $('input[type=radio][name=adolescent]:checked').val();
		 $('input[name=tkPeopleList]').val('adult='+ theNumOfAdt + '&adolescent='+ theNumOfAdsc);
	 }
	 
	 // 선택한 인원정보에 맞추어  총 가격 계산하여 input박스에 출력
	 function setTkPriceTotal() {
		 var theNumOfAdt = $('input[type=radio][name=adult]:checked').val();
		 var theNumOfAdsc = $('input[type=radio][name=adolescent]:checked').val();
		 var scdPrice = $('#scdPrice').val();
		 var tkPriceTotal = scdPrice * theNumOfAdt + (scdPrice - 3000) * theNumOfAdsc;
		 $('input[name=tkPriceTotal]').val(tkPriceTotal);
	 }
	</script>

</body>
</html>