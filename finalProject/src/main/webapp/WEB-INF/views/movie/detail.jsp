<%@page import="edu.spring.project.domain.MovieVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 포맷형태 바꾸는 taglib 이걸로 서버에서 받아온 날짜포맷변경할거-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>${vo.mvTitle}</title>
<style type="text/css">
.detail {
	display : inline-flex;
	width : 200px;
	height : 200px;
	border : 1px solid grey;
}
.mvContent {
	padding: 100px 200px 100px 200px;
	margin-right: 200px;
	margin-left: 200px;
}
.mvImage {
	margin-right: 150px;
	width: 300px;
	height: 450px;
}
</style>

</head>
<body>

	<h2>movie detail</h2>
	<img class="mvImage" src="/project/img/display?fileName=${vo.mvImage}" />
	<h2>${vo.mvTitle}</h2>
	<p>영화 개봉일 : ${vo.mvDateStarted}</p>
	<p>영화 종료일 : ${vo.mvDateEnded}</p>
	<p>영화 장르 : ${vo.mvGenre}</p>
	<p>영화 소개 : </p>
	<p>${vo.mvInfo}</p>
	<p>영화 평점 : ${vo.mvRatingAvg}</p>
	<hr>

	<!-- reply 입력 -->
	<% String mmbId = (String) session.getAttribute("mmbIdSession"); %>
	<div style="text-align: center;">
		<input type="hidden" id="mvId" value="${vo.mvId }"> 
		<input type="text" id="mmbId" value="<%=mmbId %>" > 
		<input type="text" id="rvContent">
		<select id="rvRating">                               
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
        </select>
		<button id="btn_rv_add">후기작성</button>
	</div>

	<hr>
	<!-- 출력할 div공간 마련 reply처럼 끌고와-->
	<div style="margin-left: 40px">
		<div id="rvList"></div>
	</div>
	<br><br><br>		
		

	<script type="text/javascript">
		$(document).ready(function() {
			getReviewList();
			
			// 버튼 클릭, 후기등록		
			$('#btn_rv_add').click(function() {
				var mvId = $('#mvId').val();
				var mmbId = $('#mmbId').val();
				var rvContent = $('#rvContent').val();
				var rvRating = $('#rvRating').val();
				
				var obj = {
						'mvId' : mvId,
						'mmbId' : mmbId,
						'rvContent' : rvContent,
						'rvRating' : rvRating	
				};
				console.log(obj);				
				
				// $.ajax로 후기, 평점 등록, 미구현
				$.ajax({
					type : 'POST',
					url : '/project/review', // controller 경로 생성
					headers : { // 정보를 전송할때는 (GET방식을 빼고는) headers 넣어야함
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'POST'
					},
					data : JSON.stringify(obj), // JSON으로 변환
					success : function(result, status) {
						if (result == 1) {
							alert('후기, 평점 작성 굳굳');
							getReviewList();
						}
					} // end ajax.success.function
				}); // end ajax
			}); // end btn_add.click
			
			// 영화 후기 전체 출력
			function getReviewList() {
				console.log('getReviewList() call');
				var mvId = $('#mvId').val();
				var mmbId = $('#mmbId').val();
				console.log("mvId : " + mvId);
				var url = '/project/review/all/' + mvId; // REST API 방식 적용
				// $.getJSON 방식이므로 JSON.stringify하지 않아도 되고, header도 없어도됨
				$.getJSON(			
					url,
					function(data) {// 서버에서 온 data가 저장되어있음
						var rvList = '';
						$(data).each(function() {
							var rvDateCreated = new Date(this.rvDateCreated); // string 날짜를 다시 Date로 변환
							var rvRating = this.rvRating;
							var btn_disabled = 'disabled';
							var readonly = '';
							
							if (mmbId == this.mmbId) { // this : data 컬렉션의 한줄 데이터를 의미
								btn_disabled = '';
							}
							rvList += '<div class="rvItem">' // 여러개가 생성될거니깐 class를 부여했고, 댓글 한줄마다 호출시 구분해주는 역할
								+ '<input type="hidden" class="mvId" value="' + this.mvId + '"/>'
								+ '<input type="hidden" class="rvId" value="' + this.rvId + '"/>'
								+ '<input type="hidden" class="mmbId" value="' + this.mmbId + '"/>'
								+ this.mmbId // getJSON으로 받아온 data에 저장된 memberId 의미
								+ '&nbsp;&nbsp;' // space
								+ '<input type="text" class="rvContent" value="' + this.rvContent + '" readonly/>'
								+ '&nbsp;&nbsp;'
								+ '<select class="rvRating"' + disabled + '>'                                
	                            + '<option value="1">1</option>'
	                            + '<option value="2">2</option>'
	                            + '<option value="3">3</option>'
	                            + '<option value="4">4</option>'
	                            + '<option value="5">5</option>'
	                            + '</select>'
								+ rvDateCreated
								+ '&nbsp;&nbsp;'
								+ '<button class="btn_update" ' + btn_disabled + '>수정</button>'
								+ '<button class="btn_delete" ' + btn_disabled + '>삭제</button>'
								+ '</div>';
						}); // end data.each
						$('#rvList').html(rvList); // 반복문으로 생성된 html태그 출력
					}
				); // end getJSON
			} // end getAll
			
			// 수정 버튼을 클릭하면 댓글 수정
			$('#rvList').on('click', '.rvItem .btn_update', function(){
				var isReadOnly = $(this).prevAll('.rvContent')&&prevAll('.rvRating').prop('readonly');
				
				var rvId = $(this).prevAll('#rvId').val();
				var rvContent = $(this).prevAll('#rvContent').val();
				var rvRating = $(this).prevAll('#rvRating').val();
				if (isReadOnly == true) { // readonly가 true면
					// readonly 속성제거 후 버튼 변경
					$(this).prevAll('.rvId').removeAttr('readonly');
					$(this).prevAll('.rvContent').removeAttr('readonly');
					$(this).prevAll('.rvContent').css({"border-color":"red"});
					$(this).prevAll('.rvRating').removeAttr('readonly');
					$(this).prevAll('.rvRating').css({"border-color":"green"});
					$(this).text("수정확인");
					$(this).nextAll('.btn_delete').hide();
				} else { // 아니라면 댓글 수정
					// 선택된 댓글의 rvId, rvContent 값을 저장
					// prevAll() : 선택된 노드 이전에 위치해 있는 모든 형제 노드를 접근
					var rvId = $(this).prevAll('.rvId').val();
					var rvContent = $(this).prevAll('.rvContent').val();
					var rvRating = $(this).prevAll('.rvRating').val();
					var obj = {
							'rvContent' : rvContent,
							'rvRating' : rvRating							
					};
										
					console.log("수정 rvId : " + rvId + ", rvContent : " + rvContent + ", rvRating : " + rvRating);
										
					// ajax로 서버로 수정 데이터 전송
					$.ajax({
						type : 'PUT',
						url : '/project/review/' + rvId,
						headers : {
							'Content-Type' : 'application/json',
							'X-HTTP-Method-Override' : 'PUT'
						},
						data : JSON.stringify(obj),
						success : function(result) {
							console.log("댓글수정결과 : " + result);
							getReviewList();
						}
					});
				}
			}); // end replies.on.btn_update
			
			// 삭제 버튼을 클릭하면 선택된 댓글 삭제
			$('#rvList').on('click', '.rvItem .btn_delete', function(){
				var rvId = $(this).prevAll('.rvId').val();
				console.log("삭제 rvId : " + rvId);
				$.ajax({
					type : 'DELETE',
					url : '/project/review/' + rvId, 
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'DELETE'
					},
					data : JSON.stringify({
						'rvRating' : rvRating	
					}), // 여기 데이터는 vo에 자동으로 담기는데, rvId가 노필요?
					success : function(result) {
						console.log("댓글삭제결과 : " + result);
						if(result == 1){
							alert('delete success');
							getReviewList();							
						}
					}
				});
			}); // end replies.on.btn_delete
			
		}); // end document
	</script>

</body>
</html>