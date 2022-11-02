<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>Member Register</title>
</head>
<body>
	
	<h2>회원 가입하기</h2>
	 
	  <form action="register" method="post">
	    <p>아이디</p>
	    <input type="text" name="mmbId" id="mmbId" placeholder="아이디 입력" required="required">
	    <!-- // TODO : <div id='idCheckOutput' style="font-style : italic; display: block; height: 20px;"></div> -->
	    <p>패스워드</p>
	    <input type="password" name="mmbPassword" placeholder="비밀번호 입력" required="required">
	    <p>이메일</p>
	    <input type="email" name="mmbEmail" placeholder="이메일 입력" required="required">
	 	<p>전화번호</p>
		<input type="tel" name="mmbPhone" placeholder="전화번호 입력" required="required">  
		<p>생년월일</p>
	    <input type="date" name="mmbBirthday" value="1990-07-16">
		<!-- 관리자 여부 : DB에서 권한요청으로 set 1 -->
		<p>선호지점</p>
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
		<br><br><input type="submit" value="회원가입">
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
			$.getJSON(
				url,
				function(data) {
					var brcIdList = '<select id="brcId" name="brcId" ><option>지점선택</option>';
					$(data).each(function() {
						brcIdList += '<option value="' + this.brcId + '">' + this.brcName + '</option>';
					});
					brcIdList += '</select>'
					$('#brcIdOutput').html(brcIdList);
				}
			); // end getJSON
		}
		</script>

</body>
</html>