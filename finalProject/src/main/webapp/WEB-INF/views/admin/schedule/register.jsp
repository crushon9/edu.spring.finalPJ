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
	
	<h2>스케줄 등록</h2>
	 
	  <form action="register" method="post">
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
		<div id="brcIdOutput"></div>
	  	
	    <p>상영 날짜</p>
	    <input type="date" name="inputDate" value="2022-11-01">
	    
	    <p>상영 가격</p>
	    <input type="number" name="scdPrice" placeholder="기준가격 입력" required>
	    
	    <p>상영 영화</p>
	    <div id="mvIdOutput"></div>
	 	
		<br><br><input type="submit" value="등록">
	  </form>
	  
	  <br><br><div id="ScheduleTable"></div>
	  
	  
	  
	<script type="text/javascript">
	  $(document).ready(function() {
			$('#brcArea').change(function() {
				getAreaBrcId();
			});
	   });
		// 선택 지역의 지점 가져오기
		function getAreaBrcId() {
			var brcArea = $('#brcArea').val();
			var url = '/project/admin/branch/areaList/' + brcArea; // REST API 방식 적용
			// $.getJSON 방식이므로 JSON.stringify하지 않아도 되고, header도 없어도됨
			$.getJSON(
				url,
				function(data) {
					var brcIdList = '<select id="brcId" name="brcId" ><option>지점선택</option>';
					$(data).each(function() {
						brcIdList += '<option value="' + this.brcId + '">' + this.brcName + '</option>';
					});
					brcIdList += '</select>'
					$('#brcIdOutput').html(brcIdList);
					$('#brcId').change(function() {
						getBrcVO();
					});
				}
			); // end getJSON
		}
		
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
			); // end getJSON
		}
		
		function setScheduleTable(brcTheaterNumbers) {
			var timeArray = ["00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30",
				"05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30",
				"11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30",
				"17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"];
			var ScheduleTable = '<table border="1"><thead><tr><th style="width: 50px">시간</th>';
			for (var i = 1; i <= brcTheaterNumbers; i++) {
				ScheduleTable +=
					'<th style="width: 200px">' + i + "관" + '</th>';
			}
			ScheduleTable += '</tr></thead><tbody>';
			for (var i = 0; i < 48; i++) {
				ScheduleTable += '<tr>';
				for (var j = 0; j < brcTheaterNumbers; j++) {
					if (j == 0) {
						ScheduleTable += '<td>' + timeArray[i] + '</td>';
					}
					ScheduleTable += '<td>빈칸</td>';
				}
				ScheduleTable += '</tr>';
			}
			ScheduleTable += '</tbody></table>';
			$('#ScheduleTable').html(ScheduleTable);
		}
		
	</script>
	
</body>
</html>