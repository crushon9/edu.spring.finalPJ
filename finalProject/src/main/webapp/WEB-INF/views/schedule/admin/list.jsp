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
			<h1>스케줄 목록 보기 (관리자용)</h1>
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
			<p>등록된 스케줄 목록</p>
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
			// 선택 날짜에 상영중인 영화 목록 가져오기
			$('#inputDate').change(function() {
				getMvList();
				getScheduleList();
			});
			// 선택 지역의 지점 가져오기
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
		  var url = '/project/schedule/admin/list/' + mvId + '&' + brcId + '&' + inputDate;
		  console.log(url);
		  $.getJSON(			
				url,
			 function(data) {
				var scheduleList = '해당 조건에 등록된 상영스케줄이 없습니다';
				if ($(data).length != 0) {
					scheduleList = '<table>'
							   + '<thead>'
							   + '<tr>'
							   + '<th>스케줄번호</th>'
							   + '<th>영화</th>'
							   + '<th>지점</th>'
							   + '<th>극장</th>'
							   + '<th>상영일</th>'
							   + '<th>상영시간</th>'
							   + '<th>잔여좌석</th>'
							   + '<th>상영가격</th>'
							   + '<th></th>'
							   + '</tr>'
						   	   + '</thead>'
						   	   + '<tbody>';
					$(data).each(function() {
						scheduleList += '<tr>'
								+ '<td>' + this.scdId + '</td>'
								+ '<td>' + this.mvTitle + '</td>'
								+ '<td>' + this.brcName + '</td>'
								+ '<td>' + this.scdTheater + '관</td>'
								+ '<td>' + this.scdDate + '</td>'
								+ '<td>' + publicScdTimeArray[this.scdTime] + '</td>'
								+ '<td>' + (this.scdSeatTotal - this.scdSeatBookedCnt) + '/' + this.scdSeatTotal + '</td>'
								+ '<td>' + this.scdPrice + '</td>'
								+ '<td><input class="btn_delete" type="button" value="삭제">'
								+ '<input type="hidden" name="scdId" value="' + this.scdId + '"/>'
								+ '<input type="hidden" name="scdSeatBookedCnt" value="' + this.scdSeatBookedCnt + '"/></td>'
								+ '</tr>';
					});
					scheduleList += '</tbody></table>';
				}// data.length if
				$('#scheduleListOutput').html(scheduleList);
			 }
		  ); // end getJSON
	  }

	  // 스케줄 삭제
	  $('#scheduleListOutput').on('click', '.btn_delete', function() {
		    console.log('.btn_delete 클릭');
			var scdId = $(this).nextAll('input[name=scdId]').val();
			$.ajax({
				type : 'DELETE',
				url : '/project/schedule/admin/delete/' + scdId,
				headers : {
					'Content-Type' : 'application/json',
					'X-HTTP-Method-Override' : 'DELETE'
				},
				data : JSON.stringify({
					'scdId' : scdId
				}),
				success : function(result) {
					if (result != -2) {
						// 삭제 성공 후 테이블 다시 세팅
						getScheduleList();
					} else if (result == -2) {
						alert('티켓 예매내역이 있어 삭제 불가합니다!');
					}
				}
			});
	    });
	  
  	</script>
  	
</body>
</html>