<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>Member Register</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
		<h1>회원 가입하기</h1>
		 
		<form action="register" method="post">
			<p>아이디</p>
			<input type="text" name="mmbId" id="mmbId" placeholder="아이디 입력" required="required">
			<div id='idCheckOutput' style="font-style : italic; display: inline-block; height: 20px;"></div>
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
			<select id="brcArea">
				<option value="0">지역선택</option>
				<option value="1">서울</option>
				<option value="2">경기/강원</option>
				<option value="3">부산/경상</option>
				<option value="4">대전/충청</option>
				<option value="5">광주/전라</option>
				<option value="6">제주</option>
			</select>
			<div id="brcListOutput" style="display: inline-block;"><select disabled><option>지점선택</option></select></div>
			<br><br><input type="submit" value="가입하기">
		</form>
		
		<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>
	  
	<script type="text/javascript">
		$(document).ready(function() {
			$('#brcArea').change(function() {
				publicGetBrcList();
			});
			$('#mmbId').blur(function() {
				idCheck();
			});
		});
		
		// 아이디 사용 가능 여부 검사	
		function idCheck(){
			var mmbId = $('#mmbId').val();
			// 'null' 아이디는 가입불가 (추후 세션검사 때문)
			if (mmbId == 'null') {
				$('#idCheckOutput').html('사용 불가한 아이디 입니다');
				$('#idCheckOutput').css("color", 'black');
				return;
			}
			$.ajax({
				type : 'POST',
				url : '/project/member/idCheck',
				headers : {
					'Content-Type' : 'application/json',
					'X-HTTP-Method-Override' : 'POST'
				},
				data : mmbId,
				success : function(result) {
					// -1 : 가입불가, 1 : 가입가능
					if (result == -1) {
						$('#idCheckOutput').html('중복된 아이디입니다');
						$('#idCheckOutput').css("color", 'red');
					} else if (result == 1 && mmbId != '') {
						$('#idCheckOutput').html('사용가능한 아이디입니다');
						$('#idCheckOutput').css("color", 'blue');
					}
				}	
			});
		}
	</script>

</body>
</html>