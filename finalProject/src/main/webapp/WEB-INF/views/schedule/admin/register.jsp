<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>Schedule Register</title>
</head>
<body>
	
	<h2>관리자 스케줄 등록</h2>
	 
	  	<p>상영 지점</p>
	  	<select id="brcArea" name="brcArea" >
	  		<option>지역선택</option>
			<option value="1">서울</option>
			<option value="2">경기/강원</option>
			<option value="3">부산/경상</option>
			<option value="4">대전/충청</option>
			<option value="5">광주/전라</option>
			<option value="6">제주</option>
		</select>
		<div id="brcListOutput"></div>
	  	
	    <p>상영 날짜</p>
	    <input type="date" id="inputDate" name="inputDate">
	    
	    <p>상영 영화</p>
	    <div id="mvListDiv"></div>
	    
	    <p>상영 기준 가격</p>
	    <input type="number" id="scdPrice" name="scdPrice" readonly>
	  
	  	<p>상영 스케줄</p>
	  	<div id="scheduleTable"></div>
	  
	<script type="text/javascript">
	  $(document).ready(function() {
			$('#inputDate').val(new Date().toISOString().substring(0, 10));
			setScdPrice($('#inputDate').val());
			getMvList();
			$('#brcArea').change(function() {
				getBrcList();
			});
			$('#inputDate').change(function() {
				getMvList();
				getBrcVO();
				setScdPrice($('#inputDate').val());
			});
			scheduleBtn();
	   });
	  
	  // 평일13000 주말15000 
	  function setScdPrice(date){
		  console.log('setScdPrice() 호출');
	    var dayOfWeek = new Date(date).getDay();
		  console.log(dayOfWeek);
	  	// 일요일(0), 토요일(6)
		if (dayOfWeek == 0 || dayOfWeek == 6) {
			$('#scdPrice').val(15000);
		} else {
			$('#scdPrice').val(13000);
		}
	  }
	  
	  // 선택 지역의 지점 가져오기
	  function getBrcList() {
		var brcArea = $('#brcArea').val();
		var url = '/project/branch/areaList/' + brcArea;
		$.getJSON( // $.getJSON 방식이므로 JSON.stringify하지 않아도 되고, header도 없어도됨
			url,
			function(data) {
				var brcList = '<select id="brcId" name="brcId" ><option>지점선택</option>';
				$(data).each(function() {
					brcList += '<option value="' + this.brcId + '">' + this.brcName + '</option>';
				});
				brcList += '</select>'
				$('#brcListOutput').html(brcList);
				$('#brcId').change(function() {
					getBrcVO();
				});
			}
		);
	  } // end getBrcList
	  
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
	  } // end getMvList
	  
	  // 선택지점의 VO 정보 가져오기
      function getBrcVO() {
    	console.log('getBrcVO() 호출');
		var brcId = $('#brcId').val();
		var url = '/project/branch/detail/' + brcId;
		$.getJSON(
			url,
			function(data) {
				$(data).each(function() {
					var brcTheaterNumbers = this.brcTheaterNumbers;
					var brcTheaterSeats = this.brcTheaterSeats;
					setScheduleTable(brcTheaterNumbers, brcTheaterSeats);
				});
			}
		);
	  } // end getBrcVO
	
	  // 스케줄 테이블 기본 형태 출력
	  function setScheduleTable(brcTheaterNumbers, brcTheaterSeats) {
		console.log('setScheduleTable() 호출');
		var timeArray = ["00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30",
		 	"04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00",
		 	"08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
		 	"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30",	"17:00",
		 	"17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30",
		 	"22:00", "22:30", "23:00", "23:30"];
		var scheduleTable = '<table border="1"><thead><tr><th style="width: 50px">시간</th>';
		for (var i = 1; i <= brcTheaterNumbers; i++) {
			scheduleTable +=
				'<th style="width: 200px">' + i + "관" + '</th>';
		}
		scheduleTable += '</tr></thead><tbody>';
		for (var i = 0; i < 48; i++) {
			scheduleTable += '<tr class="' + i + '">';
			for (var j = 0; j < brcTheaterNumbers; j++) {
				if (j == 0) {
					scheduleTable += '<td class="0">' + timeArray[i] + '</td>';
				}
				scheduleTable += '<td class="' + (j + 1) + '"><button style="border-color: blue;" class="scdBtnInsert">등록</button>'
				+ '<button style="border-color: lightgray;" class="scdBtnDelete" disabled>삭제</button>'
				+ '<input type="text" name="mvTitle" style="border-color: lightgray;" value="" readonly/>'
				if (i > 21) { // 조조 3000원 할인(타임인덱스21까지)
					scheduleTable += '<input type="number" name="scdPrice" value="' + $('#scdPrice').val() + '" readonly style="width:60px; display:inline;"/>';
				} else {
					var scdPrice = Number($('#scdPrice').val()) - Number(3000);
					scheduleTable += '<input type="number" name="scdPrice" value="' + scdPrice + '" readonly style="width:60px; display:inline;"/>';
				}
				scheduleTable += '<input type="hidden" name="scdId"/>'
				+ '<input type="hidden" name="mvRunningTime" value=""/>'
				+ '<input type="hidden" name="scdSeatTotal" value="' + brcTheaterSeats.split(", ")[j] + '"/></td>';
			}
			scheduleTable += '</tr>';
		}
		scheduleTable += '</tbody></table>';
		$('#scheduleTable').html(scheduleTable);
		getScheduleList();
	  } // end setScheduleTable
	  
	  function getScheduleList() {
		console.log('getScheduleList() 호출');
		var brcId = $('#brcId').val();
		var scdDate = $('#inputDate').val();
		var url = '/project/schedule/list/0&' + brcId + '&' + scdDate;
		// $.getJSON 방식이므로 JSON.stringify하지 않아도 되고, header도 없어도됨
		$.getJSON(			
				url,
			function(data) {// 서버에서 온 data가 저장되어있음
				$(data).each(function() {
					for (var i = 0; i < this.mvRunningTime; i++) {
						var trIndex = Number(this.scdTime) + Number(i);
						var thisParentTd = $('#scheduleTable').children('table').children('tbody').children('tr.' + trIndex).children('td.' + this.scdTheater);
						thisParentTd.children('.scdBtnInsert').prop('disabled', true);
						thisParentTd.children('.scdBtnInsert').css({"border-color":"lightgray"});
						if (i == 0) {
							thisParentTd.children('.scdBtnDelete').prop('disabled', false);
							thisParentTd.children('.scdBtnDelete').css({"border-color":"red"});
						}
						thisParentTd.children('input[name=mvTitle]').val(this.mvTitle);
						thisParentTd.children('input[name=mvTitle]').css({"border-color":"red"});
						thisParentTd.children('input[name=mvRunningTime]').val(this.mvRunningTime);
						thisParentTd.children('input[name=scdId]').val(this.scdId);
						thisParentTd.children('input[name=scdPrice]').val(this.scdPrice);
					}
				}); // end data.each
			}
		); // end getJSON
	  } // end getScheduleList
	  
	  // 버튼을 클릭하면 영화 등록 혹은 삭제
	  function scheduleBtn() {
		 console.log('scheduleBtn() 호출');
	     $('#scheduleTable').on('click', 'table tbody tr td .scdBtnInsert', function() {
			console.log('scdBtnInsert 클릭');
	    	var mvSelected = $('#mvId option:selected').text();
			var brcId = $('#brcId').val();
			var mvId = $('#mvId').val();
	    	var mvTitle = mvSelected.split('_')[0];
			var mvRunningTime = mvSelected.split('_')[1];
			var scdDate = $('#inputDate').val();
			var scdTime = $(this).parents().parents().attr('class');
			var scdTheater = $(this).parents().attr('class');
		 	var scdSeatTotal = $(this).nextAll('input[name=scdSeatTotal]').val();
		 	var scdPrice = $(this).nextAll('input[name=scdPrice]').val();
			
			if (mvSelected == '') {
				alert('상영 영화를 선택 해주세요');
			} else {
				var startRow = $(this).nextAll('input[name=mvTitle]').val();
				if (startRow == '') {
					var overlapFlag = 0;
					for (var i = 1; i < mvRunningTime; i++) {
						var trIndex = Number(scdTime) + Number(i);
						console.log(trIndex);
						var loopRow = $(this).parents().parents().nextAll('.' + trIndex).children('.' + scdTheater).children('input[name=mvTitle]').val();
						if (loopRow != '') {
							alert('상영 스케줄이 중복 됩니다!');
							overlapFlag = 1;
							break;
						}
					}
					// 중복되지 않았을때 데이터만 ajax로 DB에 저장하고 getScheduleList 호출	
					if (overlapFlag == 0) {		
						console.log("---스케줄 등록 정보---");
						console.log("brcId : " + brcId);
						console.log("mvId : " + mvId);
						console.log("mvTitle : " + mvTitle);
						console.log("mvRunningTime : " + mvRunningTime);
						console.log("scdDate : " + scdDate);
						console.log("scdTime : " + scdTime);
						console.log("scdTheater : " + scdTheater);
						console.log("scdSeatTotal : " + scdSeatTotal);
						console.log("scdPrice : " + scdPrice);
						
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
								console.log("스케줄 등록 결과 : " + result);
								getScheduleList();
							}
						});
					}
				} else {
					alert('상영 스케줄이 중복 됩니다');
				}
			}
		});
			
	    $('#scheduleTable').on('click', 'table tbody tr td .scdBtnDelete', function() {
			var scdId = $(this).nextAll('input[name=scdId]').val();
			console.log('scdBtnDelete 클릭 : scdId = ' + scdId);
			// view를 변경하기 위한 변수
			var mvRunningTime = $(this).nextAll('input[name=mvRunningTime]').val();
			var scdTime = $(this).parents().parents().attr('class');
			var scdTheater = $(this).parents().attr('class');
			
			$.ajax({
				type : 'DELETE',
				url : '/project/schedule/admin/delete/' + scdId,
				headers : {
					'Content-Type' : 'application/json',
					'X-HTTP-Method-Override' : 'DELETE'
				},
				data : JSON.stringify({
					'scdId' : scdId,
				}),
				success : function(result) {
					console.log("스케줄 삭제 결과 : " + result);
					// 데이터 삭제 성공후 해당 스케줄만 view에 반영
					for (var i = 0; i < mvRunningTime; i++) {
						var trIndex = Number(scdTime) + Number(i);
						var parentTd = $('#scheduleTable').children('table').children('tbody').children('tr.' + trIndex).children('td.' + scdTheater);
						parentTd.children('.scdBtnInsert').prop('disabled', false);
						parentTd.children('.scdBtnInsert').css({"border-color":"blue"});
						if (i == 0) {
							parentTd.children('.scdBtnDelete').prop('disabled', true);
							parentTd.children('.scdBtnDelete').css({"border-color":"lightgray"});
						}
						parentTd.children('input[name=mvTitle]').val('');
						parentTd.children('input[name=mvTitle]').css({"border-color":"lightgray"});
						parentTd.children('input[name=mvRunningTime]').val('');
						parentTd.children('input[name=scdId]').val('');
					}
				}	
			});
	    });
	  }
	  
	</script>
	
</body>
</html>