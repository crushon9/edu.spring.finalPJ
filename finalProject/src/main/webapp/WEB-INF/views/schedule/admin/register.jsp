<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>Schedule Register</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
			<h1>관리자 스케줄 등록</h1>
			<div>
				<table>
					<thead>
						<tr>
							<th>상영 날짜</th>
							<th>상영 영화</th>
							<th>상영 지점</th>
							<th>상영 기준 가격</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<input type="date" id="inputDate" name="inputDate">
							</td>
							<td>
								<div id="mvListDiv" style="display: inline-block;"></div>
							</td>
							<td>
								<select id="brcArea" name="brcArea" style="display: inline-block;">
							  		<option value="0">지역선택</option>
									<option value="1">서울</option>
									<option value="2">경기/강원</option>
									<option value="3">부산/경상</option>
									<option value="4">대전/충청</option>
									<option value="5">광주/전라</option>
									<option value="6">제주</option>
								</select>
								<div id="brcListOutput" style="display: inline-block;"><select disabled><option>지점선택</option></select></div>
							</td>
							<td>
								<div id="scdPriceDiv" style="display: inline-block;"></div>
								<input type="hidden" id="scdPrice" name="scdPrice">
							</td>
						</tr>
					</tbody>
			  	</table>
			  	<p>스케줄 등록</p>
			  	<!-- 스케줄 테이블 출력을 위한 히든 데이터  -->
				<input type="hidden" id="brcTheaterSeats">
				<input type="hidden" id="brcTheaterNumbers">
			  	<div id="scheduleTable"></div>
		  	</div>
		  	<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>
	  
	<script type="text/javascript">
	  $(document).ready(function() {
			// #inputDate를 오늘날짜로 기본값 세팅
			$('#inputDate').val(new Date().toISOString().substring(0, 10));
			getMvList();
			setScdPrice($('#inputDate').val());
			// 선택 날짜 변경시 상영중인 영화 목록 재설정, 상영가격 재설정
			$('#inputDate').change(function() {
				getMvList();
				setScdPrice($('#inputDate').val());
				setScheduleTable();
			});
			// 선택 지역의 지점 가져오기
			$('#brcArea').change(function() {
				getBrcList();
			});
	   });
	  
	  // 평일13000 주말15000 상영가격세팅
	  function setScdPrice(date){
		  console.log('setScdPrice() 호출');
	    var dayOfWeek = new Date(date).getDay();
	    var scdPriceResult = 13000;
	  	// 일요일(0), 토요일(6)
		if (dayOfWeek == 0 || dayOfWeek == 6) {
			scdPriceResult = 15000;
		}
		$('#scdPrice').val(scdPriceResult);
		$('#scdPriceDiv').html(scdPriceResult + '<div style="font-size: 12px; color: blue;"> *평일13000 주말15000<br>청소년할인3000</div>');
	  }
	  
	  // 선택 날짜에 상영중인 영화 목록 가져오기
	  function getMvList() {
		var inputDate = $('#inputDate').val();
		var url = '/project/movie/list/' + inputDate;
		$.getJSON(
			url,
			function(data) {
				var mvList = '<select id="mvId" name="mvId">';
				$(data).each(function() {
					mvList += '<option value="' + this.mvId + '">' + this.mvTitle + '_' + this.mvRunningTime + '</option>';
				});
				mvList += '</select>'
				$('#mvListDiv').html(mvList);
			}
		);
	  }
	  
	  // 선택 지역의 지점 가져오기
	  function getBrcList() {
	  	var brcArea = $('#brcArea').val();
	  	var url = '/project/branch/list/' + brcArea;
	  	$.getJSON(
	  		url,
	  		function(data) {
	  			var brcList = '<select id="brcId" name="brcId" ><option>지점선택</option>';
	  			$(data).each(function() {
	  				brcList += '<option value="' + this.brcId + '">' + this.brcName + '</option>';
	  			});
	  			brcList += '</select>'
	  			$('#brcListOutput').html(brcList);
	  			$('#brcId').change(function() {
	  				getBrcTheater();
	  			});
	  		}
	  	);
	  }
	  
	  // 선택지점의 극장 정보 가져오기
      function getBrcTheater() {
    	console.log('getBrcTheater() 호출');
		var brcId = $('#brcId').val();
		var url = '/project/branch/detail/' + brcId;
		$.getJSON(
			url,
			function(data) {
				$('#brcTheaterNumbers').val(data.brcTheaterNumbers);
				$('#brcTheaterSeats').val(data.brcTheaterSeats);
				// 극장 선택시 스케줄 테이블 출력
				setScheduleTable();
			}
		);
	  }
	
	  // 스케줄 테이블 기본 형태 출력
	  function setScheduleTable() {
		console.log('setScheduleTable() 호출');
		var brcTheaterNumbers = $('#brcTheaterNumbers').val();
		var brcTheaterSeats = $('#brcTheaterSeats').val();
		var scheduleTable = '<table><thead><tr><th style="width: 50px">시간</th>';
		// 지점 극장수만큼 반복하여 제목 컬럼 생성 (col == 극장관번호)
		for (var col = 1; col <= brcTheaterNumbers; col++) {
			scheduleTable += '<th style="width: 240px">' + col + "관" + '</th>';
		}
		scheduleTable += '</tr></thead><tbody>';
		// timeArray만큼 행을 반복
		for (var timeIndex = 0; timeIndex < publicScdTimeArray.length; timeIndex++) {
			// 추후 데이터 컨트롤을 위하여 tr class = DB에 저장될  timeIndex값으로 지정
			scheduleTable += '<tr class="' + timeIndex + '">';
			// 극장수 만큼 열을 반복
			for (var col = 0; col < brcTheaterNumbers; col++) {
				// 첫번째 열은 시간 정보 출력
				if (col == 0) { 
					scheduleTable += '<td class="0"><div style="font-weight:bold;">' + publicScdTimeArray[timeIndex] + '</div></td>';
				}
				// 추후 데이터 컨트롤을 위하여 td class = 극장번호로 지정
				scheduleTable += '<td class="' + (col + 1) + '">';
				// 상영가격 설정 조조 3000원 할인 (timeArray[4]="07:00", timeArray[13]="11:00")
				if (4 < timeIndex && timeIndex < 13) { 
					var scdPrice = Number($('#scdPrice').val()) - Number(3000);
					scheduleTable += '<div id="scdPrice" style="color: red; font-size: 12px; display: inline-block;">조조할인_'+ scdPrice + '</div>'
								   +'<input type="hidden" name="scdPrice" value="'+ scdPrice +'"/>'
								   + '&nbsp;';
				} else {
					var scdPrice = $('#scdPrice').val();
					scheduleTable += '<div id="scdPrice" style="font-size: 12px; display: inline-block;">상영가격_'+ scdPrice + '</div>'
					               + '<input type="hidden" name="scdPrice" value="'+ scdPrice +'"/>'
								   + '&nbsp;';
				}
				// 등록 삭제 버튼 세팅
				scheduleTable += '<button style="border-color: blue;" class="btn_Insert">등록</button>'
							+ '&nbsp;'
							// 삭제버튼은 비활성화가 기본값
							+ '<button style="border-color: lightgray;" class="btn_delete" disabled>삭제</button>'
							+ '<input width="270px" type="text" name="mvTitle" style="border-color: lightgray;" value="" readonly/>'
							+ '<input type="hidden" name="scdId"/>'
							+ '<input type="hidden" name="mvRunningTime" value=""/>'
							+ '<input type="hidden" name="scdSeatBookedCnt" value=""/>'
							// scdSeatTotal은 brcTheaterSeats을 구분자로 split한 배열의 col인덱스와 동일
							+ '<input type="hidden" name="scdSeatTotal" value="' + brcTheaterSeats.split(", ")[col] + '"/></td>';
			}
			scheduleTable += '</tr>';
		}
		scheduleTable += '</tbody></table>';
		$('#scheduleTable').html(scheduleTable);
		// 테이블 기본틀 세팅 후 DB에 저장된 스케줄 리스트를 불러오는 함수 호출
		getScheduleList();
	  }
	  
	  // DB에 저장된 스케줄 리스트를 불러와서 CSS 적용
	  function getScheduleList() {
		console.log('getScheduleList() 호출');
		var brcId = $('#brcId').val();
		var scdDate = $('#inputDate').val();
		// url : schedule/admin/list/mvId(없으므로 0으로 기본설정)&brcId&scdDate
		var url = '/project/schedule/admin/list/0&' + brcId + '&' + scdDate;
		$.getJSON(
				url,
			function(data) {
				$(data).each(function() {
					// mvRunningTime : 상영시간을 30분으로 나눈뒤 +1 한 값
					for (var i = 0; i < this.mvRunningTime; i++) {
						// DB에 저장된 상영시간 Index에 mvRunningTime길이 만큼 더하며 반복하며 효과 적용
						var trIndex = Number(this.scdTime) + Number(i);
						// 상영시간(tr, row)과 극장번호(td, col)를 좌표로 테이블에서 위치찾기
						var thisParentTd = $('#scheduleTable').children('table').children('tbody').children('tr.' + trIndex).children('td.' + this.scdTheater);
						// 찾은 좌표에 DB에서 가져온 데이터 값 출력
						thisParentTd.children('input[name=mvTitle]').val(this.mvTitle);
						thisParentTd.children('input[name=mvTitle]').css({"border-color":"red"});
						thisParentTd.children('input[name=mvRunningTime]').val(this.mvRunningTime);
						thisParentTd.children('input[name=scdId]').val(this.scdId);
						thisParentTd.children('input[name=scdPrice]').val(this.scdPrice);
						// 할인가격의 효과 조건문으로 설정
						if (4 < this.scdTime && this.scdTime < 13) {
							thisParentTd.children('#scdPrice').html('조조할인_' + this.scdPrice);
							thisParentTd.children('#scdPrice').css({'color':'red'});
						} else {
							thisParentTd.children('#scdPrice').html('상영가격_' + this.scdPrice);
							thisParentTd.children('#scdPrice').css({'color':'black'});
						}
						// 중복 등록 방지를 위해 등록 버튼 비활성화 
						thisParentTd.children('.btn_Insert').prop('disabled', true);
						thisParentTd.children('.btn_Insert').css({"border-color":"lightgray"});
						// 비활성화된 삭제버튼 첫칸만 활성화
						if (i == 0) {
							thisParentTd.children('.btn_delete').prop('disabled', false);
							thisParentTd.children('.btn_delete').css({"border-color":"red"});
						}
						// 삭제시 예매여부 예외처리를 위해 hidden 박스에 scdSeatBookedCnt 데이터 삽입
						thisParentTd.children('input[name=scdSeatBookedCnt]').val(this.scdSeatBookedCnt);
					}
				}); // end data.each
			}
		); // end getJSON
	  } // end getScheduleList
	  
	
     $('#scheduleTable').on('click', '.btn_Insert', function() {
		console.log('btn_Insert 클릭');
		// 스케줄 등록시 가져갈 정보
    	var mvSelected = $('#mvId option:selected').text();
		var brcId = $('#brcId').val();
		var mvId = $('#mvId').val();
    	var mvTitle = mvSelected.split('_')[0];
		var mvRunningTime = mvSelected.split('_')[1];
		var scdDate = $('#inputDate').val();
		var scdTime = $(this).parents().parents().attr('class');
		var scdTheater = $(this).parents().attr('class');
	 	var scdSeatTotal = $(this).nextAll('input[name=scdSeatTotal]').val();
	 	var scdPrice = $(this).prevAll('input[name=scdPrice]').val();
		// 영화 미선택시 예외처리
		if (mvSelected == '') {
			alert('상영 영화를 선택 해주세요');
		} else {
			// mvRunningTime길이만큼 스케줄 칸이 되는지 검사
			var overlapFlag = 0;
			for (var i = 1; i < mvRunningTime; i++) {
				var trIndex = Number(scdTime) + Number(i);
				var loopRow = $(this).parents().parents().nextAll('.' + trIndex).children('.' + scdTheater).children('input[name=mvTitle]').val();
				if (loopRow != '') {
					alert('상영 스케줄 시간을 확인 해주세요');
					overlapFlag = 1;
					break;
				}
			}
			// 중복되지 않았을때 데이터만 ajax로 DB에 저장
			if (overlapFlag == 0) {		
				$.ajax({
					type : 'POST',
					url : '/project/schedule/admin/register',
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'POST'
					},
					data : JSON.stringify({
						'brcId' : brcId,
						'mvId' : mvId,
						'mvTitle' : mvTitle,
						'mvRunningTime' : mvRunningTime,
						'scdDate' : scdDate,
						'scdTime' : scdTime,
						'scdTheater' : scdTheater,
						'scdSeatTotal' : scdSeatTotal,
						'scdPrice' : scdPrice
					}),
					success : function(result) {
						// 저장 성공 후 테이블 다시 세팅
						setScheduleTable();
					}
				});
			}
		}
	});
  
	  // 스케줄 삭제
	  $('#scheduleTable').on('click', '.btn_delete', function() {
		    console.log('btn_delete 클릭');
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
						setScheduleTable();
					} else if (result == -2) {
						alert('티켓 예매내역이 있어 삭제 불가합니다!');
					}
				}
			});
	    });
	</script>
	
</body>
</html>