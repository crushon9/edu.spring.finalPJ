<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mypage.css">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>review list</title>
</head>
<body>
<%String mmbIdSession = (String) session.getAttribute("mmbIdSession");%>
<h1 id="mainhead">유저 리뷰 조회</h1>
<div class="grid">
	<div class="gridleft">
		<ul>
			<li><a href="/project/member/update?mmbId=<%=mmbIdSession %>">회원정보 수정</a></li>
			<li><a href="/project/ticket/list">예매티켓 조회</a></li>
			<li id="active"><a href="/project/review/list">작성리뷰 조회</a></li>
		</ul>
	</div>
	<div class="gridright">
		<%=mmbIdSession %> 님 환영합니다
		<input type="hidden" id="mmbId" value="<%=mmbIdSession %>">
	<div id="reviewListOutput"></div>
	</div>
</div>	
	<script type="text/javascript">
	  $(document).ready(function() {
		  getUserReviewList();
		  $('#reviewListOutput').on('click', '.rvItem .btn_rv_update', function(){
		  	  rvUpdate(this);
		  });
		  $('#reviewListOutput').on('click', '.rvItem .btn_rv_delete', function(){
			  rvDelete(this);
	      });
	  });
			
		// 영화 후기 전체 출력
		function getUserReviewList() {
			console.log('getUserReviewList() call');
			var mmbId = $('#mmbId').val();
			var url = '/project/review/list/' + mmbId;
			$.getJSON(			
				url,
				function(data) {// 서버에서 온 data가 저장되어있음
					var rvList = '<ul>';
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
						rvList += '<li class="rvItem">' // 여러개가 생성될거니깐 class를 부여했고, 댓글 한줄마다 호출시 구분해주는 역할
							+ '<input type="hidden" class="mvId" value="' + this.mvId + '"/>'
							+ '<input type="hidden" class="rvId" value="' + this.rvId + '"/>'
							+ '<input type="hidden" class="mmbId" value="' + this.mmbId + '"/>'
							+ '<img src="/project/img/display?fileName=' + this.mvImage + '" width="50px"/><br>'
							+ '<strong>' + this.mvTitle + '</strong>'
							+ '<br>'
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
							+ '</li><br>';
					}); // end data.each
					rvList += '</ul>';
					$('#reviewListOutput').html(rvList); // 반복문으로 생성된 html태그 출력
				}
			); // end getJSON
		} // end getAll
		
		// 후기 수정
		function rvUpdate(btn) {
			console.log('rvUpdate() call');
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
				var mvId = $(btn).prevAll('.mvId').val();
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
						getUserReviewList();
					}
				});
			}
		}
		
		// 후기 삭제
		function rvDelete(btn) {
			console.log('rvDelete() call');
			var rvId = $(btn).prevAll('.rvId').val();
			var mvId = $(btn).prevAll('.mvId').val();
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
				}),
				success : function(result) {
					getUserReviewList();							
				}
			});
		}
			
	</script>
</body>
</html>