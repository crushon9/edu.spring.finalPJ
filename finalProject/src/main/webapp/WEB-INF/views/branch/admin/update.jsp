<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>Branch update</title>
</head>
<body>
	
	<h2>관리자 지점 수정</h2>
	  <form action="update" method="post">
	    <p>지역 수정</p>
	    <select id="brcArea" name="brcArea">
			<option value="1">서울</option>
			<option value="2">경기/강원</option>
			<option value="3">부산/경상</option>
			<option value="4">대전/충청</option>
			<option value="5">광주/전라</option>
			<option value="6">제주</option>
		</select>
	    <p>지점 이름</p>
	    <input type="text" name="brcName" value="${vo.brcName }" placeholder="지점 이름 입력" required="required">
	    <p>지점 극장 수</p>
	    <input type="number" name="brcTheaterNumbers" id="brcTheaterNumbers" value="${vo.brcTheaterNumbers }"placeholder="지점 극장 수" required="required">
	 	<p>지점 극장 좌석 수</p>
		<div id="brcTheaterSeatsOutput" ></div>
		<br><br><input type="submit" value="수정">
	  </form>
	  
	  <form action="delete" method="post">
		  <input type="hidden" name="brcId" value="${vo.brcId }">
		  <input type="submit" value="삭제">
	  </form>
	  
	  
	  <script type="text/javascript">
		  $(document).ready(function() {
				$('#brcTheaterNumbers').change(function() {
					var brcTheaterNumbers = $('#brcTheaterNumbers').val();
					var brcTheaterSeatsList = '';
					for (var i = 1; i <= brcTheaterNumbers; i++){
						brcTheaterSeatsList +=
						'<input type="number" name="arrBrcTheaterSeats" placeholder="' + i + "관" + '" required">';
					}
					$('#brcTheaterSeatsOutput').html(brcTheaterSeatsList);
				});
		   });
	  </script>
	
</body>
</html>