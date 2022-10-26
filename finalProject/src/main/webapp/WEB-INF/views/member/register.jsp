<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Register</title>
</head>
<body>
	
	 <h2>회원 가입하기</h2>
	 
  <form action="register" method="post">
    <p>아이디</p>
    <input type="text" name="mmbId" id="mmbId" placeholder="아이디 입력" required="required">
    <!-- <div id='idCheckOutput' style="font-style : italic; display: block; height: 20px;"></div> -->
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
	<select name="brcId" >
			<option value="1">강남</option>
			<option value="2">신림</option>
			<option value="3">회기</option>
			<option value="4">명동</option>
			<option value="5">판교</option>
	</select><br><br>
	<input type="submit" value="회원가입">
  </form>
	

</body>
</html>