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
							   + '<th>취소여부</th>'
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
								+ '<td>' + publicScdTimeArray[this.scdTime] + '</td>'
								+ '<td>' + this.tkPeopleList + '</td>'
								+ '<td>' + this.tkSeatList + '</td>'
								+ '<td>' + this.tkPriceTotal + '</td>'
								+ '<td>' + this.tkCancelCheck + '</td>'
								+ '</tr>'
					}); // end data.each
				ticketList += '</tbody></table>';
				}
				$('#ticketListOutput').html(ticketList); // 반복문으로 생성된 html태그 출력
			  }
			); // end getJSON
		} // end getAll	
  	</script>
	
 </body>
</html>