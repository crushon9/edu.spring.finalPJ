<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>Movie Register</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
			<h1>관리자 영화 등록</h1>
			<p>영화 이미지</p>
			<div class="imageDrop"></div>
				
			<form action="register" method="post" enctype="multipart/form-data">
				<input type="hidden" id="mvImage" name="mvImage">
				<p>영화 제목</p>
				<input type="text" name="mvTitle" placeholder="영화제목 입력" required>
				<p>영화 개봉일</p>
				<input type="date" name="mvDateStarted" value="2020-01-01">
				<p>영화 종료일</p>
				<input type="date" name="mvDateEnded" value="2023-12-31">
				<p>영화 러닝타임 (분)</p>
				<input type="number" id="mvRunningTimeTemp" placeholder="상영시간 입력" min="1" max="1000" required>
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
				<textarea rows="10" cols="100" name="mvInfo" placeholder="영화정보" required></textarea>
				<br><br><input type="submit" value="등록">
			</form>
			<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function(){
			// 기본으로 내장되어있는 브라우저 기능제거 (파일을 끌어다 놓으면 drag&drop 브라우저가 파일을 자동으로 열어줌)
			$('.imageDrop').on('dragenter dragover', function(event) {
				event.preventDefault();
			});
			$('.imageDrop').on('drop', function(event) {
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
							+ '" width="200px"/>'
							+ '</div>';
						$('.imageDrop').html(str);
						$('#mvImage').val(data);
						console.log($('#mvImage').val());
					}
				}); // .ajax()
			}); // .imageDrop.on()
			
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