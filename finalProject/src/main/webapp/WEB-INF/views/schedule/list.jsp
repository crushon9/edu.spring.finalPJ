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
		<table>
			<thead>
				<tr>
					<th>상영 날짜</th>
					<th>상영 영화</th>
					<th>상영 지점</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<input type="date" id="inputDate" name="inputDate">
					</td>
					<td>
						<div id="mvListOutput"></div>
					</td>
					<td>
						<select id="brcArea" name="brcArea">
							<option>지역선택</option>
							<option value="1">서울</option>
							<option value="2">경기/강원</option>
							<option value="3">부산/경상</option>
							<option value="4">대전/충청</option>
							<option value="5">광주/전라</option>
							<option value="6">제주</option>
						</select>
						<div id="brcListDiv" style="display: inline-block;"><select disabled><option>지점선택</option></select></div>
					</td>
				</tr>
			</tbody>
	  	</table>
	
		<p>상영 스케줄 목록</p>
		<input type="hidden" id="mmbId" value="<%=mmbIdSession%>">
		<input type="hidden" id="admin" value="<%=adminSession%>">
		<div id="scheduleListOutput"></div>
		</div>
		<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>

	<script type="text/javascript">
	  $(document).ready(function() {
			// #inputDate를 오늘날짜로 기본값 세팅
			$('#inputDate').val(new Date().toISOString().substring(0, 10));
			getMvList();
			getScheduleList();
			// 날짜 선택시 비동기로 영화정보 get
			$('#inputDate').change(function() {
				getMvList();
				getScheduleList();
			});
			// 지역 선택시 비동기로 지점정보 get
			$('#brcArea').change(function() {
				getBrcList();
			});
	   });
	  
	  // 선택 날짜에 상영중인 영화 목록 가져오기
	  function getMvList() {
		var inputDate = $('#inputDate').val();
		var url = '/project/movie/list/' + inputDate;
		$.getJSON(
			url,
			function(data) {
				var mvList = '<select id="mvId" name="mvId"><option value="0">영화선택</option>';
				$(data).each(function() {
					mvList += '<option value="' + this.mvId + '">' + this.mvTitle + '</option>';
				});
				mvList += '</select>';
				$('#mvListOutput').html(mvList);
				// 영화 바뀔때 마다 getScheduleList
				$('#mvId').change(function() {
					getScheduleList();
				});
			}
		);
	  } // end getMvList
	  
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
				// 지점정보가 바뀔때 마다 getScheduleList
				$('#brcId').change(function() {
					getScheduleList();
				});
			}
		);
	  } // end getBrcList
	  
	  function getScheduleList() {
 		  console.log('getScheduleList() 호출');
		  var mvId = $("#mvId").val();
 		  var brcId = $("#brcId").val();
		  var inputDate = $("#inputDate").val();
		  // 선택하지 않았을때 기본값 0과 none으로 세팅
		  if (mvId == null) {
			  mvId = 0;
		  }
		  if (brcId == null) {
			  brcId = 0;
		  }
		  if (inputDate == '') {
			  inputDate = 'none';
		  }
		  console.log('mvId : ' + mvId);
		  console.log('brcId : ' + brcId);
		  console.log('inputDate : ' + inputDate);
		  var url = '/project/schedule/list/' + mvId + '&' + brcId + '&' + inputDate;
		  console.log(url);
		$.getJSON(			
				url,
			function(data) {
				var scheduleList = '해당 조건으로 등록된 상영스케줄이 없습니다';
				if ($(data).length != 0) {
					scheduleList = '<table>'
							   + '<thead>'
							   + '<tr>'
							   + '<th>영화</th>'
							   + '<th>지점</th>'
							   + '<th>극장</th>'
							   + '<th>상영일</th>'
							   + '<th>상영시간</th>'
							   + '<th>잔여좌석</th>'
							   + '<th>상영가격</th>'
							   + '<th>예매</th>'
							   + '</tr>'
						   	   + '</thead>'
						   	   + '<tbody>';
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
						scheduleList += '<tr>'
										+ '<td>' + this.mvTitle + '</td>'
										+ '<td>' + this.brcName + '</td>'
										+ '<td>' + this.scdTheater + '관</td>'
										+ '<td>' + this.scdDate + '</td>'
										+ '<td>' + publicScdTimeArray[this.scdTime] + '</td>'
										+ '<td>' + (this.scdSeatTotal - this.scdSeatBookedCnt) + '/' + this.scdSeatTotal + '</td>'
										+ '<td>' + this.scdPrice + '</td>';
						// 세션으로 예매버튼 조건화하여 세팅
						var mmbId = $('#mmbId').val();
						var admin = $('#admin').val();
						// 일반회원 예매버튼URL을 ticketURL로 세팅
						if (mmbId != 'null' && admin == 'null') { 
							scheduleList += '<td><a class="ticketURL" href="' + ticketURL +'">'
										  + '<input type="button" class="btn_ticket" value="Go"></a></td></tr>';
						// 비회원은 targetURL에 ticketURL을 담고 추후 클릭시 세션 & 리다이렉트로 처리
						} else if (mmbId == 'null' && admin == 'null') {
							scheduleList += '<td><input type="hidden" class="targetURL" value="' + ticketURL + '">'
							 			  + '<input type="button" class="btn_targetURL" value="Go"></td></tr>';
						// 관리자는 예매 버튼 비활성
						} else {
							scheduleList += '<td><input type="button" value="비활성" disabled></tr>';
						}
						$('#scheduleListOutput').on('click', '.btn_ticket', function(){
							var mmbId = $('#mmbId').val();
							if (mmbId == null) {
								alert('로그인 후 예매 가능합니다');
								$(btn).prevAll('.ticketURL').prop("href", "/project/member/login");
							}
						});
					}); // data.each
					scheduleList += '</tbody></table>';
				} // data.length if
				$('#scheduleListOutput').html(scheduleList);
 			} // data
		); // end getJSON
	  }
	  
	  // 비회원이 예매 버튼을 눌렀을때
	  $('#scheduleListOutput').on('click', '.btn_targetURL', function(){
		  console.log('btn_targetURL() click');
		  // 비회원이면 티켓예매 페이지 세션 생성
		  var targetURL = $(this).prevAll('.targetURL').val();
		  console.log(targetURL);
		  sessionStorage.setItem('targetURL', targetURL);
		  location.href = '/project/member/login?alertSession=ticketSessionFail';
	  });
	  
  	</script>
 </body>
</html>