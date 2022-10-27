<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>Branch Register</title>
</head>
<body>
	
	<h2>지점 등록</h2>
	 
	  <form action="register" method="post">
	    <p>지역 설정</p>
	    <select name="brcArea" >
			<option>서울</option>
			<option>경기/강원</option>
			<option>부산/경상</option>
			<option>대전/충청</option>
			<option>광주/전라</option>
			<option>제주</option>
		</select>
	    <p>지점 이름</p>
	    <input type="text" name="brcName" placeholder="지점 이름 입력" required="required">
	    <p>지점 극장 수</p>
	    <input type="number" name="brcTheaterNumbers" id="brcTheaterNumbers" placeholder="지점 극장 수" required="required">
	 	<p>지점 극장 좌석 수</p>
		<div id="brcTheaterSeatsOutput"></div>
		<br><br><input type="submit" value="등록">
	  </form>
	  
	  <script type="text/javascript">
		  $(document).ready(function() {
				$('#brcTheaterNumbers').change(function() {
					var brcTheaterNumbers = $('#brcTheaterNumbers').val();
					var brcTheaterSeatsList = '';
					for (var i = 1; i <= brcTheaterNumbers; i++){
						brcTheaterSeatsList +=
						'<input type="number" name="brcTheaterSeats" placeholder="' + i + "관" + '" required">';
					}
					$('#brcTheaterSeatsOutput').html(brcTheaterSeatsList);
				});
		   });
	  </script>
	
</body>
</html>