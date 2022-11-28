<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>ticket buy list</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
		
		<h1><%=mmbIdSession %>님 예매 내역</h1>
		<input type="hidden" id="mmbId" value="<%=mmbIdSession %>">
		<div id="ticketListOutput"></div>
		
		<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>
	
	<script type="text/javascript">
	  $(document).ready(function() {
		  getUserTicketList();
		  $('#ticketListOutput').on('click', '.btn_delete', function(){
				tkDelete(this);
		  });
	  });
	  
	  function getUserTicketList() {
		console.log('getUserTicketList() 호출');
		var mmbId = $("#mmbId").val();
		var url = '/project/ticket/listMmbId/' + mmbId;
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
					+ '<img src="/project/img/display?fileName=thumbnail_' + this.mvImage + '"/><br>'
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
					+ '<input class="btn_delete" type="button" value="예매취소">'
					+ '</li><br>'
				});
				ticketList += '</ul>';
				$('#ticketListOutput').html(ticketList);
			}
		);
	  }
	  
	  // 예매티켓 삭제
	  function tkDelete(btn) {
		console.log('tkDelete() call');
		var tkId = $(btn).prevAll('.tkId').val();
		var scdId = $(btn).prevAll('.scdId').val();
		var mvId = $(btn).prevAll('.mvId').val();
		var tkPeopleList = $(btn).prevAll('.tkPeopleList').val();
		$.ajax({
			type : 'DELETE',
			url : '/project/ticket',
			headers : {
				'Content-Type' : 'application/json',
				'X-HTTP-Method-Override' : 'DELETE'
			},
			data : JSON.stringify({
				'tkId' : tkId,
				'scdId' : scdId,
				'mvId' : mvId,
				'tkPeopleList' : tkPeopleList,
			}),
			success : function(result) {
				getUserTicketList();							
			}
		});
	 }
  	</script>

</body>
</html>