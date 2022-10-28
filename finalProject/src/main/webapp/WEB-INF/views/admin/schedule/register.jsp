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
	  	<p>영화 선택</p>
	  	<!-- // TODO : mvId청미(이)가 비동기로 영화목록을 가져와서 select for문으로 만드는데, 현재 누른시점날짜와 비교해서 상영중인 영화목록만 가져와야함 -->
	    <p>상영 지점 및 상영관 선택</p>
	  	<!-- // TODO : brcId청미(이)가 비동기로 지점목록을 가져와서 select for문으로 만듬 근데 중복선택가능해야함(3중반복문 제일외곽)-->
	  	<!-- // TODO : scdTheater 지점을 선택하면 상영관이 체크박스로 비동기로 나와야함 <div id="아이디지정"></div>-->
	    <p>상영 요일 선택</p>
	  	<!-- // TODO : scdDate체크박스로 월화수목금토일 체크하면 나중에 DB넘길때 상영기간내에 체크요일 해당날짜만큼 반복되어 삽임되어야함(3중반복문 두번째)-->
	    <p>상영 시간 선택</p>
	  	<!-- // TODO : scdTime체크박스로 0~23시간 체크하면 나중에 DB넘길때 해당시간만큼 반복되어 삽임되어야함(3중반복문 제일내부) -->
	    <p>상영 가격</p>
	    <input type="number" name="scdPrice" placeholder="기준가격 입력" required="required">
	    <!-- scdSeatTotal 은 끌어서 계산되어서 들어가야함....  -->
	 	
		<br><br><input type="submit" value="등록">
	  </form>
	  
	<!-- 구조 참고용 코드   <script type="text/javascript">
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
	  </script> -->
	
</body>
</html>