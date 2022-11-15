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
.file-drop {
	width: 200px;
	height: 300px;
	border: 1px solid gray;
}
</style>
</head>
<body>

	<h2>관리자 영화 등록</h2>
	<p>영화 이미지</p>
	<div class="file-drop"></div>
		
	<form action="register" method="post" enctype="multipart/form-data">
		<input type="hidden" id="mvImage" name="mvImage">
		<p>영화 제목</p>
		<input type="text" name="mvTitle" placeholder="영화제목 입력" required>
		<p>영화 개봉일</p>
		<input type="date" name="mvDateStarted" value="2020-01-01">
		<p>영화 종료일</p>
		<input type="date" name="mvDateEnded" value="2023-12-31">
		<p>영화 러닝타임</p>
		<input type="number" id="mvRunningTimeTemp" placeholder="상영시간 입력" required>
		<input type="hidden" id="mvRunningTime" name="mvRunningTime" >
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
		<textarea rows="4" cols="30" name="mvInfo" placeholder="영화정보" required></textarea>
		<br><br><input type="submit" value="등록">
	</form>

	<script type="text/javascript">
		$(document).ready(function(){
			// 기본으로 내장되어있는 브라우저 기능제거 (파일을 끌어다 놓으면 drag&drop 브라우저가 파일을 자동으로 열어줌)
			$('.file-drop').on('dragenter dragover', function(event) {
				event.preventDefault();
			});
			$('.file-drop').on('drop', function(event) {
				event.preventDefault();
				// Ajax를 이용하여 서버로 파일을 업로드
				// multipart/form-data 타입으로 파일을 업로드 하는 객체
				var formData = new FormData();
				
				// 드래그한 파일 정보를 갖고 있는 객체
				var files = event.originalEvent.dataTransfer.files;
				var i = 0;
				for (i = 0; i < files.length; i++) {
					console.log(files[i]);
					formData.append("files", files[i]);
				}
				
				$.ajax({
					type : 'POST',
					url : '/project/img/upload',
					data : formData,
					processData : false,
					contentType : false,
					success : function(data) {
						var str = '';
						str += '<div>'
							+ '<img src="/project/img/display?fileName='
							+ data
							+ '" width="400px"/>'
							+ '</div>';
						$('.file-drop').html(str);
						$('#mvImage').val(data);
						console.log($('#mvImage').val());
					}
				}); // .ajax()
			}); // .file-drop.on()
			
			$('#mvRunningTimeTemp').change(function() {
				var originRunTime = $('#mvRunningTimeTemp').val();
				var resultRunTime = parseInt(originRunTime / 30) + 1;
				$('#mvRunningTime').val(resultRunTime);
				console.log($('#mvRunningTime').val());
			});
		}); // document.ready()
	</script>

</body>
</html>