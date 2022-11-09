<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>상영 스케줄</title>
</head>
<body>

	<h2>상영 스케줄 정보</h2>
	<p>상영 지점</p>
	<select id="brcArea" name="brcArea">
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
	<input type="date" id="selectDate" name="selectDate" value="2022-11-01">

	<p>상영 영화</p>
	<div id="mvListOutput"></div>

	<p>상영 스케줄</p>
	<div id="scheduleListOutput"></div>

	<script type="text/javascript">
	  $(document).ready(function() {
			$('#brcArea').change(function() {
				getBrcList();
			});
			$('#selectDate').change(function() {
				getMvList();
			});
			getScheduleList();
	   });
	  
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
			}
		);
	  } // end getBrcList
	  
	  // 선택 날짜에 상영중인 영화 목록 가져오기
	  function getMvList() {
		var selectDate = $('#selectDate').val();
		var url = '/project/movie/list/' + selectDate;
		$.getJSON(
			url,
			function(data) {
				var mvList = '<select id="mvId" name="mvId">';
				$(data).each(function() {
					mvList += '<option value="' + this.mvId + '">' + this.mvTitle + '</option>';
				});
				mvList += '</select>'
				$('#mvListOutput').html(mvList);
			}
		);
	  } // end getMvList
	  
	  function getScheduleList() {
	 	  $("#brcId, #selectDate, #mvId").change(function() {
			  var mvId = $("#mvId").val();
	 		  var brcId = $("#brcId").val();
			  var selectDate = $("#selectDate").val();
			  // 3가지 옵션_flag
			  var mvIdFlag = 0;
			  var brcIdFlag = 0;
			  var selectDateFlag = 0;
			  if (mvId != null) {
				mvIdFlag = 100;
			  }
			  if (brcId != null) {
			  	brcIdFlag = 10;
			  }
			  if (selectDate != null) {
				selectDateFlag = 1;
			  }
			  var caseResult = mvIdFlag + brcIdFlag + selectDateFlag;
		
			  switch (caseResult) {
				  case 111:
					  console.log('(where 영화 and 지점 and 날짜)');
					  break;
					  
				  case 101:
					  console.log('(where 영화 and 날짜)');
					  break;
					  
				  case 110:
					  console.log('(where 영화 and 지점)');
					  break;
					  
				  case 011:
					  console.log('(where 지점 and 날짜)');
					  break;
					  
				  case 001:
					  console.log('(where 날짜)');
					  break;
			
				  case 010:
					  console.log('(where 지점)');
					  break;
			
				  case 100:
					  console.log('(where 영화)');
					  break;
			
				  case 000:
					  console.log('(all)');
					  break;
					  
				  default:
			  } 
	 	  });
	  }
  
  	</script>
 </body>
</html>