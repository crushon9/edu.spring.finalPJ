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
		  // mmbId에 해당하는 티켓 리스트 가져오기
		  getTicketListByMmbId();
	  });
	  
	  function getTicketListByMmbId() {
		console.log('getUserTicketList() 호출');
		var mmbId = $("#mmbId").val();
		var url = '/project/ticket/listMmbId/' + mmbId;
		$.getJSON(			
				url,
			function(data) {
				var ticketList = '예매 티켓 내역이 없습니다';
				// 데이터가 있을때만 출력
				if ($(data).length != 0) {
					ticketList = '<table>'
							   + '<thead>'
							   + '<tr>'
							   + '<th>예매번호</th>'
							   + '<th>아이디</th>'
							   + '<th>이미지</th>'
							   + '<th>영화</th>'
							   + '<th>지점</th>'
							   + '<th>극장</th>'
							   + '<th>상영일</th>'
							   + '<th>상영시간</th>'
							   + '<th>예매인원</th>'
							   + '<th>예매좌석</th>'
							   + '<th>결제금액</th>'
							   + '<th>예매</th>'
							   + '<th>리뷰</th>'
							   + '</tr>'
							   + '</thead>'
							   + '<tbody>';
					$(data).each(function() {
						var tkPeopleListArray = this.tkPeopleList.split('&');
						var adult = tkPeopleListArray[0].split('=');
						var adolescent = tkPeopleListArray[1].split('=');
						ticketList += '<tr>'
								+ '<td>' + this.tkId + '</td>'
								+ '<td>' + this.mmbId + '</td>'
								+ '<td><a href="/project/movie/detail?mvId=' + this.mvId + '"><img src="/project/img/display?fileName=thumbnail_' + this.mvImage + '"/></a></td>'
								+ '<td>' + this.mvTitle + '</td>'
								+ '<td>' + this.brcName + '점</td>'
								+ '<td>' + this.scdTheater + '관</td>'
								+ '<td>' + this.scdDate + '</td>'
								+ '<td>' + publicScdTimeArray[this.scdTime] + '</td>'
								+ '<td>일반' + adult[1] + '명 & 청소년' + adolescent[1] + '명</td>'
								+ '<td>' + this.tkSeatList + '</td>'
								+ '<td>' + this.tkPriceTotal + '</td>'
								+ '<td><input class="tkId" type="hidden" value="' + this.tkId + '">'
								+ '<input class="scdId" type="hidden" value="' + this.scdId + '">'
								+ '<input class="mvId" type="hidden" value="' + this.mvId + '">'
								+ '<input class="tkPeopleList" type="hidden" value="' + this.tkPeopleList + '">'
								+ '<input class="scdDate" type="hidden" value="' + this.scdDate + '">'
								+ '<input class="scdTime" type="hidden" value="' + this.scdTime + '">'
								+ '<input class="btn_delete" type="button" value="예매취소"></td>'
								+ '<td><input class="mvId" type="hidden" value="' + this.mvId + '">'
								+ '<input class="mvTitle" type="hidden" value="' + this.mvTitle + '">'
								+ '<input class="mvImage" type="hidden" value="' + this.mvImage + '">'
								+ '<input class="btn_review" type="button" value="리뷰등록"></td>'
								+ '</tr>'
					});
					ticketList += '</tbody></table>';
				}
				$('#ticketListOutput').html(ticketList);
			}
		);
	  }
	  
	  // 티켓 삭제 버튼 클릭시 티켓 내역 삭제
	  $('#ticketListOutput').on('click', '.btn_delete', function(){
		console.log('ticketDelete() call');
		var tkId = $(this).prevAll('.tkId').val();
		var scdId = $(this).prevAll('.scdId').val();
		var mvId = $(this).prevAll('.mvId').val();
		var tkPeopleList = $(this).prevAll('.tkPeopleList').val();
		var scdDate = $(this).prevAll('.scdDate').val();
		var scdTime = $(this).prevAll('.scdTime').val();
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
				'scdDate' : scdDate,
				'scdTime' : scdTime
			}),
			success : function(result) {
				// 티켓이 상영시간이 지나서 예매취소하지 못할때
				if (result == -2) {
					alert('영화 상영 시작전 예매취소 가능합니다. 다시 확인해주세요!');
				}
				// 삭제 후 유저 티켓 리스트 다시 들고오기
				getTicketListByMmbId();							
			}
		});
	  });
	  
	  // 리뷰 등록
	  $('#ticketListOutput').on('click', '.btn_review', function(){
		 console.log('reviewRegister() 호출');
		 var mmbId = $('#mmbId').val();
		 var mvId = $(this).prevAll('.mvId').val();
		 var mvTitle = $(this).prevAll('.mvTitle').val();
		 var mvImage = $(this).prevAll('.mvImage').val();
		 $.ajax({
			type : 'GET',
			url : '/project/review/check/' + mmbId + '/' + mvId,
			success : function(data) {
				console.log(data);
				if (data == 'possible') {
					// 리뷰 등록 가능할때 리뷰등록 새창
					var popUrl = '/project/review/register?mmbId=' + mmbId + '&mvId=' + mvId + '&mvTitle=' + mvTitle + '&mvImage=' + mvImage;
				    var popOption = 'status=no, menubar=no, toolbar=no, resizable=no';
					window.open(popUrl, '_blank', popOption);
				} else if (data == 'impossible_existed') {
					alert("이미 리뷰 등록하신 영화 입니다");
				} else if (data == 'impossible_noTicket') {
					alert("관람 후 리뷰 등록 가능합니다");
				} else if (data == 'impossible_time') {
					alert("영화 상영시간 이후 리뷰 등록 가능합니다");
				}
			}
		}); // .ajax()
	  });
  	</script>

</body>
</html>