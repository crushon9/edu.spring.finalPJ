<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>Ticket List</title>
</head>
<body>

	<h2>관리자 예매 티켓 조회</h2>
	<div id="ticketListOutput"></div>
	
	<script type="text/javascript">
	  $(document).ready(function() {
		  getTicketList();
	  });
	  
	  function getTicketList() {
		console.log('getTicketList() 호출');
		var url = '/project/ticket/listAll';
		var timeArray = ["00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30",
			 	"04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00",
			 	"08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
			 	"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30",	"17:00",
			 	"17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30",
			 	"22:00", "22:30", "23:00", "23:30"];
		var ticketList = '<ul>';
		$.getJSON(			
				url,
			function(data) {
				$(data).each(function() {
					ticketList += '<li>'
					+ '<img src="/project/img/display?fileName=' + this.mvImage + '" width="50px"/><br>'
					+ '예매No. ' + this.tkId
					+ ' | 아이디 : ' + this.mmbId
					+ ' | 스케줄No. ' + this.scdId
					+ ' | ' + this.mvTitle
					+ ' | ' + this.brcName + '점'
					+ ' _' + this.scdTheater + '관'
					+ ' | ' + this.scdDate
					+ ' _' + timeArray[this.scdTime]
					+ ' | 예매인원 : ' + this.tkPeopleList
					+ ' | 예매좌석 : ' + this.tkSeatList
					+ ' | 결제금액 : ' + this.tkPriceTotal
					+ '&nbsp;&nbsp;'
					+ '<input class="tkId" type="hidden" value="' + this.tkId + '">'
					+ '<input class="scdId" type="hidden" value="' + this.scdId + '">'
					+ '<input class="mvId" type="hidden" value="' + this.mvId + '">'
					+ '<input class="tkPeopleList" type="hidden" value="' + this.tkPeopleList + '">'
					+ '</li><br>'
				});
				ticketList += '</ul>';
				$('#ticketListOutput').html(ticketList);
			}
		);
	  }
  
  	</script>
	
 </body>
</html>