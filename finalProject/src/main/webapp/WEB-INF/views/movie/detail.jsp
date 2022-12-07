<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
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
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
			<h1>영화 상세 정보</h1>
			<img class="mvImage" src="/project/img/display?fileName=${vo.mvImage}" />
			<h2>${vo.mvTitle}</h2>
			<p>예매율 </p><!-- 예매율 : 해당영화판매/영화전체판매 -->
			<div style="color:red; font-weight:bolder;"><fmt:formatNumber value="${vo.mvTicketSales / mvTicketSalesTotal * 100}" pattern="0.0"/> %</div>
			<div id="mvRatingAvgPrint"></div>
			<p>영화 장르 </p>${vo.mvGenre}
			<p>상영일</p>${vo.mvDateStarted} ~ ${vo.mvDateEnded}
			<p>영화 정보</p>
			<textarea rows="10" cols="100" style="border:none; outline:none;" readonly >${vo.mvInfo}</textarea>
			<hr>
		
			<!-- 영화리뷰출력 -->
			<input type="hidden" id="mmbId" value="<%=mmbIdSession %>" readonly> 
			<input type="hidden" id="mvId" value="${vo.mvId }">
			<input type="hidden" id="mvTitle" value="${vo.mvTitle }">
			<input type="hidden" id="mvImage" value="${vo.mvImage }">
			<input id="btn_review" type="button" value="리뷰등록">
			<div id="reviewListOutput"></div>
			<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>	
				
	<script type="text/javascript">
		$(document).ready(function() {
			// 리뷰 목록 불러오기
			getReviewList();
			// 리뷰 등록하기 버튼 이벤트
			$('#btn_review').click(function(){
				var mmbId = $('#mmbId').val();
				// 미로그인시 예외처리
				if (mmbId == 'null') {
					var targetURL = location.href;
					console.log(targetURL);
					sessionStorage.setItem('targetURL', targetURL);
					location.href = '/project/member/login?alertSession=reviewSessionFail';
				}
				reviewRegister(this);
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
					var rvList = '등록된 리뷰가 없습니다';
					// 데이터가 있을때만 출력
					if ($(data).length != 0) {
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
								break
							  case 5:
								isSelected5 = 'selected';
								break;
							}
							// 로그인된 세션과 작성자가 일치할때만 수정버튼 활성화
							var isDisabled = 'disabled';
							if (mmbId == this.mmbId) {
								isDisabled = '';
							}
							// 댓글 한줄마다 구분하여 접근하기 위해 div class 지정
							rvList += '<div class="rvItem">' 
									+ '<input type="hidden" class="mvId" value="' + this.mvId + '"/>'
									+ '<input type="hidden" class="rvId" value="' + this.rvId + '"/>'
									+ '<input type="hidden" class="mmbId" value="' + this.mmbId + '"/>'
									+ '<p style="width: 70px; display: inline-block">' + this.mmbId + '</p>'
									+ '<input type="text" class="rvContent" value="' + this.rvContent + '" readonly style="width: 400px;"/>'
									+ '&nbsp;&nbsp;'
		                            + '<div id="star-rating" style="display: inline-block; width: 120px; font-size: 1em;">';
		                            for (var star = 0; star < this.rvRating; star++) {
		                            	rvList += '⭐'
		                            }
		                            rvList += '</div>'
									+ '<select class="rvRating" disabled>'                               
		                            + '<option value="1"' + isSelected1 + '>1</option>'
		                            + '<option value="2"' + isSelected2 + '>2</option>'
		                            + '<option value="3"' + isSelected3 + '>3</option>'
		                            + '<option value="4"' + isSelected4 + '>4</option>'
		                            + '<option value="5"' + isSelected5 + '>5</option>'
		                            + '</select>'
		                            // rvRatingBefore : 추후 평점 수정할때 수정전 평점을 영화평점 계산에 보내기 위해 임시 설정
		                            + '<input type="hidden" class="rvRatingBefore"/>'
		                            + '&nbsp;&nbsp;'
									+ new Date(this.rvDateCreated).toLocaleString()
									+ '&nbsp;&nbsp;'
									+ '<input class="btn_update" type="button" value="수정"' + isDisabled + '>'
									+ '&nbsp;'
									+ '<input class="btn_delete" type="button" value="삭제"' + isDisabled + '>'
									+ '</div>';
						}); // end data.each
					}
					// 반복문으로 생성된 html태그 출력
					$('#reviewListOutput').html(rvList);
					// 리뷰를 가져올때마다 영화 평균 평점도 새로 계산하여 출력
					mvRatingRefresh();
				}
			); // end getJSON
		}
		
		// 리뷰 등록
		function reviewRegister() {
			console.log('reviewRegister() 호출');
			 var mmbId = $('#mmbId').val();
			 var mvId = $('#mvId').val();
			 var mvTitle = $('#mvTitle').val();
			 var mvImage = $('#mvImage').val();
			 $.getJSON (		
				'/project/review/check/' + mmbId + '/' + mvId,
				function(data) {
					console.log(data);
					// 0:리뷰등록가능, -2:영화미관람, -3:리뷰기등록, -4:티켓구매했으나영화상영전
					if (data == 0) {
						// 리뷰 등록 가능할때 리뷰등록 새창
						var popUrl = '/project/review/register?mmbId=' + mmbId + '&mvId=' + mvId + '&mvTitle=' + mvTitle + '&mvImage=' + mvImage;
					    var popOption = 'status=no, menubar=no, toolbar=no, resizable=no';
						window.open(popUrl, '_blank', popOption);
					} else if (data == -2) {
						alert("이미 리뷰 등록하신 영화 입니다");
					} else if (data == -3) {
						alert("관람 후 리뷰 등록 가능합니다");
					} else if (data == -4) {
						alert("영화 상영시간 이후 리뷰 등록 가능합니다");
					}
				}
			 );
		 }
		 
		// 후기 수정
		$('#reviewListOutput').on('click', '.rvItem .btn_update', function(){
			console.log('reviewUpdate() call');
			// 수정버튼을 처음누르면 readonly 속성제거, 수정확인을 누르면 ajax로 데이터 변경
			var isReadOnly = $(this).prevAll('.rvContent').prop('readonly');
			if (isReadOnly == true) { // readonly가 true면 readonly 속성제거
				$(this).prevAll('.rvRatingBefore').val($(this).prevAll('.rvRating').val());
				$(this).prevAll('.rvContent').removeAttr('readonly');
				$(this).prevAll('.rvContent').css({"border-color":"red"});
				$(this).prevAll('.rvRating').removeAttr('disabled');
				$(this).prevAll('.rvRating').css({"border-color":"red"});
				$(this).val("수정확인");
				$(this).nextAll('.btn_delete').hide();
			} else { // 아니라면(수정확인을 누르면) ajax로 데이터 변경
				var rvId = $(this).prevAll('.rvId').val();
				var mvId = $('#mvId').val();
				var rvContent = $(this).prevAll('.rvContent').val();
				// rvRatingAfter : 변경 후 평점
				var rvRatingAfter = $(this).prevAll('.rvRating').val();
				// rvRatingBefore : 변경 전 평점
				var rvRatingBefore = $(this).prevAll('.rvRatingBefore').val();
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
						// 변경 후 리뷰리스트 다시 불러오기
						getReviewList();
					}
				});
			}
		});
		
		// 후기 삭제
		$('#reviewListOutput').on('click', '.rvItem .btn_delete', function(){
			console.log('reviewDelete() call');
			var rvId = $(this).prevAll('.rvId').val();
			var mvId = $('#mvId').val();
			var rvRating = $(this).prevAll('.rvRating').val();
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
				}),
				success : function(result) {
					// 변경 후 리뷰리스트 다시 불러오기
					getReviewList();							
				}
			});
		});
		
		// 영화 평균 평점 새로고침
		function mvRatingRefresh() {
			console.log('mvRatingRefresh() 호출');
			var mvId = $('#mvId').val();
			var url = '/project/movie/mvRatingAvg/' + mvId;
			$.getJSON(			
				url,
				function(data) {
					console.log(data);
					var mvRatingAvgText = '<p>영화 평점</p><div style="color:red; font-weight:bolder;">';
					var mvRatingAvg = (data).toFixed(2); // 소수점 둘째 반올림
					if (mvRatingAvg != 0) {
						mvRatingAvgText += mvRatingAvg + ' / 5.00</div>';
					// 평점이 0이면 리뷰 미등록 표기
					} else {
						mvRatingAvgText += '리뷰 등록 전 입니다</div>';
					}
					$('#mvRatingAvgPrint').html(mvRatingAvgText)
				}
			);
		}
			
	</script>

</body>
</html>