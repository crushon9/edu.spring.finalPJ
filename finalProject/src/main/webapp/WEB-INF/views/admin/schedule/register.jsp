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
	  	<p>상영 지점 선택</p>
	  	<select id="brcArea" name="brcArea" >
			<option value="1">서울</option>
			<option value="2">경기/강원</option>
			<option value="3">부산/경상</option>
			<option value="4">대전/충청</option>
			<option value="5">광주/전라</option>
			<option value="6">제주</option>
		</select>
		<div id="brcIdOutput">
		</div>
	  	
	    <p>상영 요일 선택</p>
	    <p>상영 시간 선택</p>
	    <p>상영 가격</p>
	    <input type="number" name="scdPrice" placeholder="기준가격 입력" required="required">
	 	
		<br><br><input type="submit" value="등록">
	  </form>
	  
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
					function(data) {// 서버에서 온 data가 저장되어있음
						var brcIdList = '<select name="brcId" >';
						$(data).each(function() {
							brcIdList += '<option value="' + this.brcId + '">' + this.brcName + '</option>';
						});
						brcIdList += '</select>';
						$('#brcIdOutput').html(brcIdList); // 반복문으로 생성된 html태그 출력
					}
				); // end getJSON
			} // end getAllReplies
	  </script>
	
</body>
</html>