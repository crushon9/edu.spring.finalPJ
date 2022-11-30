<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>Schedule List</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
		<h1>상영 스케줄 정보</h1>
		
		<div>
		<input type="number" id="mvId" value="${mvId }">
		<input type="number" id="brcId" value="${brcId }">
		<input type="text" id="scdDate" value="${scdDate }">
		<p>상영 날짜</p>
		<input type="date" id="dateSelected" name="dateSelected">
		<p>상영 영화</p>
		<div id="mvListOutput"></div>
		
		<p>상영 지점</p>
		<select id="brcArea" name="brcArea">
			<option>지역선택</option>
			<option value="1">서울</option>
			<option value="2">경기/강원</option>
			<option value="3">부산/경상</option>
			<option value="4">대전/충청</option>
			<option value="5">광주/전라</option>
			<option value="6">제주</option>
		</select>
		<div id="brcListDiv"></div>
	
		<p>상영 스케줄</p>
		<input type="hidden" id="mmbId" value="<%=mmbIdSession%>">
		<input type="hidden" id="admin" value="<%=adminSession%>">
		<div id="scheduleListOutput"></div>
		</div>
		<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>

	<script type="text/javascript">
	  $(document).ready(function() {
			// #dateSelected를 오늘날짜로 기본값 세팅
			$('#dateSelected').val(new Date().toISOString().substring(0, 10));
			getScheduleList();
			// 값 선택시 비동기로 데이터 가져옴
			$('#brcArea').change(function() {
				getBrcList();
			});
			$('#dateSelected').change(function() {
				console.log("#dateSelected.change");
				getMvList();
				getScheduleList();
			});
	   });
	  
	  // 선택 지역의 지점 가져오기
	  function getBrcList() {
		var brcArea = $('#brcArea').val();
		var url = '/project/branch/list/' + brcArea;
		$.getJSON( // $.getJSON 방식이므로 JSON.stringify하지 않아도 되고, header도 없어도됨
			url,
			function(data) {
				var brcList = '<select id="brcId" name="brcId" ><option value="0">지점선택</option>';
				$(data).each(function() {
					brcList += '<option value="' + this.brcId + '">' + this.brcName + '</option>';
				});
				brcList += '</select>';
				$('#brcListDiv').html(brcList);
				$('#brcId').change(function() {
					console.log("#brcId.click");
					getScheduleList();
				});
			}
		);
	  } // end getBrcList
	  
	  // 선택 날짜에 상영중인 영화 목록 가져오기
	  function getMvList() {
		var dateSelected = $('#dateSelected').val();
		var url = '/project/movie/list/' + dateSelected;
		$.getJSON(
			url,
			function(data) {
				var mvList = '<select id="mvId" name="mvId"><option value="0">영화선택</option>';
				$(data).each(function() {
					mvList += '<option value="' + this.mvId + '">' + this.mvTitle + '</option>';
				});
				mvList += '</select>';
				$('#mvListOutput').html(mvList);
				$('#mvId').change(function() {
					console.log("#mvId.click");
					getScheduleList();
				});
			}
		);
	  } // end getMvList
	  
	  function getScheduleList() {
 		  console.log('getScheduleList() 호출');
		  var mvId = $("#mvId").val();
 		  var brcId = $("#brcId").val();
		  var dateSelected = $("#dateSelected").val();
		  if (mvId == null) {
			  mvId = 0;
		  }
		  if (brcId == null) {
			  brcId = 0;
		  }
		  if (dateSelected == '') {
			  dateSelected = 'none';
		  }
		  console.log('mvId : ' + mvId);
		  console.log('brcId : ' + brcId);
		  console.log('dateSelected : ' + dateSelected);
		  var url = '/project/schedule/list/' + mvId + '&' + brcId + '&' + dateSelected;
		  console.log(url);
		  var timeArray = ["00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30",
			 	"04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00",
			 	"08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
			 	"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30",	"17:00",
			 	"17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30",
			 	"22:00", "22:30", "23:00", "23:30"];
		  var mmbId = $('#mmbId').val();
		  var admin = $('#admin').val();
		  var scheduleList = '<ul>';
			$.getJSON(			
					url,
				function(data) {
					$(data).each(function() {
						var ticketURL = '/project/ticket/buy'
										+ '?scdId=' + this.scdId
										+ '&mvId=' + this.mvId
										+ '&mvTitle=' + this.mvTitle
										+ '&brcName=' + this.brcName
										+ '&scdDate=' + this.scdDate
										+ '&scdTime=' + this.scdTime 
										+ '&scdTheater=' + this.scdTheater 
										+ '&scdSeatTotal=' + this.scdSeatTotal 
										+ '&scdSeatBookedCnt=' + this.scdSeatBookedCnt 
										+ '&scdPrice=' + this.scdPrice;
						
						scheduleList += '<li>'
										+ '지점 : ' + this.brcName
										+ ', 영화 : ' + this.mvTitle
										+ ', 상영일 : ' + this.scdDate
										+ ', 상영시간 : ' + timeArray[this.scdTime]
										+ ', 상영관 : ' + this.scdTheater + '관'
										+ ', 잔여좌석 : ' + (this.scdSeatTotal - this.scdSeatBookedCnt) + '/' + this.scdSeatTotal
										+ ', 예매가격 : ' + this.scdPrice
										+ '&nbsp;&nbsp;';
						// 일반회원 티켓예매가능
						if (mmbId != 'null' && admin == 'null') { 
							scheduleList += '<a class="ticketURL" href="' + ticketURL +'">'
										  + '<input type="button" class="btn_ticket" value="예매하기"></a></li>';
						// 비회원은 로그인페이지로
						} else if (mmbId == 'null' && admin == 'null') {
							scheduleList += '<a class="ticketURL" href="/project/member/login?alertMessage=ticketMmbIdSessionFail">'
							 			  + '<input type="button" class="btn_ticket" value="예매하기"></a></li>';
						}
						$('#scheduleListOutput').on('click', '.btn_ticket', function(){
							var mmbId = $('#mmbId').val();
							if (mmbId == null) {
								alert('로그인 후 예매 가능합니다');
								$(this).prevAll('.ticketURL').prop("href", "/project/member/login");
							}
						});
					});
					scheduleList += '</ul>';
					$('#scheduleListOutput').html(scheduleList);
				}
			); // end getJSON
	  }
  
  	</script>
 </body>
</html>