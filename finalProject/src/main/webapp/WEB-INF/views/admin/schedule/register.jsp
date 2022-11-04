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
	    <input type="date" id="inputDate" name="inputDate" value="2022-11-01">
	    
	    <p>상영 영화</p>
	    <div id="mvIdOutput"></div>
	    
	    <p>상영 가격</p>
	    <input type="number" name="scdPrice" placeholder="기준가격 입력" required>
	  
	  	<p>상영 스케줄</p>
	  	<div id="scheduleTable"></div>
	  
	<script type="text/javascript">
	  $(document).ready(function() {
			$('#brcArea').change(function() {
				getBrcList();
			});
			$('#inputDate').change(function() {
				getMvList();
			});
	   });
	  
	  // 선택 날짜에 상영중인 영화 목록 가져오기
	  function getMvList() {
		var inputDate = $('#inputDate').val();
		var url = '/project/admin/movie/list/' + inputDate;
		$.getJSON(
			url,
			function(data) {
				var mvList = '<select id="mvId" name="mvId">';
				$(data).each(function() {
					mvList += '<option value="' + this.mvId + '">' + this.mvTitle + '_' + this.mvRuningTime + '</option>';
				});
				mvList += '</select>'
				$('#mvIdOutput').html(mvList);
			}
		);
	  } // end getMvList
	  
	  // 선택 지역의 지점 가져오기
	  function getBrcList() {
		var brcArea = $('#brcArea').val();
		var url = '/project/admin/branch/areaList/' + brcArea;
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
	
	  // 선택지점의 VO 정보 가져오기
      function getBrcVO() {
		var brcId = $('#brcId').val();
		var url = '/project/admin/branch/detail/' + brcId;
		$.getJSON(
			url,
			function(data) {
				$(data).each(function() {
					var brcTheaterNumbers = this.brcTheaterNumbers;
					setScheduleTable(brcTheaterNumbers);
				});
			}
		);
	  } // end getBrcVO
	
	  // 스케줄 테이블 출력
	  function setScheduleTable(brcTheaterNumbers) {
		var timeArray = ["00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30",
			"05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30",
			"11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30",
			"17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"];
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
				scheduleTable += '<td class="' + (j + 1) + '"><div class="scdItem"><button class="scdBtnInsert">등록</button><button class="scdBtnDelete" disabled>삭제</button><input type="text" class="scdContent"/></div></td>';
			}
			scheduleTable += '</tr>';
		}
		scheduleTable += '</tbody></table>';
		$('#scheduleTable').html(scheduleTable);
		scheduleBtn();
	  } // end setScheduleTable
	  
	  // 등록 버튼을 클릭하면 영화 등록
	  function scheduleBtn() {
	    $('#scheduleTable').on('click', 'table tbody tr td .scdItem .scdBtnInsert', function() {
			var mvSelected = $('#mvId option:selected').text();
			var scdTheater = $(this).parents().parents().attr('class');
			var mvRuningTime = mvSelected.split('_')[1];
			var scdTime = $(this).parents().parents().parents().attr('class');
			console.log('scdTheater : ' + scdTheater);
			console.log('mvRuningTime : ' + mvRuningTime);
			console.log('scdTime : ' + scdTime);
			console.log("---------");
			
			// 상영영화를 input 칸에 등록함
			$(this).nextAll('.scdContent').val(mvSelected);
			for (var i = 0; i < mvRuningTime; i++) {
				var trIndex = Number(scdTime) + Number(i);
				$(this).parents().parents().parents().nextAll('.' + trIndex).children('.' + scdTheater).children().children('.scdContent').val(mvSelected);
			}
			
			
			/* var insertIsDisabled = $(this).prevAll('.scdBtnInsert').prop('disabled');
			/* var deleteIsDisabled = $(this).prevAll('.scdBtnDelete').prop('disabled');
			if (isDisabled == true) {
				$(this).prevAll('.scdContent').removeAttr('readonly');
				$(this).prevAll('.scdContent').css({"border-color":"red"});
				$(this).text("수정확인");
				$(this).nextAll('.scdBtnDelete').prop('abled');
			} else { // 아니라면 댓글 수정
				// 선택된 댓글의 replyId, replyContent 값을 저장
				// prevAll() : 선택된 노드 이전에 위치해 있는 모든 형제 노드를 접근
				var replyId = $(this).prevAll('.replyId').val();
				var replyContent = $(this).prevAll('.replyContent').val();
				console.log("수정 replyId : " + replyId + ", replyContent : " + replyContent);
				// ajax로 서버로 수정 데이터 전송
				$.ajax({
					type : 'PUT',
					url : '/ex03/replies/' + replyId,
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'PUT'
					},
					data : JSON.stringify({ // ****JSON으로 파싱해서 보내야 오류가 안남!
						'replyId' : replyId,
						'replyContent' : replyContent
					}),
					success : function(result) {
						console.log("댓글수정결과 : " + result);
						getAllReplies();
					}
				});
			} */
		});
	  }
	  
	</script>
	
</body>
</html>