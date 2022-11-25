<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>Member Update 내가 !!! 바로!!! Admin!!!</title>
</head>
<body>
	
	<h2>회원 정보 강제 조작</h2>
	 
	  <form action="update" method="post">
	    <p>아이디</p>
	    <input type="text" name="mmbId" value="${vo.mmbId }" readonly>	    
	    <p>패스워드</p>
	    <input type="password" name="mmbPassword" value="${vo.mmbPassword }">
	    <p>이메일</p>
	    <input type="email" name="mmbEmail" value="${vo.mmbEmail }">
	 	<p>전화번호</p>
		<input type="tel" name="mmbPhone" value="${vo.mmbPhone }">  
		<p>생년월일</p>
	    <input type="date" name="mmbBirthday" value="${vo.mmbBirthday }">
		<!-- 관리자 여부 : DB에서 권한요청으로 set 1 -->
		<p>선호지점</p>
	  	<select id="brcArea" name="brcArea" >
	  		<option value="0">지역선택</option>
			<option value="1">서울</option>
			<option value="2">경기/강원</option>
			<option value="3">부산/경상</option>
			<option value="4">대전/충청</option>
			<option value="5">광주/전라</option>
			<option value="6">제주</option>
		</select>
		<div id="brcListOutput"></div>
		<br><br><input type="submit" value="강제수정">
	  </form>
	  
	  <form action="delete" method="post">
		  <input type="hidden" name="mmbId" value="${vo.mmbId }">
		  <input type="submit" value="강제 탈퇴">
	  </form>
	  
	  <script type="text/javascript">
		  $(document).ready(function() {
			$('#brcArea').change(function() {
				getBrcList();
			});
			// 선택 지역의 지점 가져오기
			function getBrcList() {
				var brcArea = $('#brcArea').val();
				var url = '/project/branch/list/' + brcArea; // admin없이 
				$.getJSON(
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
				); // end getJSON
			}
		 });	
	 </script>
</body>
</html>