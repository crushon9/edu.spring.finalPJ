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
.main {
	padding: 150px 150px 200px 200px;
	margin-right: 50px;
	margin-left: 50px;
}

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
	
	<c:if test="${empty mmbIdSession }">
		<button type="button" id="btn_login">login</button>
	</c:if>
	
	<c:if test="${not empty mmbIdSession }">
		<%=mmbIdSession %>님 환영!
		<button type="button" id="btn_logout">logout</button>
		<button type="button" id="btn_mypage">mypage</button>
	</c:if>
	
	<div class="main">		
		<hr style="width : 85%; margin-left : 0;"><br>
			<div id="chart" style=" float: right; margin-right: 255px;"><!-- 접근 -->
				<select id="orderChoice">
					<option value="ts">예매율순</option><!-- 조건절로 바꾸기 -->
					<option value="ra">평점순</option>			
				</select>
				<a id="orderUrl" href="">
				<input type="button" value="go!">
				</a>
				
				<!--  go url로 보내기 -->
			</div>
			<br><br>
			<div>
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
		</div>
	</div>
	
	<script type="text/javascript">			
		$(document).ready(function() {
			$('#orderChoice').click(function() {
				var orderChoice = $('#orderChoice').val();
				var orderUrl = 'main?orderChoice=' + orderChoice;
				console.log(orderUrl);
				$('#orderUrl').prop("href", orderUrl);
			});//end orderChoice_click();
		
			$('#btn_login').click(function(){
				var target = encodeURI('/project/member/login');
				location = target;	
			});//end btn_login_click();
			
			$('#btn_logout').click(function() {
				var target = encodeURI('/project/member/logout');
				location = target;	
			});//end btn_logout_click();	
			
			$('#btn_mypage').click(function() {
				var target = encodeURI('/project/member/mypage/home');
				location = target;							
			});//end btn_mypage_click();
			
		});	
	</script>
	
</body>
</html>