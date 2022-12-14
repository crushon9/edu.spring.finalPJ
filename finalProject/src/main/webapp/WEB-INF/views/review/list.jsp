<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>review list</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
			<h1><%=mmbIdSession %>님 리뷰 내역</h1>
			<input type="hidden" id="mmbId" value="<%=mmbIdSession %>">
			<div id="reviewListOutput"></div>
			
			<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>
	<script type="text/javascript">
	  $(document).ready(function() {
		  getUserReviewList();
	  });
			
		// 영화 후기 전체 출력
		function getUserReviewList() {
			console.log('getUserReviewList() call');
			var mmbId = $('#mmbId').val();
			var url = '/project/review/list/' + mmbId;
			$.getJSON(			
				url,
				function(data) {// 서버에서 온 data가 저장되어있음
					var rvList = '등록된 리뷰가 없습니다';
					if ($(data).length != 0) {
						rvList = '<table>'
							   + '<thead>'
							   + '<tr>'
							   + '<th></th>'
							   + '<th>영화제목</th>'
							   + '<th>관람평</th>'
							   + '<th>평점</th>'
							   + '<th>등록일</th>'
							   + '<th></th>'
							   + '<th></th>'
							   + '</tr>'
							   + '</thead>'
							   + '<tbody>';
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
							// 여러 댓글이 생성되기에 각 접근을 위해 class를 부여, 각 댓글 한줄마다 호출시 구분
							rvList += '<tr class="rvItem">' 
									+ '<input type="hidden" class="mmbId" value="' + this.mmbId + '"/>'
									+ '<input type="hidden" class="rvId" value="' + this.rvId + '"/>'
									+ '<input type="hidden" class="mvId" value="' + this.mvId + '"/>'
		                            + '<input type="hidden" class="rvRatingBefore"/>'
									+ '<td><a href="/project/movie/detail?mvId=' + this.mvId + '"><img src="/project/img/display?fileName=thumbnail_' + this.mvImage + '"/></a></td>'
									+ '<td><strong>' + this.mvTitle + '</strong></td>'
									+ '<td class="rvContent"><input type="text" class="rvContent" value="' + this.rvContent + '" readonly/></td>'
									+ '<td class="rvRating">'
									// 이모지 별 모양 출력 공간
									+ '<div id="star-rating" style="display: inline-block; width:120px; text-align: left; font-size: 1em;">';
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
		                            + '</select></td>'
									+ '<td>' + new Date(this.rvDateCreated).toLocaleString() + '</td>'
									+ '<td class="update"><input class="btn_update" type="button" value="수정"' + isDisabled + '></td>'
									+ '<td class="delete"><input class="btn_delete" type="button" value="삭제"' + isDisabled + '></td>'
									+ '</tr>';
						}); // end data.each
						rvList += '</tbody></table>';
					}
					$('#reviewListOutput').html(rvList);
				}
			); // end getJSON
		} // end getAll
		
		// 후기 수정
		$('#reviewListOutput').on('click', '.rvItem .btn_update', function() {
			console.log('reviewUpdate() call');
			// 수정버튼을 처음누르면 readonly 속성제거, 수정확인을 누르면 ajax로 데이터 변경
			var isReadOnly = $(this).parent().prevAll('.rvContent').children('.rvContent').prop('readonly');
			if (isReadOnly == true) { // readonly가 true면
				var rvRating = $(this).parent().prevAll('.rvRating').children('.rvRating').val();
				$(this).parent().prevAll('.rvRatingBefore').val(rvRating);
				$(this).parent().prevAll('.rvContent').children('.rvContent').removeAttr('readonly');
				$(this).parent().prevAll('.rvContent').children('.rvContent').css({"border-color":"red"});
				$(this).parent().prevAll('.rvRating').children('.rvRating').removeAttr('disabled');
				$(this).parent().prevAll('.rvRating').children('.rvRating').css({"border-color":"red"});
				$(this).val("수정확인");
				// 수정 버튼 옆 생성하고 숨겨둔다.
				$(this).parent().nextAll('.delete').children('.btn_delete').hide();
			} else { // 아니라면 댓글 수정
				var rvId = $(this).parent().prevAll('.rvId').val();
				var mvId = $(this).parent().prevAll('.mvId').val();
				var rvRatingBefore = $(this).parent().prevAll('.rvRatingBefore').val();
				var rvContent = $(this).parent().prevAll('.rvContent').children('.rvContent').val();
				var rvRatingAfter = $(this).parent().prevAll('.rvRating').children('.rvRating').val();
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
						getUserReviewList();
					}
				});
			}
		});
		
		// 후기 삭제
		$('#reviewListOutput').on('click', '.rvItem .btn_delete', function() {
			console.log('reviewDelete() call');
			var rvId = $(this).parent().prevAll('.rvId').val();
			var mvId = $(this).parent().prevAll('.mvId').val();
			var rvRating = $(this).parent().prevAll('.rvRating').children('.rvRating').val();
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
					getUserReviewList();							
				}
			});
		});
	</script>
</body>
</html>