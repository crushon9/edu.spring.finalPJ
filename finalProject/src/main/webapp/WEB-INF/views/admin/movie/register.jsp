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
	    <input type="text" name="mvTitle" placeholder="영화 제목 입력" required="required">
	    <p>영화 개봉일</p>
	    <input type="date" name="mvDateStarted" value="2020-01-01">
	    <p>영화 종료일</p>
	    <input type="date" name="mvDateEnded" value="2023-12-31">
	    <p>영화 장르</p>
	    <select name="mvGenre" >
			<option>SF</option>
			<option>스릴러</option>
			<option>로맨스</option>
			<option>에로</option>
			<option>드라마</option>
			<option>공포</option>
			<option>애니메이션</option>
		</select>
		<p>영화 이미지</p>
		<!-- 폼을 하나로 합쳐야하는지? 아니면 새창으로 빼야하는건지? -->
		<!-- <form action="upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file">
		<input type="submit" value="업로드">
		</form> -->
		<br><br><input type="submit" value="등록">
	  </form>
	
</body>
</html>