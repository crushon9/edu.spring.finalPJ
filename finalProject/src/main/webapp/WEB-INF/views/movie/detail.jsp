<%@page import="edu.spring.project.domain.MovieVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<div id="mvRatingAvgPrint"></div>
	<hr>

	<!-- reply 입력 -->
	<% String mmbId = (String) session.getAttribute("mmbIdSession"); %>
	<div style="margin-left: 40px">
		<input type="hidden" id="mvId" value="${vo.mvId }"> 
		작성자 <input type="text" id="mmbId" value="1" > 
		관람평 <input type="text" id="rvContent">
		평점 <select id="rvRating">                               
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
	       </select>&emsp;
		<button id="btn_rv_add">등록</button>
	</div>
	<hr>

	<!-- 출력할 div공간 마련 reply처럼 끌고와-->
	<div style="margin-left: 40px" id="rvList"></div>		
		

	<script type="text/javascript">
		$(document).ready(function() {
			getReviewList();
			$('#btn_rv_add').click(function() {
				rvAdd();
			});
			$('#rvList').on('click', '.rvItem .btn_rv_update', function(){
				rvUpdate(this);
			});
			$('#rvList').on('click', '.rvItem .btn_rv_delete', function(){
				rvDelete(this);
			});
		});
			
		// 영화 후기 전체 출력
		function getReviewList() {
			console.log('getReviewList() call');
			var mvId = $('#mvId').val();
			var url = '/project/review/' + mvId; // REST API 방식 적용
			var mmbId = $('#mmbId').val(); // 수정권한 확인용 세션 검사
			// $.getJSON 방식이므로 JSON.stringify하지 않아도 되고, header도 없어도됨
			$.getJSON(			
				url,
				function(data) {// 서버에서 온 data가 저장되어있음
					var rvList = '';
					$(data).each(function() {
						// DB에 저장된 평점으로 옵션 selected 설정
						var isSelected1 = '';
						var isSelected2 = '';
						var isSelected3 = '';
						var isSelected4 = '';
						var isSelected5 = '';
						switch (this.rvRating) {
						  case 1:
							isSelected1 = 'selected';
						    break;
						  case 2:
							isSelected2 = 'selected';
							break;
						  case 3:
							isSelected3 = 'selected';
						    break;
						  case 4:
							isSelected4 = 'selected';
							break;
						  case 5:
							isSelected5 = 'selected';
							break;
						}
						
						// 로그인된 세션과 작성자가 일치할때만 수정활성화
						var isDisabled = 'disabled';
						if (mmbId == this.mmbId) {
							isDisabled = '';
						}
						rvList += '<div class="rvItem">' // 여러개가 생성될거니깐 class를 부여했고, 댓글 한줄마다 호출시 구분해주는 역할
							+ '<input type="hidden" class="mvId" value="' + this.mvId + '"/>'
							+ '<input type="hidden" class="rvId" value="' + this.rvId + '"/>'
							+ '<input type="hidden" class="mmbId" value="' + this.mmbId + '"/>'
							+ this.mmbId // getJSON으로 받아온 data에 저장된 memberId 의미
							+ '&nbsp;&nbsp;' // space
							+ '<input type="text" class="rvContent" value="' + this.rvContent + '" readonly/>'
							+ '&nbsp;&nbsp;'
							+ '<select class="rvRating" disabled>'                               
                            + '<option value="1"' + isSelected1 + '>1</option>'
                            + '<option value="2"' + isSelected2 + '>2</option>'
                            + '<option value="3"' + isSelected3 + '>3</option>'
                            + '<option value="4"' + isSelected4 + '>4</option>'
                            + '<option value="5"' + isSelected5 + '>5</option>'
                            + '</select>'
                            + '<input type="hidden" class="rvRatingBefore"/>'
                            + '&nbsp;&nbsp;'
							+ new Date(this.rvDateCreated).toLocaleString()
							+ '&nbsp;&nbsp;'
							+ '<button class="btn_rv_update" ' + isDisabled + '>수정</button>'
							+ '<button class="btn_rv_delete" ' + isDisabled + '>삭제</button>'
							+ '</div>';
					}); // end data.each
					$('#rvList').html(rvList); // 반복문으로 생성된 html태그 출력
					mvRatingRefresh();
				}
			); // end getJSON
		} // end getAll
			
		// 후기 등록
		function rvAdd() {
			console.log('rvAdd() call');
			var mvId = $('#mvId').val();
			var mmbId = $('#mmbId').val();
			var rvContent = $('#rvContent').val();
			var rvRating = $('#rvRating').val();
			$.ajax({
				type : 'POST',
				url : '/project/review', // controller 경로 생성
				headers : { // 정보를 전송할때는 (GET방식을 빼고는) headers 넣어야함
					'Content-Type' : 'application/json',
					'X-HTTP-Method-Override' : 'POST'
				},
				data : JSON.stringify({
					'mvId' : mvId,
					'mmbId' : mmbId,
					'rvContent' : rvContent,
					'rvRating' : rvRating	
				}), // JSON으로 파싱
				success : function(result, status) {
					getReviewList();
				} // end ajax.success.function
			}); // end ajax
		}	
		
		// 후기 수정
		function rvUpdate(btn) {
			console.log('rvUpdate() call');
			// prevAll() : 선택된 노드 이전에 위치해 있는 모든 형제 노드를 접근
			// 수정버튼을 처음누르면 readonly 속성제거, 수정확인을 누르면 ajax로 데이터 변경
			var isReadOnly = $(btn).prevAll('.rvContent').prop('readonly');
			if (isReadOnly == true) { // readonly가 true면
				$(btn).prevAll('.rvRatingBefore').val($(btn).prevAll('.rvRating').val());
				$(btn).prevAll('.rvContent').removeAttr('readonly');
				$(btn).prevAll('.rvContent').css({"border-color":"red"});
				$(btn).prevAll('.rvRating').removeAttr('disabled');
				$(btn).prevAll('.rvRating').css({"border-color":"red"});
				$(btn).text("수정확인");
				$(btn).nextAll('.btn_rv_delete').hide();
			} else { // 아니라면 댓글 수정
				var rvId = $(btn).prevAll('.rvId').val();
				var mvId = $('#mvId').val();
				var rvContent = $(btn).prevAll('.rvContent').val();
				var rvRatingAfter = $(btn).prevAll('.rvRating').val();
				var rvRatingBefore = $(btn).prevAll('.rvRatingBefore').val();
				$.ajax({
					type : 'PUT',
					url : '/project/review',
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'PUT'
					},
					data : JSON.stringify({
						'rvId' : rvId,
						'mvId' : mvId,
						'rvContent' : rvContent,
						'rvRating' : rvRatingAfter,
						'rvRatingBefore' : rvRatingBefore
					}),
					success : function(result) {
						getReviewList();
					}
				});
			}
		}
		
		// 후기 삭제
		function rvDelete(btn) {
			console.log('rvDelete() call');
			var rvId = $(btn).prevAll('.rvId').val();
			var mvId = $('#mvId').val();
			var rvRating = $(btn).prevAll('.rvRating').val();
			$.ajax({
				type : 'DELETE',
				url : '/project/review',
				headers : {
					'Content-Type' : 'application/json',
					'X-HTTP-Method-Override' : 'DELETE'
				},
				data : JSON.stringify({
					'rvId' : rvId,
					'mvId' : mvId,
					'rvRating' : rvRating	
				}), // 여기 데이터는 vo에 자동으로 담기는데, rvId가 노필요?
				success : function(result) {
					getReviewList();							
				}
			});
		}
		
		function mvRatingRefresh() {
			console.log('mvRatingRefresh() 호출');
			var mvId = $('#mvId').val();
			var url = '/project/movie/mvRatingAvg/' + mvId;
			$.getJSON(			
				url,
				function(data) {
					console.log(data);
					var mvRatingAvgText = '영화 평점 : '
										+ (data).toFixed(2) // 소수점 둘째 반올림
										+ ' / 5.00';
					$('#mvRatingAvgPrint').html(mvRatingAvgText); // 반복문으로 생성된 html태그 출력
				}
			);
		}
			
	</script>

</body>
</html>