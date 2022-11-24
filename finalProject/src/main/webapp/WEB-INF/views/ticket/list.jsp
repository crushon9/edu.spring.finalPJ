<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>ticket buy list</title>
</head>
<body>
	<h2>유저 티켓 리스트</h2>
	<% String mmbId = (String) session.getAttribute("mmbIdSession"); %>
	<input type="hidden" id="mmbId" value="<%=mmbId %>">
	<div id="ticketListOutput"></div>
	
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
		var ticketList = '';
		$.getJSON(			
				url,
			function(data) {
				$(data).each(function() {
					ticketList += '<li>'
					+ '예매번호 : ' + this.tkId
					+ ', 아이디 : ' + this.mmbId
					+ ', 스케줄번호 : ' + this.scdId
					+ ', 영화번호 : ' + this.mvId
					+ ', 예매인원 : ' + this.tkPeopleList
					+ ', 예매좌석 : ' + this.tkSeatList
					+ ', 총금액 : ' + this.tkPriceTotal
					+ '&nbsp;&nbsp;'
					+ '<input class="tkId" type="hidden" value="' + this.tkId + '">'
					+ '<input class="scdId" type="hidden" value="' + this.scdId + '">'
					+ '<input class="mvId" type="hidden" value="' + this.mvId + '">'
					+ '<input class="tkPeopleList" type="hidden" value="' + this.tkPeopleList + '">'
					+ '<input class="btn_delete" type="button" value="예매취소"></li>';
				});
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