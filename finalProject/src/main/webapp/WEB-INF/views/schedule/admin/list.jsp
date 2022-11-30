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
			<h1>관리자 스케줄 리스트</h1>
			<div>
			<table>
				<thead>
					<tr>
						<th>상영 영화</th>
						<th>상영 지점</th>
						<th>상영 날짜</th>
					</tr>
				</thead>
				<tbody>
					<tr>
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
							<div id="brcListDiv"></div>
						</td>
						<td>
							<input type="date" id="dateSelected" name="dateSelected">
						</td>
					</tr>
				</tbody>
		  	</table>
		
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
		  $.getJSON(			
				url,
			 function(data) {
				var scheduleList = '등록된 상영스케줄이 없습니다';
				if ($(data).length != 0) {
					scheduleList = '<table>'
							   + '<thead>'
							   + '<tr>'
							   + '<th>영화</th>'
							   + '<th>지점</th>'
							   + '<th>관</th>'
							   + '<th>상영일</th>'
							   + '<th>상영시간</th>'
							   + '<th>좌석</th>'
							   + '<th>상영가격</th>'
							   + '</tr>'
						   	   + '</thead>'
						   	   + '<tbody>';
					$(data).each(function() {
						scheduleList
						+= '<li>'
						+ this.brcName
						+ '_ ' + this.scdTheater + '관'
						+ ' | ' + this.mvTitle
						+ ' | ' + this.scdDate
						+ '_ ' + timeArray[this.scdTime]
						+ ' | 잔여좌석 : ' + (this.scdSeatTotal - this.scdSeatBookedCnt) + '/' + this.scdSeatTotal
						+ ' | 예매기준가격 : ' + this.scdPrice
						+ '&nbsp;&nbsp;'
						+ '<input class="btn_delete" type="button" value="삭제">'
						+ '<input type="hidden" name="scdId" value="' + this.scdId + '"/>'
						+ '<input type="hidden" name="scdSeatBookedCnt" value="' + this.scdSeatBookedCnt + '"/>'
						+ '</li>'
					});
					scheduleList += '</tbody></table>';
				}
				$('#scheduleListOutput').html(scheduleList);
			 }
		  ); // end getJSON
	  }
	  
	  $('#scheduleListOutput').on('click', 'ul li .btn_delete', function() {
			var scdId = $(this).nextAll('input[name=scdId]').val();
			var scdSeatBookedCnt = $(this).nextAll('input[name=scdSeatBookedCnt]').val();
			$.ajax({
				type : 'DELETE',
				url : '/project/schedule/admin/delete',
				headers : {
					'Content-Type' : 'application/json',
					'X-HTTP-Method-Override' : 'DELETE'
				},
				data : JSON.stringify({
					'scdId' : scdId,
					'scdSeatBookedCnt' : scdSeatBookedCnt
				}),
				success : function(result) {
					console.log("스케줄 삭제 결과 : " + result);
					if (result != -2) {
						getScheduleList();
					} else if (result == -2) {
						alert('예매된 좌석이 있어 삭제 불가합니다!');
					}
				}	
			});
	    });
  	</script>
  	
</body>
</html>