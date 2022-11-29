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
			<p>영화 장르 </p>${vo.mvGenre}
			<p>상영일</p>${vo.mvDateStarted} ~ ${vo.mvDateEnded}
			<p>영화 정보</p>${vo.mvInfo}
			<p>예매율 </p>
			<fmt:formatNumber value="${vo.mvTicketSales / mvTicketSalesTotal * 100}" pattern="0.0"/> %
			<div id="mvRatingAvgPrint"></div>
			<hr>
		
			<!-- 출력할 div공간 마련 reply처럼 끌고와-->
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
			getReviewList();
			$('#reviewListOutput').on('click', '.rvItem .btn_update', function(){
				rvUpdate(this);
			});
			$('#reviewListOutput').on('click', '.rvItem .btn_delete', function(){
				rvDelete(this);
			});
			$('#btn_review').click(function(){
				rvRegister(this);
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
							break
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
							+ '<p style="width: 70px; display: inline-block">' + this.mmbId + '</p>'// getJSON으로 받아온 data에 저장된 memberId 의미
							+ '&nbsp;&nbsp;' // space
							+ '<input type="text" class="rvContent" value="' + this.rvContent + '" readonly style="width: 400px;"/>'
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
							+ '<input class="btn_update" type="button" value="수정"' + isDisabled + '>'
							+ '&nbsp;'
							+ '<input class="btn_delete" type="button" value="삭제"' + isDisabled + '>'
							+ '</div>';
					}); // end data.each
					$('#reviewListOutput').html(rvList); // 반복문으로 생성된 html태그 출력
					mvRatingRefresh();
				}
			); // end getJSON
		} // end getAll
		
		function rvRegister() {
			console.log('rvRegister() 호출');
			 var mmbId = $('#mmbId').val();
			 var mvId = $('#mvId').val();
			 var mvTitle = $('#mvTitle').val();
			 var mvImage = $('#mvImage').val();
			 $.getJSON (		
				'/project/review/check/' + mmbId + '/' + mvId,
				function(data) {
					console.log(data);
					// 0:리뷰등록가능, -1:영화미관람, -2:리뷰기등록
					if (data == 0) {
						var popUrl = '/project/review/register?mmbId=' + mmbId + '&mvId=' + mvId + '&mvTitle=' + mvTitle + '&mvImage=' + mvImage;
					    var popOption = 'status=no, menubar=no, toolbar=no, resizable=no';
						window.open(popUrl, '_blank', popOption);
					} else if (data == -2) {
						alert("동일계정으로 리뷰 등록된 영화 입니다");
					} else if (data == -1) {
						alert("관람 후 리뷰 등록 가능합니다");
					}
				}
			 );
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
				$(btn).val("수정확인");
				$(btn).nextAll('.btn_delete').hide();
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
					var mvRatingAvgText = '<p>영화 평점</p>';
					var mvRatingAvg = (data).toFixed(2); // 소수점 둘째 반올림
					if (mvRatingAvg != 0) {
						mvRatingAvgText += mvRatingAvg + ' / 5.00';
					} else {
						mvRatingAvgText += '리뷰 등록 전 입니다';
					}
					$('#mvRatingAvgPrint').html(mvRatingAvgText); // 반복문으로 생성된 html태그 출력
				}
			);
		}
			
	</script>

</body>
</html>