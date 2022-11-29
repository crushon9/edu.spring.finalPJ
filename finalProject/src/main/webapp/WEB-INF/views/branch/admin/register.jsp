<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>Branch Register</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
		<h1>관리자 지점 등록</h1>
		 
		  <form action="register" method="post">
		    <p>지역 설정</p>
		    <select name="brcArea" >
				<option value="1">서울</option>
				<option value="2">경기/강원</option>
				<option value="3">부산/경상</option>
				<option value="4">대전/충청</option>
				<option value="5">광주/전라</option>
				<option value="6">제주</option>
			</select>
		    <p>지점 이름</p>
		    <input type="text" name="brcName" placeholder="지점 이름 입력" required="required">
		    <p>지점 극장 수</p>
		    <input type="number" name="brcTheaterNumbers" id="brcTheaterNumbers" placeholder="지점 극장 수" min="1" max="20" required="required">
		 	<p>지점 극장 좌석 수</p>
			<div id="brcTheaterSeatsOutput"></div>
			<p>지점 소개</p>
			<textarea rows="10" cols="100" name="brcInfo" placeholder="지점 정보" required></textarea>
			
			<br><br><input type="submit" value="등록">
		  </form>
		  
		  <%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>	
	 
	<input type="hidden" id="branchRegisterResult" value="${branchRegisterResult}"/>
	
	<script type="text/javascript">
	 	$(document).ready(function() {
	 		responseAlert();
			$('#brcTheaterNumbers').change(function() {
				var brcTheaterNumbers = $('#brcTheaterNumbers').val();
				var brcTheaterSeatsList = '';
				for (var i = 1; i <= brcTheaterNumbers; i++){
					brcTheaterSeatsList +=
					'<input type="number" name="arrBrcTheaterSeats" placeholder="' + i + "관" + '" required" min="1" max="650">';
				}
				$('#brcTheaterSeatsOutput').html(brcTheaterSeatsList);
			});
		  });
	 
	 	// 결과 값에 대한 알러트
		function responseAlert() {
			var branchRegisterResult = $('#branchRegisterResult').val();
			if (branchRegisterResult == 'fail'){
				alert("지점등록에 실패하였습니다.");
			} 
		}
	 
	</script>
	
</body>
</html>