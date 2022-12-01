<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>Ticket List for admin</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
			<h1>예매티켓 목록 보기 (관리자용)</h1>
			<div><input id="searchText" type="text" placeholder="영화제목 검색">&nbsp;<input id="searchTextBtn" type="button" value="Search"></div>
			<hr>
			<div id="ticketListOutput">
		</div>
		<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>
	
	<script type="text/javascript">
	  $(document).ready(function() {
		getTicketList('/project/ticket/listAll');
		// 문자열 검색
		$('#searchTextBtn').click(function() {
			var searchText = $('#searchText').val();
			getTicketList('/project/ticket/list/' + searchText);
		});
	  });
	  
	  function getTicketList(url) {
		console.log('getTicketList() 호출');
		var url = url;
		$.getJSON(			
				url,
			function(data) {
				var ticketList = '검색 조건에 해당하는 예매 내역이 없습니다';
				// 데이터가 있을때만 테이블 출력
				if ($(data).length != 0) {
					ticketList = '<table>'
							   + '<thead>'
							   + '<tr>'
							   + '<th>예매번호</th>'
							   + '<th>아이디</th>'
							   + '<th></th>'
							   + '<th>영화</th>'
							   + '<th>스케줄번호</th>'
							   + '<th>지점</th>'
							   + '<th>극장</th>'
							   + '<th>상영일</th>'
							   + '<th>상영시간</th>'
							   + '<th>예매인원</th>'
							   + '<th>예매좌석</th>'
							   + '<th>결제금액</th>'
							   + '</tr>'
							   + '</thead>'
							   + '<tbody>';
					$(data).each(function() {
						ticketList += '<tr>'
								+ '<td>' + this.tkId + '</td>'
								+ '<td>' + this.mmbId + '</td>'
								+ '<td><img src="/project/img/display?fileName=thumbnail_' + this.mvImage + '"/></td>'
								+ '<td>' + this.mvTitle + '</td>'
								+ '<td>' + this.scdId + '</td>'
								+ '<td>' + this.brcName + '</td>'
								+ '<td>' + this.scdTheater + '관</td>'
								+ '<td>' + this.scdDate + '</td>'
								+ '<td>' + setScdTime(this.scdTime) + '</td>'
								+ '<td>' + this.tkPeopleList + '</td>'
								+ '<td>' + this.tkSeatList + '</td>'
								+ '<td>' + this.tkPriceTotal + '</td>'
								+ '</tr>'
					}); // end data.each
				ticketList += '</tbody></table>';
				}
				$('#ticketListOutput').html(ticketList); // 반복문으로 생성된 html태그 출력
			  }
			); // end getJSON
		} // end getAll
	
	    // DB에 저장된 타임 인덱스를 시간 String으로 변환
	    function setScdTime(scdIndex) {
			  var timeArray = ["00:00", "00:30", "01:00", "01:30", "02:00", "07:00", "07:30", "08:00",
				 	"08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
				 	"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30",	"17:00",
				 	"17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30",
				 	"22:00", "22:30", "23:00", "23:30"];
			  return timeArray[scdIndex];
	    }
				
  	</script>
	
 </body>
</html>