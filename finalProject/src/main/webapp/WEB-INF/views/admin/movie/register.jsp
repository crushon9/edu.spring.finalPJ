<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>Movie Register</title>
</head>
<body>
	
	<h2>관리자 영화 등록</h2>
	 
	  <form action="register" method="post">
	    <p>영화 제목</p>
	    <input type="text" name="mvTitle" placeholder="영화 제목 입력" required>
		<p>영화 이미지</p>
		<input type="text" name="mvImage" placeholder="임시 파일명" required>
		<!-- 폼을 하나로 합쳐야하는지? 아니면 새창으로 빼야하는건지? -->
		<!-- <form action="upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file">
		<input type="submit" value="업로드">
		</form> -->
	    <p>영화 개봉일</p>
	    <input type="date" name="mvDateStarted" value="2020-01-01">
	    <p>영화 종료일</p>
	    <input type="date" name="mvDateEnded" value="2023-12-31">
	    <p>영화 러닝타임</p>
	    <!-- 여기 30분으로 나누어서 반올림한 int를 넘겨줘야함 -->
		<input type="number" name="mvRuningTime" placeholder="상영시간 입력" required>
	    <p>영화 장르</p>
	    <select name="mvGenre" >
			<option value="SF">SF</option>
			<option value="스릴러">스릴러</option>
			<option value="로맨스">로맨스</option>
			<option value="에로">에로</option>
			<option value="드라마">드라마</option>
			<option value="공포">공포</option>
			<option value="애니메이션">애니메이션</option>
		</select>
		<br><br><input type="submit" value="등록">
	  </form>
	
</body>
</html>