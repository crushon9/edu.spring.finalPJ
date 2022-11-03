<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>Movie Register</title>


<style type="text/css">
	.register {
		width: 300px;
		height: 3400px;
		border: 2px solid green;
	}
</style>

</head>

<body>

	<h2>관리자 영화 등록</h2>
	<form action="register" method="post" enctype="multipart/form-data">
		<p>영화 제목</p>
		<input type="text" name="mvTitle" placeholder="영화 제목 입력" required>
		<p>영화 이미지</p>
		<input type="file" name="mvImage">
		<p>영화 개봉일</p>
		<input type="date" name="mvDateStarted" value="2020-01-01">
		<p>영화 종료일</p>
		<input type="date" name="mvDateEnded" value="2023-12-31">
		<p>영화 러닝타임</p>
		<!-- 여기 30분으로 나누어서 반올림한 int를 넘겨줘야함 -->
		<input type="number" name="mvRuningTime" placeholder="상영시간 입력"
			required>
		<p>영화 장르</p>
		<select name="mvGenre" required>
			<option value="SF">SF</option>
			<option value="스릴러">스릴러</option>
			<option value="로맨스">로맨스</option>
			<option value="에로">에로</option>
			<option value="드라마">드라마</option>
			<option value="공포">공포</option>
			<option value="애니메이션">애니메이션</option>
		</select>
		<p>영화 소개</p>
		<input type="text" name="mvInfo" value="${vo.mvInfo}">
		
		<br><br><input type="submit" value="등록">
	</form>

	<script type="text/javascript">
		$('#mvImage').change(function() {
			setImageFromFile(this, '#preview');
		});

		function setImageFromFile(input, expression) {
			if (input.files && input.files[0]) {
				var re = new FileReader();
				re.onload = function(e) {
					$(expression).attr('src', e.target.result);
				}
				re.readAsDataURL(input.files[0]);
			}
		}
	</script>

</body>
</html>