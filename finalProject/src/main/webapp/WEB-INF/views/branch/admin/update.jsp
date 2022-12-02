<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>Branch update</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
		<h1>관리자 지점 수정</h1>
		  <form action="update" method="post">
		  	<input type="hidden" name="brcId" value="${vo.brcId}">
		    <p>지역 수정</p>
		    <select name="brcArea">
				<option value="1">서울</option>
				<option value="2">경기/강원</option>
				<option value="3">부산/경상</option>
				<option value="4">대전/충청</option>
				<option value="5">광주/전라</option>
				<option value="6">제주</option>
			</select>
		    <p>지점 이름</p>
		    <input type="text" name="brcName" value="${vo.brcName }" required="required">
		    <p>지점 극장 수</p>
		    <input type="number" name="brcTheaterNumbers" id="brcTheaterNumbers" placeholder="지점 극장 수" min="1" max="20" required required="required">
		 	<p>지점 극장 좌석 수</p>
			<div id="brcTheaterSeatsOutput" ><input type="text" disabled></div>
			<p>지점 소개</p>
			<textarea rows="10" cols="100" name="brcInfo" placeholder="지점 정보" required></textarea>
			
			<br><br><input type="submit" value="수정">
		  </form>
		  
		  <%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>		
	  
	<script type="text/javascript">
	 	$(document).ready(function() {
	 		// 지점 극장수에 맞춰서 좌석수 입력 칸 반복하여 출력
			$('#brcTheaterNumbers').change(function() {
				var brcTheaterNumbers = $('#brcTheaterNumbers').val();
				var brcTheaterSeatsList = '';
				for (var i = 1; i <= brcTheaterNumbers; i++){
					brcTheaterSeatsList +=
					'<input type="number" name="arrBrcTheaterSeats" placeholder="' + i + "관" + '" min="1" max="650" required">';
				}
				$('#brcTheaterSeatsOutput').html(brcTheaterSeatsList);
			});
	    });
	 </script>
	
</body>
</html>