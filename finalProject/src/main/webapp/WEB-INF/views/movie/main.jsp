<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 포맷형태 바꾸는 taglib 이걸로 서버에서 받아온 날짜포맷변경할거-->
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<style type="text/css">

.imageSpace {
	width: 200px;
	height: 300px;
}
</style>

<meta charset="UTF-8">
<title>movie chart</title>
</head>

<body>
	<h1>movie chart</h1>
	<%String mmbIdSession = (String) session.getAttribute("mmbIdSession");%>
	
	<!-- <a href="/project/member/login"><input type="button" value="로그인"></a> -->
	
	<!-- 로그인 -->
	<c:if test="${empty mmbIdSession }">
		<button type="button" id="btn_login">login</button>
	</c:if>
	
	<c:if test="${not empty mmbIdSession }">
		<%=mmbIdSession %>님 환영!
		<button type="button" id="btn_logout">logout</button>
		<a href="/project/member/mypage/main"><input type="button" value="mypage"></a>
	</c:if>
	<hr>
	
	<!-- 영화 정렬 -->
	<select id="orderChoice">
		<option value="ticketSales">예매율순</option><!-- 조건절로 바꾸기 -->
		<option value="reviewAvg">평점순</option>			
	</select>
	<a id="orderUrl" href=""><input type="button" value="Go"></a>
	<br><br>
	
	<!-- 영화 검색 -->
	<input id="searchText" type="text"><a id="searchUrl" href=""><input id="searchBtn" type="button" value="검색"></a>
	
	<!-- 영화 목록 출력 -->
	<c:forEach var="vo" items="${mvList }">
		<ol class="mvItem">						
			<li style="list-style-type: none">
				<div>
					<a href="movieDetail?mvId=${vo.mvId}"><img class="imageSpace" src="/project/img/display?fileName=${vo.mvImage}"/></a>
				</div>
				<div class="mvTitle">
					<Strong class="mvTitle" >${vo.mvTitle }</Strong><br>
				</div>
				<a href="/project/schedule/list"><input id="mvTicket" type="button" value="예매하기"></a>							
				<a href="detail?mvId=${vo.mvId}"><input id="mvDetail" type="button" value="영화상세정보"></a>	
			</li>				
		</ol>											
	</c:forEach>
	
	<script type="text/javascript">			
		$(document).ready(function() {
			// 정렬
			$('#orderChoice').click(function() {
				var orderChoice = $('#orderChoice').val();
				var orderUrl = 'main?orderChoice=' + orderChoice;
				console.log(orderChoice);
				console.log(orderUrl);
				$('#orderUrl').prop("href", orderUrl);
			});//end orderChoice_click();
			
			// 검색
			$('#searchBtn').click(function() {
				var searchText = $('#searchText').val();
				var searchUrl = 'main?searchText=' + searchText;
				$('#searchUrl').prop("href", searchUrl);
			});//end searchBtn_click();
		
			// 로그인
			$('#btn_login').click(function(){
				var target = encodeURI('/project/member/login');
				location = target;	
			});//end btn_login_click();
			
			$('#btn_logout').click(function() {
				var target = encodeURI('/project/member/logout');
				location = target;	
			});//end btn_logout_click();	
			
			$('#btn_mypage').click(function() {
				var target = encodeURI('/project/member/mypage/main');
				location = target;							
			});//end btn_mypage_click();
			
		});	
	</script>
	
</body>
</html>